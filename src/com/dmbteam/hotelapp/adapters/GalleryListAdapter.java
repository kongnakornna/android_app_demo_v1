package com.dmbteam.hotelapp.adapters;

import java.util.ArrayList;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmbteam.hotelapp.ApplicationContext;
import com.dmbteam.hotelapp.R;
import com.dmbteam.hotelapp.fragments.GalleryFragment.ActionListener;
import com.dmbteam.hotelapp.models.PreviewPictureItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * The adapter for the GalleryList
 * 
 */
public class GalleryListAdapter extends ArrayAdapter<PreviewPictureItem> {

	private ApplicationContext mContext;

	private ImageLoader mImageLoader;
	private DisplayImageOptions mImageOptions;

	private ActionListener mActionListener;

	/**
	 * 
	 * @param context
	 *            the application context
	 * @param items
	 *            gallery PreviePicture items to show
	 * @param mActionListener
	 */
	public GalleryListAdapter(ApplicationContext context,
			ArrayList<PreviewPictureItem> items, ActionListener actionListener) {
		super(context, 0, items);
		mContext = context;

		mImageLoader = ImageLoader.getInstance();

		mImageOptions = com.dmbteam.hotelapp.MainActivity.buildImageOptions(
				ImageScaleType.IN_SAMPLE_INT, true, true, 0, 0,
				R.drawable.action_bar_hotel_logo);

		mActionListener = actionListener;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		convertView = LayoutInflater.from(mContext).inflate(
				R.layout.gallery_list_item, null);

		ImageView pictureImage = (ImageView) convertView
				.findViewById(R.id.galleryItemImageView);

		TextView pictureDescription = (TextView) convertView
				.findViewById(R.id.galleryItemTextView);

		PreviewPictureItem item = getItem(position);

		String uri = "drawable://"
				+ mContext.getResources().getIdentifier(
						item.getPreviewPictureUri(), "drawable",
						mContext.getPackageName());

		mImageLoader.displayImage(uri, pictureImage, mImageOptions);

		pictureDescription.setText(item.getPreviewPictureTitleText());

		convertView.setTag(position);

		convertView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mActionListener != null) {

					mActionListener.onGalleryItemClicked((Integer) v.getTag());
				}
			}
		});

		return convertView;
	}
}
