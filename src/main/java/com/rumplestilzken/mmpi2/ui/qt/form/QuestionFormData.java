package com.rumplestilzken.mmpi2.ui.qt.form;

import io.qt.core.Qt;
import io.qt.widgets.QGridLayout;
import io.qt.widgets.QLabel;
import io.qt.widgets.QRadioButton;
import io.qt.widgets.QWidget;

public class QuestionFormData extends Form {
    String text = "";
    int index = -1;

    public void setText(String text) {
        this.text = text;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public QuestionFormData(QWidget parent) {
        super(parent);
    }

    @Override
    public void buildUI() {
        super.buildUI();

        QGridLayout layout = (QGridLayout)layout();

        QRadioButton trueRadio = new QRadioButton();
        trueRadio.setMaximumSize(20, 20);
        layout.addWidget(trueRadio, 0, 0);

        QRadioButton falseRadio = new QRadioButton();
        falseRadio.setMaximumSize(20, 20);
        layout.addWidget(falseRadio, 0, 1);

//        System.out.println(text);

        QLabel label = new QLabel(text);
        layout.addWidget(label, 0, 2, Qt.AlignmentFlag.AlignLeft);
    }
}
