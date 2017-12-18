package com.dmbteam.hotelapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class RoomRatesItem extends RoomItem implements Parcelable {
	private String roomPrice;

	public RoomRatesItem(String title, String description, String picture,
			String price) {
		super(title, description, picture);
		this.roomPrice = price;
	}

	public String getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(String roomPrice) {
		this.roomPrice = roomPrice;
	}

	protected RoomRatesItem(Parcel in) {

		super(in);
		roomPrice = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeString(roomPrice);
	}

	public static final Parcelable.Creator<RoomRatesItem> CREATOR = new Parcelable.Creator<RoomRatesItem>() {

		@Override
		public RoomRatesItem createFromParcel(Parcel source) {
			return new RoomRatesItem(source);
		}

		@Override
		public RoomRatesItem[] newArray(int size) {
			return new RoomRatesItem[size];
		}
	};

}