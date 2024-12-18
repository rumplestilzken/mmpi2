package com.rumplestilzken.mmpi.ui.qt.form;

import io.qt.core.QMetaObject;
import io.qt.core.QObject;
import io.qt.core.Qt;
import io.qt.widgets.*;

import java.util.function.Consumer;

public class IntroForm extends Form {


    public IntroForm(QWidget parent) {
        super(parent);
        setObjectName("IntroForm");
    }

    @Override
    public void buildUI() {
        super.buildUI();
        QGridLayout layout = (QGridLayout)layout();

        QLabel headerLabel = new QLabel("<b>MMPI-2</b>");
        headerLabel.setAccessibleName("MMPI");
        headerLabel.setTextFormat(Qt.TextFormat.RichText);
        layout.addWidget(headerLabel, 0, 0, Qt.AlignmentFlag.AlignHCenter);

        String introText = "Welcome, this is a small implementation of the MMPI-2. \nYou can save your question answers and your results using File->*";
        QLabel introLabel = new QLabel(introText);
        layout.addWidget(introLabel, 1, 0, Qt.AlignmentFlag.AlignHCenter);
    }



}
