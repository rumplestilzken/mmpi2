package com.rumplestilzken.mmpi2.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rumplestilzken.mmpi2.data.scale.Scale;
import com.rumplestilzken.mmpi2.data.scale.ScaleProcessor;
import com.rumplestilzken.mmpi2.data.scale.Scales;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultProcessor {

    public ResultProcessor(boolean isMale) {
        this.isMale = isMale;
    }

    boolean isMale = false;

    public String getJSONFromAnswers(List<QuestionData.QuestionAnswerData> answers) {
        JSONObject answerObject = new JSONObject();
        JSONObject scales = new JSONObject();
        List<Scale> scaleList = Scales.getScales();

        ScaleProcessor sp = new ScaleProcessor();
        answerObject.put("Profile Evaluation", sp.process(answers, scaleList, isMale));

        scaleList.stream().forEachOrdered(i -> {
            JSONObject scale = new JSONObject();
            Map<String, String> map = new HashMap<>();
            map.put("description", i.getDescription());
            map.put("rawScore", Long.toString(i.getRawScore()));
            map.put("kScore", Long.toString(i.getkScore()));
            map.put("tScore", i.gettScore());
            map.put("Index", Integer.toString(i.getIndex()));
            scale.put(i.toString(), map);
            scales.put(i.toString(), scale);
        });
        answerObject.put("scales", scales);

        JSONObject answerWrapper = new JSONObject();
        for(QuestionData.QuestionAnswerData answer : answers) {
            answerWrapper.put(Integer.toString(answer.getIndex()), answer.getAnswer() == null ? "?" : answer.getAnswer());
        }
        answerObject.put("Answers", answerWrapper);

        ObjectMapper objectMapper = new ObjectMapper();
        Object jsonObject = null;
        String prettyPrint = "";
        try {
            jsonObject = objectMapper.readValue(answerObject.toString(), Object.class);
            prettyPrint = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return prettyPrint;
    }
}
