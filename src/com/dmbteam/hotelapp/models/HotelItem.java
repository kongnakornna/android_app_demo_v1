package com.dmbteam.hotelapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class HotelItem implements Parcelable {
	private HotelItemLocation hotelLocation;
	private String hotelPhone;

	public HotelItem(HotelItemLocation location, String phone) {
		this.hotelLocation = location;
		this.hotelPhone = phone;
	}

	protected HotelItem(Parcel in) {

		hotelLocation = in.readParcelable(null);
		hotelPhone = in.readString();
	}

	public HotelItemLocation getHotelLocation() {
		return hotelLocation;
	}

	public void setHotelLocation(HotelItemLocation hotelLocation) {
		this.hotelLocation = hotelLocation;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(hotelLocation, 0);
		dest.writeString(hotelPhone);
	}

	public static final Parcelable.Creator<HotelItem> CREATOR = new Parcelable.Creator<HotelItem>() {

		@Override
		public HotelItem createFromParcel(Parcel source) {
			return new HotelItem(source);
		}

		@Override
		public HotelItem[] newArray(int size) {
			return new HotelItem[size];
		}
	};

}