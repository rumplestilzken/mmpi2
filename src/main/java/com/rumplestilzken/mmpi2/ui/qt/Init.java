package com.rumplestilzken.mmpi2.ui.qt;

import com.rumplestilzken.mmpi2.data.SaveProcessor;
import com.rumplestilzken.mmpi2.ui.qt.form.MainForm;
import io.qt.gui.QIcon;
import io.qt.gui.QWindow;
import io.qt.widgets.*;

public class Init {
    public void start(String[] args) {
        QApplication.initialize(args);
        QApplication.setWindowIcon(new QIcon("resources/icon.jpg"));
        
        QMainWindow window = new QMainWindow();
        window.setFixedSize(1024, 768);

        QMenu fileMenu = window.menuBar().addMenu("File");
        fileMenu.setObjectName("File");
        QAction loadQuestionAnswersMenuItem = new QAction("Load Question Answers");
        loadQuestionAnswersMenuItem.setObjectName("LoadAnswers");
        loadQuestionAnswersMenuItem.setEnabled(false);
        fileMenu.addAction(loadQuestionAnswersMenuItem);

        QAction saveQuestionAnswersMenuItem = new QAction("Save Question Answers");
        saveQuestionAnswersMenuItem.setObjectName("SaveQuestions");
        saveQuestionAnswersMenuItem.setEnabled(false);
        fileMenu.addAction(saveQuestionAnswersMenuItem);

        QAction saveResultsMenuItem = new QAction("Save Results");
        saveResultsMenuItem.setObjectName("SaveResults");
        saveResultsMenuItem.setEnabled(false);
        fileMenu.addAction(saveResultsMenuItem);

        QMenu aboutMenu = window.menuBar().addMenu("About");

        window.setCentralWidget(new MainForm(window));
        window.show();
        
        QApplication.exec();
        QApplication.shutdown();
    }
}
