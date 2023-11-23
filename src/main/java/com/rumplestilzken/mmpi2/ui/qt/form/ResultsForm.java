package com.rumplestilzken.mmpi2.ui.qt.form;

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
        QLabel label = new QLabel("label");
        layout.addWidget(label);
    }
}
