package com.company.sclab.view.equipamento;

import com.company.sclab.entity.Equipamento;
import com.company.sclab.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "equipamentoes", layout = MainView.class)
@ViewController(id = "Equipamento.list")
@ViewDescriptor(path = "equipamento-list-view.xml")
@LookupComponent("equipamentoesDataGrid")
@DialogMode(width = "64em")
public class EquipamentoListView extends StandardListView<Equipamento> {
}