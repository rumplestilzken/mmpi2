package com.rumplestilzken.mmpi.ui.qt.form;

import io.qt.widgets.*;

public class ResultsForm extends Form {
    public ResultsForm(QWidget parent) {
        super(parent);
        setObjectName("ResultForm");
    }

    @Override
    public void buildUI() {
        super.buildUI();

        QMainWindow mainWindow = (QMainWindow) parent;

        QPushButton next = ((QPushButton)mainWindow.children().stream().filter(i -> i.getObjectName().equals("MainForm")).findFirst().get()
                .children().stream().filter(i -> i.getObjectName().equals("Next")).findFirst().get());
        next.hide();

        QGridLayout layout = (QGridLayout)layout();

        QTableWidget table = new QTableWidget();
        table.setColumnCount(5);
        table.setItem(0, 0, new QTableWidgetItem("Scale"));
        table.setItem(0, 1, new QTableWidgetItem("Scale Description"));
        table.setItem(0, 2, new QTableWidgetItem("Raw Score"));
        table.setItem(0, 3, new QTableWidgetItem("K Score"));
        table.setItem(0, 4, new QTableWidgetItem("T Score"));


        layout.addWidget(table);
    }
}
