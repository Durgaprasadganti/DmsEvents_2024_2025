package com.digitalminds.dmssevent.adapters;

import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitalminds.dmssevent.R;
import com.digitalminds.dmssevent.common.TouchImageView;
import com.digitalminds.dmssevent.models.PhotoDetailsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by jaya.krishna on 17-Mar-17.
 */
public class BannerGalleryViewPagerAdapter extends PagerAdapter {

    ArrayList<PhotoDetailsModel> photoDetailsModels = new ArrayList<PhotoDetailsModel>();
    Context context;

    public BannerGalleryViewPagerAdapter(ArrayList<PhotoDetailsModel> photoDetailsModels, Context context) {
        this.photoDetailsModels = photoDetailsModels;
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.gallery_view_pager_item, collection, false);
        final TouchImageView galleryViewPagerImageZoom = (TouchImageView) rootView.findViewById(R.id.galleryViewPagerImageZoom);
        galleryViewPagerImageZoom.bringToFront();
        final TextView loadingTextView = (TextView) rootView.findViewById(R.id.loadingTextView);
            Picasso.with(context).load(photoDetailsModels.get(position).getImageURL()).into(galleryViewPagerImageZoom, new com.squareup.picasso.Callback() {

                @Override
                public void onSuccess() {
                    /// mainHolder.loadingTextView.setVisibility(View.GONE);
                }

                @Override
                public void onError() {
                    loadingTextView.setVisibility(View.VISIBLE);
                    loadingTextView.setText("Error in loading.");

                }

            });
            galleryViewPagerImageZoom.setVisibility(View.VISIBLE);

        collection.addView(rootView);
        return rootView;
    }


    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return photoDetailsModels.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

    @Override
    public float getPageWidth(int position) {
        float test = super.getPageWidth(position);
        return test;
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
