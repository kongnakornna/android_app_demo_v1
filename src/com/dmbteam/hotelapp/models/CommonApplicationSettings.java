package com.dmbteam.hotelapp.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;

public class CommonApplicationSettings implements Parcelable {

	private String hotelLogo;
	private String hotelName;
	private String hotelFacebookPage;
	private String hotelGooglePlusPage;
	private String hotelTwitterPage;
	private String hotelTripAdvisorPage;

	public CommonApplicationSettings(String hotelLogo, String hotelName,
			String hotelFacebookPage, String hotelGooglePlusPage,
			String hotelTwitterPage, String tripAdvisorPage) {
		super();
		this.hotelLogo = hotelLogo;
		this.hotelName = hotelName;
		this.hotelFacebookPage = hotelFacebookPage;
		this.hotelGooglePlusPage = hotelGooglePlusPage;
		this.hotelTwitterPage = hotelTwitterPage;
		this.hotelTripAdvisorPage = tripAdvisorPage;
	}

	protected CommonApplicationSettings(Parcel in) {

		hotelLogo = in.readString();
		hotelName = in.readString();
		hotelFacebookPage = in.readString();
		hotelGooglePlusPage = in.readString();
		hotelTwitterPage = in.readString();
		hotelTripAdvisorPage = in.readString();
	}

	public String getHotelLogo() {
		return hotelLogo;
	}

	public void setHotelLogo(String hotelLogo) {
		this.hotelLogo = hotelLogo;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelFacebookPage() {
		return hotelFacebookPage;
	}

	public void setHotelFacebookPage(String hotelFacebookPage) {
		this.hotelFacebookPage = hotelFacebookPage;
	}

	public String getHotelGooglePlusPage() {
		return hotelGooglePlusPage;
	}

	public void setHotelGooglePlusPage(String hotelGooglePlusPage) {
		this.hotelGooglePlusPage = hotelGooglePlusPage;
	}

	public String getHotelTwitterPage() {
		return hotelTwitterPage;
	}

	public void setHotelTwitterPage(String hotelTwitterPage) {
		this.hotelTwitterPage = hotelTwitterPage;
	}

	public String getHotelTripAdvisorPage() {
		return hotelTripAdvisorPage;
	}

	public void setHotelTripAdvisorPage(String hotelTripAdvisorPage) {
		this.hotelTripAdvisorPage = hotelTripAdvisorPage;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeString(hotelLogo);
		dest.writeString(hotelName);
		dest.writeString(hotelFacebookPage);
		dest.writeString(hotelGooglePlusPage);
		dest.writeString(hotelTwitterPage);
		dest.writeString(hotelTripAdvisorPage);

	}

	public static final Parcelable.Creator<CommonApplicationSettings> CREATOR = new Parcelable.Creator<CommonApplicationSettings>() {

		@Override
		public CommonApplicationSettings createFromParcel(Parcel source) {
			return new CommonApplicationSettings(source);
		}

		@Override
		public CommonApplicationSettings[] newArray(int size) {
			return new CommonApplicationSettings[size];
		}
	};

}
