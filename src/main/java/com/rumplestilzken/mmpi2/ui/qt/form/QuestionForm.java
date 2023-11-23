package com.rumplestilzken.mmpi2.ui.qt.form;

import com.rumplestilzken.mmpi2.data.QuestionData;
import com.rumplestilzken.mmpi2.data.ResultProcessor;
import io.qt.core.QObject;
import io.qt.core.Qt;
import io.qt.widgets.*;

import java.util.ArrayList;
import java.util.List;

public class QuestionForm extends Form{
    public QuestionForm(QWidget parent) {
        super(parent);
    }

    @Override
    public void buildUI() {
        super.buildUI();

        QMainWindow mainWindow = (QMainWindow) parent;

        ((QMenu)mainWindow.menuBar().findChild("File")).clear();

        QAction loadQuestionsAction = new QAction("Load Answers From JSON");
        loadQuestionsAction.setObjectName("LoadAnswers");
        loadQuestionsAction.triggered.connect(this, "loadAnswers()");
        ((QMenu)mainWindow.menuBar().findChild("File")).addAction(loadQuestionsAction);

        QAction saveJSONAction = new QAction("Save JSON");
        saveJSONAction.setObjectName("SaveJSON");
        saveJSONAction.triggered.connect(this, "getResults()");
        saveJSONAction.setEnabled(false);
        ((QMenu)mainWindow.menuBar().findChild("File")).addAction(saveJSONAction);

        QAction savePDFAction = new QAction("Save PDF");
        savePDFAction.setObjectName("SavePDF");
        savePDFAction.triggered.connect(this, "savePDFResults()");
        savePDFAction.setEnabled(false);
        ((QMenu)mainWindow.menuBar().findChild("File")).addAction(savePDFAction);

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
        male.clicked.connect(this, "genderButtonClicked()");
        mfgb.layout().addWidget(male);
        QRadioButton female = new QRadioButton("Female");
        female.setObjectName("FemaleRadioButton");
        female.clicked.connect(this, "genderButtonClicked()");
        mfgb.layout().addWidget(female);

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
    }

    void genderButtonClicked() {
        QMainWindow mainWindow = (QMainWindow) parent;
        ((QMenu)mainWindow.menuBar().findChild("File")).actions().stream().filter(i -> i.getObjectName().equals("SaveJSON")).findFirst().get().setEnabled(true);
        ((QMenu)mainWindow.menuBar().findChild("File")).actions().stream().filter(i -> i.getObjectName().equals("SavePDF")).findFirst().get().setEnabled(true);
    }

    void loadAnswers() {
        String path = "";
        loadAnswersFromText(path);
        loadAnswersFromJSON(path);
    }

    private void loadAnswersFromJSON(String path) {
        String answers = ""; //TODO:
        if(!answers.startsWith("{"))
        {
            return;
        }
    }

    private void loadAnswersFromText(String path) {

        String answers = "TFFTFTTTTFTFTTTTFFTTTTTFFTFFTFTTTFTFTTTFTFFFTFTTTTTTTFTFFTFFTFTFFFTTFFFTFFT" +
                "TTTTFTTFTTFTTTTFFTTTFTTFFFTFFTFTTFTFFTFTTTTFTFTTTFTFTTTFTFTFTFFTFTTTFFFTFFF" +
                "FTTTTFTFTFTFFTFTFTTFFTFTFTFTFTTTTFFTTTTFFTFFTTFFTTFFTFFTFTTTTTFTTFFTTFTTFTT" +
                "TTFTTTTTFFFTFFFFTFTFTFFTTTFFTTTFTFTTTFTFFTFFFTTFFTTTTTTFFFTTTFFFFFFFFTFTFTF" +
                "FTFTTFTTTFFFFTTTTTFTTFFTTFTTFTTFFFTFTTTFTFFFTTFFFTFTFTFTFFFTFTFFTTTFFFFFTTF" +
                "FTFTTFFTFTTFFTTTFFFFFFTFFFFTTTFFTTTFFTTTTFTTTFFTTFFTFFTTTTTFTTTFTFTFFFFFFTF" +
                "FTFFFFFTTTTTFTTTTFTTFTTTTTFFTTTFFFTTTFFTFTTTFFFFTFTTFTFFTTFTTTTTFFFTFFTFTFT" +
                "TTFTFFTTTTFFTFFFTTFTTTTTTTTTFFFTFFFFTTFTTF";

        if(!(answers.startsWith("T") || answers.startsWith("F") | answers.startsWith("?"))){
            return;
        }

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
                else if (currentChar == 'F') {
                    qfd.getFalseRadio().setChecked(true);
                }
                else {
                    qfd.getTrueRadio().setChecked(false);
                    qfd.getFalseRadio().setChecked(false);
                }
            }
            catch (Exception e) {
                break;
            }
        }
    }

    void savePDFResults() {
        QGroupBox gb = ((QGroupBox)children().stream().filter(i -> i.getObjectName().equals("TopGroupBox")).findFirst().get());
        QGroupBox mfgb = (QGroupBox) gb.children().stream().filter(i -> i.getObjectName().equals("MFGB")).findFirst().get();
        QRadioButton mRadio = (QRadioButton)mfgb.children().stream().filter(i -> i.getObjectName().equals("MaleRadioButton")).findFirst().get();
        boolean male = mRadio.isChecked();

        List<QuestionData.QuestionAnswerData> answers = getAnswers();

        ResultProcessor rp = new ResultProcessor(male);
        boolean success = rp.writePDFDocument(answers, "/home/nick/dev/tmp/MMPI2.pdf");
    }

    List<QuestionData.QuestionAnswerData> getAnswers(){
        List<QObject> objects = new ArrayList<QObject>();
        List<QuestionData.QuestionAnswerData> answers = new ArrayList<QuestionData.QuestionAnswerData>();

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
        return answers;
    }

    void getResults() {
        List<QuestionData.QuestionAnswerData> answers = getAnswers();

        QGroupBox gb = ((QGroupBox)children().stream().filter(i -> i.getObjectName().equals("TopGroupBox")).findFirst().get());
        QGroupBox mfgb = (QGroupBox) gb.children().stream().filter(i -> i.getObjectName().equals("MFGB")).findFirst().get();
        QRadioButton mRadio = (QRadioButton)mfgb.children().stream().filter(i -> i.getObjectName().equals("MaleRadioButton")).findFirst().get();
        boolean male = mRadio.isChecked();

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
