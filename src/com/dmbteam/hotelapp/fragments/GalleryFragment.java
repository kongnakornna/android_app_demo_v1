package com.dmbteam.hotelapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmbteam.hotelapp.ApplicationContext;
import com.dmbteam.hotelapp.R;
import com.dmbteam.hotelapp.adapters.GalleryListAdapter;
import com.dmbteam.hotelapp.models.GalleryPageSettings;
import com.huewu.pla.lib.MultiColumnListView;

public class GalleryFragment extends Fragment {

	public static final String TAG = GalleryFragment.class.getSimpleName();

	public interface ActionListener {

		void onGalleryItemClicked(int position);

	}

	private MultiColumnListView mGalleryListView;

	private ApplicationContext mAppContext;
	private ActionListener mActionListener;

	public static GalleryFragment newInstance(ApplicationContext appContext,
			ActionListener actionListener) {

		if (actionListener == null) {
			throw new IllegalArgumentException(ActionListener.class.getName()
					+ " is null!");
		}

		GalleryFragment fragment = new GalleryFragment();
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

		View view = inflater.inflate(R.layout.fragment_gallery, container,
				false);

		GalleryPageSettings galleryPageSettings = mAppContext
				.getParsedApplicationSettings().getGalleryPageSettings();
		GalleryListAdapter galleryAdapter = new GalleryListAdapter(mAppContext,
				galleryPageSettings.getGalleryItemsList(), mActionListener);

		mGalleryListView = (MultiColumnListView) view
				.findViewById(R.id.pinterestLikeView);
		mGalleryListView.setAdapter(galleryAdapter);

		return view;
	}
}
