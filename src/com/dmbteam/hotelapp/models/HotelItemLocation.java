package com.dmbteam.hotelapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class HotelItemLocation implements Parcelable {
	private String hotelLocationLatitude;
	private String hotelLocationLongtitude;

	public HotelItemLocation(String lat, String lon) {
		this.hotelLocationLatitude = lat;
		this.hotelLocationLongtitude = lon;
	}

	protected HotelItemLocation(Parcel in) {

		hotelLocationLatitude = in.readString();
		hotelLocationLongtitude = in.readString();
	}

	public String getHotelLocationLatitude() {
		return hotelLocationLatitude;
	}

	public void setHotelLocationLatitude(String hotelLocationLatitude) {
		this.hotelLocationLatitude = hotelLocationLatitude;
	}

	public String getHotelLocationLongtitude() {
		return hotelLocationLongtitude;
	}

	public void setHotelLocationLongtitude(String hotelLocationLongtitude) {
		this.hotelLocationLongtitude = hotelLocationLongtitude;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(hotelLocationLatitude);
		dest.writeString(hotelLocationLongtitude);
	}

	public static final Parcelable.Creator<HotelItemLocation> CREATOR = new Parcelable.Creator<HotelItemLocation>() {

		@Override
		public HotelItemLocation createFromParcel(Parcel source) {
			return new HotelItemLocation(source);
		}

		@Override
		public HotelItemLocation[] newArray(int size) {
			return new HotelItemLocation[size];
		}
	};

}