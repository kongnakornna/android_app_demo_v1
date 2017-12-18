package com.dmbteam.hotelapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmbteam.hotelapp.ApplicationContext;
import com.dmbteam.hotelapp.R;
import com.dmbteam.hotelapp.adapters.PhotosPreviewPagerAdapter;

public class PhotosPreviewFragment extends Fragment {

	public static final String TAG = PhotosPreviewFragment.class
			.getSimpleName();

	private int position;

	private ViewPager mViewPager;

	private ApplicationContext mContext;

	public static PhotosPreviewFragment newInstance(ApplicationContext context,
			int position) {

		PhotosPreviewFragment fragment = new PhotosPreviewFragment();
		fragment.position = position;
		fragment.mContext = context;

		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_photo_preview,
				container, false);

		mViewPager = (ViewPager) view.findViewById(R.id.photosPreviewViewPager);
		mViewPager.setAdapter(new PhotosPreviewPagerAdapter(mContext, mContext
				.getParsedApplicationSettings().getGalleryPageSettings()
				.getGalleryItemsList()));
		if (position < mViewPager.getAdapter().getCount()) {
			mViewPager.setCurrentItem(position);
		} else {
			mViewPager.setCurrentItem(0);
		}
		return view;
	}
}
