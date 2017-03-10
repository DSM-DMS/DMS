package com.dms.beinone.application;

import com.dms.beinone.application.afterschoolapply.Afterschool;
import com.dms.beinone.application.appcontent.Appcontent;
import com.dms.beinone.application.appcontent.Attachment;
import com.dms.beinone.application.comment.Comment;
import com.dms.beinone.application.facilityreport.FacilityReport;
import com.dms.beinone.application.faq.FAQ;
import com.dms.beinone.application.faq.FAQContent;
import com.dms.beinone.application.meal.Meal;
import com.dms.beinone.application.qna.QnA;
import com.dms.beinone.application.rule.Rule;
import com.dms.beinone.application.rule.RuleContent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BeINone on 2017-01-26.
 */

public class JSONParser {

    public static Meal parseMealJSON(JSONObject rootJsonObject) throws JSONException {
        JSONObject resultJSONObject = rootJsonObject.getJSONObject("result");

        JSONArray mealJSONArray = resultJSONObject.getJSONArray("Meals");


        Meal meal = new Meal();
        for (int index = 0; index < mealJSONArray.length(); index++) {
            JSONObject mealJSONObject = mealJSONArray.getJSONObject(index);

            // parse menu
            JSONArray menuJSONArray = mealJSONObject.getJSONArray("Menu");
            StringBuilder menuBuilder = new StringBuilder();
            for (int i = 0; i < menuJSONArray.length(); i++) {
                menuBuilder.append(menuJSONArray.getString(i));
                if (i != menuJSONArray.length() - 1) {
                    menuBuilder.append(", ");
                }
            }

            // parse allergy
            JSONArray allergyJSONArray = mealJSONObject.getJSONArray("Allergy");
            StringBuilder allergyBuilder = new StringBuilder();
            for (int i = 0; i < allergyJSONArray.length(); i++) {
                allergyBuilder.append(allergyJSONArray.getString(i));

                // no comma at last position
                if (i != allergyJSONArray.length() - 1) {
                    allergyBuilder.append(", ");
                }
            }

            if (index == 0) {
                meal.setBreakfast(menuBuilder.toString());
                meal.setBreakfastAllergy(allergyBuilder.toString());
            } else if (index == 1) {
                meal.setLunch(menuBuilder.toString());
                meal.setLunchAllergy(allergyBuilder.toString());
            } else {
                meal.setDinner(menuBuilder.toString());
                meal.setDinnerAllergy(allergyBuilder.toString());
            }
        }

        return meal;
    }

    public static List<Appcontent> parseAppcontentListJSON(JSONObject rootJSONObject)
            throws JSONException {

        List<Appcontent> appcontentList = new ArrayList<>();

        JSONArray resultJSONArray = rootJSONObject.getJSONArray("result");
        for (int index = 0; index < resultJSONArray.length(); index++) {
            JSONObject noticeJSONObject = resultJSONArray.getJSONObject(index);

            int category = noticeJSONObject.getInt("Category");
            int number = noticeJSONObject.getInt("HomeNumber");
            String title = noticeJSONObject.getString("Title");
            String writer = noticeJSONObject.getString("Writer");
            String date = noticeJSONObject.getString("Date");

            appcontentList.add(new Appcontent(category, number, title, writer, date));
        }

        return appcontentList;
    }

    public static Appcontent parseAppcontentJSON(JSONObject rootJSONObject) throws JSONException {
        JSONObject resultJSONObject = rootJSONObject.getJSONObject("result");

        int category = resultJSONObject.getInt("Category");
        int number = resultJSONObject.getInt("HomeNumber");
        String title = resultJSONObject.getString("Title");
        String content = resultJSONObject.getString("Content");
        String writer = resultJSONObject.getString("Writer");
        String date = resultJSONObject.getString("Date");
        List<Attachment> attachmentList =
                parseAttachmentsJSON(resultJSONObject.getJSONObject("Attachments"));

        return new Appcontent(category, number, title, content, writer, date, attachmentList);
    }

    private static List<Attachment> parseAttachmentsJSON(JSONObject attachmentsJSONObject) throws JSONException {
        List<Attachment> attachmentList = new ArrayList<>();

        JSONArray listJSONArray = attachmentsJSONObject.getJSONArray("List");
        int jsonArraySize = attachmentsJSONObject.getInt("Size");

        for (int index = 0; index < jsonArraySize; index++) {
            JSONObject listJSONObject = listJSONArray.getJSONObject(index);
            String name = listJSONObject.getString("Name");
            String link = listJSONObject.getString("Link");

            attachmentList.add(new Attachment(name, link));
        }

        return attachmentList;
    }

