package com.dms.beinone.application.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dms.beinone.application.fragments.SurveyFragment;
import com.dms.beinone.application.models.Survey;

import java.util.ArrayList;

/**
 * Created by dsm2017 on 2017-11-12.
 */

public class SurveyViewPagerAdapter extends FragmentPagerAdapter{

    private ArrayList<Survey> surveyArrayList;

    public SurveyViewPagerAdapter (ArrayList<Survey> surveyArrayList, FragmentManager fm) {

        super(fm);
        this.surveyArrayList = surveyArrayList;
    }

    @Override
    public Fragment getItem (int position) {

        return null;
    }

    @Override
    public int getCount () {

        return (surveyArrayList == null) ? surveyArrayList.size() : 0;
    }
}
