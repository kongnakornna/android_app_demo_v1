package com.dmbteam.hotelapp.adapters;

import java.util.ArrayList;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmbteam.hotelapp.ApplicationContext;
import com.dmbteam.hotelapp.R;
import com.dmbteam.hotelapp.models.DrawerListMenuItem;

/**
 * 
 * Adapter used for the left list menu
 * 
 */
public class DrawerListMenuAdapter extends ArrayAdapter<DrawerListMenuItem> {

	private ApplicationContext mContext;

	/**
	 * 
	 * @param context
	 *            the application context
	 * @param items
	 *            the menu items to show
	 */
	public DrawerListMenuAdapter(ApplicationContext context,
			ArrayList<DrawerListMenuItem> items) {
		super(context, 0, items);
		mContext = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		convertView = LayoutInflater.from(mContext).inflate(
				R.layout.drawer_list_item, null);

		if (position % 2 > 0) {
			convertView.setBackgroundColor(mContext.getResources().getColor(
					R.color.drawer_grey_light));
		} else {
			convertView.setBackgroundColor(mContext.getResources().getColor(
					R.color.drawer_grey));
		}

		ImageView menuItemSelectionImage = (ImageView) convertView
				.findViewById(R.id.drawerSelectionImageView);

		TextView menuItemText = (TextView) convertView
				.findViewById(R.id.drawerMenuItemTextView);

		ImageView menuIteLogoImage = (ImageView) convertView
				.findViewById(R.id.drawerMenuItemLogoImageView);

		DrawerListMenuItem item = getItem(position);

		if (item.isMenuSelected()) {
			menuItemSelectionImage.setVisibility(View.VISIBLE);
			menuItemText.setTypeface(null, Typeface.BOLD);
		} else {
			menuItemSelectionImage.setVisibility(View.INVISIBLE);
			menuItemText.setTypeface(null, Typeface.NORMAL);
		}

		menuItemText.setText(item.getMenuItemName());

		/**
		 * 
		 */
		menuIteLogoImage.setBackgroundResource(item.getLogoResourceId());

		return convertView;
	}

	/**
	 * Used to deselect all menu items
	 */
	public void deselectAll() {
		for (int i = 0; i < getCount(); i++) {
			if (getItem(i) instanceof DrawerListMenuItem) {
				getItem(i).setMenuSelected(false);
			}
		}
	}

	/**
	 * Used to select a certain menu item
	 * 
	 * @param menuItemText
	 *            the menu item to be selected
	 */
	public void setSelected(String menuItemText) {
		deselectAll();
		for (int i = 0; i < getCount(); i++) {
			if (getItem(i) instanceof DrawerListMenuItem) {
				DrawerListMenuItem item = getItem(i);
				if (item.getMenuItemName().equalsIgnoreCase(menuItemText)) {
					item.setMenuSelected(true);
				}
			}
		}
		notifyDataSetChanged();
	}
}
