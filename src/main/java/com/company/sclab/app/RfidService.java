package com.company.sclab.app;

import com.fazecast.jSerialComm.SerialPort;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RfidService {

    private SerialPort serialPort;

    // Lista todas as portas seriais disponíveis
    public String[] listarPortas() {
        return Arrays.stream(SerialPort.getCommPorts())
                .map(SerialPort::getSystemPortName)
                .toArray(String[]::new);
    }

    // Conecta à porta específica
    public boolean conectar(String nomePorta) {
        serialPort = SerialPort.getCommPort(nomePorta);
        serialPort.setComPortParameters(9600, 8, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1000, 1000);
        return serialPort.openPort();
    }

    // Desconecta a porta
    public void desconectar() {
        if (serialPort != null && serialPort.isOpen()) {
            serialPort.closePort();
        }
    }

    // Lê a tag do leitor com suporte a delimitadores <STX>, <ETX>, \r e \n
    public String lerTag() {
        if (serialPort == null || !serialPort.isOpen()) {
            throw new IllegalStateException("Porta serial não conectada.");
        }

        long startTime = System.currentTimeMillis();
        byte[] buffer = new byte[256];
        StringBuilder dados = new StringBuilder();

        while (System.currentTimeMillis() - startTime < 2000) { // até 2 segundos de espera
            while (serialPort.bytesAvailable() > 0) {
                int bytesLidos = serialPort.readBytes(buffer, buffer.length);
                if (bytesLidos > 0) {
                    dados.append(new String(buffer, 0, bytesLidos, StandardCharsets.UTF_8));
                }
            }

            // Tenta extrair uma tag completa usando padrões conhecidos
            String tag = extrairTag(dados.toString());
            if (tag != null && !tag.isEmpty()) {
                return tag;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {}
        }

        return "";
    }

    // Função auxiliar que tenta identificar uma tag completa
    private String extrairTag(String input) {
        // Remove caracteres invisíveis exceto delimitadores
        input = input.replaceAll("[^\\x02\\x03\\r\\nA-Za-z0-9]", "");

        // 1️⃣ Caso padrão: entre <STX> e <ETX>
        Pattern patternSTX = Pattern.compile("\\x02([A-Za-z0-9]+)\\x03");
        Matcher matcherSTX = patternSTX.matcher(input);
        if (matcherSTX.find()) {
            return matcherSTX.group(1);
        }

        // 2️⃣ Caso delimitado por \r ou \n (sem STX/ETX)
        Pattern patternRN = Pattern.compile("[\\r\\n]+([A-Za-z0-9]{4,})[\\r\\n]+");
        Matcher matcherRN = patternRN.matcher(input);
        if (matcherRN.find()) {
            return matcherRN.group(1);
        }

        // 3️⃣ Se não achar delimitadores, mas a string é longa o suficiente
        String raw = input.trim().replaceAll("[^A-Za-z0-9]", "");
        if (raw.length() >= 8 && raw.length() <= 16) {
            return raw;
        }

        return null; // nenhum padrão detectado
    }

    // Detecta automaticamente a porta do leitor
    public String detectarPortaLeitor() {
        for (String porta : listarPortas()) {
            if (conectar(porta)) {
                try {
                    String codigo = lerTag();
                    if (codigo != null && !codigo.isEmpty()) {
                        return porta;
                    }
                } catch (Exception ignored) {
                } finally {
                    desconectar();
                }
            }
        }
        return null; // Nenhuma porta detectada
    }
}
