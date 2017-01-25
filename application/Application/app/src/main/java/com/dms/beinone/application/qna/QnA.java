package com.dms.beinone.application.qna;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by BeINone on 2017-01-23.
 */

public class QnA implements Parcelable {

    private int no;
    private String title;
    private String questionContent;
    private String questionDate;
    private String questioner;
    private String answerContent;
    private String answerDate;
    private boolean privacy;

    public QnA(int no, String title, String questionContent, String questionDate, String questioner,
               String answerContent, String answerDate, boolean privacy) {
        setNo(no);
        setTitle(title);
        setQuestionContent(questionContent);
        setQuestionDate(questionDate);
        setQuestioner(questioner);
        setAnswerContent(answerContent);
        setAnswerDate(answerDate);
        setPrivacy(privacy);
    }

    public QnA(Parcel in) {
        setNo(in.readInt());
        setTitle(in.readString());
        setQuestionContent(in.readString());
        setQuestionDate(in.readString());
        setQuestioner(in.readString());
        setAnswerContent(in.readString());
        setAnswerDate(in.readString());

        // privacy is true if byte != 0
        setPrivacy(in.readByte() != 0);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(no);
        dest.writeString(title);
        dest.writeString(questionContent);
        dest.writeString(questionDate);
        dest.writeString(questioner);
        dest.writeString(answerContent);
        dest.writeString(answerDate);

        // if privacy is true, byte == 1
        dest.writeByte((byte) (privacy ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(String questionDate) {
        this.questionDate = questionDate;
    }

    public String getQuestioner() {
        return questioner;
    }

    public void setQuestioner(String questioner) {
        this.questioner = questioner;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public String getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(String answerDate) {
        this.answerDate = answerDate;
    }

    public boolean isPrivacy() {
        return privacy;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }

    public static final Creator<QnA> CREATOR = new Creator<QnA>() {
        @Override
        public QnA createFromParcel(Parcel source) {
            return new QnA(source);
        }

        @Override
        public QnA[] newArray(int size) {
            return new QnA[size];
        }
    };

}
