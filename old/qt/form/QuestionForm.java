package com.rumplestilzken.mmpi.ui.qt.form;

import com.rumplestilzken.mmpi.data.QuestionData;
import com.rumplestilzken.mmpi.data.ResultProcessor;
import com.rumplestilzken.mmpi.data.Storage;
import com.rumplestilzken.mmpi.data.scale.CriticalScale;
import com.rumplestilzken.mmpi.data.scale.Scale;
import com.rumplestilzken.mmpi.data.scale.ScaleProcessor;
import com.rumplestilzken.mmpi.data.scale.Scales;
import io.qt.core.QObject;
import io.qt.core.Qt;
import io.qt.widgets.*;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionForm extends Form{
    public QuestionForm(QWidget parent) {
        super(parent);
        setObjectName("QuestionForm");
    }

    @Override
    public void buildUI() {
        super.buildUI();

        QMainWindow mainWindow = (QMainWindow) parent;

        QPushButton next = ((QPushButton)mainWindow.children().stream().filter(i -> i.getObjectName().equals("MainForm")).findFirst().get()
                .children().stream().filter(i -> i.getObjectName().equals("Next")).findFirst().get());
        next.setText("Get Results");
        next.setEnabled(false);
        next.hide();

        ((QMenu)mainWindow.menuBar().findChild("File")).clear();

        QAction loadQuestionsAction = new QAction("Load Answers From File");
        loadQuestionsAction.setObjectName("LoadAnswers");
        loadQuestionsAction.triggered.connect(this, "loadAnswersFromFile()");
        ((QMenu)mainWindow.menuBar().findChild("File")).addAction(loadQuestionsAction);

        QAction loadQuestionsTextAction = new QAction("Load Answers From Text");
        loadQuestionsTextAction.setObjectName("LoadAnswersText");
        loadQuestionsTextAction.triggered.connect(this, "loadAnswersFromText()");
        ((QMenu)mainWindow.menuBar().findChild("File")).addAction(loadQuestionsTextAction);

        QAction saveJSONAction = new QAction("Save JSON");
        saveJSONAction.setObjectName("SaveJSON");
        saveJSONAction.triggered.connect(this, "saveJSON()");
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

//        QPushButton getResultsButton = new QPushButton("Get Results");
//        getResultsButton.released.connect(this, "getResults()");
//        layout.addWidget(getResultsButton, 0, 2, Qt.AlignmentFlag.AlignTop);
    }

    @Override
    public void nextForm() {
        QGroupBox gb = ((QGroupBox)children().stream().filter(i -> i.getObjectName().equals("TopGroupBox")).findFirst().get());
        QGroupBox lsForm = (QGroupBox) gb.children().stream().filter(i -> i.getObjectName().equals("Form")).findFirst().get();
        QGroupBox mfgb = (QGroupBox) gb.children().stream().filter(i -> i.getObjectName().equals("MFGB")).findFirst().get();
        QRadioButton mRadio = (QRadioButton)mfgb.children().stream().filter(i -> i.getObjectName().equals("MaleRadioButton")).findFirst().get();
        QRadioButton lRadio = (QRadioButton)lsForm.children().stream().filter(i -> i.getObjectName().equals("LongRadioButton")).findFirst().get();
        boolean male = mRadio.isChecked();
        boolean isLong = lRadio.isChecked();

        List<QuestionData.QuestionAnswerData> answers = getAnswers();
        List<Scale> scales = Scales.getScales();
        List<CriticalScale> criticalScales = Scales.getCriticalScales();

        ScaleProcessor sp = new ScaleProcessor();
        double pe = sp.process(answers, scales, criticalScales, male);

        Storage.setIsMale(male);
        Storage.setIsLong(isLong);
        Storage.setAnswers(answers);
        Storage.setScales(scales);
        Storage.setCriticalScales(criticalScales);
        Storage.setProfileEvaluation(pe);

        Form nextForm = new ResultsForm(parent);
        setCurrentForm(nextForm);

        QMainWindow mainWindow = (QMainWindow) parent;

        ((QMenu)mainWindow.menuBar().findChild("File")).actions().stream().filter(i -> i.getObjectName().equals("LoadAnswers")).findFirst().get().dispose();
        ((QMenu)mainWindow.menuBar().findChild("File")).actions().stream().filter(i -> i.getObjectName().equals("LoadAnswersText")).findFirst().get().dispose();
        ((QMenu)mainWindow.menuBar().findChild("File")).actions().stream().filter(i -> i.getObjectName().equals("SaveJSON")).findFirst().get().dispose();
        ((QMenu)mainWindow.menuBar().findChild("File")).actions().stream().filter(i -> i.getObjectName().equals("SavePDF")).findFirst().get().dispose();
    }

    void genderButtonClicked() {
        QMainWindow mainWindow = (QMainWindow) parent;
        ((QMenu)mainWindow.menuBar().findChild("File")).actions().stream().filter(i -> i.getObjectName().equals("SaveJSON")).findFirst().get().setEnabled(true);
        ((QMenu)mainWindow.menuBar().findChild("File")).actions().stream().filter(i -> i.getObjectName().equals("SavePDF")).findFirst().get().setEnabled(true);
        QPushButton next = ((QPushButton)mainWindow.children().stream().filter(i -> i.getObjectName().equals("MainForm")).findFirst().get()
                .children().stream().filter(i -> i.getObjectName().equals("Next")).findFirst().get());
        next.setEnabled(true);
    }

    private void loadAnswersFromFile() {
        QFileDialog.Result<String> dialog = QFileDialog.getOpenFileName();
        String file = dialog.result;
        try {
            InputStream fis = new FileInputStream(file);
            String answers = new String(fis.readAllBytes());
            if(answers.startsWith("{"))
            {
                loadAnswersFromJSON(answers);
            }
            else  {
                loadAnswersFromText(answers);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        genderButtonClicked();
    }

    private void loadAnswersFromText() {
        QFileDialog.Result<String> dialog = QFileDialog.getOpenFileName();
        String file = dialog.result;
        try {
            InputStream fis = new FileInputStream(file);
            String answers = new String(fis.readAllBytes());
            if(answers.startsWith("{"))
            {
                loadAnswersFromJSON(answers);
            }
            else  {
                loadAnswersFromText(answers);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAnswersFromText(String answers) {

        if(!(answers.startsWith("T") || answers.startsWith("F") | answers.startsWith("?"))){
            return;
        }

        QGroupBox gb = (QGroupBox) children().stream().filter(i -> i.getObjectName().equals("QuestionScroll")).findFirst().get()
                .children().stream().findFirst().get()
                .children().stream().findFirst().get();

        char[] array = answers.toCharArray();
        for(int index = 0; index < answers.length(); index++) {
            final int finalIndex = index+1;
            try {
                char currentChar = array[index];
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

    private void loadAnswersFromJSON(String answers) {
        if(!answers.startsWith("{"))
        {
            return;
        }
        JSONObject jsonObject = new JSONObject(answers);
        JSONObject answersObject = jsonObject.getJSONObject("Answers");
        QVBoxLayout layout = ((QVBoxLayout)((QScrollArea) children().stream().filter(i -> i.getObjectName().equals("QuestionScroll")).findFirst().get())
                .children().stream().findFirst().get()
                .children().stream().findFirst().get()
                .children().stream().findFirst().get());
        QGroupBox gb = ((QGroupBox)children().stream().filter(i -> i.getObjectName().equals("TopGroupBox")).findFirst().get());
        QGroupBox lsForm = (QGroupBox) gb.children().stream().filter(i -> i.getObjectName().equals("Form")).findFirst().get();
        QGroupBox mfgb = (QGroupBox) gb.children().stream().filter(i -> i.getObjectName().equals("MFGB")).findFirst().get();
        QRadioButton lRadio = (QRadioButton)lsForm.children().stream().filter(i -> i.getObjectName().equals("LongRadioButton")).findFirst().get();
        QRadioButton sRadio = (QRadioButton)lsForm.children().stream().filter(i -> i.getObjectName().equals("ShortRadioButton")).findFirst().get();
        QRadioButton mRadio = (QRadioButton)mfgb.children().stream().filter(i -> i.getObjectName().equals("MaleRadioButton")).findFirst().get();
        QRadioButton fRadio = (QRadioButton)mfgb.children().stream().filter(i -> i.getObjectName().equals("FemaleRadioButton")).findFirst().get();

        String gender = jsonObject.getString("gender");
        if(gender.equals("Male"))
        {
            mRadio.setChecked(true);
        }
        else {
            fRadio.setChecked(true);
        }

        String form = jsonObject.getString("form");
        if(form.equals("Short"))
        {
            sRadio.setChecked(true);
        }
        else {
            lRadio.setChecked(true);
        }

        for(int i = 1; i <= answersObject.length(); i++) {
            String answerRaw = "";
            Boolean answer = null;
            try {
                answerRaw = answersObject.getString(Integer.toString(i));
            }
            catch (Exception e) {
                answerRaw = "?";
            }
            try {
                answer = answersObject.getBoolean(Integer.toString(i));
            }
            catch (Exception e) {
                answer = null;
            }
            QuestionFormData qfd = (QuestionFormData) layout.itemAt(i-1).widget();
            if(answer == null && answerRaw.equals("?")) {
                qfd.getTrueRadio().setChecked(false);
                qfd.getFalseRadio().setChecked(false);
            }
            else if (answer) {
                qfd.getTrueRadio().setChecked(true);
            }
            else {
                qfd.getFalseRadio().setChecked(true);
            }
        }
    }

    void savePDFResults() {
        QGroupBox gb = ((QGroupBox)children().stream().filter(i -> i.getObjectName().equals("TopGroupBox")).findFirst().get());
        QGroupBox mfgb = (QGroupBox) gb.children().stream().filter(i -> i.getObjectName().equals("MFGB")).findFirst().get();
        QRadioButton mRadio = (QRadioButton)mfgb.children().stream().filter(i -> i.getObjectName().equals("MaleRadioButton")).findFirst().get();
        QGroupBox lsForm = (QGroupBox) gb.children().stream().filter(i -> i.getObjectName().equals("Form")).findFirst().get();
        QRadioButton lRadio = (QRadioButton)lsForm.children().stream().filter(i -> i.getObjectName().equals("LongRadioButton")).findFirst().get();
        boolean male = mRadio.isChecked();
        boolean isLong = lRadio.isChecked();

        List<QuestionData.QuestionAnswerData> answers = getAnswers();

        ResultProcessor rp = new ResultProcessor(male, isLong);

        QFileDialog.Result<String> dialog = QFileDialog.getSaveFileName();
        boolean success = rp.writePDFDocument(answers, dialog.result);
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

    void saveJSON() {
        List<QuestionData.QuestionAnswerData> answers = getAnswers();

        QGroupBox gb = ((QGroupBox)children().stream().filter(i -> i.getObjectName().equals("TopGroupBox")).findFirst().get());
        QGroupBox mfgb = (QGroupBox) gb.children().stream().filter(i -> i.getObjectName().equals("MFGB")).findFirst().get();
        QRadioButton mRadio = (QRadioButton)mfgb.children().stream().filter(i -> i.getObjectName().equals("MaleRadioButton")).findFirst().get();
        QGroupBox lsForm = (QGroupBox) gb.children().stream().filter(i -> i.getObjectName().equals("Form")).findFirst().get();
        QRadioButton lRadio = (QRadioButton)lsForm.children().stream().filter(i -> i.getObjectName().equals("LongRadioButton")).findFirst().get();
        boolean male = mRadio.isChecked();
        boolean isLong = lRadio.isChecked();

        ResultProcessor rp = new ResultProcessor(male, isLong);
        String json = rp.getJSONFromAnswers(answers);

        QFileDialog.Result<String> dialog = QFileDialog.getSaveFileName();
        OutputStream out = null;
        try {
            out = new FileOutputStream(dialog.result);
            out.write(json.getBytes());
            out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
