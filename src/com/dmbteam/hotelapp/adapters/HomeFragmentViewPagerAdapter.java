package com.dmbteam.hotelapp.adapters;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmbteam.hotelapp.ApplicationContext;
import com.dmbteam.hotelapp.R;
import com.dmbteam.hotelapp.models.PreviewPictureItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * 
 * The ViewPagerAdapter used for the HomeFragment ViewPager
 * 
 */
public class HomeFragmentViewPagerAdapter extends PagerAdapter {

	private LayoutInflater mLayoutInflater;

	private ImageLoader mImageLoader;
	private DisplayImageOptions mImageOptions;

	private ArrayList<PreviewPictureItem> mItems;

	private ApplicationContext mAppContext;

	/**
	 * 
	 * @param context
	 *            the application context
	 * @param itemsParam
	 *            ViewPager PreviewPicture items
	 */
	public HomeFragmentViewPagerAdapter(ApplicationContext context,
			ArrayList<PreviewPictureItem> itemsParam) {
		mLayoutInflater = LayoutInflater.from(context);
		mImageLoader = ImageLoader.getInstance();
		mImageOptions = com.dmbteam.hotelapp.MainActivity.buildImageOptions(
				ImageScaleType.IN_SAMPLE_INT, true, true, 0, 0, 0);
		mAppContext = context;
		mItems = itemsParam;
	}

	@Override
	public Object instantiateItem(View collection, int position) {
		PreviewPictureItem item = mItems.get(position);

		View convertView = mLayoutInflater.inflate(
				R.layout.fragment_main_view_pager_adapter, null);
		ImageView imageView = (ImageView) convertView
				.findViewById(R.id.viewPagerItemImageView);
		TextView textView = (TextView) convertView
				.findViewById(R.id.viewPagerItemTitleTextView);

		String uri = "drawable://"
				+ mAppContext.getResources().getIdentifier(
						item.getPreviewPictureUri(), "drawable",
						mAppContext.getPackageName());

		mImageLoader.displayImage(uri, imageView, mImageOptions);

		textView.setText(item.getPreviewPictureTitleText());

		((ViewPager) collection).addView(convertView, 0);

		return convertView;

	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager) arg0).removeView((View) arg2);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == ((View) arg1);
	}

	@Override
	public int getCount() {
		return mItems.size();
	}

}
