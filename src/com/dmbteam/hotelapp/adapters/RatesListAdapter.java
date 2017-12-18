package com.dmbteam.hotelapp.adapters;

import java.util.ArrayList;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmbteam.hotelapp.ApplicationContext;
import com.dmbteam.hotelapp.MainActivity;
import com.dmbteam.hotelapp.R;
import com.dmbteam.hotelapp.fragments.RatesFragment;
import com.dmbteam.hotelapp.fragments.ReservationFragment;
import com.dmbteam.hotelapp.models.HotelReservationContactItem;
import com.dmbteam.hotelapp.models.RatesPageSettings;
import com.dmbteam.hotelapp.models.RoomRatesItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * The adapter used for the Rates Fragment
 * 
 */
public class RatesListAdapter extends ArrayAdapter<RoomRatesItem> {

	private ApplicationContext mContext;

	private ImageLoader mImageLoader;
	private DisplayImageOptions mImageOptions;

	/**
	 * 
	 * @param context
	 *            the application context
	 * @param items
	 *            the RoomRates items to show
	 */
	public RatesListAdapter(ApplicationContext context,
			ArrayList<RoomRatesItem> items) {
		super(context, 0, items);
		mContext = context;

		mImageLoader = ImageLoader.getInstance();
		mImageOptions = com.dmbteam.hotelapp.MainActivity.buildImageOptions(
				ImageScaleType.IN_SAMPLE_INT, true, true,
				MainActivity.dipsToPixels(mContext, 5), 0,
				R.drawable.action_bar_hotel_logo);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		convertView = LayoutInflater.from(mContext).inflate(
				R.layout.rates_list_item, null);

		if (position % 2 > 0) {
			convertView.setBackgroundColor(mContext.getResources().getColor(
					R.color.drawer_grey_light));
		} else {
			convertView.setBackgroundColor(mContext.getResources().getColor(
					R.color.drawer_grey));
		}

		RoomRatesItem item = getItem(position);

		TextView title = (TextView) convertView
				.findViewById(R.id.ratesListTitleTextView);
		title.setText(item.getRoomTitle());

		TextView description = (TextView) convertView
				.findViewById(R.id.ratesListDescriptionTextView);
		description.setText(item.getRoomDescription());

		TextView price = (TextView) convertView
				.findViewById(R.id.ratesListRatesTextView);
		price.setText(item.getRoomPrice());

		ImageView image = (ImageView) convertView
				.findViewById(R.id.ratesListItemImageView);

		String uri = "drawable://"
				+ mContext.getResources().getIdentifier(item.getRoomPicture(),
						"drawable", mContext.getPackageName());

		mImageLoader.displayImage(uri, image, mImageOptions);

		return convertView;
	}
}
