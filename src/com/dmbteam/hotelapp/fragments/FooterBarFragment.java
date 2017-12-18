package com.dmbteam.hotelapp.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dmbteam.hotelapp.ApplicationContext;
import com.dmbteam.hotelapp.R;
import com.dmbteam.hotelapp.models.CommonApplicationSettings;

public class FooterBarFragment extends Fragment {

	public static final String TAG = FooterBarFragment.class.getSimpleName();

	private ApplicationContext mAppContext;

	public static FooterBarFragment newInstance(ApplicationContext appContext) {

		FooterBarFragment fragment = new FooterBarFragment();
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
		View view = inflater.inflate(R.layout.fragment_social_connections,
				container, false);

		final CommonApplicationSettings commonSettings = mAppContext
				.getParsedApplicationSettings().getCommonAppSettings();

		ImageView facebook = (ImageView) view.findViewById(R.id.footerFacebook);
		facebook.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(commonSettings.getHotelFacebookPage()));
				startActivity(intent);
			}
		});
		ImageView twitter = (ImageView) view.findViewById(R.id.footerTwitter);
		twitter.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(commonSettings.getHotelTwitterPage()));
				startActivity(intent);
			}
		});

		ImageView tripAdvisor = (ImageView) view
				.findViewById(R.id.footerTripAdvisor);
		tripAdvisor.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(commonSettings
						.getHotelTripAdvisorPage()));
				startActivity(intent);
			}
		});

		return view;
	}

}
