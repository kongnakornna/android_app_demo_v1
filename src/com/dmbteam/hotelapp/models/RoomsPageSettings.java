package com.dmbteam.hotelapp.models;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class RoomsPageSettings implements Parcelable {

	private ArrayList<RoomItem> roomsList = new ArrayList<RoomItem>(0);

	public RoomsPageSettings(ArrayList<RoomItem> roomsList) {
		super();
		this.roomsList = roomsList;
	}

	public ArrayList<RoomItem> getRoomsList() {
		return roomsList;
	}

	public void setRoomsList(ArrayList<RoomItem> roomRatesList) {
		this.roomsList = roomRatesList;
	}

	protected RoomsPageSettings(Parcel in) {

		in.readList(roomsList, getClass().getClassLoader());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeList(roomsList);

	}

	public static final Parcelable.Creator<RoomsPageSettings> CREATOR = new Parcelable.Creator<RoomsPageSettings>() {

		@Override
		public RoomsPageSettings createFromParcel(Parcel source) {
			return new RoomsPageSettings(source);
		}

		@Override
		public RoomsPageSettings[] newArray(int size) {
			return new RoomsPageSettings[size];
		}
	};

}
