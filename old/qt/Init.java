package com.rumplestilzken.mmpi.ui.qt;

import com.rumplestilzken.mmpi.data.SaveProcessor;
import com.rumplestilzken.mmpi.ui.qt.form.MainForm;
import io.qt.gui.QIcon;
import io.qt.gui.QWindow;
import io.qt.widgets.*;

public class Init {
    public void start(String[] args) {
        QApplication.initialize(args);
        QApplication.setWindowIcon(new QIcon("resources/icon.jpg"));
        QApplication.setApplicationName("MMPI-2");
        
        QMainWindow window = new QMainWindow();
        window.setFixedSize(1024, 768);

        QMenu fileMenu = window.menuBar().addMenu("File");
        fileMenu.setObjectName("File");

        QMenu aboutMenu = window.menuBar().addMenu("About");
        aboutMenu.setObjectName("About");

        window.setCentralWidget(new MainForm(window));
        window.show();
        
        QApplication.exec();
        QApplication.shutdown();
    }
}
