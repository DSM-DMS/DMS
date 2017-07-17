package com.dms.beinone.application.activities;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.Space;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.beinone.application.DMSService;
import com.dms.beinone.application.R;
import com.dms.beinone.application.SelectableButtonGroup;
import com.dms.beinone.application.managers.HttpManager;
import com.dms.beinone.application.models.Class;
import com.dms.beinone.application.utils.DensityConverter;
import com.dms.beinone.application.utils.DrawableUtils;
import com.dms.beinone.application.utils.StringUtils;
import com.dms.beinone.application.views.custom.SelectableButton;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dms.beinone.application.DMSService.HTTP_BAD_REQUEST;
import static com.dms.beinone.application.DMSService.HTTP_INTERNAL_SERVER_ERROR;
import static com.dms.beinone.application.DMSService.HTTP_NO_CONTENT;
import static com.dms.beinone.application.DMSService.HTTP_OK;

/**
 * Created by BeINone on 2017-05-31.
 */

public class ExtensionActivity extends AppCompatActivity {

    private FloatingActionMenu mFAM;
    private List<FloatingActionButton> mFloatingMenuButtons;
    private SelectableButtonGroup mButtonGroup;

    private int mCurrentClass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extension);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView titleTV = (TextView) findViewById(R.id.tv_toolbar_title);
        titleTV.setText(R.string.extension);

        ImageButton backIB = (ImageButton) findViewById(R.id.ib_toolbar_back);
        backIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button applyBtn = (Button) findViewById(R.id.btn_extension_apply);
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apply(mCurrentClass, (int) mButtonGroup.getSelectedButton().getTag());
            }
        });

        mFloatingMenuButtons = new ArrayList<>();
        mFAM = (FloatingActionMenu) findViewById(R.id.fam_extension);
        mFAM.setClosedOnTouchOutside(true);

        changeClass(Class.CLASS_GA);
    }

    private void changeClass(int clazz) {
        mCurrentClass = clazz;
        setFloatingMenuButtons(clazz);
        loadClass(Class.OPTION_MAP, clazz);
    }

    private void loadClass(String option, int clazz) {
        DMSService dmsService = HttpManager.createDMSService(this);
        Call<Class> call = dmsService.loadExtensionClass(option, clazz);
        call.enqueue(new Callback<Class>() {
            @Override
            public void onResponse(Call<Class> call, Response<Class> response) {
                int code = response.code();
                switch (code) {
                    case HTTP_OK:
                        Class clazz = response.body();
                        if (clazz != null) drawMap(clazz.getMap());
                        break;
                    case HTTP_BAD_REQUEST:
                        break;
                    case HTTP_INTERNAL_SERVER_ERROR:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Class> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void apply(int clazz, int seat) {
        DMSService dmsService = HttpManager.createDMSService(this);
        Call<Void> call = dmsService.applyExtension(clazz, seat);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case HTTP_OK:
                        Toast.makeText(ExtensionActivity.this, R.string.apply_ok, Toast.LENGTH_SHORT).show();
                        break;
                    case HTTP_NO_CONTENT:
                        Toast.makeText(ExtensionActivity.this, R.string.apply_no_content, Toast.LENGTH_SHORT).show();
                        break;
                    case HTTP_BAD_REQUEST:
                        Toast.makeText(ExtensionActivity.this, R.string.http_bad_request, Toast.LENGTH_SHORT).show();
                        break;
                    case HTTP_INTERNAL_SERVER_ERROR:
                        Toast.makeText(ExtensionActivity.this, R.string.apply_internal_server_error, Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void setFloatingMenuButtons(int currentClass) {
//        mFAM.setIcon

        for (FloatingActionButton fab : mFloatingMenuButtons) {
            mFAM.removeMenuButton(fab);
        }
        mFloatingMenuButtons.clear();

        for (int clazz = Class.CLASS_GA; clazz <= Class.CLASS_5; clazz++) {
            if (clazz != currentClass) {
                FloatingActionButton fab = createFloatingActionButton(clazz);
                mFAM.addMenuButton(fab);
                mFloatingMenuButtons.add(fab);
            }
        }
    }

    private FloatingActionButton createFloatingActionButton(final int clazz) {
        FloatingActionButton fab = new FloatingActionButton(this);
        fab.setButtonSize(FloatingActionButton.SIZE_MINI);

        Drawable imageDrawable = null;
        int fabColorResId = 0;
        switch (clazz) {
            case Class.CLASS_GA:
                imageDrawable = ContextCompat.getDrawable(this, R.drawable.ic_class_ga);
                fabColorResId = R.color.extension2;
                break;
            case Class.CLASS_NA:
                imageDrawable = ContextCompat.getDrawable(this, R.drawable.ic_class_na);
                fabColorResId = R.color.extension2;
                break;
            case Class.CLASS_DA:
                imageDrawable = ContextCompat.getDrawable(this, R.drawable.ic_class_da);
                fabColorResId = R.color.extension2;
                break;
            case Class.CLASS_RA:
                imageDrawable = ContextCompat.getDrawable(this, R.drawable.ic_class_ra);
                fabColorResId = R.color.extension2;
                break;
            case Class.CLASS_3:
                imageDrawable = ContextCompat.getDrawable(this, R.drawable.ic_class_3);
                fabColorResId = R.color.extension3;
                break;
            case Class.CLASS_4:
                imageDrawable = ContextCompat.getDrawable(this, R.drawable.ic_class_4);
                fabColorResId = R.color.extension3;
                break;
            case Class.CLASS_5:
                imageDrawable = ContextCompat.getDrawable(this, R.drawable.ic_class_5);
                fabColorResId = R.color.extension4;
                break;
        }
        Drawable resizedImageDrawable = DrawableUtils.resizeWithDp(this, imageDrawable, 24);
        fab.setImageDrawable(resizedImageDrawable);
        fab.setColorNormal(fabColorResId);
        fab.setColorNormalResId(fabColorResId);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFAM.close(true);
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        changeClass(clazz);
                    }
                }.execute();
            }
        });

        return fab;
    }

    private void drawMap(List<List<String>> map) {
        mButtonGroup = new SelectableButtonGroup();

        LinearLayout container = (LinearLayout) findViewById(R.id.container_extension);
        container.removeAllViews();

        addVerticalMargin(container);

        for (List<String> row : map) {
            LinearLayout rowContainer = new LinearLayout(this);
            addHorizontalMargin(rowContainer);

            for (String seat : row) {
                if (StringUtils.isNumeric(seat)) {
                    if (Integer.valueOf(seat) == 0) {
                        // space
                        addSpaceView(rowContainer);
                    } else {
                        // available seat
                        addAvailableBtn(rowContainer, Integer.valueOf(seat));
                    }
                } else {
                    // unavailable seat
                    addUnavailableBtn(rowContainer, seat);
                }
                addHorizontalMargin(rowContainer);
            }
            rowContainer.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            container.addView(rowContainer);
            addVerticalMargin(container);
        }
    }

    private void addVerticalMargin(LinearLayout container) {
        View spaceView = new Space(this);
        container.addView(spaceView, LinearLayout.LayoutParams.MATCH_PARENT, (int) DensityConverter.dpToPx(this, 10.0f));
    }

    private void addHorizontalMargin(LinearLayout container) {
        View spaceView = new Space(this);
        container.addView(spaceView, (int) DensityConverter.dpToPx(this, 10.0f), LinearLayout.LayoutParams.MATCH_PARENT);
    }

    private void addSpaceView(LinearLayout container) {
        View spaceView = new Space(this);
        container.addView(spaceView, (int) DensityConverter.dpToPx(this, 64.0f), (int) DensityConverter.dpToPx(this, 32.0f));
    }

    private void addAvailableBtn(LinearLayout container, int seat) {
        SelectableButton availableBtn = (SelectableButton) LayoutInflater.from(this)
                .inflate(R.layout.view_extension_seat_available, container, false);
        container.addView(availableBtn);
        availableBtn.setTag(seat);
        mButtonGroup.add(availableBtn);
    }

    private void addUnavailableBtn(LinearLayout container, String name) {
        Button unavailableBtn = (Button) LayoutInflater.from(this)
                .inflate(R.layout.view_extension_seat_unavailable, container, false);
        unavailableBtn.setText(name);
        container.addView(unavailableBtn);
    }
}
