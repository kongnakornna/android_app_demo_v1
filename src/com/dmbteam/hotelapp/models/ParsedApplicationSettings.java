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

public class ParsedApplicationSettings {

	private CommonApplicationSettings commonAppSettings;
	private MainPageSettings mainPageSettings;
	private ReservationPageSettings reservationPageSettings;
	private RatesPageSettings ratesPageSettings;
	private GalleryPageSettings galleryPageSettings;
	private RoomsPageSettings roomsPageSettings;
	private FacilitiesPageSettings facilitiesPageSettings;
	private LocationsPageSettings locationsPageSettings;

	public ParsedApplicationSettings(
			CommonApplicationSettings commonAppSettings,
			MainPageSettings mainPageSettings,
			ReservationPageSettings reservationPageSettings,
			RatesPageSettings ratesPageSettings,
			GalleryPageSettings galleryPageSettings,
			RoomsPageSettings roomsPageSettings,
			FacilitiesPageSettings facilitiesPageSettings,
			LocationsPageSettings locationsPageSettings) {

		this.commonAppSettings = commonAppSettings;
		this.mainPageSettings = mainPageSettings;
		this.reservationPageSettings = reservationPageSettings;
		this.ratesPageSettings = ratesPageSettings;
		this.galleryPageSettings = galleryPageSettings;
		this.roomsPageSettings = roomsPageSettings;
		this.facilitiesPageSettings = facilitiesPageSettings;
		this.locationsPageSettings = locationsPageSettings;
	}

	public CommonApplicationSettings getCommonAppSettings() {
		return commonAppSettings;
	}

	public void setCommonAppSettings(CommonApplicationSettings commonAppSettings) {
		this.commonAppSettings = commonAppSettings;
	}

	public MainPageSettings getMainPageSettings() {
		return mainPageSettings;
	}

	public void setMainPageSettings(MainPageSettings mainPageSettings) {
		this.mainPageSettings = mainPageSettings;
	}

	public ReservationPageSettings getReservationPageSettings() {
		return reservationPageSettings;
	}

	public void setReservationPageSettings(
			ReservationPageSettings reservationPageSettings) {
		this.reservationPageSettings = reservationPageSettings;
	}

	public RatesPageSettings getRatesPageSettings() {
		return ratesPageSettings;
	}

	public void setRatesPageSettings(RatesPageSettings ratesPageSettings) {
		this.ratesPageSettings = ratesPageSettings;
	}

	public GalleryPageSettings getGalleryPageSettings() {
		return galleryPageSettings;
	}

	public void setGalleryPageSettings(GalleryPageSettings galleryPageSettings) {
		this.galleryPageSettings = galleryPageSettings;
	}

	public RoomsPageSettings getRoomsPageSettings() {
		return roomsPageSettings;
	}

	public void setRoomsPageSettings(RoomsPageSettings roomsPageSettings) {
		this.roomsPageSettings = roomsPageSettings;
	}

	public FacilitiesPageSettings getFacilitiesPageSettings() {
		return facilitiesPageSettings;
	}

	public void setFacilitiesPageSettings(
			FacilitiesPageSettings facilitiesPageSettings) {
		this.facilitiesPageSettings = facilitiesPageSettings;
	}

	public LocationsPageSettings getLocationsPageSettings() {
		return locationsPageSettings;
	}

	public void setLocationsPageSettings(
			LocationsPageSettings locationsPageSettings) {
		this.locationsPageSettings = locationsPageSettings;
	}

}
