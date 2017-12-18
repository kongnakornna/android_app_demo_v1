package com.dmbteam.hotelapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class HotelReservationContactItem implements Parcelable {

	private String contactPhone;
	private String contactEmail;
	private String contactDescription;

	public HotelReservationContactItem(String phone, String mail,
			String description) {
		this.contactPhone = phone;
		this.contactEmail = mail;
		this.contactDescription = description;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactDescription() {
		return contactDescription;
	}

	public void setContactDescription(String contactDescription) {
		this.contactDescription = contactDescription;
	}

	protected HotelReservationContactItem(Parcel in) {

		contactPhone = in.readString();
		contactEmail = in.readString();
		contactDescription = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(contactPhone);
		dest.writeString(contactEmail);
		dest.writeString(contactDescription);

	}

	public static final Parcelable.Creator<HotelReservationContactItem> CREATOR = new Parcelable.Creator<HotelReservationContactItem>() {

		@Override
		public HotelReservationContactItem createFromParcel(Parcel source) {
			return new HotelReservationContactItem(source);
		}

		@Override
		public HotelReservationContactItem[] newArray(int size) {
			return new HotelReservationContactItem[size];
		}
	};

}