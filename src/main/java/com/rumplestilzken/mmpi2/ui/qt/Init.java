package com.rumplestilzken.mmpi2.ui.qt;

import com.rumplestilzken.mmpi2.data.SaveProcessor;
import com.rumplestilzken.mmpi2.ui.qt.form.MainForm;
import io.qt.gui.QWindow;
import io.qt.widgets.*;

public class Init {
    public void start(String[] args) {
        QApplication.initialize(args);
        
        QMainWindow window = new QMainWindow();
        window.setFixedSize(700, 500);

        QMenu fileMenu = window.menuBar().addMenu("File");
        QAction saveQuestionAnswersMenuItem = new QAction("Save Question Answers");
        saveQuestionAnswersMenuItem.setEnabled(false);
        saveQuestionAnswersMenuItem.triggered.connect(SaveProcessor::saveQuestionData);
        fileMenu.addAction(saveQuestionAnswersMenuItem);

        QAction saveResultsMenuItem = new QAction("Save Results");
        saveResultsMenuItem.setEnabled(false);
        saveResultsMenuItem.triggered.connect(SaveProcessor::saveResults);
        fileMenu.addAction(saveResultsMenuItem);

        QMenu aboutMenu = window.menuBar().addMenu("About");


        window.setCentralWidget(new MainForm(window));
        window.show();
        
        QApplication.exec();
        QApplication.shutdown();
    }
}
