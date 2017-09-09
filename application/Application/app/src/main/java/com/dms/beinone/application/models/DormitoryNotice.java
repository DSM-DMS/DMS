package com.dms.beinone.application.models;

import java.util.ArrayList;

/**
 * Created by dsm2017 on 2017-08-31.
 */

public class DormitoryNotice {

    private String backOffice;
    private String noticeTitle;

    public DormitoryNotice (String backOffice, String noticeTitle) {

        this.backOffice = backOffice;
        this.noticeTitle = noticeTitle;
    }

    public String getBackOffice () {

        return backOffice;
    }

    public String getNoticeTitle () {

        return noticeTitle;
    }
}
