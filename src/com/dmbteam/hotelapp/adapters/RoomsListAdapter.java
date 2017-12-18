package com.dmbteam.hotelapp.adapters;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmbteam.hotelapp.ApplicationContext;
import com.dmbteam.hotelapp.MainActivity;
import com.dmbteam.hotelapp.R;
import com.dmbteam.hotelapp.fragments.RoomsFragment;
import com.dmbteam.hotelapp.models.RoomItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * 
 * the adapter for the Rooms Fragment
 */
public class RoomsListAdapter extends ArrayAdapter<RoomItem> {

	private ApplicationContext mContext;

	private RoomsFragment.ActionListener mActionListener;

	private ImageLoader mImageLoader;
	private DisplayImageOptions mImageOptions;

	/**
	 * 
	 * @param context
	 *            the application context
	 * @param items
	 *            the Room items to show
	 * @param listener
	 *            action listener called when check availability is clicked
	 */
	public RoomsListAdapter(ApplicationContext context,
			ArrayList<RoomItem> items, RoomsFragment.ActionListener listener) {
		super(context, 0, items);
		mContext = context;
		mActionListener = listener;

		mImageLoader = ImageLoader.getInstance();
		mImageOptions = com.dmbteam.hotelapp.MainActivity.buildImageOptions(
				ImageScaleType.IN_SAMPLE_INT, true, true,
				MainActivity.dipsToPixels(mContext, 5), 0, 0);

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		convertView = LayoutInflater.from(mContext).inflate(
				R.layout.rooms_list_item, null);

		if (position % 2 > 0) {
			convertView.setBackgroundColor(mContext.getResources().getColor(
					R.color.drawer_grey_light));
		} else {
			convertView.setBackgroundColor(mContext.getResources().getColor(
					R.color.drawer_grey));
		}

		RoomItem item = getItem(position);

		TextView title = (TextView) convertView
				.findViewById(R.id.roomsListTitleTextView);
		title.setText(item.getRoomTitle());

		TextView description = (TextView) convertView
				.findViewById(R.id.roomsListDescriptionTextView);
		description.setText(item.getRoomDescription());

		ImageView image = (ImageView) convertView
				.findViewById(R.id.roomsListItemImageView);

		Button checkAvailabilityButton = (Button) convertView
				.findViewById(R.id.roomsListAvailabilityButton);
		checkAvailabilityButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mActionListener.onCheckAvailabilityClicked();
			}
		});

		String uri = "drawable://"
				+ mContext.getResources().getIdentifier(item.getRoomPicture(),
						"drawable", mContext.getPackageName());

		mImageLoader.displayImage(uri, image, mImageOptions);

		return convertView;
	}
}
