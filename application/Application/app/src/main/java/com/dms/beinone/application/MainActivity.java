package com.dms.beinone.application;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dms.beinone.application.afterschoolapply.AfterschoolApplyFragment;
import com.dms.beinone.application.appcontent.Appcontent;
import com.dms.beinone.application.appcontent.AppcontentFragment;
import com.dms.beinone.application.dmsview.DMSButton;
import com.dms.beinone.application.facilityreport.FacilityReportFragment;
import com.dms.beinone.application.faq.FAQFragment;
import com.dms.beinone.application.goingoutapply.GoingoutApplyFragment;
import com.dms.beinone.application.home.HomeFragment;
import com.dms.beinone.application.login.LoginActivity;
import com.dms.beinone.application.meal.MealFragment;
import com.dms.beinone.application.qna.QnAFragment;
import com.dms.beinone.application.rewardscore.RewardscoreApplyFragment;
import com.dms.beinone.application.rule.RuleFragment;
import com.dms.beinone.application.stayapply.StayApplyFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SharedPreferences mPrefs;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPrefs = getSharedPreferences(getString(R.string.PREFS_ACCOUNT), MODE_PRIVATE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

        // display login activity when login button in navigation header is clicked
        View navHeaderView = mNavigationView.getHeaderView(0);
        DMSButton navHeaderLoginBtn =
                (DMSButton) navHeaderView.findViewById(R.id.btn_nav_header_login);
        navHeaderLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // display home fragment at initially
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.relativelayout_main_container, new HomeFragment())
                .commit();

        mPrefs.edit().clear().apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mPrefs.getString(getString(R.string.PREFS_ACCOUNT_ID), "").equals("")) {
            hideApplyMenuItems(mNavigationView);
        } else {
            showApplyMenuItems(mNavigationView);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_item_home) {
            replaceFragment(new HomeFragment());
        } else if (id == R.id.nav_item_extensionapply) {

        } else if (id == R.id.nav_item_stayapply) {
            replaceFragment(new StayApplyFragment());
        } else if (id == R.id.nav_item_goingoutapply) {
            replaceFragment(new GoingoutApplyFragment());
        } else if (id == R.id.nav_item_rewardscoreapply) {
            replaceFragment(new RewardscoreApplyFragment());
        } else if (id == R.id.nav_item_afterschoolapply) {
            replaceFragment(new AfterschoolApplyFragment());
        } else if (id == R.id.nav_item_meal) {
            replaceFragment(new MealFragment());
        } else if (id == R.id.nav_item_notice) {
            replaceFragment(AppcontentFragment.newInstance(this, Appcontent.NOTICE));
//            replaceFragment(new NoticeFragment());
        } else if (id == R.id.nav_item_newsletter) {
            replaceFragment(AppcontentFragment.newInstance(this, Appcontent.NEWSLETTER));
//            replaceFragment(new NewsletterFragment());
        } else if (id == R.id.nav_item_competition) {
            replaceFragment(AppcontentFragment.newInstance(this, Appcontent.COMPETITION));
        } else if (id == R.id.nav_item_facilityreport) {
            replaceFragment(new FacilityReportFragment());
        } else if (id == R.id.nav_item_rule) {
            replaceFragment(new RuleFragment());
        } else if (id == R.id.nav_item_faq) {
            replaceFragment(new FAQFragment());
        } else if (id == R.id.nav_item_qna) {
            replaceFragment(new QnAFragment());
        } else if (id == R.id.nav_item_settings) {

        } else if (id == R.id.nav_item_mypage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showApplyMenuItems(NavigationView navigationView) {
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_item_apply).setVisible(true);
    }

    private void hideApplyMenuItems(NavigationView navigationView) {
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_item_apply).setVisible(false);
    }

    /**
     * 기존의 Fragment를 새로운 Fragment로 교체
     * @param fragment 교체할 Fragment
     */
    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.relativelayout_main_container, fragment)
                .commit();
    }

}
