class QuestionData:
    def get_questions(self):
        questions = []
        for question in self.get_question_text():
            Question(len(questions)+1, question)

        return questions

    def get_question_text(self):
        questions = []
        # questions.append("MMPI-2 Questions")
        questions.append("I like mechanics magazines.")
        questions.append("I have a good appetite.")
        questions.append("I wake up fresh and rested most mornings.")


class Question:
    index = -1
    text = ""

    def __init__(self, index, text):
        self.text = text
        self.index = index
