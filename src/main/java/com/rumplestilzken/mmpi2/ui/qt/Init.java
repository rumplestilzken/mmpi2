package com.rumplestilzken.mmpi2.ui.qt;

import io.qt.gui.QWindow;
import io.qt.widgets.QApplication;
import io.qt.widgets.QLabel;
import io.qt.widgets.QMainWindow;

public class Init {
    public void start(String[] args) {
        QApplication.initialize(args);
        QMainWindow window = new QMainWindow();
        window.setCentralWidget(new QLabel("Hello World", window));
        window.show();
        QApplication.exec();
        QApplication.shutdown();
    }
}
