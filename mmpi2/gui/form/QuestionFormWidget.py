from PyQt5.QtWidgets import *

from mmpi2.gui.form.FormWidget import FormWidget


class QuestionFormWidget(FormWidget):

    def __init__(self, parent):
        super(QuestionFormWidget, self).__init__(parent)

        self.layout.text = QLabel("Question")
        self.layout.addWidget(self.layout.text)

        # self.layout = QGridLayout(self)
        #
        # self.layout.radio_true = QRadioButton(self)
        # self.layout.addWidget(self.layout.radio_true)
        #
        # self.layout.radio_false = QRadioButton(self)
        # self.layout.addWidget(self.layout.radio_false)
        #
        # self.layout.text = QLabel("")

        # self.setLayout(self.layout)
