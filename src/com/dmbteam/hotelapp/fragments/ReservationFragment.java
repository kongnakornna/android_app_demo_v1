package com.dmbteam.hotelapp.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.dmbteam.hotelapp.ApplicationContext;
import com.dmbteam.hotelapp.R;
import com.dmbteam.hotelapp.adapters.ReservationListItemAdapter;
import com.dmbteam.hotelapp.models.ReservationPageSettings;

public class ReservationFragment extends Fragment {

	public static final String TAG = ReservationFragment.class.getSimpleName();

	public interface ActionListener {

		void onEmailChosen(String email);

		void onPhoneChosen(String phone);
		
		void onReservationFormChosen();
	}

	private ImageView mReservationHotelLogoImageView;
	private ListView mReservationContactsList;

	private ActionListener mActionListener;

	private ApplicationContext mAppContext;

	public static ReservationFragment newInstance(
			ApplicationContext appContext, ActionListener actionListener) {

		if (actionListener == null) {
			throw new IllegalArgumentException(ActionListener.class.getName()
					+ " is null!");
		}

		ReservationFragment fragment = new ReservationFragment();
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

		View view = inflater.inflate(R.layout.fragment_reservation, container,
				false);

		ReservationPageSettings reservationPageSettings = mAppContext
				.getParsedApplicationSettings().getReservationPageSettings();

		mReservationContactsList = (ListView) view
				.findViewById(R.id.reservationFragmentListView);

		View footerView = inflater.inflate(R.layout.book_online, container,
				false);
		footerView
				.setTag(reservationPageSettings.getReservationBookOnlineURL());
		footerView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (v.getTag() != null && !((String) v.getTag()).trim().isEmpty()) {
					try {
						Intent intent = new Intent(Intent.ACTION_VIEW);
						intent.setData(Uri.parse((String) v.getTag()));
						startActivity(intent);
					} catch (Exception e) {
						Toast.makeText(
								mAppContext,
								mAppContext
										.getString(R.string.book_online_no_address),
								Toast.LENGTH_SHORT).show();
					}
				} else {
					mActionListener.onReservationFormChosen();
				}
			}
		});
		footerView.setLayoutParams(new AbsListView.LayoutParams(
				AbsListView.LayoutParams.MATCH_PARENT,
				AbsListView.LayoutParams.WRAP_CONTENT));

		mReservationContactsList.addFooterView(footerView);

		mReservationContactsList.setAdapter(new ReservationListItemAdapter(
				mAppContext, reservationPageSettings
						.getReservationContactsList(), mActionListener));

		mReservationHotelLogoImageView = (ImageView) view
				.findViewById(R.id.reservationFragmentTopLogoImageView);

		return view;
	}
}