    public static List<Afterschool> parseAfterschoolListJSON(JSONObject rootJSONObject)
            throws JSONException {

        List<Afterschool> afterschoolList = new ArrayList<>();

        JSONArray resultJSONArray = rootJSONObject.getJSONArray("result");

        for (int index = 0; index < resultJSONArray.length(); index++) {
            JSONObject afterschoolJSONObject = resultJSONArray.getJSONObject(index);

            int no = afterschoolJSONObject.getInt("no");
            String title = afterschoolJSONObject.getString("title");
            int target = afterschoolJSONObject.getInt("target");
            String place = afterschoolJSONObject.getString("place");
            boolean onMonday = afterschoolJSONObject.getBoolean("on_monday");
            boolean onTuesday = afterschoolJSONObject.getBoolean("on_tuesday");
            boolean onWednesday = afterschoolJSONObject.getBoolean("on_wednesday");
            boolean onSaturday = afterschoolJSONObject.getBoolean("on_saturday");
            String instructor = afterschoolJSONObject.getString("instuctor");

            afterschoolList.add(new Afterschool(no, title, target, place,
                    onMonday, onTuesday, onWednesday, onSaturday, instructor));
        }

        return afterschoolList;
    }

    public static FacilityReport parseFacilityReportJSON(JSONObject rootJSONObject, int no)
            throws JSONException {

        String title = rootJSONObject.getString("title");
        String content = rootJSONObject.getString("content");
        int room = rootJSONObject.getInt("room");
        String writeDate = rootJSONObject.getString("write_date");
        String writer = rootJSONObject.getString("writer");
        boolean hasResult = rootJSONObject.getBoolean("has_result");

        String result = null;
        String resultDate = null;
        if (hasResult) {
            result = rootJSONObject.getString("result");
            resultDate = rootJSONObject.getString("result_date");
        }

        return new FacilityReport(no, title, content, room, writeDate,
                writer, hasResult, result, resultDate);
    }

    public static List<FAQ> parseFAQJSON(JSONObject rootJSONObject) throws JSONException {
        List<FAQ> faqList = new ArrayList<>();

        JSONArray resultJSONArray = rootJSONObject.getJSONArray("result");
        for (int index = 0; index < resultJSONArray.length(); index++) {
            JSONObject noticeJSONObject = resultJSONArray.getJSONObject(index);

            int no = noticeJSONObject.getInt("no");
            String title = noticeJSONObject.getString("title");
            String content = noticeJSONObject.getString("content");
            List<FAQContent> faqContentList = new ArrayList<>();
            faqContentList.add(new FAQContent(content));

            faqList.add(new FAQ(no, title, faqContentList));
        }

        return faqList;
    }

    public static List<Rule> parseRuleJSON(JSONObject rootJSONObject) throws JSONException {
        List<Rule> ruleList = new ArrayList<>();

        JSONArray resultJSONArray = rootJSONObject.getJSONArray("result");
        for (int index = 0; index < resultJSONArray.length(); index++) {
            JSONObject ruleJSONObject = resultJSONArray.getJSONObject(index);

            int no = ruleJSONObject.getInt("no");
            String title = ruleJSONObject.getString("title");
            String content = ruleJSONObject.getString("content");
            List<RuleContent> ruleContentList = new ArrayList<>();
            ruleContentList.add(new RuleContent(content));

            ruleList.add(new Rule(no, title, ruleContentList));
        }

        return ruleList;
    }

    public static List<QnA> parseQnAListJSON(JSONObject rootJSONObject) throws JSONException {
        List<QnA> qnaList = new ArrayList<>();

        JSONArray resultJSONArray = rootJSONObject.getJSONArray("result");
        for (int index = 0; index < resultJSONArray.length(); index++) {
            JSONObject qnaJSONObject = resultJSONArray.getJSONObject(index);

            int no = qnaJSONObject.getInt("no");
            String title = qnaJSONObject.getString("title");
            String questionDate = qnaJSONObject.getString("question_date");
            String writer = qnaJSONObject.getString("writer");
            boolean privacy = qnaJSONObject.getBoolean("privacy");

            qnaList.add(new QnA(no, title, questionDate, writer, privacy));
        }

        return qnaList;
    }

    public static QnA parseQnAJSON(JSONObject rootJSONObject, int no) throws JSONException {
        String title = rootJSONObject.getString("title");
        String questionContent = rootJSONObject.getString("question_content");
        String questionDate = rootJSONObject.getString("question_date");
        String writer = rootJSONObject.getString("writer");
        boolean privacy = rootJSONObject.getBoolean("privacy");

        String answerContent = null;
        String answerDate = null;
        if (rootJSONObject.getBoolean("has_answer")) {
            answerContent = rootJSONObject.getString("answer_content");
            answerDate = rootJSONObject.getString("answer_date");
        }

        return new QnA(no, title, questionContent, questionDate,
                writer, answerContent, answerDate, privacy);
    }

    public static List<Comment> parseCommentJSON(JSONObject rootJSONObject) throws JSONException {
        List<Comment> commentList = new ArrayList<>();

        JSONArray resultJSONArray = rootJSONObject.getJSONArray("result");
        for (int index = 0; index < resultJSONArray.length(); index++) {
            JSONObject commentJSONObject = resultJSONArray.getJSONObject(index);

            String writer = commentJSONObject.getString("writer");
            String commentDate = commentJSONObject.getString("comment_date");
            String content = commentJSONObject.getString("content");

            commentList.add(new Comment(writer, commentDate, content));
        }

        return commentList;
    }

}
