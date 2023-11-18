package com.rumplestilzken.mmpi2.data;

import org.json.JSONObject;

import java.util.List;

public class ResultProcessor {
    boolean isMale = false;
    boolean isFemale = false;

    public JSONObject getJSONFromAnswers(List<QuestionData.QuestionAnswerData> answers) {
        JSONObject answerObject = new JSONObject();
        for(QuestionData.QuestionAnswerData answer : answers) {
            answerObject.put(Integer.toString(answer.getIndex()), answer.getAnswer() == null ? "?" : answer.getAnswer());
        }
        return answerObject;
    }
}
