package com.dmbteam.hotelapp.models;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class FacilitiesPageSettings implements Parcelable {

	private ArrayList<FacilityItem> facilitiesList = new ArrayList<FacilityItem>(
			0);

	public FacilitiesPageSettings(ArrayList<FacilityItem> roomsList) {
		super();
		this.facilitiesList = roomsList;
	}

	public ArrayList<FacilityItem> getFacilitiesList() {
		return facilitiesList;
	}

	public void setFacilitiesList(ArrayList<FacilityItem> facilitiesList) {
		this.facilitiesList = facilitiesList;
	}

	protected FacilitiesPageSettings(Parcel in) {

		in.readList(facilitiesList, getClass().getClassLoader());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeList(facilitiesList);

	}

	public static final Parcelable.Creator<FacilitiesPageSettings> CREATOR = new Parcelable.Creator<FacilitiesPageSettings>() {

		@Override
		public FacilitiesPageSettings createFromParcel(Parcel source) {
			return new FacilitiesPageSettings(source);
		}

		@Override
		public FacilitiesPageSettings[] newArray(int size) {
			return new FacilitiesPageSettings[size];
		}
	};

}
