package com.dmbteam.hotelapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class RoomItem implements Parcelable {
	private String roomTitle;
	private String roomDescription;
	private String roomPicture;

	public RoomItem(String title, String description, String picture) {
		this.roomTitle = title;
		this.roomDescription = description;
		this.roomPicture = picture;
	}

	public String getRoomTitle() {
		return roomTitle;
	}

	public void setRoomTitle(String roomTitle) {
		this.roomTitle = roomTitle;
	}

	public String getRoomDescription() {
		return roomDescription;
	}

	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}

	public String getRoomPicture() {
		return roomPicture;
	}

	public void setRoomPicture(String roomPicture) {
		this.roomPicture = roomPicture;
	}

	protected RoomItem(Parcel in) {

		roomTitle = in.readString();
		roomDescription = in.readString();
		roomPicture = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(roomTitle);
		dest.writeString(roomDescription);
		dest.writeString(roomPicture);
	}

	public static final Parcelable.Creator<RoomItem> CREATOR = new Parcelable.Creator<RoomItem>() {

		@Override
		public RoomItem createFromParcel(Parcel source) {
			return new RoomItem(source);
		}

		@Override
		public RoomItem[] newArray(int size) {
			return new RoomItem[size];
		}
	};

}