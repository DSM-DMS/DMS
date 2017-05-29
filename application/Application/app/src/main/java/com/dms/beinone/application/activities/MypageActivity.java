package com.dms.beinone.application.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dms.beinone.application.R;
import com.dms.beinone.application.models.Account;
import com.dms.beinone.application.utils.JSONParser;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.HttpBoxCallback;
import com.dms.boxfox.networking.MultipartUtility;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by BeINone on 2017-02-20.
 */

public class MypageActivity extends AppCompatActivity {

    private static final int ACTIVITY_REQUEST_PICK_IMAGE = 11;
    private static final int PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 21;

    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private ImageView mProfileIV;
    private TextView mNameTV;
    private TextView mMeritTV;
    private TextView mDemeritTV;
    private TextView mTotalTV;
    private TextView mExtensionApplyStatusTV;

    private File mProfileImageFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mypage);

        init();
    }

    /**
     * 초기화
     */
    private void init() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_mypage);
        mProfileIV = (ImageView) findViewById(R.id.iv_mypage_profile);
        mNameTV = (TextView) findViewById(R.id.tv_mypage_name);
        mMeritTV = (TextView) findViewById(R.id.tv_mypage_merit);
        mDemeritTV = (TextView) findViewById(R.id.tv_mypage_demerit);
        mTotalTV = (TextView) findViewById(R.id.tv_mypage_total);
        mExtensionApplyStatusTV = (TextView) findViewById(R.id.tv_mypage_extensionapply_status);

        setSupportActionBar(mToolbar);
        // display back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final CollapsingToolbarLayout collapsingToolbarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout_mypage);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout_mypage);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("조성빈");
                } else {
                    collapsingToolbarLayout.setTitle(" ");
                }
            }
        });

        mProfileIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MypageActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    if (ActivityCompat.shouldShowRequestPermissionRationale(MypageActivity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        Toast.makeText(MypageActivity.this, "프로필 사진을 변경하기 위해서는 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
                        ActivityCompat.requestPermissions(MypageActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    } else {
                        ActivityCompat.requestPermissions(MypageActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                } else {
                    selectProfileImage();
                }
            }
        });

        try {
            loadMypage();
        } catch (IOException e) {
            System.out.println("IOException in MypageActivity: loadMyPage()");
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case ACTIVITY_REQUEST_PICK_IMAGE:
                if (resultCode == RESULT_OK && data != null && data.getData() != null) {
                    Uri uri = data.getData();
//                    uploadImage(new File(getRealPathFromURI(uri)));
                    try {
                        Bitmap image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                        Uri tempUri = getImageUri(getApplicationContext(), image_bitmap);
                        File imageFile = new File(getRealPathFromURI(tempUri));
                        uploadImage(imageFile);
                    } catch (IOException e) {
                        System.out.println("IOException in MypageActivity");
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    selectProfileImage();
                } else {
                    Toast.makeText(this, "프로필 사진을 변경하기 위해서는 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private void selectProfileImage() {
        Intent intent = new Intent();
        // Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        // Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Image"), ACTIVITY_REQUEST_PICK_IMAGE);
    }

    private void bind(Account account) {
        mNameTV.setText(account.getName());
        mMeritTV.setText(String.valueOf(account.getMerit()));
        mDemeritTV.setText(String.valueOf(account.getDemerit()));
        mTotalTV.setText(String.valueOf(account.getMerit() - account.getDemerit()));
        if (account.getRoom() == -1) {
            // not applied
            mExtensionApplyStatusTV.setText(R.string.mypage_notapplied);
            mExtensionApplyStatusTV.setTextColor(ContextCompat.getColor(this, R.color.negative));
        } else {
            // applied
            mExtensionApplyStatusTV.setText(R.string.mypage_applied);
            mExtensionApplyStatusTV.setTextColor(ContextCompat.getColor(this, R.color.positive));
        }
//        if (account.getProfileImage() != null) {
//            Glide.with(this).load(account.getProfileImage()).centerCrop().into(mProfileIV);
//        }
    }

    private void loadMypage() throws IOException {
        HttpBox.get(MypageActivity.this, "/account/student")
                .push(new HttpBoxCallback() {
                    @Override
                    public void done(Response response) {
                        int code = response.getCode();
                        switch (code) {
                            case HttpBox.HTTP_OK:
                                try {
                                    Account account = JSONParser.parseStudentJSON(response.getJsonObject());
                                    bind(account);
                                } catch (JSONException e) {
                                    System.out.println("JSONException in MypageActivity: GET /account/student");
                                    e.printStackTrace();
                                }
                                break;
                            case HttpBox.HTTP_NO_CONTENT:
                                Toast.makeText(MypageActivity.this, R.string.mypage_load_no_content, Toast.LENGTH_SHORT).show();
                                break;
                            case HttpBox.HTTP_BAD_REQUEST:
                                Toast.makeText(MypageActivity.this, R.string.http_bad_request, Toast.LENGTH_SHORT).show();
                                break;
                            case HttpBox.HTTP_INTERNAL_SERVER_ERROR:
                                Toast.makeText(MypageActivity.this, R.string.mypage_load_internal_server_error, Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void err(Exception e) {
                        System.out.println("Error in LoginActivity: GET /account/student");
                        e.printStackTrace();
                    }
                });
        loadImage();
    }

    private void loadImage() throws IOException {
        SharedPreferences accountPrefs = getSharedPreferences(getString(R.string.PREFS_ACCOUNT), MODE_PRIVATE);
        String id = accountPrefs.getString(getString(R.string.PREFS_ACCOUNT_ID), null);
        if (id != null) {
            Glide.with(this).load(HttpBox.SERVER_URL + "/lookup/image/" + id).centerCrop().into(mProfileIV);
        }
    }

    private void uploadImage(final File uploadFile) {
        new AsyncTask<Void, Void, Object>() {
            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);

                if (o instanceof Exception) {
                    Log.d("testLog", "failed");
                    ((Exception) o).printStackTrace();
                } else {
                    setProfileImage(uploadFile);
                }
            }

            @Override
            protected Object doInBackground(Void... params) {
                try {
                    MultipartUtility multipart = new MultipartUtility(MypageActivity.this, HttpBox.SERVER_URL + "/upload/image", "UTF-8");
                    multipart.addFilePart("profile_image", uploadFile);
                    return multipart.finish();
                } catch (IOException e) {
                    System.out.println("IOException in MypageActivity: POST /upload/image");
                    e.printStackTrace();
                    return e;
                }
            }
        }.execute();
    }

    private void updateProfileImage() {
        Glide.with(this).load(mProfileImageFile).centerCrop().into(mProfileIV);
    }

    private void setProfileImage(File imageFile) {
        mProfileImageFile = imageFile;
        updateProfileImage();
    }

}
