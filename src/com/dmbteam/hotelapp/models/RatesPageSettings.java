package com.dmbteam.hotelapp.models;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class RatesPageSettings implements Parcelable {

	private ArrayList<RoomRatesItem> roomRatesList = new ArrayList<RoomRatesItem>(
			0);

	public RatesPageSettings(ArrayList<RoomRatesItem> roomRatesList) {
		super();
		this.roomRatesList = roomRatesList;
	}

	public ArrayList<RoomRatesItem> getRoomRatesList() {
		return roomRatesList;
	}

	public void setRoomRatesList(ArrayList<RoomRatesItem> roomRatesList) {
		this.roomRatesList = roomRatesList;
	}

	protected RatesPageSettings(Parcel in) {

		in.readList(roomRatesList, getClass().getClassLoader());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeList(roomRatesList);

	}

	public static final Parcelable.Creator<RatesPageSettings> CREATOR = new Parcelable.Creator<RatesPageSettings>() {

		@Override
		public RatesPageSettings createFromParcel(Parcel source) {
			return new RatesPageSettings(source);
		}

		@Override
		public RatesPageSettings[] newArray(int size) {
			return new RatesPageSettings[size];
		}
	};

}
