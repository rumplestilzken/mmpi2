package com.rumplestilzken.mmpi2.ui.qt.form;

import io.qt.core.Qt;
import io.qt.widgets.*;

public class MainForm extends Form {
    Form currentForm = null;

    public Form getCurrentForm() {
        return currentForm;
    }

    public void setCurrentForm(Form currentForm) {
        this.currentForm.dispose();
//        previousForm = this.currentForm;
        this.currentForm = currentForm;
        layout().addWidget(currentForm);
    }

    public MainForm(QWidget parent) {
        super(parent);
        currentForm = new IntroForm(this);
        layout().addWidget(currentForm);
    }

    @Override
    public void buildUI() {
        super.buildUI();

        QGridLayout layout = (QGridLayout)layout();

        QPushButton nextButton = new QPushButton("Next");
        nextButton.setObjectName("Next");

        nextButton.released.connect(this, "next()");
        layout.addWidget(nextButton, 2, 0, Qt.AlignmentFlag.AlignRight);
    }

    public void next() {
        System.out.println(children().stream().count());
//        children().stream().forEach(i -> System.out.println(i.getObjectName()));
//        children().stream().forEach(i -> i.dispose());

        setCurrentForm(new QuestionForm(parent));
        (((QPushButton)children().
                stream()
                .filter(i -> i.objectName().equals("Next")).findFirst().get()
        )).hide();
//        ((MainForm)parent).setCurrentForm(new QuestionForm(null));
        System.out.println(children().stream().count());


//        System.out.println("Next");

//        super.next(new MainForm(parent));
//        for(QObject o : layout().children()) {
//            parent.children().dispose();
//            parent.update();
//        System.out.println(parent.children().stream().count());

//        System.out.println(parent.children().stream().count());

//        }
//        layout().update();

    }
}
