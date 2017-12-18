package com.dmbteam.hotelapp.models;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class MainPageSettings implements Parcelable {

	private ArrayList<PreviewPictureItem> mainPreviewPictures = new ArrayList<PreviewPictureItem>(
			0);

	public MainPageSettings(ArrayList<PreviewPictureItem> mainPreviewPictures) {
		super();
		this.mainPreviewPictures = mainPreviewPictures;
	}

	protected MainPageSettings(Parcel in) {

		in.readList(mainPreviewPictures, getClass().getClassLoader());

	}

	public ArrayList<PreviewPictureItem> getMainPreviewPictures() {
		return mainPreviewPictures;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeList(mainPreviewPictures);

	}

	public static final Parcelable.Creator<MainPageSettings> CREATOR = new Parcelable.Creator<MainPageSettings>() {

		@Override
		public MainPageSettings createFromParcel(Parcel source) {
			return new MainPageSettings(source);
		}

		@Override
		public MainPageSettings[] newArray(int size) {
			return new MainPageSettings[size];
		}
	};

}
