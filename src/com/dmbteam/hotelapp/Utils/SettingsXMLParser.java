package com.dmbteam.hotelapp.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.dmbteam.hotelapp.models.CommonApplicationSettings;
import com.dmbteam.hotelapp.models.FacilitiesPageSettings;
import com.dmbteam.hotelapp.models.FacilityItem;
import com.dmbteam.hotelapp.models.GalleryPageSettings;
import com.dmbteam.hotelapp.models.HotelItem;
import com.dmbteam.hotelapp.models.HotelItemLocation;
import com.dmbteam.hotelapp.models.HotelReservationContactItem;
import com.dmbteam.hotelapp.models.LocationsPageSettings;
import com.dmbteam.hotelapp.models.MainHotelItem;
import com.dmbteam.hotelapp.models.MainPageSettings;
import com.dmbteam.hotelapp.models.ParsedApplicationSettings;
import com.dmbteam.hotelapp.models.PreviewPictureItem;
import com.dmbteam.hotelapp.models.RatesPageSettings;
import com.dmbteam.hotelapp.models.ReservationPageSettings;
import com.dmbteam.hotelapp.models.RoomItem;
import com.dmbteam.hotelapp.models.RoomRatesItem;
import com.dmbteam.hotelapp.models.RoomsPageSettings;

import android.util.Xml;

/**
 * The XML Parser used to obtain the information provided in the XML
 */
public class SettingsXMLParser {

	private static final String ns = null;

	public static ParsedApplicationSettings parse(InputStream in) {
		try {
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(new InputStreamReader(in));
			parser.nextTag();
			return readFeed(parser);
		} catch (Exception e) {
			// Do notging
		} finally {
			try {
				in.close();
			} catch (Exception e) {
				// Do nothing
			}
		}

		return null;
	}

