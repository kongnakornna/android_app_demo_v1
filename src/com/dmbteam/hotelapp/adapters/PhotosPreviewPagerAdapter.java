package com.dmbteam.hotelapp.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.dmbteam.hotelapp.ApplicationContext;
import com.dmbteam.hotelapp.R;
import com.dmbteam.hotelapp.models.PreviewPictureItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class PhotosPreviewPagerAdapter extends PagerAdapter {

	private ArrayList<PreviewPictureItem> mItems = new ArrayList<PreviewPictureItem>(
			0);
	private ImageLoader mImageLoader;
	private DisplayImageOptions mImageOptions;
	private ApplicationContext mContext;

	public PhotosPreviewPagerAdapter(ApplicationContext context,
			ArrayList<PreviewPictureItem> mItems) {
		super();

		this.mItems = mItems;
		this.mContext = context;

		mImageLoader = ImageLoader.getInstance();

		mImageOptions = com.dmbteam.hotelapp.MainActivity.buildImageOptions(
				ImageScaleType.IN_SAMPLE_INT, true, true, 0, 0,
				R.drawable.action_bar_hotel_logo);
	}

	@Override
	public Object instantiateItem(View container, int position) {
		LayoutInflater inflater = (LayoutInflater) container.getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View view = inflater.inflate(R.layout.photos_preview_view_pager_item,
				null);

		ImageView imageView = (ImageView) view
				.findViewById(R.id.photosPreviewFragmentViewPagerItemImageView);

		ProgressBar progress = (ProgressBar) view
				.findViewById(R.id.photosPreviewFragmentProgressBar);

		String uri = "drawable://"
				+ mContext.getResources().getIdentifier(
						mItems.get(position).getPreviewPictureUri(),
						"drawable", mContext.getPackageName());

		mImageLoader.displayImage(uri, imageView, mImageOptions,
				new ViewPagerImageLoadingListener(progress));

		((ViewPager) container).addView(view, 0);

		return view;

	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager) arg0).removeView((View) arg2);
	}

	@Override
	public int getCount() {
		return mItems.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg1 == ((View) arg0);
	}

	private class ViewPagerImageLoadingListener implements ImageLoadingListener {

		ProgressBar progressBar;

		public ViewPagerImageLoadingListener(ProgressBar progressBarParam) {
			progressBar = progressBarParam;
		}

		@Override
		public void onLoadingStarted(String imageUri, View view) {

		}

		@Override
		public void onLoadingFailed(String imageUri, View view,
				FailReason failReason) {

		}

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			progressBar.setVisibility(View.GONE);
		}

		@Override
		public void onLoadingCancelled(String imageUri, View view) {
			progressBar.setVisibility(View.GONE);
		}

	}
}
