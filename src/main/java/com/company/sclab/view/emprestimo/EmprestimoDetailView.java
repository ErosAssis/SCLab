package com.company.sclab.view.emprestimo;

import com.company.sclab.entity.Emprestimo;
import com.company.sclab.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "emprestimoes/:id", layout = MainView.class)
@ViewController(id = "Emprestimo.detail")
@ViewDescriptor(path = "emprestimo-detail-view.xml")
@EditedEntityContainer("emprestimoDc")
public class EmprestimoDetailView extends StandardDetailView<Emprestimo> {
}