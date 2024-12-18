package com.rumplestilzken.mmpi.ui.qt.form;

import io.qt.core.Qt;
import io.qt.widgets.QGridLayout;
import io.qt.widgets.QLabel;
import io.qt.widgets.QRadioButton;
import io.qt.widgets.QWidget;

public class QuestionFormData extends Form {
    String text = "";
    int index = -1;
    QRadioButton trueRadio = null;
    QRadioButton falseRadio = null;

    public QRadioButton getTrueRadio() {
        return trueRadio;
    }

    public void setTrueRadio(QRadioButton trueRadio) {
        this.trueRadio = trueRadio;
    }

    public QRadioButton getFalseRadio() {
        return falseRadio;
    }

    public void setFalseRadio(QRadioButton falseRadio) {
        this.falseRadio = falseRadio;
    }

    public String getText() {
        return text;
    }

    public int getIndex() {
        return index;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public QuestionFormData(QWidget parent) {
        super(parent);
        setObjectName("QFD");
    }

    @Override
    public void buildUI() {
        super.buildUI();

        QGridLayout layout = (QGridLayout)layout();

        trueRadio = new QRadioButton();
        trueRadio.setObjectName("True");
        trueRadio.setMaximumSize(20, 20);
        layout.addWidget(trueRadio, 0, 0);

        falseRadio = new QRadioButton();
        falseRadio.setObjectName("False");
        falseRadio.setMaximumSize(40, 20);
        layout.addWidget(falseRadio, 0, 1);

//        System.out.println(text);

        QLabel label = new QLabel(text);
        layout.addWidget(label, 0, 2, Qt.AlignmentFlag.AlignLeft);
    }
}
