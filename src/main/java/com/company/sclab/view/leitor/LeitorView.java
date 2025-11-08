package com.company.sclab.view.leitor;

import com.company.sclab.app.RfidService;
import com.company.sclab.view.main.MainView;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewComponent;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

@Route(value = "leitor-view", layout = MainView.class)
@ViewController("LeitorView")
@ViewDescriptor("leitor-view.xml")
public class LeitorView extends StandardView {

    @Autowired
    private RfidService rfidService;

    @ViewComponent
    private Span statusLabel;

    @ViewComponent
    private TextField tagField;

    @ViewComponent
    private VerticalLayout historicoLayout;

    private String portaDetectada;
    private volatile boolean executando = true;
    private Thread leituraThread;

    private final List<String> historicoTags = new LinkedList<>();

    @Subscribe
    public void onInit(InitEvent event) {
        leituraThread = new Thread(() -> {
            try {
                status("Detectando leitor...");
                portaDetectada = rfidService.detectarPortaLeitor();

                if (portaDetectada == null) {
                    status("Nenhum leitor encontrado.");
                    return;
                }

                status("Leitor conectado na porta: " + portaDetectada);

                if (!rfidService.conectar(portaDetectada)) {
                    status("Falha ao conectar na porta: " + portaDetectada);
                    return;
                }

                status("Lendo continuamente a cada 3 segundos...");

                while (executando) {
                    try {
                        String tag = rfidService.lerTag();
                        if (tag != null && !tag.isEmpty()) {
                            atualizarTag(tag);
                        } else {
                            status("Nenhuma tag detectada. Tentando novamente...");
                        }

                        Thread.sleep(3000);

                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    } catch (Exception e) {
                        status("Erro na leitura: " + e.getMessage());
                        Thread.sleep(3000);
                    }
                }

            } catch (Exception e) {
                status("Erro: " + e.getMessage());
            } finally {
                rfidService.desconectar();
                status("Leitor desconectado.");
            }
        });

        leituraThread.start();
    }

    @Subscribe
    public void onBeforeClose(BeforeCloseEvent event) {
        executando = false;
        if (leituraThread != null && leituraThread.isAlive()) {
            leituraThread.interrupt();
        }
        rfidService.desconectar();
    }

    private void atualizarTag(String tag) {
        getUI().ifPresent(ui -> ui.access(() -> {
            tagField.setValue(tag);
            statusLabel.setText("Tag detectada: " + tag);
            atualizarHistorico(tag);
        }));
    }

    private void atualizarHistorico(String novaTag) {
        // Adiciona nova tag no início da lista
        historicoTags.add(0, novaTag);

        // Mantém no máximo 10 itens
        if (historicoTags.size() > 10) {
            historicoTags.remove(historicoTags.size() - 1);
        }

        // Atualiza visualmente a lista
        historicoLayout.removeAll();
        for (String tag : historicoTags) {
            historicoLayout.add(new Span(tag));
        }
    }

    private void status(String msg) {
        getUI().ifPresent(ui -> ui.access(() -> statusLabel.setText(msg)));
    }
}
