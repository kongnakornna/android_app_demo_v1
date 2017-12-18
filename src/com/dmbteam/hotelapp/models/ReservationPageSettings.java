package com.dmbteam.hotelapp.models;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class ReservationPageSettings implements Parcelable {

	private ArrayList<HotelReservationContactItem> reservationContactsList = new ArrayList<HotelReservationContactItem>(
			0);

	private String reservationBookOnlineURL;

	public ReservationPageSettings(
			ArrayList<HotelReservationContactItem> reservationContactsList,
			String reservationBookOnlineURL) {
		super();
		this.reservationContactsList = reservationContactsList;
		this.reservationBookOnlineURL = reservationBookOnlineURL;
	}

	protected ReservationPageSettings(Parcel in) {

		in.readList(reservationContactsList, getClass().getClassLoader());
		reservationBookOnlineURL = in.readString();

	}

	@Override
	public int describeContents() {
		return 0;
	}

	public ArrayList<HotelReservationContactItem> getReservationContactsList() {
		return reservationContactsList;
	}

	public void setReservationContactsList(
			ArrayList<HotelReservationContactItem> reservationContactsList) {
		this.reservationContactsList = reservationContactsList;
	}

	public String getReservationBookOnlineURL() {
		return reservationBookOnlineURL;
	}

	public void setReservationBookOnlineURL(String reservationBookOnlineURL) {
		this.reservationBookOnlineURL = reservationBookOnlineURL;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeList(reservationContactsList);
		dest.writeString(reservationBookOnlineURL);

	}

	public static final Parcelable.Creator<ReservationPageSettings> CREATOR = new Parcelable.Creator<ReservationPageSettings>() {

		@Override
		public ReservationPageSettings createFromParcel(Parcel source) {
			return new ReservationPageSettings(source);
		}

		@Override
		public ReservationPageSettings[] newArray(int size) {
			return new ReservationPageSettings[size];
		}
	};

}
