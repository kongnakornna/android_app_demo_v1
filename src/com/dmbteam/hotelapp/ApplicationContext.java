package com.dmbteam.hotelapp;

import android.app.Application;

import com.dmbteam.hotelapp.models.ParsedApplicationSettings;

public class ApplicationContext extends Application {

	/**
	 * The settings obtained after parsing the XML file
	 */
	public ParsedApplicationSettings mParsedApplicationSettings = null;

	/**
	 * 
	 * @return the parsed application data from the XML
	 */
	public ParsedApplicationSettings getParsedApplicationSettings() {
		return mParsedApplicationSettings;
	}

	/**
	 * Setter for ParsedApplicationSettings
	 * 
	 * @param parsedApplicationSettings
	 *            the parsed XML settings to set for the applicationContext;
	 */
	public void setParsedApplicationSettings(
			ParsedApplicationSettings parsedApplicationSettings) {
		this.mParsedApplicationSettings = parsedApplicationSettings;
	}

}
