package com.dmbteam.hotelapp.Utils;

/**
 * 
 * Hotel XML tag constants used for parsing the XML
 */
public final class HotelXMLTagConstants {

	// Optional tag used for obtaining XML data from provided URL address. If
	// blank XML data is collected from the XML file provided in the assets
	// folder
	public static final String TAG_URL_SETTINGS = "";

	public static final String TAG_SETTINGS = "Settings";

	public static final String TAG_COMMON_SETTINGS = "CommonApplicationSettings";
	public static final String TAG_HOTEL_LOGO = "HotelLogo";
	public static final String TAG_HOTEL_NAME = "HotelName";
	public static final String TAG_HOTEL_FACEBOOK = "Facebook";
	public static final String TAG_HOTEL_GOOGLE_PLUS = "GooglePlus";
	public static final String TAG_HOTEL_TWITTER = "Twitter";
	public static final String TAG_HOTEL_TRIPADVISOR = "TripAdvisor";

	public static final String TAG_MAIN_PAGE_SETTINGS = "MainPageSettings";
	public static final String TAG_MAIN_PAGE_PREVIEW_PICTURES = "HotelPreviewPictures";
	public static final String TAG_MAIN_PAGE_PREVIEW_PICTURE_ITEM = "PreviewPicture";
	public static final String TAG_MAIN_PAGE_PREVIEW_PICTURE_ITEM_PHOTO = "Picture";
	public static final String TAG_MAIN_PAGE_PREVIEW_PICTURE_ITEM_PHOTO_DESCRIPTION = "PictureDescription";

	public static final String TAG_RESERVATION_PAGE_SETTINGS = "ReservationPageSettings";
	public static final String TAG_RESERVATION_PAGE_CONTACT_LIST = "HotelReservationContactList";
	public static final String TAG_RESERVATION_PAGE_CONTACT_LIST_ITEM = "HotelReservationContact";
	public static final String TAG_RESERVATION_PAGE_CONTACT_LIST_ITEM_PHONE = "ContactPhone";
	public static final String TAG_RESERVATION_PAGE_CONTACT_LIST_ITEM_EMAIL = "ContactEmail";
	public static final String TAG_RESERVATION_PAGE_CONTACT_LIST_ITEM_DESCRIPTION = "ContactDescription";
	public static final String TAG_RESERVATION_PAGE_BOOK_ONLINE_URL = "BookOnlinePageUrl";

	public static final String TAG_RATES_PAGE_SETTINGS = "RatesPageSettings";
	public static final String TAG_RATES_PAGE_ROOMS_LIST = "RoomTypesList";
	public static final String TAG_RATES_PAGE_ROOMS_LIST_ITEM = "RoomRateInfo";
	public static final String TAG_RATES_PAGE_ROOMS_LIST_ITEM_TITLE = "RoomTitle";
	public static final String TAG_RATES_PAGE_ROOMS_LIST_ITEM_DESCRIPTION = "RoomDescription";
	public static final String TAG_RATES_PAGE_ROOMS_LIST_ITEM_PICTURE = "RoomPicture";
	public static final String TAG_RATES_PAGE_ROOMS_LIST_ITEM_PRICE = "RoomPrice";

	public static final String TAG_GALLERY_PAGE_SETTINGS = "GalleryPageSettings";
	public static final String TAG_GALLERY_PAGE_PICTURE_LIST = "GalleryPictureList";
	public static final String TAG_GALLERY_PAGE_PICTURE_LIST_ITEM = "GalleryPictureItem";
	public static final String TAG_GALLERY_PAGE_PICTURE_LIST_ITEM_PHOTO = "RoomPicture";
	public static final String TAG_GALLERY_PAGE_PICTURE_LIST_ITEM_DESCRIPTION = "RoomTitle";

	public static final String TAG_ROOMS_PAGE_SETTINGS = "RoomsPageSettings";
	public static final String TAG_ROOMS_PAGE_LIST = "RoomsList";
	public static final String TAG_ROOMS_PAGE_LIST_ITEM = "RoomItem";
	public static final String TAG_ROOMS_PAGE_LIST_ITEM_PICTURE = "RoomPicture";
	public static final String TAG_ROOMS_PAGE_LIST_ITEM_TITLE = "RoomTitle";
	public static final String TAG_ROOMS_PAGE_LIST_ITEM_DESCRIPTION = "RoomDescription";

	public static final String TAG_FACILITIES_PAGE_SETTINGS = "FacilitiesPageSettings";
	public static final String TAG_FACILITIES_PAGE_LIST = "HotelFacilitiesList";
	public static final String TAG_FACILITIES_PAGE_LIST_ITEM = "FacilityItem";
	public static final String TAG_FACILITIES_PAGE_LIST_ITEM_DESCRIPTION = "FacilityItemDescription";

	public static final String TAG_LOCATION_PAGE_SETTINGS = "LocationPageSettings";
	public static final String TAG_LOCATION_PAGE_MAIN_LOCATION = "MainHotelItem";
	public static final String TAG_LOCATION_PAGE_LOCATION_COORDINATES = "HotelLocation";
	public static final String TAG_LOCATION_PAGE_LOCATION_COORDINATES_LATITUDE = "HotelLocationLatitude";
	public static final String TAG_LOCATION_PAGE_LOCATION_COORDINATES_LONGTITUDE = "HotelLocationLongtitude";
	public static final String TAG_LOCATION_PAGE_LOCATION_PHONE = "HotelPhone";
	public static final String TAG_LOCATION_PAGE_MAIN_LOCATION_DESCRIPTION = "HotelPhoneDescription";
	public static final String TAG_LOCATION_PAGE_ALTERNATE_LOCATIONS_LIST = "LocationsList";
	public static final String TAG_LOCATION_PAGE_ALTERNATE_LOCATIONS_LIST_ITEM = "HotelItem";
}
