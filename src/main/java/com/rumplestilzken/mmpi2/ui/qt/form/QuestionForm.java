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

        QMainWindow mainWindow = (QMainWindow) parent;
        ((QMenu)mainWindow.menuBar().findChild("File")).actions().stream().filter(i -> i.getObjectName().equals("LoadAnswers")).findFirst().get().setEnabled(true);
        ((QMenu)mainWindow.menuBar().findChild("File")).actions().stream().filter(i -> i.getObjectName().equals("SaveResults")).findFirst().get().setEnabled(true);
        ((QMenu)mainWindow.menuBar().findChild("File")).actions().stream().filter(i -> i.getObjectName().equals("SaveQuestions")).findFirst().get().setEnabled(true);

        ((QMenu)mainWindow.menuBar().findChild("File")).actions().stream().filter(i -> i.getObjectName().equals("LoadAnswers")).findFirst().get()
                .triggered.connect(this, "loadAnswers()");

        QGridLayout layout = (QGridLayout)layout();
        layout.setObjectName("Layout");

        QGroupBox topGroupBox = new QGroupBox();
        topGroupBox.setObjectName("TopGroupBox");
        topGroupBox.setLayout(new QGridLayout());

        QGroupBox tf = new QGroupBox();
        tf.setObjectName("TF");

        tf.setLayout(new QHBoxLayout());
        tf.setFixedHeight(40);
        tf.setFixedWidth(90);

//        QSpacerItem spacer = new QSpacerItem(8, 0);
//        ((QHBoxLayout)tf.layout()).addSpacerItem(spacer);

        QLabel t = new QLabel("T");
        t.setObjectName("T");
        tf.layout().addWidget(t);

        QLabel f = new QLabel("F");
        f.setObjectName("F");
        tf.layout().addWidget(f);


        QGroupBox mfgb = new QGroupBox();
        mfgb.setObjectName("MFGB");
        mfgb.setLayout(new QVBoxLayout());
        mfgb.setFixedWidth(100);

        QRadioButton male = new QRadioButton("Male");
        male.setObjectName("MaleRadioButton");
        mfgb.layout().addWidget(male);
        QRadioButton female = new QRadioButton("Female");
        female.setObjectName("FemaleRadioButton");
        mfgb.layout().addWidget(female);
//        layout.addWidget(mfgb);

        QGroupBox form = new QGroupBox();
        form.setObjectName("Form");
        form.setLayout(new QVBoxLayout());
        form.setFixedWidth(110);

        QRadioButton shortForm = new QRadioButton("Short Form");
        shortForm.setObjectName("ShortRadioButton");
        shortForm.clicked.connect(this, "shortForm()");
        form.layout().addWidget(shortForm);
        QRadioButton longForm = new QRadioButton("Long Form");
        longForm.clicked.connect(this, "longForm()");
        longForm.setChecked(true);
        longForm.setObjectName("LongRadioButton");
        form.layout().addWidget(longForm);

        ((QGridLayout)topGroupBox.layout()).addWidget(tf, 0, 0, Qt.AlignmentFlag.AlignLeft);
        ((QGridLayout)topGroupBox.layout()).addWidget(mfgb, 0, 1, Qt.AlignmentFlag.AlignTop);
        ((QGridLayout)topGroupBox.layout()).addWidget(form, 0, 2, Qt.AlignmentFlag.AlignTop);

