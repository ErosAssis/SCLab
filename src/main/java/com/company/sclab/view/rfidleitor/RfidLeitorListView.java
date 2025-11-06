package com.company.sclab.view.rfidleitor;

import com.company.sclab.entity.RfidLeitor;
import com.company.sclab.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "rfid-leitors", layout = MainView.class)
@ViewController(id = "RfidLeitor.list")
@ViewDescriptor(path = "rfid-leitor-list-view.xml")
@LookupComponent("rfidLeitorsDataGrid")
@DialogMode(width = "64em")
public class RfidLeitorListView extends StandardListView<RfidLeitor> {
}