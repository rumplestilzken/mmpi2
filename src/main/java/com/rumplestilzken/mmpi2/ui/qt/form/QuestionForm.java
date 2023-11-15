package com.rumplestilzken.mmpi2.ui.qt.form;

import com.rumplestilzken.mmpi2.data.QuestionData;
import com.rumplestilzken.mmpi2.data.ResultProcessor;
import io.qt.core.QObject;
import io.qt.core.Qt;
import io.qt.widgets.*;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

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
        QVBoxLayout vboxLayout = new QVBoxLayout();
        vboxLayout.setObjectName("VBoxLayout");
        questionBox.setLayout(vboxLayout);

        for(QuestionData.Question q : new QuestionData().getQuestions())
        {
            QuestionFormData qfd = new QuestionFormData(this);
            qfd.setObjectName("QFD+" + q.getIndex());
            qfd.setText(q.getText());
            qfd.setIndex(q.getIndex());
            qfd.buildUI();
            qfd.setObjectName("QFD_" + q.getIndex());
            questionBox.layout().addWidget(qfd);
        }

        questionsScroll.layout().addWidget(questionBox);
        questionsScroll.setWidget(questionBox);

        layout.addWidget(questionsScroll);

        QPushButton getResultsButton = new QPushButton("Get Results");
        getResultsButton.released.connect(this, "getResults()");
        layout.addWidget(getResultsButton, 0, 2, Qt.AlignmentFlag.AlignTop);

    }

    void getResults() {
        QGridLayout layout = (QGridLayout)layout();

        List<QObject> objects = new ArrayList<QObject>();
        List<QuestionData.QuestionAnswerData> answers = new ArrayList<QuestionData.QuestionAnswerData>();

        objects.addAll(((QScrollArea) children().stream().filter(i -> i.getObjectName().equals("QuestionScroll")).findFirst().get())
                .children().stream().findFirst().get()
                .children().stream().findFirst().get()
                .children());

        for(int i = 0; i < objects.stream().count()-1; i++){
            System.out.println(i);
            QWidget widget = ((QVBoxLayout)objects.stream().findFirst().get()).itemAt(i).widget();
            Boolean answer = null;
            if (((QuestionFormData)widget).getTrueRadio().isChecked()) answer = Boolean.TRUE;
            if (((QuestionFormData)widget).getFalseRadio().isChecked()) answer = Boolean.FALSE;
            answers.add(new QuestionData.QuestionAnswerData(i, answer));
        }

        ResultProcessor rp = new ResultProcessor();
        JSONObject jo = rp.getJSONFromAnswers(answers);
        System.out.println(jo.toString());
    }

    Boolean getBoolean(Boolean t, Boolean f ){
        if (t) return Boolean.TRUE;
        if (f) return Boolean.FALSE;

        return null;
    }
}
