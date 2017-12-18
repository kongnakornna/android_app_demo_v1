package com.dmbteam.hotelapp.adapters;

import java.util.ArrayList;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmbteam.hotelapp.ApplicationContext;
import com.dmbteam.hotelapp.R;
import com.dmbteam.hotelapp.fragments.ReservationFragment;
import com.dmbteam.hotelapp.models.HotelReservationContactItem;

/**
 * The adapter used for the Reservation Fragment
 * 
 */
public class ReservationListItemAdapter extends
		ArrayAdapter<HotelReservationContactItem> {

	private ApplicationContext mContext;

	private ReservationFragment.ActionListener mActionListener;

	/**
	 * 
	 * @param context
	 *            the application context
	 * @param items
	 *            the Reservation Contact items to show
	 * @param listener
	 *            action listener called when an item is clicked
	 */
	public ReservationListItemAdapter(ApplicationContext context,
			ArrayList<HotelReservationContactItem> items,
			ReservationFragment.ActionListener listener) {
		super(context, 0, items);
		mContext = context;
		mActionListener = listener;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		convertView = LayoutInflater.from(mContext).inflate(
				R.layout.reservation_list_item, null);

		HotelReservationContactItem item = getItem(position);

		View whiteArrow = convertView
				.findViewById(R.id.reservationListWhiteArrow);
		if (position == 0) {
			whiteArrow.setVisibility(View.VISIBLE);
		} else {
			whiteArrow.setVisibility(View.GONE);
		}

		TextView email = (TextView) convertView
				.findViewById(R.id.reservationListEmailTextView);
		email.setText(item.getContactEmail());

		TextView phone = (TextView) convertView
				.findViewById(R.id.reservationListPhoneTextView);
		phone.setText(item.getContactPhone());

		TextView phoneDescription = (TextView) convertView
				.findViewById(R.id.reservationListPhoneDescriptionTextView);
		phoneDescription.setText(item.getContactDescription());

		TextView emailDescription = (TextView) convertView
				.findViewById(R.id.reservationListEmailDescriptionTextView);
		emailDescription.setText(item.getContactDescription());

		RelativeLayout phoneContainer = (RelativeLayout) convertView
				.findViewById(R.id.reservationListPhoneContainer);
		phoneContainer.setTag(item.getContactPhone());
		phoneContainer.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mActionListener.onPhoneChosen((String) v.getTag());

			}
		});

		RelativeLayout emailContainer = (RelativeLayout) convertView
				.findViewById(R.id.reservationListEmailContainer);
		emailContainer.setTag(item.getContactEmail());
		emailContainer.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mActionListener.onEmailChosen((String) v.getTag());

			}
		});

		return convertView;
	}
}