	private static ParsedApplicationSettings readFeed(XmlPullParser parser)
			throws XmlPullParserException, IOException {

		CommonApplicationSettings commonAppSettings = null;
		MainPageSettings mainPageSettings = null;
		ReservationPageSettings reservationPageSettings = null;
		RatesPageSettings ratesPageSettings = null;
		GalleryPageSettings galleryPageSettings = null;
		RoomsPageSettings roomsPageSettings = null;
		FacilitiesPageSettings facilitiesPageSettings = null;
		LocationsPageSettings locationsPageSettings = null;

		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_SETTINGS);
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(HotelXMLTagConstants.TAG_COMMON_SETTINGS)) {
				commonAppSettings = readCommonSettings(parser);
			} else if (name.equals(HotelXMLTagConstants.TAG_MAIN_PAGE_SETTINGS)) {
				mainPageSettings = readMainPageSettings(parser);
			} else if (name
					.equals(HotelXMLTagConstants.TAG_RESERVATION_PAGE_SETTINGS)) {
				reservationPageSettings = readReservationPageSettings(parser);
			} else if (name
					.equals(HotelXMLTagConstants.TAG_RATES_PAGE_SETTINGS)) {
				ratesPageSettings = readRatesPageSettings(parser);
			} else if (name
					.equals(HotelXMLTagConstants.TAG_GALLERY_PAGE_SETTINGS)) {
				galleryPageSettings = readGalleryPageSettings(parser);
			} else if (name
					.equals(HotelXMLTagConstants.TAG_ROOMS_PAGE_SETTINGS)) {
				roomsPageSettings = readRoomsPageSettings(parser);
			} else if (name
					.equals(HotelXMLTagConstants.TAG_FACILITIES_PAGE_SETTINGS)) {
				facilitiesPageSettings = readFacilitiesPageSettings(parser);
			} else if (name
					.equals(HotelXMLTagConstants.TAG_LOCATION_PAGE_SETTINGS)) {
				locationsPageSettings = readLocationsPageSettings(parser);
			} else {
				skip(parser);
			}
		}
		return new ParsedApplicationSettings(commonAppSettings,
				mainPageSettings, reservationPageSettings, ratesPageSettings,
				galleryPageSettings, roomsPageSettings, facilitiesPageSettings,
				locationsPageSettings);
	}

	/* ------------- Common Settings ------------- */
	private static CommonApplicationSettings readCommonSettings(
			XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_COMMON_SETTINGS);

		String hotelLogo = null;
		String hotelName = null;
		String hotelFacebookPage = null;
		String hotelGooglePlusPage = null;
		String hotelTwitterPage = null;
		String hotelTripAdvisorPage = null;

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(HotelXMLTagConstants.TAG_HOTEL_LOGO)) {
				hotelLogo = readLogo(parser);
			} else if (name.equals(HotelXMLTagConstants.TAG_HOTEL_NAME)) {
				hotelName = readName(parser);
			} else if (name.equals(HotelXMLTagConstants.TAG_HOTEL_FACEBOOK)) {
				hotelFacebookPage = readFacebookPage(parser);
			} else if (name.equals(HotelXMLTagConstants.TAG_HOTEL_GOOGLE_PLUS)) {
				hotelGooglePlusPage = readGooglePlusPage(parser);
			} else if (name.equals(HotelXMLTagConstants.TAG_HOTEL_TWITTER)) {
				hotelTwitterPage = readTwitterPage(parser);
			} else if (name.equals(HotelXMLTagConstants.TAG_HOTEL_TRIPADVISOR)) {
				hotelTripAdvisorPage = readTripAdvisorPage(parser);
			} else {
				skip(parser);
			}
		}
		return new CommonApplicationSettings(hotelLogo, hotelName,
				hotelFacebookPage, hotelGooglePlusPage, hotelTwitterPage,
				hotelTripAdvisorPage);
	}

	private static String readLogo(XmlPullParser parser) throws IOException,
			XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_HOTEL_LOGO);
		String item = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns,
				HotelXMLTagConstants.TAG_HOTEL_LOGO);
		return item;
	}

	private static String readName(XmlPullParser parser) throws IOException,
			XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_HOTEL_NAME);
		String item = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns,
				HotelXMLTagConstants.TAG_HOTEL_NAME);
		return item;
	}

	private static String readFacebookPage(XmlPullParser parser)
			throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_HOTEL_FACEBOOK);
		String item = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns,
				HotelXMLTagConstants.TAG_HOTEL_FACEBOOK);
		return item;
	}

	private static String readGooglePlusPage(XmlPullParser parser)
			throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_HOTEL_GOOGLE_PLUS);
		String item = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns,
				HotelXMLTagConstants.TAG_HOTEL_GOOGLE_PLUS);
		return item;
	}

	private static String readTwitterPage(XmlPullParser parser)
			throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_HOTEL_TWITTER);
		String item = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns,
				HotelXMLTagConstants.TAG_HOTEL_TWITTER);
		return item;
	}

	private static String readTripAdvisorPage(XmlPullParser parser)
			throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_HOTEL_TRIPADVISOR);
		String item = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns,
				HotelXMLTagConstants.TAG_HOTEL_TRIPADVISOR);
		return item;
	}

	/* ------------- Common Settings END ------------- */

	/* ------------- Main Page Settings ------------- */
	private static MainPageSettings readMainPageSettings(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_MAIN_PAGE_SETTINGS);

		ArrayList<PreviewPictureItem> mainPreviewPictures = new ArrayList<PreviewPictureItem>(
				0);

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(HotelXMLTagConstants.TAG_MAIN_PAGE_PREVIEW_PICTURES)) {
				mainPreviewPictures = readMainPagePicturesList(parser);
			} else {
				skip(parser);
			}
		}
		return new MainPageSettings(mainPreviewPictures);
	}

	private static ArrayList<PreviewPictureItem> readMainPagePicturesList(
			XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_MAIN_PAGE_PREVIEW_PICTURES);

		ArrayList<PreviewPictureItem> mainPreviewPictures = new ArrayList<PreviewPictureItem>(
				0);

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(HotelXMLTagConstants.TAG_MAIN_PAGE_PREVIEW_PICTURE_ITEM)) {
				mainPreviewPictures.add(readMainPreviewPictureItem(parser));
			} else {
				skip(parser);
			}
		}
		return mainPreviewPictures;
	}

	private static PreviewPictureItem readMainPreviewPictureItem(
			XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_MAIN_PAGE_PREVIEW_PICTURE_ITEM);

		String uri = null;
		String title = null;

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(HotelXMLTagConstants.TAG_MAIN_PAGE_PREVIEW_PICTURE_ITEM_PHOTO)) {
				uri = readMainPagePreviewPictureItemPhoto(parser);
			} else if (name
					.equals(HotelXMLTagConstants.TAG_MAIN_PAGE_PREVIEW_PICTURE_ITEM_PHOTO_DESCRIPTION)) {
				title = readMainPagePreviewPictureItemPhotoDescription(parser);
			} else {
				skip(parser);
			}
		}
		return new PreviewPictureItem(uri, title);
	}

	private static String readMainPagePreviewPictureItemPhoto(
			XmlPullParser parser) throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_MAIN_PAGE_PREVIEW_PICTURE_ITEM_PHOTO);
		String item = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns,
				HotelXMLTagConstants.TAG_MAIN_PAGE_PREVIEW_PICTURE_ITEM_PHOTO);
		return item;
	}

	private static String readMainPagePreviewPictureItemPhotoDescription(
			XmlPullParser parser) throws IOException, XmlPullParserException {
		parser.require(
				XmlPullParser.START_TAG,
				ns,
				HotelXMLTagConstants.TAG_MAIN_PAGE_PREVIEW_PICTURE_ITEM_PHOTO_DESCRIPTION);
		String item = readText(parser);
		parser.require(
				XmlPullParser.END_TAG,
				ns,
				HotelXMLTagConstants.TAG_MAIN_PAGE_PREVIEW_PICTURE_ITEM_PHOTO_DESCRIPTION);
		return item;
	}

	/* ------------- Main Page Settings END ------------- */

	/* ------------- Reservation Page Settings ------------- */
	private static ReservationPageSettings readReservationPageSettings(
			XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_RESERVATION_PAGE_SETTINGS);

		ArrayList<HotelReservationContactItem> reservationContactsList = new ArrayList<HotelReservationContactItem>(
				0);

		String reservationBookOnlineURL = null;

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(HotelXMLTagConstants.TAG_RESERVATION_PAGE_CONTACT_LIST)) {
				reservationContactsList = readReservationPageContactsList(parser);
			} else if (name
					.equals(HotelXMLTagConstants.TAG_RESERVATION_PAGE_BOOK_ONLINE_URL)) {
				reservationBookOnlineURL = readReservationPageBookOnlineUrl(parser);
			} else {
				skip(parser);
			}
		}
		return new ReservationPageSettings(reservationContactsList,
				reservationBookOnlineURL);
	}

	private static ArrayList<HotelReservationContactItem> readReservationPageContactsList(
			XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_RESERVATION_PAGE_CONTACT_LIST);

		ArrayList<HotelReservationContactItem> reservationContactsList = new ArrayList<HotelReservationContactItem>(
				0);

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(HotelXMLTagConstants.TAG_RESERVATION_PAGE_CONTACT_LIST_ITEM)) {
				reservationContactsList
						.add(readReservationPageContactsListItem(parser));
			} else {
				skip(parser);
			}
		}
		return reservationContactsList;
	}

	private static HotelReservationContactItem readReservationPageContactsListItem(
			XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_RESERVATION_PAGE_CONTACT_LIST_ITEM);

		String phone = null;
		String mail = null;
		String description = null;

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(HotelXMLTagConstants.TAG_RESERVATION_PAGE_CONTACT_LIST_ITEM_PHONE)) {
				phone = readReservationPageContactsListItemPhone(parser);
			} else if (name
					.equals(HotelXMLTagConstants.TAG_RESERVATION_PAGE_CONTACT_LIST_ITEM_EMAIL)) {
				mail = readReservationPageContactsListItemMail(parser);
			} else if (name
					.equals(HotelXMLTagConstants.TAG_RESERVATION_PAGE_CONTACT_LIST_ITEM_DESCRIPTION)) {
				description = readReservationPageContactsListItemDescription(parser);
			} else {
				skip(parser);
			}
		}
		return new HotelReservationContactItem(phone, mail, description);
	}

	private static String readReservationPageContactsListItemPhone(
			XmlPullParser parser) throws IOException, XmlPullParserException {
		parser.require(
				XmlPullParser.START_TAG,
				ns,
				HotelXMLTagConstants.TAG_RESERVATION_PAGE_CONTACT_LIST_ITEM_PHONE);
		String item = readText(parser);
		parser.require(
				XmlPullParser.END_TAG,
				ns,
				HotelXMLTagConstants.TAG_RESERVATION_PAGE_CONTACT_LIST_ITEM_PHONE);
		return item;
	}

	private static String readReservationPageContactsListItemMail(
			XmlPullParser parser) throws IOException, XmlPullParserException {
		parser.require(
				XmlPullParser.START_TAG,
				ns,
				HotelXMLTagConstants.TAG_RESERVATION_PAGE_CONTACT_LIST_ITEM_EMAIL);
		String item = readText(parser);
		parser.require(
				XmlPullParser.END_TAG,
				ns,
				HotelXMLTagConstants.TAG_RESERVATION_PAGE_CONTACT_LIST_ITEM_EMAIL);
		return item;
	}

	private static String readReservationPageContactsListItemDescription(
			XmlPullParser parser) throws IOException, XmlPullParserException {
		parser.require(
				XmlPullParser.START_TAG,
				ns,
				HotelXMLTagConstants.TAG_RESERVATION_PAGE_CONTACT_LIST_ITEM_DESCRIPTION);
		String item = readText(parser);
		parser.require(
				XmlPullParser.END_TAG,
				ns,
				HotelXMLTagConstants.TAG_RESERVATION_PAGE_CONTACT_LIST_ITEM_DESCRIPTION);
		return item;
	}

	private static String readReservationPageBookOnlineUrl(XmlPullParser parser)
			throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_RESERVATION_PAGE_BOOK_ONLINE_URL);
		String item = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns,
				HotelXMLTagConstants.TAG_RESERVATION_PAGE_BOOK_ONLINE_URL);
		return item;
	}

	/* ------------- Reservation Page Settings END ------------- */

	/* ------------- Rates Page Settings ------------- */
	private static RatesPageSettings readRatesPageSettings(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_RATES_PAGE_SETTINGS);

		ArrayList<RoomRatesItem> roomRatesList = new ArrayList<RoomRatesItem>(0);

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(HotelXMLTagConstants.TAG_RATES_PAGE_ROOMS_LIST)) {
				roomRatesList = readRatesPageRoomsList(parser);
			} else {
				skip(parser);
			}
		}
		return new RatesPageSettings(roomRatesList);
	}

	private static ArrayList<RoomRatesItem> readRatesPageRoomsList(
			XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_RATES_PAGE_ROOMS_LIST);

		ArrayList<RoomRatesItem> ratesRoomList = new ArrayList<RoomRatesItem>(0);

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(HotelXMLTagConstants.TAG_RATES_PAGE_ROOMS_LIST_ITEM)) {
				ratesRoomList.add(readRatesPageRoomListItem(parser));
			} else {
				skip(parser);
			}
		}
		return ratesRoomList;
	}

	private static RoomRatesItem readRatesPageRoomListItem(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_RATES_PAGE_ROOMS_LIST_ITEM);

		String title = null;
		String description = null;
		String picture = null;
		String price = null;

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(HotelXMLTagConstants.TAG_RATES_PAGE_ROOMS_LIST_ITEM_TITLE)) {
				title = readRatesPageRoomsListItemTitle(parser);
			} else if (name
					.equals(HotelXMLTagConstants.TAG_RATES_PAGE_ROOMS_LIST_ITEM_DESCRIPTION)) {
				description = readRatesPageRoomsListItemDescription(parser);
			} else if (name
					.equals(HotelXMLTagConstants.TAG_RATES_PAGE_ROOMS_LIST_ITEM_PICTURE)) {
				picture = readRatesPageRoomsListItemPicture(parser);
			} else if (name
					.equals(HotelXMLTagConstants.TAG_RATES_PAGE_ROOMS_LIST_ITEM_PRICE)) {
				price = readRatesPageRoomsListItemPrice(parser);
			} else {
				skip(parser);
			}
		}
		return new RoomRatesItem(title, description, picture, price);
	}

	private static String readRatesPageRoomsListItemTitle(XmlPullParser parser)
			throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_RATES_PAGE_ROOMS_LIST_ITEM_TITLE);
		String item = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns,
				HotelXMLTagConstants.TAG_RATES_PAGE_ROOMS_LIST_ITEM_TITLE);
		return item;
	}

	private static String readRatesPageRoomsListItemDescription(
			XmlPullParser parser) throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_RATES_PAGE_ROOMS_LIST_ITEM_DESCRIPTION);
		String item = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns,
				HotelXMLTagConstants.TAG_RATES_PAGE_ROOMS_LIST_ITEM_DESCRIPTION);
		return item;
	}

	private static String readRatesPageRoomsListItemPicture(XmlPullParser parser)
			throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_RATES_PAGE_ROOMS_LIST_ITEM_PICTURE);
		String item = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns,
				HotelXMLTagConstants.TAG_RATES_PAGE_ROOMS_LIST_ITEM_PICTURE);
		return item;
	}

	private static String readRatesPageRoomsListItemPrice(XmlPullParser parser)
			throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_RATES_PAGE_ROOMS_LIST_ITEM_PRICE);
		String item = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns,
				HotelXMLTagConstants.TAG_RATES_PAGE_ROOMS_LIST_ITEM_PRICE);
		return item;
	}

	/* ------------- Rates Page Settings END ------------- */

	/* ------------- Gallery Page Settings ------------- */
	private static GalleryPageSettings readGalleryPageSettings(
			XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_GALLERY_PAGE_SETTINGS);

		ArrayList<PreviewPictureItem> reservationContactsList = new ArrayList<PreviewPictureItem>(
				0);

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(HotelXMLTagConstants.TAG_GALLERY_PAGE_PICTURE_LIST)) {
				reservationContactsList = readGalleryPagePicturesList(parser);
			} else {
				skip(parser);
			}
		}
		return new GalleryPageSettings(reservationContactsList);
	}

	private static ArrayList<PreviewPictureItem> readGalleryPagePicturesList(
			XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_GALLERY_PAGE_PICTURE_LIST);

		ArrayList<PreviewPictureItem> galleryPreviewPictures = new ArrayList<PreviewPictureItem>(
				0);

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(HotelXMLTagConstants.TAG_GALLERY_PAGE_PICTURE_LIST_ITEM)) {
				galleryPreviewPictures
						.add(readGalleryPreviewPictureItem(parser));
			} else {
				skip(parser);
			}
		}
		return galleryPreviewPictures;
	}

	private static PreviewPictureItem readGalleryPreviewPictureItem(
			XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_GALLERY_PAGE_PICTURE_LIST_ITEM);

		String uri = null;
		String title = null;

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(HotelXMLTagConstants.TAG_GALLERY_PAGE_PICTURE_LIST_ITEM_PHOTO)) {
				uri = readGalleryPagePreviewPictureItemPhoto(parser);
			} else if (name
					.equals(HotelXMLTagConstants.TAG_GALLERY_PAGE_PICTURE_LIST_ITEM_DESCRIPTION)) {
				title = readGalleryPagePreviewPictureItemPhotoDescription(parser);
			} else {
				skip(parser);
			}
		}
		return new PreviewPictureItem(uri, title);
	}

	private static String readGalleryPagePreviewPictureItemPhoto(
			XmlPullParser parser) throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_GALLERY_PAGE_PICTURE_LIST_ITEM_PHOTO);
		String item = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns,
				HotelXMLTagConstants.TAG_GALLERY_PAGE_PICTURE_LIST_ITEM_PHOTO);
		return item;
	}

	private static String readGalleryPagePreviewPictureItemPhotoDescription(
			XmlPullParser parser) throws IOException, XmlPullParserException {
		parser.require(
				XmlPullParser.START_TAG,
				ns,
				HotelXMLTagConstants.TAG_GALLERY_PAGE_PICTURE_LIST_ITEM_DESCRIPTION);
		String item = readText(parser);
		parser.require(
				XmlPullParser.END_TAG,
				ns,
				HotelXMLTagConstants.TAG_GALLERY_PAGE_PICTURE_LIST_ITEM_DESCRIPTION);
		return item;
	}

	/* ------------- Gallery Page Settings END ------------- */

	/* ------------- Rooms Page Settings ------------- */
	private static RoomsPageSettings readRoomsPageSettings(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_ROOMS_PAGE_SETTINGS);

		ArrayList<RoomItem> roomsList = new ArrayList<RoomItem>(0);

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(HotelXMLTagConstants.TAG_ROOMS_PAGE_LIST)) {
				roomsList = readRoomsPageRoomsList(parser);
			} else {
				skip(parser);
			}
		}
		return new RoomsPageSettings(roomsList);
	}

	private static ArrayList<RoomItem> readRoomsPageRoomsList(
			XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_ROOMS_PAGE_LIST);

		ArrayList<RoomItem> galleryPreviewPictures = new ArrayList<RoomItem>(0);

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(HotelXMLTagConstants.TAG_ROOMS_PAGE_LIST_ITEM)) {
				galleryPreviewPictures.add(readRoomsPageListItem(parser));
			} else {
				skip(parser);
			}
		}
		return galleryPreviewPictures;
	}

	private static RoomItem readRoomsPageListItem(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_ROOMS_PAGE_LIST_ITEM);

		String title = null;
		String description = null;
		String uri = null;

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(HotelXMLTagConstants.TAG_ROOMS_PAGE_LIST_ITEM_TITLE)) {
				title = readRoomsPageRoomListItemTitle(parser);
			} else if (name
					.equals(HotelXMLTagConstants.TAG_ROOMS_PAGE_LIST_ITEM_PICTURE)) {
				uri = readRoomsPageRoomListItemPhoto(parser);
			} else if (name
					.equals(HotelXMLTagConstants.TAG_ROOMS_PAGE_LIST_ITEM_DESCRIPTION)) {
				description = readRoomsPageRoomListItemDescription(parser);
			} else {
				skip(parser);
			}
		}
		return new RoomItem(title, description, uri);
	}

	private static String readRoomsPageRoomListItemPhoto(XmlPullParser parser)
			throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_ROOMS_PAGE_LIST_ITEM_PICTURE);
		String item = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns,
				HotelXMLTagConstants.TAG_ROOMS_PAGE_LIST_ITEM_PICTURE);
		return item;
	}

	private static String readRoomsPageRoomListItemDescription(
			XmlPullParser parser) throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_ROOMS_PAGE_LIST_ITEM_DESCRIPTION);
		String item = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns,
				HotelXMLTagConstants.TAG_ROOMS_PAGE_LIST_ITEM_DESCRIPTION);
		return item;
	}

	private static String readRoomsPageRoomListItemTitle(XmlPullParser parser)
			throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_ROOMS_PAGE_LIST_ITEM_TITLE);
		String item = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns,
				HotelXMLTagConstants.TAG_ROOMS_PAGE_LIST_ITEM_TITLE);
		return item;
	}

	/* ------------- Rooms Page Settings END ------------- */

	/* ------------- Facilities Page Settings ------------- */
	private static FacilitiesPageSettings readFacilitiesPageSettings(
			XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_FACILITIES_PAGE_SETTINGS);

		ArrayList<FacilityItem> facilitiesList = new ArrayList<FacilityItem>(0);

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(HotelXMLTagConstants.TAG_FACILITIES_PAGE_LIST)) {
				facilitiesList = readFacilitiesPageList(parser);
			} else {
				skip(parser);
			}
		}
		return new FacilitiesPageSettings(facilitiesList);
	}

	private static ArrayList<FacilityItem> readFacilitiesPageList(
			XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_FACILITIES_PAGE_LIST);

		ArrayList<FacilityItem> facilitiesList = new ArrayList<FacilityItem>(0);

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(HotelXMLTagConstants.TAG_FACILITIES_PAGE_LIST_ITEM)) {
				facilitiesList.add(readFacilitiesPageListItem(parser));
			} else {
				skip(parser);
			}
		}
		return facilitiesList;
	}

	private static FacilityItem readFacilitiesPageListItem(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_FACILITIES_PAGE_LIST_ITEM);

		String description = null;

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(HotelXMLTagConstants.TAG_FACILITIES_PAGE_LIST_ITEM_DESCRIPTION)) {
				description = readFacilitiesPageListItemDescription(parser);
			} else {
				skip(parser);
			}
		}
		return new FacilityItem(description);
	}

	private static String readFacilitiesPageListItemDescription(
			XmlPullParser parser) throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_FACILITIES_PAGE_LIST_ITEM_DESCRIPTION);
		String item = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns,
				HotelXMLTagConstants.TAG_FACILITIES_PAGE_LIST_ITEM_DESCRIPTION);
		return item;
	}

	/* ------------- Facilities Page Settings END ------------- */

	/* ------------- Locations Page Settings ------------- */
	private static LocationsPageSettings readLocationsPageSettings(
			XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_LOCATION_PAGE_SETTINGS);

		MainHotelItem mainHotelLocation = null;
		ArrayList<HotelItem> alternateLocationsList = new ArrayList<HotelItem>(
				0);

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(HotelXMLTagConstants.TAG_LOCATION_PAGE_MAIN_LOCATION)) {
				mainHotelLocation = readLocationsPageMainHotelItem(parser);
			} else if (name
					.equals(HotelXMLTagConstants.TAG_LOCATION_PAGE_ALTERNATE_LOCATIONS_LIST)) {
				alternateLocationsList = readLocationsPageAlternateLocationsList(parser);
			} else {
				skip(parser);
			}
		}
		return new LocationsPageSettings(mainHotelLocation,
				alternateLocationsList);
	}

	private static MainHotelItem readLocationsPageMainHotelItem(
			XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_LOCATION_PAGE_MAIN_LOCATION);

		HotelItemLocation location = null;
		String phone = null;
		String phoneDescription = null;

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(HotelXMLTagConstants.TAG_LOCATION_PAGE_LOCATION_COORDINATES)) {
				location = readLocationsPageMainHotelItemLocation(parser);
			} else if (name
					.equals(HotelXMLTagConstants.TAG_LOCATION_PAGE_LOCATION_PHONE)) {
				phone = readLocationPageMainLocationPhone(parser);
			} else if (name
					.equals(HotelXMLTagConstants.TAG_LOCATION_PAGE_MAIN_LOCATION_DESCRIPTION)) {
				phoneDescription = readLocationPageMainLocationPhoneDescription(parser);
			} else {
				skip(parser);
			}
		}
		return new MainHotelItem(location, phone, phoneDescription);
	}

	private static HotelItemLocation readLocationsPageMainHotelItemLocation(
			XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_LOCATION_PAGE_LOCATION_COORDINATES);

		String lat = null;
		String lon = null;

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(HotelXMLTagConstants.TAG_LOCATION_PAGE_LOCATION_COORDINATES_LATITUDE)) {
				lat = readLocationPageLocationCoordinatesLatitude(parser);
			} else if (name
					.equals(HotelXMLTagConstants.TAG_LOCATION_PAGE_LOCATION_COORDINATES_LONGTITUDE)) {
				lon = readLocationPageLocationCoordinatesLongtitude(parser);
			} else {
				skip(parser);
			}
		}
		return new HotelItemLocation(lat, lon);
	}

	private static String readLocationPageMainLocationPhone(XmlPullParser parser)
			throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_LOCATION_PAGE_LOCATION_PHONE);
		String item = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns,
				HotelXMLTagConstants.TAG_LOCATION_PAGE_LOCATION_PHONE);
		return item;
	}

	private static String readLocationPageMainLocationPhoneDescription(
			XmlPullParser parser) throws IOException, XmlPullParserException {
		parser.require(
				XmlPullParser.START_TAG,
				ns,
				HotelXMLTagConstants.TAG_LOCATION_PAGE_MAIN_LOCATION_DESCRIPTION);
		String item = readText(parser);
		parser.require(
				XmlPullParser.END_TAG,
				ns,
				HotelXMLTagConstants.TAG_LOCATION_PAGE_MAIN_LOCATION_DESCRIPTION);
		return item;
	}

	private static String readLocationPageLocationCoordinatesLongtitude(
			XmlPullParser parser) throws IOException, XmlPullParserException {
		parser.require(
				XmlPullParser.START_TAG,
				ns,
				HotelXMLTagConstants.TAG_LOCATION_PAGE_LOCATION_COORDINATES_LONGTITUDE);
		String item = readText(parser);
		parser.require(
				XmlPullParser.END_TAG,
				ns,
				HotelXMLTagConstants.TAG_LOCATION_PAGE_LOCATION_COORDINATES_LONGTITUDE);
		return item;
	}

	private static String readLocationPageLocationCoordinatesLatitude(
			XmlPullParser parser) throws IOException, XmlPullParserException {
		parser.require(
				XmlPullParser.START_TAG,
				ns,
				HotelXMLTagConstants.TAG_LOCATION_PAGE_LOCATION_COORDINATES_LATITUDE);
		String item = readText(parser);
		parser.require(
				XmlPullParser.END_TAG,
				ns,
				HotelXMLTagConstants.TAG_LOCATION_PAGE_LOCATION_COORDINATES_LATITUDE);
		return item;
	}

	private static ArrayList<HotelItem> readLocationsPageAlternateLocationsList(
			XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns,
				HotelXMLTagConstants.TAG_LOCATION_PAGE_ALTERNATE_LOCATIONS_LIST);

		ArrayList<HotelItem> locationsPageAlternateLocationsList = new ArrayList<HotelItem>(
				0);

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(HotelXMLTagConstants.TAG_LOCATION_PAGE_ALTERNATE_LOCATIONS_LIST_ITEM)) {
				locationsPageAlternateLocationsList
						.add(readLocationsPageHotelItem(parser));
			} else {
				skip(parser);
			}
		}
		return locationsPageAlternateLocationsList;
	}

	private static HotelItem readLocationsPageHotelItem(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(
				XmlPullParser.START_TAG,
				ns,
				HotelXMLTagConstants.TAG_LOCATION_PAGE_ALTERNATE_LOCATIONS_LIST_ITEM);

		HotelItemLocation location = null;
		String phone = null;

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(HotelXMLTagConstants.TAG_LOCATION_PAGE_LOCATION_COORDINATES)) {
				location = readLocationsPageMainHotelItemLocation(parser);
			} else if (name
					.equals(HotelXMLTagConstants.TAG_LOCATION_PAGE_LOCATION_PHONE)) {
				phone = readLocationPageMainLocationPhone(parser);
			} else {
				skip(parser);
			}
		}
		return new HotelItem(location, phone);
	}

	/* ------------- Locations Page Settings END ------------- */

	private static String readText(XmlPullParser parser) throws IOException,
			XmlPullParserException {
		String result = "";
		if (parser.next() == XmlPullParser.TEXT) {
			result = parser.getText();
			parser.nextTag();
		}
		return result;
	}

	private static void skip(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		if (parser.getEventType() != XmlPullParser.START_TAG) {
			throw new IllegalStateException();
		}
		int depth = 1;
		while (depth != 0) {
			switch (parser.next()) {
			case XmlPullParser.END_TAG:
				depth--;
				break;
			case XmlPullParser.START_TAG:
				depth++;
				break;
			}
		}
	}

}
