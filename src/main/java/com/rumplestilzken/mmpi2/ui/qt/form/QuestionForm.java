package com.rumplestilzken.mmpi2.ui.qt.form;

import io.qt.core.Qt;
import io.qt.widgets.QGridLayout;
import io.qt.widgets.QLabel;
import io.qt.widgets.QWidget;

public class QuestionForm extends Form{
    public QuestionForm(QWidget parent) {
        super(parent);
    }

    @Override
    public void buildUI() {
        super.buildUI();

        QGridLayout layout = (QGridLayout)layout();

        QLabel label = new QLabel("QuestionForm");
        layout.addWidget(label, 0, 0, Qt.AlignmentFlag.AlignLeft);

    }
}
