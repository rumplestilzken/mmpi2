from PyQt5.QtWidgets import *
from mmpi2.data.QuestionData import QuestionData


class FormWidget(QWidget):

    previous_form_widget = None
    next_form_widget = None

    def __init__(self, parent):
        super(FormWidget, self).__init__(parent)
        self.layout = QGridLayout(self)

        # self.layout.true = QLabel("True")
        # self.layout.addWidget(self.layout.true)
        #
        # self.layout.false = QLabel("False")
        # self.layout.addWidget(self.layout.false)

        # for question in QuestionData().get_questions():
        #     if question.index == 0:
        #         continue
        #
        #     q = QuestionWidget(self, question)
        #     self.layout.addWidget(q)

        self.setLayout(self.layout)


