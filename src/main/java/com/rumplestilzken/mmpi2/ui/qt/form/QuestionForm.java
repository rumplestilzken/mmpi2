package com.rumplestilzken.mmpi2.ui.qt.form;

import com.rumplestilzken.mmpi2.data.QuestionData;
import io.qt.core.Qt;
import io.qt.widgets.*;

public class QuestionForm extends Form{
    public QuestionForm(QWidget parent) {
        super(parent);
    }

    @Override
    public void buildUI() {
        super.buildUI();

        QGridLayout layout = (QGridLayout)layout();
        layout.setObjectName("Layout");

        QGroupBox tf = new QGroupBox();

        tf.setLayout(new QHBoxLayout());
        tf.setFixedHeight(40);
        tf.setFixedWidth(100);

        QSpacerItem spacer = new QSpacerItem(8, 0);
        ((QHBoxLayout)tf.layout()).addSpacerItem(spacer);

        QLabel t = new QLabel("T");
//        t.setFixedWidth(200);
//        ((QGridLayout)tf.layout()).addWidget(t, 0, 0, Qt.AlignmentFlag.AlignLeft);
        tf.layout().addWidget(t);

//        QSpacerItem spacer2 = new QSpacerItem(5, 0);
//        ((QHBoxLayout)tf.layout()).addSpacerItem(spacer2);

        QLabel f = new QLabel("F");
        tf.layout().addWidget(f);

//        f.setFixedWidth(50);
//        layout.addWidget(f, 0, 0, Qt.AlignmentFlag.AlignTop);
//        ((QGridLayout)tf.layout()).addWidget(f, 0 , 1, Qt.AlignmentFlag.AlignRight);
        layout.addWidget(tf, 0, 0, Qt.AlignmentFlag.AlignTop);

        QScrollArea questionsScroll = new QScrollArea();
        questionsScroll.setObjectName("QuestionScroll");
        questionsScroll.setVerticalScrollBarPolicy(Qt.ScrollBarPolicy.ScrollBarAlwaysOn);
        questionsScroll.setLayout(new QHBoxLayout());
        questionsScroll.setFixedWidth(980);
        questionsScroll.setFixedHeight(650);
        questionsScroll.setWidgetResizable(true);

        QGroupBox questionBox = new QGroupBox();
        questionBox.setObjectName("QuestionBox");
        questionBox.setLayout(new QVBoxLayout());

        for(QuestionData.Question q : new QuestionData().getQuestions())
        {
            QuestionFormData qfd = new QuestionFormData(this);
            qfd.setText(q.getText());
            qfd.setIndex(q.getIndex());
            qfd.buildUI();
            questionBox.layout().addWidget(qfd);
        }

//        questionsScroll.layout().addWidget(questionBox);
        questionsScroll.setWidget(questionBox);

        layout.addWidget(questionsScroll);

        QPushButton getResultsButton = new QPushButton("Get Results");
        getResultsButton.released.connect(this, "getResults()");
        layout.addWidget(getResultsButton, 0, 2, Qt.AlignmentFlag.AlignTop);

    }

    void getResults() {
        System.out.println("Get Results");
        QGridLayout layout = (QGridLayout)layout();
        System.out.println(layout.getObjectName());
        System.out.println("Parent");
        parent.children().stream().forEach(i -> System.out.println(i.getObjectName()));
        System.out.println("layout.children()");
        layout.children().stream().forEach(i -> System.out.println(i.getObjectName()));
        System.out.println("children()");
        children().stream().forEach(i -> System.out.println(i.getObjectName()));
        System.out.println("QuestionScroll--");
        children().stream().filter(i -> i.getObjectName().equals("QuestionScroll")).findFirst().get().children().stream().forEach(i -> System.out.println(i.getObjectName()) );
        System.out.println((children().stream().filter(i -> i.getObjectName().equals("QuestionScroll")).findFirst().get().children().stream().filter(i -> i.objectName().equals("")).findFirst().get().objectName()));
        System.out.println(parent.children().stream().filter(i -> i.getObjectName().equals("_layout")).count());
        System.out.println(parent.children().stream().filter(i -> i.getObjectName().equals("_layout")).findFirst().get().children().stream().count());



    }
}
