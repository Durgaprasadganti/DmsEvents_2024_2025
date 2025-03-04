package com.digitalminds.dmssevent;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.digitalminds.dmssevent.adapters.EventBannersAdapter;
import com.digitalminds.dmssevent.common.DmsEventsAppController;
import com.digitalminds.dmssevent.common.DmsEventsBaseActivity;
import com.digitalminds.dmssevent.common.Utils;
import com.digitalminds.dmssevent.interfaces.AdapterCallBack;
import com.digitalminds.dmssevent.interfaces.WebServiceResponseCallBack;
import com.digitalminds.dmssevent.models.PhotoDetailsModel;

import java.util.ArrayList;

/**
 * Created by sandeep.kumar on 30-03-2017.
 */
public class EventBanners extends DmsEventsBaseActivity implements WebServiceResponseCallBack{
    GridView eventsGridView;
    String[] imageUrls={"https://s17.postimg.org/4zkwr1cm7/1-05.jpg","https://s9.postimg.org/3s87m7pfz/firstimage.jpg","https://s21.postimg.org/ysopjyhef/banner1.png","https://s8.postimg.org/jwwwxtb9x/banner2.png","https://s16.postimg.org/7747p1ulx/banner3.png"};
    int[] images={R.drawable.banner1,R.drawable.banner2,R.drawable.banner3};
    String[] title={"Awards","MileStone 2017 Skits","Awards","Dances","Dances"};
    androidx.appcompat.app.ActionBar actionBar;
    ArrayList<PhotoDetailsModel> arrayListPhotoDetails=new ArrayList<PhotoDetailsModel>();
    DmsEventsAppController controller;
    int viewDimensions, requestCode = 222;
    AdapterCallBack adapterCallBack;
   int selectedGridItemPosition;
    LinearLayout emptyElement;
    TextView retryTextView;
    Toolbar toolbar;
    TextView toolbar_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbanners);
        //actionBarSettings();
        toolbar = (Toolbar) findViewById(R.id.toolbarGamesList);
        toolbar_title=(TextView)toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText("Milestone-2017-Schedule");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        initializeUIElements();
    }

    @Override
    public void initializeUIElements() {
        controller = (DmsEventsAppController) getApplicationContext();
        eventsGridView=(GridView)findViewById(R.id.eventsGridView);
        emptyElement=(LinearLayout)findViewById(R.id.emptyElement);
        retryTextView=(TextView)findViewById(R.id.retryTextView);
        if (Utils.isNetworkAvailable(EventBanners.this)) {
            emptyElement.setVisibility(View.GONE);
            for (int i = 0; i < title.length; i++) {
                PhotoDetailsModel photoDetailsModel = new PhotoDetailsModel(title[i], imageUrls[i]);
                arrayListPhotoDetails.add(photoDetailsModel);
            }
            eventsGridView.setAdapter(new EventBannersAdapter(EventBanners.this, arrayListPhotoDetails));
            eventsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    controller.setPhotoDetailsModel(arrayListPhotoDetails.get(position));
                    selectedGridItemPosition = position;

                    Intent intent = new Intent(EventBanners.this, BannerGalleryActivity.class);
                    BannerGalleryActivity.photoDetailsModels = arrayListPhotoDetails;
                    BannerGalleryActivity.bannerPosition = selectedGridItemPosition;
                    startActivityForResult(intent, requestCode);
                }
            });
        }else{
            emptyElement.setVisibility(View.VISIBLE);
            retryTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EventBanners.this, EventBanners.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void actionBarSettings() {
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_action_bar, null);
        actionBar.setCustomView(view, new androidx.appcompat.app.ActionBar.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT));
        Toolbar parent = (Toolbar) view.getParent();
        parent.setContentInsetsAbsolute(0, 0);
        actionBar.setDisplayOptions(androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM | androidx.appcompat.app.ActionBar.DISPLAY_SHOW_HOME);
        TextView actionBarHeadingTextView = (TextView) view.findViewById(R.id.actionBarHeadingTextView);
        ImageView actionBarBackImageView = (ImageView) view.findViewById(R.id.actionBarBackImageView);
        actionBarBackImageView.setVisibility(View.VISIBLE);
        actionBarHeadingTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        actionBarHeadingTextView.setText("Milestone-2017-Schedule");
        actionBarBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onServiceCallSuccess(String result) {

    }

    @Override
    public void onServiceCallFail(String error) {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
