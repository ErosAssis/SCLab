package com.company.sclab.view.emprestimo;

import com.company.sclab.entity.Emprestimo;
import com.company.sclab.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "emprestimoes", layout = MainView.class)
@ViewController(id = "Emprestimo.list")
@ViewDescriptor(path = "emprestimo-list-view.xml")
@LookupComponent("emprestimoesDataGrid")
@DialogMode(width = "64em")
public class EmprestimoListView extends StandardListView<Emprestimo> {
}