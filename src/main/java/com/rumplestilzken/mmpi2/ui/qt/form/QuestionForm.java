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
        questionsScroll.setVerticalScrollBarPolicy(Qt.ScrollBarPolicy.ScrollBarAlwaysOn);

        questionsScroll.setLayout(new QHBoxLayout());

//        questionsScroll.setLayout(new QGridLayout());
//        questionBoxLayout.setMaximumSize(400, 400);
        questionsScroll.setFixedWidth(980);
        questionsScroll.setFixedHeight(650);

//        questionBoxLayout.addWidget(questionsScroll);
        QGroupBox questionBox = new QGroupBox();
        questionBox.setLayout(new QVBoxLayout());

        for(QuestionData.Question q : new QuestionData().getQuestions())
        {
            QuestionFormData qfd = new QuestionFormData(this);
            qfd.setText(q.getText());
            qfd.setIndex(q.getIndex());
            qfd.buildUI();
//            layout.addWidget(qfd);
            questionBox.layout().addWidget(qfd);
        }
        questionsScroll.setWidgetResizable(true);
        questionsScroll.setWidget(questionBox);

        layout.addWidget(questionsScroll);

    }
}
