package com.dms.beinone.application.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.beinone.application.DMSService;
import com.dms.beinone.application.R;
import com.dms.beinone.application.activities.LoginActivity;
import com.dms.beinone.application.dialogs.LogoutDialogFragment;
import com.dms.beinone.application.managers.AccountManager;
import com.dms.beinone.application.managers.HttpManager;
import com.dms.beinone.application.models.Account;
import com.dms.beinone.application.models.Meal;
import com.dms.beinone.application.utils.ExtensionUtils;
import com.google.gson.JsonObject;

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

public class MyPageFragment extends Fragment {

    public static final int REQUEST_CODE_LOGOUT_DIALOG = 1;

    private TextView mStayStatusTV;
    private TextView mExtensionStatusTV;
    private TextView mMeritTV;
    private TextView mDemeritTV;
    private View mLogoutMenu;
    private TextView mLogoutTV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_page, container, false);

        mStayStatusTV = (TextView) view.findViewById(R.id.tv_my_page_stay_status);
        mExtensionStatusTV = (TextView) view.findViewById(R.id.tv_my_page_extension_status);
        mMeritTV = (TextView) view.findViewById(R.id.tv_my_page_merit);
        mDemeritTV = (TextView) view.findViewById(R.id.tv_my_page_demerit);
        mLogoutMenu = view.findViewById(R.id.layout_my_page_logout);
        mLogoutTV = (TextView) view.findViewById(R.id.tv_my_page_logout);



        initMenuArrowColor(view);

        View qnaListMenu = view.findViewById(R.id.layout_my_page_qna_list);
        qnaListMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        View reportFacilityMenu = view.findViewById(R.id.layout_my_page_report_facility);
        reportFacilityMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        View changePasswordMenu = view.findViewById(R.id.layout_my_page_change_password);
        changePasswordMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

      /*  Button loginBtn = (Button) view.findViewById(R.id.btn_my_page_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });*/

        loadMyPage();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setLogoutMenu();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_LOGOUT_DIALOG:
                if (resultCode == Activity.RESULT_OK) {
                    setLogoutMenu();
                }
                break;
        }
    }

    private void initMenuArrowColor(View rootView) {
        ImageView arrowIV1 = (ImageView) rootView.findViewById(R.id.iv_my_page_arrow1);
        ImageView arrowIV2 = (ImageView) rootView.findViewById(R.id.iv_my_page_arrow2);
        ImageView arrowIV3 = (ImageView) rootView.findViewById(R.id.iv_my_page_arrow3);
        ImageView arrowIV4 = (ImageView) rootView.findViewById(R.id.iv_my_page_arrow4);

        arrowIV1.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        arrowIV2.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        arrowIV3.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        arrowIV4.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
    }

    private void bind(Account account) {
        mStayStatusTV.setText(account.getStayValue());
        String extensionStatus = ExtensionUtils.getStringFromClass(account.getRoom()) + account.getSeat();
        mExtensionStatusTV.setText(extensionStatus);
        mMeritTV.setText(account.getMerit());
        mDemeritTV.setText(account.getDemerit());
    }

    private void setLogoutMenu() {
        if (AccountManager.isLogined(getContext())) {
            mLogoutTV.setText(R.string.logout);
            mLogoutMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogoutDialogFragment logoutDialogFragment = new LogoutDialogFragment();
                    logoutDialogFragment.setTargetFragment(MyPageFragment.this, REQUEST_CODE_LOGOUT_DIALOG);
                    logoutDialogFragment.show(getChildFragmentManager(), null);
                }
            });
        } else {
            mLogoutTV.setText(R.string.login);
            mLogoutMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }
            });
        }
    }

    private void loadMyPage() {
        DMSService dmsService = HttpManager.createDMSService(getContext());
        Call<Account> call = dmsService.loadMyPage();
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                switch (response.code()) {
                    case HTTP_OK:
                        bind(response.body());
                        break;
                    case HTTP_NO_CONTENT:
                        Toast.makeText(getContext(), R.string.my_page_load_no_content, Toast.LENGTH_SHORT).show();
                        break;
                    case HTTP_BAD_REQUEST:

                        break;
                    case HTTP_INTERNAL_SERVER_ERROR:
                        Toast.makeText(getContext(), R.string.my_page_load_internal_server_error, Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
