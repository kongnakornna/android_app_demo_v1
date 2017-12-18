package com.dmbteam.hotelapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class PreviewPictureItem implements Parcelable {
	private String previewPictureUri;
	private String previewPictureTitleText;

	public PreviewPictureItem(String uri, String title) {
		this.previewPictureUri = uri;
		this.previewPictureTitleText = title;
	}

	public String getPreviewPictureUri() {
		return previewPictureUri;
	}

	public void setPreviewPictureUri(String previewPictureUri) {
		this.previewPictureUri = previewPictureUri;
	}

	public String getPreviewPictureTitleText() {
		return previewPictureTitleText;
	}

	public void setPreviewPictureTitleText(String previewPictureTitleText) {
		this.previewPictureTitleText = previewPictureTitleText;
	}

	protected PreviewPictureItem(Parcel in) {

		previewPictureUri = in.readString();
		previewPictureTitleText = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(previewPictureUri);
		dest.writeString(previewPictureTitleText);
	}

	public static final Parcelable.Creator<PreviewPictureItem> CREATOR = new Parcelable.Creator<PreviewPictureItem>() {

		@Override
		public PreviewPictureItem createFromParcel(Parcel source) {
			return new PreviewPictureItem(source);
		}

		@Override
		public PreviewPictureItem[] newArray(int size) {
			return new PreviewPictureItem[size];
		}
	};

}