//        layout.addWidget(tf);

        layout.addWidget(topGroupBox);

        QScrollArea questionsScroll = new QScrollArea();
        questionsScroll.setObjectName("QuestionScroll");
        questionsScroll.setVerticalScrollBarPolicy(Qt.ScrollBarPolicy.ScrollBarAlwaysOn);
        questionsScroll.setLayout(new QHBoxLayout());
        questionsScroll.setFixedWidth(980);
        questionsScroll.setFixedHeight(600);
        questionsScroll.setWidgetResizable(true);

        QGroupBox questionBox = new QGroupBox();
        questionBox.setObjectName("QuestionBox");
        QVBoxLayout vboxLayout = new QVBoxLayout();
        vboxLayout.setObjectName("VBoxLayout");
        questionBox.setLayout(vboxLayout);

        for(QuestionData.Question q : new QuestionData().getQuestions())
        {
            QuestionFormData qfd = new QuestionFormData(this);
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

    void loadAnswers() {
        String answers = "TFFTFTTTTFTFTTTTFFTTTTTFFTFFTFTTTFTFTTTFTFFFTFTTTTTTTFTFFTFFTFTFFFTTFFFTFFT" +
                "TTTTFTTFTTFTTTTFFTTTFTTFFFTFFTFTTFTFFTFTTTTFTFTTTFTFTTTFTFTFTFFTFTTTFFFTFFF" +
                "FTTTTFTFTFTFFTFTFTTFFTFTFTFTFTTTTFFTTTTFFTFFTTFFTTFFTFFTFTTTTTFTTFFTTFTTFTT" +
                "TTFTTTTTFFFTFFFFTFTFTFFTTTFFTTTFTFTTTFTFFTFFFTTFFTTTTTTFFFTTTFFFFFFFFTFTFTF" +
                "FTFTTFTTTFFFFTTTTTFTTFFTTFTTFTTFFFTFTTTFTFFFTTFFFTFTFTFTFFFTFTFFTTTFFFFFTTF" +
                "FTFTTFFTFTTFFTTTFFFFFFTFFFFTTTFFTTTFFTTTTFTTTFFTTFFTFFTTTTTFTTTFTFTFFFFFFTF" +
                "FTFFFFFTTTTTFTTTTFTTFTTTTTFFTTTFFFTTTFFTFTTTFFFFTFTTFTFFTTFTTTTTFFFTFFTFTFT" +
                "TTFTFFTTTTFFTFFFTTFTTTTTTTTTFFFTFFFFTTFTTF";

        QGroupBox gb = (QGroupBox) children().stream().filter(i -> i.getObjectName().equals("QuestionScroll")).findFirst().get()
                .children().stream().findFirst().get()
                .children().stream().findFirst().get();

        for(int index = 0; index < answers.length(); index++) {
            final int finalIndex = index+1;
            char currentChar = answers.toCharArray()[index];
            try {
                QuestionFormData qfd = (QuestionFormData) gb.children().stream().filter(i -> i.getObjectName().equals("QFD_" + Integer.toString(finalIndex))).findFirst().get();
                if(currentChar == 'T')
                {
                    qfd.getTrueRadio().setChecked(true);
                }
                else {
                    qfd.getFalseRadio().setChecked(true);
                }
            }
            catch (Exception e) {
                break;
            }
        }
    }

    void getResults() {
        QGridLayout layout = (QGridLayout)layout();

        List<QObject> objects = new ArrayList<QObject>();
        List<QuestionData.QuestionAnswerData> answers = new ArrayList<QuestionData.QuestionAnswerData>();

        QGroupBox gb = ((QGroupBox)children().stream().filter(i -> i.getObjectName().equals("TopGroupBox")).findFirst().get());
        QGroupBox mfgb = (QGroupBox) gb.children().stream().filter(i -> i.getObjectName().equals("MFGB")).findFirst().get();
        QRadioButton mRadio = (QRadioButton)mfgb.children().stream().filter(i -> i.getObjectName().equals("MaleRadioButton")).findFirst().get();
        QRadioButton fRadio = (QRadioButton)mfgb.children().stream().filter(i -> i.getObjectName().equals("FemaleRadioButton")).findFirst().get();
        boolean male = mRadio.isChecked();
        boolean female = fRadio.isChecked();

        objects.addAll(((QScrollArea) children().stream().filter(i -> i.getObjectName().equals("QuestionScroll")).findFirst().get())
                .children().stream().findFirst().get()
                .children().stream().findFirst().get()
                .children());

        QVBoxLayout vboxLayout = ((QVBoxLayout)objects.stream().findFirst().get());
        for(int i = 0; i < objects.stream().count()-1; i++){
            QWidget widget = vboxLayout.itemAt(i).widget();
            Boolean answer = null;
            if (((QuestionFormData)widget).getTrueRadio().isChecked()) answer = Boolean.TRUE;
            if (((QuestionFormData)widget).getFalseRadio().isChecked()) answer = Boolean.FALSE;
            answers.add(new QuestionData.QuestionAnswerData(i+1, answer));
        }

        ResultProcessor rp = new ResultProcessor(male);
        System.out.println(rp.getJSONFromAnswers(answers));
    }

    void shortForm() {
        QGroupBox gb = ((QGroupBox)((QScrollArea) children().stream().filter(i -> i.getObjectName().equals("QuestionScroll")).findFirst().get())
                .children().stream().findFirst().get()
                .children().stream().findFirst().get());
        gb.children().stream().forEach(i -> i.dispose());

        QVBoxLayout vboxLayout = new QVBoxLayout();
        vboxLayout.setObjectName("VBoxLayout");
        gb.setLayout(vboxLayout);

        for(QuestionData.Question q : new QuestionData().getShortFormQuestions())
        {
            QuestionFormData qfd = new QuestionFormData(this);
            qfd.setText(q.getText());
            qfd.setIndex(q.getIndex());
            qfd.buildUI();
            qfd.setObjectName("QFD_" + q.getIndex());
            gb.layout().addWidget(qfd);
        }

    }

    void longForm() {
        QGroupBox gb = ((QGroupBox)((QScrollArea) children().stream().filter(i -> i.getObjectName().equals("QuestionScroll")).findFirst().get())
                .children().stream().findFirst().get()
                .children().stream().findFirst().get());
        gb.children().stream().forEach(i -> i.dispose());

        QVBoxLayout vboxLayout = new QVBoxLayout();
        vboxLayout.setObjectName("VBoxLayout");
        gb.setLayout(vboxLayout);

        for(QuestionData.Question q : new QuestionData().getQuestions())
        {
            QuestionFormData qfd = new QuestionFormData(this);
            qfd.setText(q.getText());
            qfd.setIndex(q.getIndex());
            qfd.buildUI();
            qfd.setObjectName("QFD_" + q.getIndex());
            gb.layout().addWidget(qfd);
        }
    }
}
