#!/usr/bin/env python3

from PyQt5.QtGui import *
from PyQt5.QtWidgets import *

from mmpi2.gui.form.MainForm import MainForm


class MainWindow(QMainWindow):
    def __init__(self):
        super().__init__()

        self.form = MainForm(self)
        # self.current_form.previous_form.hide()
        # self.current_form.next_form.hide()
        self.setCentralWidget(self.form)

        self.setWindowIcon(QIcon('resources/icon.jpg'))
        # set the title
        self.setWindowTitle("MMPI-2 Scoring")

        self.show()






