package com.company.sclab.view.rfidleitor;

import com.company.sclab.RfidService;
import com.company.sclab.entity.RfidLeitor;
import com.company.sclab.view.main.MainView;

import com.fazecast.jSerialComm.SerialPort;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;

import io.jmix.flowui.view.*;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Route(value = "rfid-leitors/:id", layout = MainView.class)
@ViewController("RfidLeitor.detail")
@ViewDescriptor("rfid-leitor-detail-view.xml")
@EditedEntityContainer("rfidLeitorDc")
public class RfidLeitorDetailView extends StandardDetailView<RfidLeitor> {

    @Autowired
    private RfidService rfidService;

    @ViewComponent
    private TextField tagCodigoField;

    @ViewComponent
    private DatePicker lidoEmField;

    @ViewComponent
    private TextField portaUtilizadaField;

    /**
     * Detecta automaticamente a porta onde o leitor RFID está conectado.
     */
    private String detectarPortaRfid() {
        SerialPort[] portas = SerialPort.getCommPorts();
        for (SerialPort porta : portas) {
            try {
                porta.openPort();
                porta.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 500, 500);

                byte[] buffer = new byte[64];
                int bytesLidos = porta.getInputStream().read(buffer);
                porta.closePort();

                if (bytesLidos > 0) {
                    System.out.println("✅ Leitor RFID detectado na porta: " + porta.getSystemPortName());
                    return porta.getSystemPortName();
                }
            } catch (Exception e) {
                if (porta.isOpen()) porta.closePort();
            }
        }
        return null;
    }

    @Subscribe("lerRfidButton")
    public void onLerRfidButtonClick(ClickEvent<Button> event) {
        try {
            // 🔍 Detecta automaticamente a porta
            String porta = detectarPortaRfid();

            if (porta == null) {
                Notification.show("Nenhum leitor RFID detectado!", 3000, Position.MIDDLE);
                return;
            }

            // Conecta à porta detectada
            if (rfidService.conectar(porta)) {
                try {
                    String codigo = rfidService.lerTag();

                    if (codigo != null && !codigo.isEmpty()) {
                        // Atualiza os campos da tela
                        tagCodigoField.setValue(codigo);
                        lidoEmField.setValue(LocalDate.now());
                        portaUtilizadaField.setValue(porta);

                        // Atualiza a entidade editada
                        getEditedEntity().setTagCodigo(codigo);
                        getEditedEntity().setLidoEm(LocalDate.now());
                        getEditedEntity().setPortaUtilizada(porta);

                        Notification.show("Tag lida com sucesso!", 3000, Position.MIDDLE);
                    } else {
                        Notification.show("Nenhum código lido. Aproxime a tag do leitor.", 3000, Position.MIDDLE);
                    }

                } finally {
                    rfidService.desconectar();
                }
            } else {
                Notification.show("Não foi possível abrir a porta " + porta, 3000, Position.MIDDLE);
            }

        } catch (Exception e) {
            Notification.show("Erro ao ler RFID: " + e.getMessage(), 4000, Position.MIDDLE);
            e.printStackTrace();
        }
    }
}