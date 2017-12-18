package com.dmbteam.hotelapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class FacilityItem implements Parcelable {
	private String facilityName;

	public FacilityItem(String name) {
		this.facilityName = name;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	protected FacilityItem(Parcel in) {

		facilityName = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(facilityName);
	}

	public static final Parcelable.Creator<FacilityItem> CREATOR = new Parcelable.Creator<FacilityItem>() {

		@Override
		public FacilityItem createFromParcel(Parcel source) {
			return new FacilityItem(source);
		}

		@Override
		public FacilityItem[] newArray(int size) {
			return new FacilityItem[size];
		}
	};

}