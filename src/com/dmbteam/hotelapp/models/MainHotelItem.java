package com.dmbteam.hotelapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class MainHotelItem extends HotelItem implements Parcelable {
	private String hotelPhoneDescription;

	public MainHotelItem(HotelItemLocation location, String phone,
			String phoneDescription) {
		super(location, phone);
		this.hotelPhoneDescription = phoneDescription;
	}

	public String getHotelPhoneDescription() {
		return hotelPhoneDescription;
	}

	public void setHotelPhoneDescription(String hotelPhoneDescription) {
		this.hotelPhoneDescription = hotelPhoneDescription;
	}

	protected MainHotelItem(Parcel in) {
		super(in);
		hotelPhoneDescription = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeString(hotelPhoneDescription);
	}

	public static final Parcelable.Creator<MainHotelItem> CREATOR = new Parcelable.Creator<MainHotelItem>() {

		@Override
		public MainHotelItem createFromParcel(Parcel source) {
			return new MainHotelItem(source);
		}

		@Override
		public MainHotelItem[] newArray(int size) {
			return new MainHotelItem[size];
		}
	};

}