package com.dmbteam.hotelapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dmbteam.hotelapp.ApplicationContext;
import com.dmbteam.hotelapp.R;
import com.dmbteam.hotelapp.adapters.RatesListAdapter;
import com.dmbteam.hotelapp.models.RatesPageSettings;

public class RatesFragment extends Fragment {

	public static final String TAG = RatesFragment.class.getSimpleName();

	private ListView mRatesList;

	private ApplicationContext mAppContext;

	public static RatesFragment newInstance(ApplicationContext appContext) {

		RatesFragment fragment = new RatesFragment();
		fragment.mAppContext = appContext;

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

		View view = inflater.inflate(R.layout.fragment_rates, container, false);

		RatesPageSettings ratesPageSettings = mAppContext
				.getParsedApplicationSettings().getRatesPageSettings();

		mRatesList = (ListView) view.findViewById(R.id.ratesFragmentListView);

		mRatesList.setAdapter(new RatesListAdapter(mAppContext,
				ratesPageSettings.getRoomRatesList()));

		return view;
	}
}
