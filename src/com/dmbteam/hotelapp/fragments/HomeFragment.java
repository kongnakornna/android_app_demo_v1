package com.dmbteam.hotelapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dmbteam.hotelapp.ApplicationContext;
import com.dmbteam.hotelapp.MainActivity;
import com.dmbteam.hotelapp.R;
import com.dmbteam.hotelapp.adapters.HomeFragmentViewPagerAdapter;
import com.dmbteam.hotelapp.models.MainPageSettings;
import com.viewpagerindicator.CirclePageIndicator;

public class HomeFragment extends Fragment {

	public static final String TAG = HomeFragment.class.getSimpleName();

	public interface ActionListener {
		void onReservationButtonClicked();

		void onRatesButtonClicked();

		void onGalleryButtonClicked();

		void onRoomsButtonClicked();

		void onFacilitiesButtonClicked();

		void onLocationsButtonClicked();
	}

	private android.support.v4.view.ViewPager mViewPager;

	private RelativeLayout mReservationButton;
	private RelativeLayout mRatesButton;
	private RelativeLayout mGalleryButton;
	private RelativeLayout mRoomsButton;
	private RelativeLayout mFacilitiesButton;
	private RelativeLayout mLocationsButton;

	private ActionListener mActionListener;

	private ApplicationContext mAppContext;

	public HomeFragment() {
		super();
	}

	public static HomeFragment newInstance(ApplicationContext appContext,
			ActionListener actionListener) {

		if (actionListener == null) {
			throw new IllegalArgumentException(ActionListener.class.getName()
					+ " is null!");
		}

		HomeFragment fragment = new HomeFragment();
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
		View view = inflater.inflate(R.layout.fragment_main, container, false);

		mViewPager = (ViewPager) view.findViewById(R.id.mainFragmentViewPager);

		MainPageSettings mainPageSettings = mAppContext
				.getParsedApplicationSettings().getMainPageSettings();

		HomeFragmentViewPagerAdapter adapter = new HomeFragmentViewPagerAdapter(
				mAppContext, mainPageSettings.getMainPreviewPictures());
		mViewPager.setAdapter(adapter);

		CirclePageIndicator circleIndicator = (CirclePageIndicator) view
				.findViewById(R.id.mainFragmentViewPagerBullets);
		circleIndicator.setRadius(MainActivity.dipsToPixels(mAppContext, 8));
		circleIndicator.setViewPager(mViewPager);

		ImageView rightButton = (ImageView) view
				.findViewById(R.id.mainFragmentViewPagerRightButton);
		rightButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mViewPager.getAdapter() != null
						&& mViewPager.getAdapter().getCount() > mViewPager
								.getCurrentItem()) {
					mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
				}
			}
		});

		ImageView leftButton = (ImageView) view
				.findViewById(R.id.mainFragmentViewPagerLeftButton);

		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mViewPager.getAdapter() != null
						&& mViewPager.getCurrentItem() > 0) {
					mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
				}
			}
		});

		if (mViewPager.getAdapter() != null
				&& mViewPager.getAdapter().getCount() < 2) {
			leftButton.setVisibility(View.GONE);
			rightButton.setVisibility(View.GONE);
		} else {
			leftButton.setVisibility(View.VISIBLE);
			rightButton.setVisibility(View.VISIBLE);
		}

		mReservationButton = (RelativeLayout) view
				.findViewById(R.id.mainFragmentButtonsContainerFirstRowReservationButton);
		mReservationButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mActionListener.onReservationButtonClicked();
			}
		});
		mRoomsButton = (RelativeLayout) view
				.findViewById(R.id.mainFragmentButtonsContainerFirstRowRoomsButton);
		mRoomsButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mActionListener.onRoomsButtonClicked();
			}
		});

		mRatesButton = (RelativeLayout) view
				.findViewById(R.id.mainFragmentButtonsContainerSecondRowRatesButton);
		mRatesButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mActionListener.onRatesButtonClicked();
			}
		});

		mFacilitiesButton = (RelativeLayout) view
				.findViewById(R.id.mainFragmentButtonsContainerSecondRowFacilitiesButton);
		mFacilitiesButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mActionListener.onFacilitiesButtonClicked();
			}
		});

		mGalleryButton = (RelativeLayout) view
				.findViewById(R.id.mainFragmentButtonsContainerThirdRowGalleryButton);
		mGalleryButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mActionListener.onGalleryButtonClicked();
			}
		});

		mLocationsButton = (RelativeLayout) view
				.findViewById(R.id.mainFragmentButtonsContainerThirdRowLocationsButton);
		mLocationsButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mActionListener.onLocationsButtonClicked();
			}
		});

		return view;
	}
}
