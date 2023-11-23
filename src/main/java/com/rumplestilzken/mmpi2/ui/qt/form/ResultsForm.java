package com.rumplestilzken.mmpi2.ui.qt.form;

import io.qt.widgets.QGridLayout;
import io.qt.widgets.QLabel;
import io.qt.widgets.QWidget;

public class ResultsForm extends Form {
    public ResultsForm(QWidget parent) {
        super(parent);
        setObjectName("ResultForm");
    }

    @Override
    public void buildUI() {
        super.buildUI();

        QGridLayout layout = (QGridLayout)layout();
        QLabel label = new QLabel("label");
        layout.addWidget(label);
    }
}
