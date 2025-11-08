package com.company.sclab.view.equipamento;

import com.company.sclab.app.RfidService;
import com.company.sclab.entity.Equipamento;
import com.company.sclab.view.main.MainView;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "equipamentoes/:id", layout = MainView.class)
@ViewController("Equipamento.detail")
@ViewDescriptor("equipamento-detail-view.xml")
@EditedEntityContainer("equipamentoDc")
public class EquipamentoDetailView extends StandardDetailView<Equipamento> {

    @Autowired
    private RfidService rfidService;

    @Autowired
    private DataManager dataManager;

    @ViewComponent
    private TextField idRFIDField;

    @Subscribe
    public void onInit(InitEvent event) {
        // Quando o campo ganhar foco, tenta ler o RFID automaticamente
        idRFIDField.addFocusListener(e -> {
            new Thread(() -> {
                try {
                    // Detecta automaticamente a porta do leitor
                    String porta = rfidService.detectarPortaLeitor();
                    if (porta != null) {
                        rfidService.conectar(porta);
                        String codigo = rfidService.lerTag();
                        rfidService.desconectar();

                        // Atualiza o campo na thread principal do Vaadin
                        getUI().ifPresent(ui ->
                                ui.access(() -> idRFIDField.setValue(codigo != null ? codigo : ""))
                        );
                    } else {
                        getUI().ifPresent(ui ->
                                ui.access(() -> idRFIDField.setValue("Leitor não Detectado"))
                        );
                    }
                } catch (Exception ex) {
                    getUI().ifPresent(ui ->
                            ui.access(() -> idRFIDField.setValue("Erro na Leitura"))
                    );
                }
            }).start();
        });
    }

    @Subscribe
    public void onBeforeSave(BeforeSaveEvent event) {
        Equipamento equipamento = getEditedEntity();

        // Verifica se já existe outro equipamento com o mesmo RFID
        boolean jaExiste = dataManager.load(Equipamento.class)
                .query("select e from Equipamento e where e.idRFID = :idRFID and e.id <> :id")
                .parameter("idRFID", equipamento.getIdRFID())
                .parameter("id", equipamento.getId())
                .list()
                .size() > 0;

        if (jaExiste) {
            Notification.show("⚠️ Já existe um equipamento com esse código RFID!", 5000, Notification.Position.MIDDLE);
            event.preventSave(); // Impede o salvamento
        }
    }
}
