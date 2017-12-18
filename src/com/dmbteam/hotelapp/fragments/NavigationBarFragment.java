package com.dmbteam.hotelapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmbteam.hotelapp.ApplicationContext;
import com.dmbteam.hotelapp.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class NavigationBarFragment extends Fragment {

	public static final String TAG = NavigationBarFragment.class
			.getSimpleName();

	public interface ActionListener {

		void onMenuClicked();

		void onEmailClicked();

		void onBackPressed();

	}

	private ActionListener mActionListener;

	private ApplicationContext mAppContext;

	private String mTitleText;

	private boolean isShowBack;

	public static NavigationBarFragment newInstance(
			ApplicationContext appContext, ActionListener actionListener, boolean showBack) {

		if (actionListener == null) {
			throw new IllegalArgumentException(ActionListener.class.getName()
					+ " is null!");
		}

		NavigationBarFragment fragment = new NavigationBarFragment();
		fragment.mActionListener = actionListener;
		fragment.mAppContext = appContext;
		fragment.isShowBack = showBack;
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
		View view = inflater.inflate(R.layout.fragment_navigation_bar,
				container, false);

		ImageView menuImage = (ImageView) view
				.findViewById(R.id.navigationBarMenu);
		if (isShowBack) {
			menuImage.setImageResource(R.drawable.action_bar_back);
			menuImage.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mActionListener.onBackPressed();
				}

			});
		} else {
			menuImage.setImageResource(R.drawable.action_bar_sliding_menu);
			menuImage.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mActionListener.onMenuClicked();
				}
			});

		}

		ImageView emailImage = (ImageView) view
				.findViewById(R.id.navigationBarEmail);
		emailImage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mActionListener.onEmailClicked();
			}
		});
		ImageView logoImage = (ImageView) view
				.findViewById(R.id.navigationBarLogo);

		String logoUri = "drawable://"
				+ getResources().getIdentifier(
						mAppContext.getParsedApplicationSettings()
								.getCommonAppSettings().getHotelLogo(),
						"drawable", mAppContext.getPackageName());

		ImageLoader.getInstance().displayImage(logoUri, logoImage);

		TextView titleText = (TextView) view
				.findViewById(R.id.navigationBarTitle);

		if (mTitleText != null && mTitleText.length() > 0) {
			logoImage.setVisibility(View.GONE);
			titleText.setVisibility(View.VISIBLE);
			titleText.setText(mTitleText);
		} else {
			logoImage.setVisibility(View.VISIBLE);
			titleText.setVisibility(View.GONE);
		}

		return view;
	}

	public boolean isShowBack() {
		return isShowBack;
	}

	public void setShowBack(boolean isShowBack) {
		this.isShowBack = isShowBack;
	}

	public void setTitleText(String mTitleText) {
		this.mTitleText = mTitleText;
	}

}
