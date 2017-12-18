package com.dmbteam.hotelapp.fragments;

import java.util.Locale;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.dmbteam.hotelapp.ApplicationContext;
import com.dmbteam.hotelapp.R;
import com.dmbteam.hotelapp.models.LocationsPageSettings;

public class LocationsFragment extends android.support.v4.app.Fragment {

	public static final String TAG = LocationsFragment.class.getSimpleName();
	public static final String MAPS_ENDPOINT = "http://maps.google.com/maps?q=";

	private ApplicationContext mAppContext;

	public static LocationsFragment newInstance(ApplicationContext appContext) {

		LocationsFragment fragment = new LocationsFragment();
		fragment.mAppContext = appContext;
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_location, container,
				false);

		WebView mWebView = (WebView) view
				.findViewById(R.id.locationsFragmentWebView);
		mWebView.getSettings().setJavaScriptEnabled(true);
		// mWebView.getSettings().setPluginsEnabled(true);
		mWebView.getSettings().setSupportZoom(true);
		mWebView.getSettings().setBuiltInZoomControls(true);
		mWebView.setWebViewClient(new WebViewClient());

		LocationsPageSettings pageSettings = mAppContext
				.getParsedApplicationSettings().getLocationsPageSettings();

		final String longitude = pageSettings.getMainHotelItem()
				.getHotelLocation().getHotelLocationLongtitude();
		final String latitude = pageSettings.getMainHotelItem()
				.getHotelLocation().getHotelLocationLatitude();

		String url = "http://maps.googleapis.com/maps/api/staticmap?&zoom=14&size=2048x2048&maptype=roadmap&markers=color:red%7Ccolor:red%7Clabel:C%7C"
				+ latitude + "," + longitude + "&sensor=false";

		mWebView.loadUrl(url);

		Button btnOpenMaps = (Button) view
				.findViewById(R.id.fra_location_btn_open_maps);

		btnOpenMaps.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					String uri = String.format(Locale.ENGLISH,
							"geo:%f,%f?z=14", Float.valueOf(latitude),
							Float.valueOf(longitude));
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri
							.parse(uri));
					getActivity().startActivity(intent);
				} catch (Exception e) {
					Toast.makeText(getActivity(), "asdasda", Toast.LENGTH_LONG)
							.show();
				}
			}
		});

		return view;
	}
}
