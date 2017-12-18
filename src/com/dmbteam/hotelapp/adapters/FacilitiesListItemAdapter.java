package com.dmbteam.hotelapp.adapters;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dmbteam.hotelapp.ApplicationContext;
import com.dmbteam.hotelapp.R;
import com.dmbteam.hotelapp.models.FacilityItem;

/**
 * The adapter for the facilities list
 * 
 */
public class FacilitiesListItemAdapter extends ArrayAdapter<FacilityItem> {

	private ApplicationContext mContext;

	/**
	 * 
	 * @param context
	 *            the application context
	 * @param items
	 *            the FacilityItems to show
	 */
	public FacilitiesListItemAdapter(ApplicationContext context,
			ArrayList<FacilityItem> items) {
		super(context, 0, items);
		mContext = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(mContext).inflate(
				R.layout.fragment_facilities_item, null);
		TextView textView = (TextView) convertView
				.findViewById(R.id.facilitiesTextView);
		FacilityItem item = getItem(position);
		textView.setText(item.getFacilityName());
		return convertView;
	}
}
