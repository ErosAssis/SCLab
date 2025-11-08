package com.company.sclab.view.emprestimo;

import com.company.sclab.app.RfidService;
import com.company.sclab.entity.Emprestimo;
import com.company.sclab.view.main.MainView;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "emprestimoes/:id", layout = MainView.class)
@ViewController(id = "Emprestimo.detail")
@ViewDescriptor(path = "emprestimo-detail-view.xml")
@EditedEntityContainer("emprestimoDc")
public class EmprestimoDetailView extends StandardDetailView<Emprestimo> {

    @Autowired
    private RfidService rfidService;

    @ViewComponent
    private TextField idRFIDField;

    @Subscribe
    public void onInit(InitEvent event) {
        // Quando o campo ganhar foco, tenta ler o RFID
        idRFIDField.addFocusListener(e -> {
            new Thread(() -> {
                try {
                    // Tenta detectar a porta automaticamente
                    String porta = rfidService.detectarPortaLeitor();
                    if (porta != null) {
                        rfidService.conectar(porta);
                        String codigo = rfidService.lerTag();
                        rfidService.desconectar();

                        // Atualiza o campo na UI (deve ser feito na thread principal do Vaadin)
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
}