package com.dms.beinone.application;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.beinone.application.afterschoolapply.AfterschoolApplyFragment;
import com.dms.beinone.application.appcontent.Appcontent;
import com.dms.beinone.application.appcontent.AppcontentFragment;
import com.dms.beinone.application.extensionapply.ExtensionApplyFragment;
import com.dms.beinone.application.facilityreport.FacilityReportFragment;
import com.dms.beinone.application.faq.FAQFragment;
import com.dms.beinone.application.goingoutapply.GoingoutApplyFragment;
import com.dms.beinone.application.home.HomeFragment;
import com.dms.beinone.application.login.LoginActivity;
import com.dms.beinone.application.meal.MealFragment;
import com.dms.beinone.application.mypage.MypageActivity;
import com.dms.beinone.application.qna.QnAFragment;
import com.dms.beinone.application.rewardscoreapply.RewardscoreApplyFragment;
import com.dms.beinone.application.rule.RuleFragment;
import com.dms.beinone.application.stayapply.StayApplyFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SharedPreferences mAccountPrefs;

    private NavigationView mNavigationView;
    private View mLoginNavHeaderView;
    private View mLogoutNavHeaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAccountPrefs = getSharedPreferences(getString(R.string.PREFS_ACCOUNT), MODE_PRIVATE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        // check home item in navigation drawer at initially
        mNavigationView.setCheckedItem(R.id.nav_item_home);

        mLoginNavHeaderView = getLayoutInflater().inflate(R.layout.nav_header_login, null);
        Button navHeaderLoginBtn = (Button) mLoginNavHeaderView.findViewById(R.id.btn_nav_header_login);
        navHeaderLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // display login activity when login button in navigation header is clicked
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        mLogoutNavHeaderView = getLayoutInflater().inflate(R.layout.nav_header_logout, null);
        Button logoutBtn = (Button) mLogoutNavHeaderView.findViewById(R.id.btn_nav_header_logout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this).setMessage("로그아웃 하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // logout
                                mAccountPrefs.edit().clear().apply();
                                dialog.dismiss();

                                // update drawer
                                MainActivity.this.onBackPressed();
                                MainActivity.this.onResume();

                                // return to home fragment
                                clearBackStack();
                                replaceFragment(new HomeFragment());

                                Toast.makeText(MainActivity.this, R.string.logout_success,
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();
            }
        });

        // display home fragment at initially
        replaceFragment(new HomeFragment());
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mAccountPrefs.getString(getString(R.string.PREFS_ACCOUNT_ID), "").equals("")) {
            if (mNavigationView.getHeaderView(0) != mLoginNavHeaderView) {
                hideMemberMenuItems();
                setLoginHeaderView();
            }
        } else {
            if (mNavigationView.getHeaderView(0) != mLogoutNavHeaderView) {
                showMemberMenuItems();
                setLogoutHeaderView();
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_item_home) {
            replaceFragmentWithBackStack(new HomeFragment());
        } else if (id == R.id.nav_item_extensionapply) {
            replaceFragmentWithBackStack(new ExtensionApplyFragment());
        } else if (id == R.id.nav_item_stayapply) {
            replaceFragmentWithBackStack(new StayApplyFragment());
        } else if (id == R.id.nav_item_goingoutapply) {
//            replaceFragmentWithBackStack(new NewGoingoutApplyFragment());
            replaceFragmentWithBackStack(new GoingoutApplyFragment());
        } else if (id == R.id.nav_item_rewardscoreapply) {
            replaceFragmentWithBackStack(new RewardscoreApplyFragment());
        } else if (id == R.id.nav_item_afterschoolapply) {
            replaceFragmentWithBackStack(new AfterschoolApplyFragment());
        } else if (id == R.id.nav_item_meal) {
            replaceFragmentWithBackStack(new MealFragment());
        } else if (id == R.id.nav_item_notice) {
            replaceFragmentWithBackStack(AppcontentFragment.newInstance(this, Appcontent.NOTICE));
        } else if (id == R.id.nav_item_newsletter) {
            replaceFragmentWithBackStack(AppcontentFragment.newInstance(this, Appcontent.NEWSLETTER));
        } else if (id == R.id.nav_item_competition) {
            replaceFragmentWithBackStack(AppcontentFragment.newInstance(this, Appcontent.COMPETITION));
        } else if (id == R.id.nav_item_facilityreport) {
            replaceFragmentWithBackStack(new FacilityReportFragment());
        } else if (id == R.id.nav_item_rule) {
            replaceFragmentWithBackStack(new RuleFragment());
        } else if (id == R.id.nav_item_faq) {
            replaceFragmentWithBackStack(new FAQFragment());
        } else if (id == R.id.nav_item_qna) {
            replaceFragmentWithBackStack(new QnAFragment());
        } else if (id == R.id.nav_item_settings) {

        } else if (id == R.id.nav_item_mypage) {
//            replaceFragment(new MypageFragment());
            startActivity(new Intent(this, MypageActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setLoginHeaderView() {
        mNavigationView.removeHeaderView(mNavigationView.getHeaderView(0));
        mNavigationView.addHeaderView(mLoginNavHeaderView);
    }

    private void setLogoutHeaderView() {
        TextView numberTV = (TextView) mLogoutNavHeaderView.findViewById(R.id.tv_nav_header_number);
        TextView nameTV = (TextView) mLogoutNavHeaderView.findViewById(R.id.tv_nav_header_name);
        TextView meritTV = (TextView) mLogoutNavHeaderView.findViewById(R.id.tv_nav_header_merit);
        TextView demeritTV = (TextView) mLogoutNavHeaderView.findViewById(R.id.tv_nav_header_demerit);

        int number = mAccountPrefs.getInt(getString(R.string.PREFS_ACCOUNT_NUMBER), 0);
//        numberTV.setText(String.valueOf(StudentUtils.numberToString(number)));
//        nameTV.setText(mAccountPrefs.getString(getString(R.string.PREFS_ACCOUNT_NAME), ""));
//        meritTV.setText(String.valueOf(mAccountPrefs.getInt(getString(R.string.PREFS_ACCOUNT_MERIT), 0)));
//        demeritTV.setText(String.valueOf(mAccountPrefs.getInt(getString(R.string.PREFS_ACCOUNT_DEMERIT), 0)));

        mNavigationView.removeHeaderView(mNavigationView.getHeaderView(0));
        mNavigationView.addHeaderView(mLogoutNavHeaderView);
    }

    private void showMemberMenuItems() {
        Menu menu = mNavigationView.getMenu();
        menu.findItem(R.id.nav_item_apply).setVisible(true);
        menu.findItem(R.id.nav_item_mypage).setVisible(true);
    }

    private void hideMemberMenuItems() {
        Menu menu = mNavigationView.getMenu();
        menu.findItem(R.id.nav_item_apply).setVisible(false);
        menu.findItem(R.id.nav_item_mypage).setVisible(false);
    }

    private int getFragmentCount() {
        return getSupportFragmentManager().getBackStackEntryCount();
    }

    private void clearBackStack() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**
     * 기존의 Fragment를 새로운 Fragment로 교체
     *
     * @param fragment 교체할 Fragment
     */
    private void replaceFragmentWithBackStack(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.relativelayout_main_container, fragment)
                .commit();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.relativelayout_main_container, fragment)
                .commit();
    }

}
