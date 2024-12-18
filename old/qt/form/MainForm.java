package com.rumplestilzken.mmpi.ui.qt.form;

import io.qt.core.Qt;
import io.qt.widgets.*;

public class MainForm extends Form {

    public MainForm(QWidget parent) {
        super(parent);
        currentForm = new IntroForm(this);
        layout().addWidget(currentForm);
        setObjectName("MainForm");
    }

    @Override
    public void buildUI() {
        super.buildUI();

        QGridLayout layout = (QGridLayout)layout();

        QPushButton nextButton = new QPushButton("Next");
        nextButton.setObjectName("Next");

        nextButton.released.connect(this, "nextForm()");
        layout.addWidget(nextButton, 2, 0, Qt.AlignmentFlag.AlignRight);
    }

    @Override
    public void nextForm() {
        Form nextForm = new QuestionForm(parent);
        setCurrentForm(nextForm);
        QPushButton nextButton = (((QPushButton)children().stream().filter(i -> i.objectName().equals("Next")).findFirst().get()));
        nextButton.released.disconnect();
        nextButton.released.connect(nextForm, "nextForm()");
    }
}
