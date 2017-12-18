package com.dmbteam.hotelapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dmbteam.hotelapp.ApplicationContext;
import com.dmbteam.hotelapp.R;
import com.dmbteam.hotelapp.adapters.FacilitiesListItemAdapter;
import com.dmbteam.hotelapp.models.FacilitiesPageSettings;

public class FacilitiesFragment extends ListFragment {

	public static final String TAG = FacilitiesFragment.class.getSimpleName();

	private ApplicationContext mContext;
	private FacilitiesPageSettings mFacilities;

	public static FacilitiesFragment newInstance(ApplicationContext context) {
		FacilitiesFragment fragment = new FacilitiesFragment();
		fragment.mContext = context;
		fragment.mFacilities = context.getParsedApplicationSettings()
				.getFacilitiesPageSettings();
		return fragment;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_facilities, container,
				false);

		ListView listView = (ListView) view.findViewById(android.R.id.list);
		listView.setAdapter(new FacilitiesListItemAdapter(mContext, mFacilities
				.getFacilitiesList()));

		return view;
	}
}
