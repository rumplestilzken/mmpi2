package com.rumplestilzken.mmpi2.ui.qt.form;

import io.qt.widgets.*;

public class Form extends QWidget {

    QWidget parent = null;

    public Form(QWidget parent) {
        this.parent = parent;
        buildUI();
        parent.children().add(this);
    }

    public void buildUI() {
        QLayout layout = new QGridLayout();
        this.setLayout(layout);
    }

    public void next(Form currentForm) {
        parent.children().clear();
        parent.children().add(currentForm);
        parent.update();
        parent.repaint();
    }
}
