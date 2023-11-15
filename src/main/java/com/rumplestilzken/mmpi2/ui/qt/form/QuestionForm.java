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
        System.out.println("Get Results");
        QGridLayout layout = (QGridLayout)layout();

        List<QObject> objects = new ArrayList<QObject>();
        List<QuestionData.QuestionAnswerData> answers = new ArrayList<QuestionData.QuestionAnswerData>();

//        ((QScrollArea)children().stream().filter(i -> i.getObjectName().equals("QuestionScroll")).findFirst().get())
//                .children().stream().findFirst().get()
//                .children().stream().findFirst().get()
//                .children().forEach(i -> System.out.println(i.getObjectName()));

        ((QScrollArea)children().stream().filter(i -> i.getObjectName().equals("QuestionScroll")).findFirst().get())
                .children().stream().findFirst().get()
                .children().stream().findFirst().get()
                .children().forEach(i -> objects.add(i));

//          objects.stream().forEach(i -> i.dumpObjectTree());

//        System.out.println(objects.stream().findFirst().get().inherits("io.qt.widgets.QVBoxLayout"));
//        System.out.println(objects.stream().findFirst().get().inherits("com.rumplestilzken.mmpi2.ui.qt.form.QuestionFormData"));
//        System.out.println(((QVBoxLayout)objects.stream().findFirst().get()).getObjectName());
//        System.out.println(((QVBoxLayout)objects.stream().findFirst().get()).count());
//        System.out.println(((QVBoxLayout)objects.stream().findFirst().get()).children().stream().count());
//        System.out.println(((QVBoxLayout)objects.stream().findFirst().get()).children().count());
//        System.out.println(((QVBoxLayout)objects.stream().findFirst().get()).itemAt(0).getClass().toString());
//        System.out.println(((QVBoxLayout)objects.stream().findFirst().get()).itemAt(0).widget().getObjectName());
//        ((QVBoxLayout)objects.stream().findFirst().get()).itemAt(0).widget().children().forEach(i -> System.out.println(i.getObjectName()));
//        System.out.println(((QVBoxLayout)objects.stream().findFirst().get()).itemAt(0).widget().findChild("True").getObjectName());

        for(int i = 0; i < objects.stream().count()-1; i++){
            System.out.println(i);
            QWidget widget = ((QVBoxLayout)objects.stream().findFirst().get()).itemAt(i).widget();
            Boolean answer = null;
            if (((QuestionFormData)widget).getTrueRadio().isChecked()) answer = Boolean.TRUE;
            if (((QuestionFormData)widget).getFalseRadio().isChecked()) answer = Boolean.FALSE;
            answers.add(new QuestionData.QuestionAnswerData(i, answer));
        }



        System.out.println(((QVBoxLayout)objects.stream().findFirst().get()).itemAt(0).widget().layout().children().stream().count());



//        System.out.println(((QVBoxLayout)objects.stream().findFirst().get()).layout().getObjectName());
//        System.out.println(((QVBoxLayout)objects.stream().findFirst().get()).layout().children().first().getObjectName());
//        System.out.println(((QVBoxLayout)objects.stream().findFirst().get()).children().first().getObjectName());
//
//        objects.stream().forEach(i -> System.out.println(i.getObjectName()));

//        objects.stream().forEach(i -> System.out.println(((QVBoxLayout)i).parent().children().stream().count()));

//        objects.stream().forEach(i -> ((QVBoxLayout)objects.stream().findFirst().get()).children().stream().count());

        List<QLayoutItem> qobjects = new ArrayList<QLayoutItem>();
//        objects.stream().forEach(i -> qobjects.add(((QVBoxLayout)objects.stream().findFirst().get()).widget().getObjectName()));

        qobjects.forEach(i -> i.layout().getObjectName());


//        System.out.println(((QVBoxLayout)objects.stream().findFirst().get()).children().first().inherits("com.rumplestilzken.mmpi2.ui.qt.form.QuestionFormData"));

//        System.out.println(objects.stream().findFirst().get().getObjectName());

//        objects.stream().forEach(i -> System.out.println(i.children().first().getObjectName()));
//        objects.stream().forEach(i -> System.out.println(((QVBoxLayout)i).layout().children().first().getObjectName()));



//        ((QScrollArea)children().stream().filter(i -> i.getObjectName().equals("QuestionScroll")).findFirst().get())
//                .children().stream().findFirst().get()
//                .children().stream().findFirst().get()
//                .children().forEach(i -> System.out.println(((QVBoxLayout)i).children().stream().findFirst().get().getObjectName()));

//        System.out.println(((QGroupBox)((QScrollArea)children().stream().filter(i -> i.getObjectName().equals("QuestionScroll")).findFirst().get())
//                .children().stream().findFirst().get()
//                .children().stream().findFirst().get()) //GroupBox
//                .children().stream().findFirst().get().getObjectName());
//                .children().stream().forEach(i -> System.out.println(i.getObjectName()));
//                children().stream().findFirst().get()) //QVBoxLayout
//                .children().stream().count()

//        ((QScrollArea)children().stream().filter(i -> i.getObjectName().equals("QuestionScroll")).findFirst().get())
//                .children().stream().findFirst().get()
//                .children().stream().findFirst().get().children().forEach(i -> qfd.add((QuestionFormData) i));

//        System.out.println(qfd.stream().count());

//        qfd.stream().forEach(i -> answers.add(new QuestionData.QuestionAnswerData(i.getIndex(), getBoolean(Boolean.valueOf(i.getTrueRadio().isEnabled()), Boolean.valueOf(i.getFalseRadio().isEnabled())))));

        ResultProcessor rp = new ResultProcessor();
        JSONObject jo = rp.getJSONFromAnswers(answers);
        System.out.println(jo.toString());

//        ((QScrollArea)children().stream().filter(i -> i.getObjectName().equals("QuestionScroll")).findFirst().get()).layout().children().stream().findFirst().get().children().forEach(i -> System.out.println(i.getObjectName()));
//        System.out.println(parent.children().stream().filter(i -> i.getObjectName().equals("_layout")).count());
//        System.out.println(parent.children().stream().filter(i -> i.getObjectName().equals("_layout")).findFirst().get().children().stream().count());



    }

    Boolean getBoolean(Boolean t, Boolean f ){
        if (t) return Boolean.TRUE;
        if (f) return Boolean.FALSE;

        return null;
    }
}
