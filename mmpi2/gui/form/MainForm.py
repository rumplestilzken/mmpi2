from PyQt5.QtWidgets import *
from mmpi2.gui.form.FormWidget import *
from mmpi2.data.QuestionData import QuestionData
from mmpi2.gui.form.IntroFormWidget import IntroFormWidget
from mmpi2.gui.form.QuestionFormWidget import QuestionFormWidget


class MainForm(QWidget):

    form_widget = None

    def __init__(self, parent):
        super(MainForm, self).__init__(parent)

        self.layout = QGridLayout(self)

        self.form_widget = IntroFormWidget(self)
        self.layout.addWidget(self.form_widget)

        if self.form_widget.previous_form_widget is not None:
            self.form_widget.previous_form_widget.hide()
        if self.form_widget.next_form_widget is not None:
            self.form_widget.next_form_widget.hide()

        #
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
