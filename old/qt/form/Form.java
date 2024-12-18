package com.rumplestilzken.mmpi.ui.qt.form;

import io.qt.QtObject;
import io.qt.widgets.*;

public class Form extends QWidget {

    QWidget parent = null;
    Form currentForm = null;

    public Form getCurrentForm() {
        return currentForm;
    }

    public void setCurrentForm(Form currentForm) {
        QMainWindow mainWindow = (QMainWindow) parent;
//        mainWindow.children().stream().filter(i -> i.getObjectName().equals("MainForm")).findFirst().get().children().stream().filter(i -> i instanceof Form).forEach(i -> System.out.println(i.getObjectName()));
        mainWindow.children().stream().filter(i -> i.getObjectName().equals("MainForm")).findFirst().get().children().stream().filter(i -> i instanceof Form).forEach(QtObject::dispose);

        this.currentForm = currentForm;

        ((Form)mainWindow.children().stream().filter(i -> i.getObjectName().equals("MainForm")).findFirst().get()).layout().addWidget(currentForm);
    }

    public Form(QWidget parent) {
        this.parent = parent;
        buildUI();
//        parent.children().add(this);
    }

    public void buildUI() {
        if(layout() == null) {
            QLayout layout = new QGridLayout();
            this.setLayout(layout);
        }
    }

    public void nextForm(){

    }
}
