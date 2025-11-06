package com.company.sclab.view.tipoequipamento;

import com.company.sclab.entity.TipoEquipamento;
import com.company.sclab.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "tipo-equipamentoes", layout = MainView.class)
@ViewController(id = "TipoEquipamento.list")
@ViewDescriptor(path = "tipo-equipamento-list-view.xml")
@LookupComponent("tipoEquipamentoesDataGrid")
@DialogMode(width = "64em")
public class TipoEquipamentoListView extends StandardListView<TipoEquipamento> {
}