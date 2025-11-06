package com.company.sclab.view.equipamento;

import com.company.sclab.entity.Equipamento;
import com.company.sclab.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "equipamentoes/:id", layout = MainView.class)
@ViewController(id = "Equipamento.detail")
@ViewDescriptor(path = "equipamento-detail-view.xml")
@EditedEntityContainer("equipamentoDc")
public class EquipamentoDetailView extends StandardDetailView<Equipamento> {
}