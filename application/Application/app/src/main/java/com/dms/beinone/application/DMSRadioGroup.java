package com.dms.beinone.application;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

/**
 * Created by BeINone on 2017-01-15.
 */

public class DMSRadioGroup extends RadioGroup {

    public DMSRadioGroup(Context context) {
        super(context);
        setOnCheckedChangeListener(onCheckedChangeListener);
    }

    public DMSRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnCheckedChangeListener(onCheckedChangeListener);
    }

    private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            for (int index = 0; index < radioGroup.getChildCount(); index++) {
                setCheckedChildren(radioGroup, i);
            }
        }
    };

    /**
     * Search and set checked state of DMSRadioButton in this view
     * @param viewGroup search in this ViewGroup
     * @param id resource id to search
     */
    private void setCheckedChildren(ViewGroup viewGroup, int id) {
        for (int index = 0; index < viewGroup.getChildCount(); index++) {
            View view = viewGroup.getChildAt(index);
            if (view instanceof DMSRadioButton) {
                // 자식 뷰가 DMSRadioButton이면 체크 상태 설정
                ((DMSRadioButton) view).setChecked(view.getId() == id);
            } else if (view instanceof ViewGroup) {
                // 자식 뷰가 DMSRadioButton이 아니고, ViewGroup이면 그 내부의 자식 뷰들을 검사
                setCheckedChildren((ViewGroup) view, id);
            }
        }
    }

}
