package com.dms.beinone.application;

import android.view.View;
import android.widget.Button;

import com.dms.beinone.application.views.custom.SelectableButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BeINone on 2017-07-13.
 */

public class SelectableButtonGroup implements SelectableButton.OnSelectedListener {

    private List<SelectableButton> buttons;

    public SelectableButtonGroup() {
        buttons = new ArrayList<>();
    }

    @Override
    public void onSelected(View v) {
        for (Button button : buttons) {
            if (!button.equals(v) && button.isSelected()) {
                button.setSelected(false);
            }
        }
    }

    public boolean add(SelectableButton button) {
        button.setOnSelectedListener(this);
        return buttons.add(button);
    }

    public boolean remove(SelectableButton button) {
        button.removeOnSelectedListener();
        return buttons.remove(button);
    }

    public SelectableButton getSelectedButton() {
        for (SelectableButton button : buttons) {
            if (button.isSelected()) return button;
        }
        return null;
    }
}
