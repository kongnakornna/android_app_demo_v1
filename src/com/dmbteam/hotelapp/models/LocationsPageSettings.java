package com.dmbteam.hotelapp.models;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class LocationsPageSettings implements Parcelable {

	private MainHotelItem mainHotelItem;
	private ArrayList<HotelItem> alternateLocations = new ArrayList<HotelItem>(
			0);

	public LocationsPageSettings(MainHotelItem mainHotelItemParam,
			ArrayList<HotelItem> alternateLocations) {
		this.mainHotelItem = mainHotelItemParam;
		this.alternateLocations = alternateLocations;
	}

	public MainHotelItem getMainHotelItem() {
		return mainHotelItem;
	}

	public void setMainHotelItem(MainHotelItem mainHotelItem) {
		this.mainHotelItem = mainHotelItem;
	}

	public ArrayList<HotelItem> getAlternateLocations() {
		return alternateLocations;
	}

	public void setAlternateLocations(ArrayList<HotelItem> alternateLocations) {
		this.alternateLocations = alternateLocations;
	}

	protected LocationsPageSettings(Parcel in) {

		in.readList(alternateLocations, getClass().getClassLoader());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeList(alternateLocations);

	}

	public static final Parcelable.Creator<LocationsPageSettings> CREATOR = new Parcelable.Creator<LocationsPageSettings>() {

		@Override
		public LocationsPageSettings createFromParcel(Parcel source) {
			return new LocationsPageSettings(source);
		}

		@Override
		public LocationsPageSettings[] newArray(int size) {
			return new LocationsPageSettings[size];
		}
	};

}
