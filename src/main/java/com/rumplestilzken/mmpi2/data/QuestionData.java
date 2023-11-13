package com.rumplestilzken.mmpi2.data;

import java.util.ArrayList;
import java.util.List;

public class QuestionData {
    public static List<String> getQuestionText() {
        List<String> questions = new ArrayList<String>();
        questions.add("I like mechanics magazines.");
        questions.add("I have a good appetite.");
        questions.add("I wake up fresh and rested most mornings.");
        return questions;
    }

    public static List<Question> getQuestions() {
        List<String> questionText = getQuestionText();
        List<Question> questions = new ArrayList<Question>();
        for(int i = 1; i < questionText.size(); i++)
        {
            Question q = new Question(i, questionText.get(i-1));
            questions.add(q);
        }
        return questions;
    }

    public static class Question {
        int index = -1;
        String text = "";

        public Question(int index, String text) {
            this.index = index;
            this.text = text;
        }
    }
}
