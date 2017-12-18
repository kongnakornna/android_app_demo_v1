package com.dmbteam.hotelapp.models;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class GalleryPageSettings implements Parcelable {

	private ArrayList<PreviewPictureItem> galleryItemsList = new ArrayList<PreviewPictureItem>(
			0);

	public GalleryPageSettings(ArrayList<PreviewPictureItem> picturesList) {
		super();
		this.galleryItemsList = picturesList;
	}

	protected GalleryPageSettings(Parcel in) {

		in.readList(galleryItemsList, getClass().getClassLoader());
	}

	public ArrayList<PreviewPictureItem> getGalleryItemsList() {
		return galleryItemsList;
	}

	public void setGalleryItemsList(
			ArrayList<PreviewPictureItem> galleryItemsList) {
		this.galleryItemsList = galleryItemsList;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeList(galleryItemsList);

	}

	public static final Parcelable.Creator<GalleryPageSettings> CREATOR = new Parcelable.Creator<GalleryPageSettings>() {

		@Override
		public GalleryPageSettings createFromParcel(Parcel source) {
			return new GalleryPageSettings(source);
		}

		@Override
		public GalleryPageSettings[] newArray(int size) {
			return new GalleryPageSettings[size];
		}
	};

}
