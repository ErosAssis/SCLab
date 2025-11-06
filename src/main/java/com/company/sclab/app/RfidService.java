package com.company.sclab;

import com.fazecast.jSerialComm.SerialPort;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

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

    // Lê a tag do leitor com espera de até 2 segundos
    public String lerTag() {
        if (serialPort == null || !serialPort.isOpen()) {
            throw new IllegalStateException("Porta serial não conectada.");
        }

        byte[] buffer = new byte[128];
        StringBuilder sb = new StringBuilder();
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < 2000) { // espera até 2 segundos
            while (serialPort.bytesAvailable() > 0) {
                int bytesLidos = serialPort.readBytes(buffer, buffer.length);
                if (bytesLidos > 0) {
                    sb.append(new String(buffer, 0, bytesLidos, StandardCharsets.UTF_8));
                }
            }
            if (sb.length() > 0) break;
            try { Thread.sleep(50); } catch (InterruptedException ignored) {}
        }

        // Limpa caracteres indesejados (STX, ETX, etc.)
        String raw = sb.toString().trim();
        raw = raw.replaceAll("[^a-zA-Z0-9]", "");

        // Se for muito longa, corta para o tamanho típico da tag
        if (raw.length() > 12) {
            raw = raw.substring(0, 12);
        }

        return raw;
    }

    // Detecta automaticamente a porta do leitor (opcional)
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