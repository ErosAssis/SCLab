package com.company.sclab.view.tipoequipamento;

import com.company.sclab.entity.TipoEquipamento;
import com.company.sclab.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "tipo-equipamentoes/:id", layout = MainView.class)
@ViewController(id = "TipoEquipamento.detail")
@ViewDescriptor(path = "tipo-equipamento-detail-view.xml")
@EditedEntityContainer("tipoEquipamentoDc")
public class TipoEquipamentoDetailView extends StandardDetailView<TipoEquipamento> {
}