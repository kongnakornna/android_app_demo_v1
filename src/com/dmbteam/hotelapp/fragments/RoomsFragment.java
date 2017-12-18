package com.dmbteam.hotelapp.fragments;

import java.io.Serializable;
import java.util.EnumSet;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dmbteam.hotelapp.ApplicationContext;
import com.dmbteam.hotelapp.R;
import com.dmbteam.hotelapp.adapters.RoomsListAdapter;
import com.dmbteam.hotelapp.models.RoomsPageSettings;

public class RoomsFragment extends Fragment {

	public static final String TAG = RoomsFragment.class.getSimpleName();

	public interface ActionListener {
		void onCheckAvailabilityClicked();
	}

	private ListView mRatesList;

	private ActionListener mActionListener;

	private ApplicationContext mAppContext;

	public static RoomsFragment newInstance(ApplicationContext appContext,
			ActionListener actionListener) {

		if (actionListener == null) {
			throw new IllegalArgumentException(ActionListener.class.getName()
					+ " is null!");
		}

		RoomsFragment fragment = new RoomsFragment();
		fragment.mActionListener = actionListener;
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

		RoomsPageSettings roomsPageSettings = mAppContext
				.getParsedApplicationSettings().getRoomsPageSettings();

		mRatesList = (ListView) view.findViewById(R.id.ratesFragmentListView);

		mRatesList.setAdapter(new RoomsListAdapter(mAppContext,
				roomsPageSettings.getRoomsList(), mActionListener));

		return view;
	}
}
