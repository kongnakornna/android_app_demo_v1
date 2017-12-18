package com.dmbteam.hotelapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class DrawerListMenuItem implements Parcelable {

	private int logoResourceId;
	private boolean menuState;
	private boolean isSelectable;
	private String menuName;

	public DrawerListMenuItem(String name, boolean state, int logoId,
			boolean isSelectable) {
		this.menuName = name;
		this.menuState = state;
		this.logoResourceId = logoId;
		this.isSelectable = isSelectable;
	}

	public int getLogoResourceId() {
		return logoResourceId;
	}

	public void setLogoResourceId(int logoResourceId) {
		this.logoResourceId = logoResourceId;
	}

	public boolean isMenuSelected() {
		return menuState;
	}

	public void setMenuSelected(boolean selected) {
		this.menuState = selected;
	}

	public String getMenuItemName() {
		return menuName;
	}

	public void setMenuItemName(String facilityName) {
		this.menuName = facilityName;
	}

	public boolean isSelectable() {
		return isSelectable;
	}

	public void setSelectable(boolean isSelectable) {
		this.isSelectable = isSelectable;
	}

	protected DrawerListMenuItem(Parcel in) {

		menuName = in.readString();

		int i = in.readInt();
		if (i == 0) {
			menuState = false;
		} else {
			menuState = true;
		}
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(menuName);
		if (menuState) {
			dest.writeInt(1);
		} else {
			dest.writeInt(0);
		}
	}

	public static final Parcelable.Creator<DrawerListMenuItem> CREATOR = new Parcelable.Creator<DrawerListMenuItem>() {

		@Override
		public DrawerListMenuItem createFromParcel(Parcel source) {
			return new DrawerListMenuItem(source);
		}

		@Override
		public DrawerListMenuItem[] newArray(int size) {
			return new DrawerListMenuItem[size];
		}
	};

}