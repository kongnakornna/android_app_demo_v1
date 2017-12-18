package com.dmbteam.hotelapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.dmbteam.hotelapp.Utils.HotelXMLTagConstants;
import com.dmbteam.hotelapp.Utils.SettingsXMLParser;

/**
 * Used to obtain the XML file either from URL or local assets folder
 * 
 */
public class ObtainApplicationDataActivity extends FragmentActivity {

	private boolean shouldShowToastMessage;

	private static final String TAG = ObtainApplicationDataActivity.class
			.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RetrieveXMLData task = new RetrieveXMLData();
		task.execute();
	}

	private boolean isNetworkAvailable() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			// There are no active networks.
			return false;
		} else
			return true;
	}

	/**
	 * Async task to load the XML file input stream to the parser
	 * 
	 */
	class RetrieveXMLData extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {

			AssetManager assetManager = getAssets();
			InputStream inputStream = null;
			try {
				if (HotelXMLTagConstants.TAG_URL_SETTINGS == null
						|| HotelXMLTagConstants.TAG_URL_SETTINGS.length() == 0) {
					inputStream = assetManager.open("hotel_settings.xml");

				} else {
					if (isNetworkAvailable()) {
						URL url = new URL(HotelXMLTagConstants.TAG_URL_SETTINGS);
						inputStream = url.openStream();
					} else {
						shouldShowToastMessage = true;
					}
				}

				((ApplicationContext) getApplicationContext())
						.setParsedApplicationSettings(SettingsXMLParser
								.parse(inputStream));
			} catch (IOException e) {
				Log.e(TAG, e.getMessage());
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (shouldShowToastMessage) {
				Toast.makeText(ObtainApplicationDataActivity.this,
						getString(R.string.no_internet_connectivity),
						Toast.LENGTH_LONG).show();

				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						try {
							ObtainApplicationDataActivity.this.finish();
						} catch (Exception e) {

						}
					}
				}, 3000);

			} else {
				finish();
				Intent intent = new Intent(ObtainApplicationDataActivity.this,
						MainActivity.class);
				startActivity(intent);
			}
		}
	}
}
