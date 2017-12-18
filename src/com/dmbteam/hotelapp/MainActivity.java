package com.dmbteam.hotelapp;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

import com.dmbteam.hotelapp.adapters.DrawerListMenuAdapter;
import com.dmbteam.hotelapp.fragments.FacilitiesFragment;
import com.dmbteam.hotelapp.fragments.FooterBarFragment;
import com.dmbteam.hotelapp.fragments.GalleryFragment;
import com.dmbteam.hotelapp.fragments.HomeFragment;
import com.dmbteam.hotelapp.fragments.LocationsFragment;
import com.dmbteam.hotelapp.fragments.NavigationBarFragment;
import com.dmbteam.hotelapp.fragments.PhotosPreviewFragment;
import com.dmbteam.hotelapp.fragments.RatesFragment;
import com.dmbteam.hotelapp.fragments.ReservationFormFragment;
import com.dmbteam.hotelapp.fragments.ReservationFragment;
import com.dmbteam.hotelapp.fragments.RoomsFragment;
import com.dmbteam.hotelapp.models.DrawerListMenuItem;
import com.dmbteam.hotelapp.models.HotelReservationContactItem;
import com.dmbteam.hotelapp.models.ReservationPageSettings;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.FixedImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class MainActivity extends FragmentActivity {

	// Disc cache 10mb maximum
	private static final int MAX_DISC_CACHE_SIZE = 10 * 1024 * 1024;

	// Set memory storage
	private static final int MEMORY_CACHE_SIZE = (int) (Runtime.getRuntime()
			.maxMemory() * 0.25);

	private ApplicationContext mAppContext;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private DrawerListMenuAdapter mDrawerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_main);

		mAppContext = (ApplicationContext) getApplicationContext();

		/**
		 * Initialize and configure image loader
		 */
		ImageLoaderConfiguration imageLoaderConfiguration = createImageLoaderConfiguration(this);
		ImageLoader.getInstance().init(imageLoaderConfiguration);
		FixedImageLoader.getFixedLoaderInstance().init(imageLoaderConfiguration);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		/**
		 * Create left menu list items
		 */
		ArrayList<DrawerListMenuItem> menuItems = new ArrayList<DrawerListMenuItem>(
				7);
		menuItems.add(new DrawerListMenuItem(getString(R.string.home), false,
				R.drawable.sliding_menu_home_icon, true));
		menuItems.add(new DrawerListMenuItem(getString(R.string.reservation),
				false, R.drawable.sliding_menu_reservation_icon, true));
		menuItems.add(new DrawerListMenuItem(getString(R.string.rooms), false,
				R.drawable.sliding_menu_rooms_icon, true));
		menuItems.add(new DrawerListMenuItem(getString(R.string.rates), false,
				R.drawable.sliding_menu_rates_icon, true));
		menuItems.add(new DrawerListMenuItem(getString(R.string.facilities),
				false, R.drawable.sliding_menu_facilities_icon, true));
		menuItems.add(new DrawerListMenuItem(getString(R.string.gallery),
				false, R.drawable.sliding_menu_gallery_icon, true));
		menuItems.add(new DrawerListMenuItem(getString(R.string.location),
				false, R.drawable.sliding_menu_location_icon, true));

		/**
		 * Left menu Logo picture on top
		 */
		ImageView imageView = new ImageView(this);
		imageView.setImageResource(R.drawable.sliding_menu_hotel_logo);
		imageView.setLayoutParams(new AbsListView.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

		mDrawerList.addHeaderView(imageView);

		mDrawerAdapter = new DrawerListMenuAdapter(mAppContext, menuItems);

		mDrawerList.setAdapter(mDrawerAdapter);

		/**
		 * Called when item from the left menu is clicked
		 */
		mDrawerList
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						DrawerListMenuItem menuItem = (DrawerListMenuItem) arg0
								.getItemAtPosition(arg2);
						if (menuItem != null && menuItem.isSelectable()) {
							mDrawerAdapter.deselectAll();
							//menuItem.setMenuSelected(true);
							if (menuItem.getMenuItemName().equalsIgnoreCase(
									getString(R.string.home))) {

								mDrawerLayout.closeDrawer(Gravity.LEFT);
								showHomeScreen();
							} else if (menuItem.getMenuItemName()
									.equalsIgnoreCase(
											getString(R.string.facilities))) {
								mDrawerLayout.closeDrawer(Gravity.LEFT);
								showFacilitiesScreen();
							} else if (menuItem.getMenuItemName()
									.equalsIgnoreCase(
											getString(R.string.reservation))) {
								mDrawerLayout.closeDrawer(Gravity.LEFT);
								showReservationScreen();
							} else if (menuItem.getMenuItemName()
									.equalsIgnoreCase(
											getString(R.string.gallery))) {
								mDrawerLayout.closeDrawer(Gravity.LEFT);
								showGalleryScreen();
							} else if (menuItem.getMenuItemName()
									.equalsIgnoreCase(
											getString(R.string.location))) {
								mDrawerLayout.closeDrawer(Gravity.LEFT);
								showLocationsScreen();
							} else if (menuItem
									.getMenuItemName()
									.equalsIgnoreCase(getString(R.string.rates))) {
								mDrawerLayout.closeDrawer(Gravity.LEFT);
								showRatesFragment();
							} else if (menuItem
									.getMenuItemName()
									.equalsIgnoreCase(getString(R.string.rooms))) {
								mDrawerLayout.closeDrawer(Gravity.LEFT);
								showRoomsFragment();
							}

							mDrawerAdapter.notifyDataSetChanged();
						}
					}
				});

		showHomeScreen();
	}

	/**
	 * Shows HomeFragment
	 */
	private void showHomeScreen() {
		HomeFragment homeFragment = HomeFragment.newInstance(mAppContext,
				new HomeFragment.ActionListener() {

					@Override
					public void onRoomsButtonClicked() {
						showRoomsFragment();
						if (mDrawerAdapter != null) {
							mDrawerAdapter
									.setSelected(getString(R.string.rooms));
						}
					}

					@Override
					public void onReservationButtonClicked() {
						showReservationScreen();
						if (mDrawerAdapter != null) {
							mDrawerAdapter
									.setSelected(getString(R.string.reservation));
						}
					}

					@Override
					public void onRatesButtonClicked() {
						showRatesFragment();
						if (mDrawerAdapter != null) {
							mDrawerAdapter
									.setSelected(getString(R.string.rates));
						}
					}

					@Override
					public void onLocationsButtonClicked() {
						showLocationsScreen();
						if (mDrawerAdapter != null) {
							mDrawerAdapter
									.setSelected(getString(R.string.location));
						}
					}

					@Override
					public void onGalleryButtonClicked() {
						showGalleryScreen();
						if (mDrawerAdapter != null) {
							mDrawerAdapter
									.setSelected(getString(R.string.gallery));
						}
					}

					@Override
					public void onFacilitiesButtonClicked() {
						showFacilitiesScreen();
						if (mDrawerAdapter != null) {
							mDrawerAdapter
									.setSelected(getString(R.string.facilities));
						}
					}
				});

		NavigationBarFragment navigationBar = NavigationBarFragment
				.newInstance(mAppContext, new NavigationBarActionListner(), false);

		FooterBarFragment footerFragment = FooterBarFragment
				.newInstance(mAppContext);

		showScreen(navigationBar, homeFragment, footerFragment,
				HomeFragment.TAG, false, true, true);
	}

	/**
	 * Shows FacilitiesFragment
	 */
	private void showFacilitiesScreen() {
		FacilitiesFragment content = FacilitiesFragment
				.newInstance(mAppContext);
		NavigationBarFragment navigationBar = NavigationBarFragment
				.newInstance(mAppContext, new NavigationBarActionListner(), true);
		navigationBar.setTitleText(getString(R.string.facilities));

		FooterBarFragment footerFragment = FooterBarFragment
				.newInstance(mAppContext);

		showScreen(navigationBar, content, footerFragment,
				FacilitiesFragment.TAG, true, true, true);
	}

	/**
	 * Shows RatesFragment
	 */
	private void showRatesFragment() {
		RatesFragment content = RatesFragment.newInstance(mAppContext);
		NavigationBarFragment navigationBar = NavigationBarFragment
				.newInstance(mAppContext, new NavigationBarActionListner(), true);
		navigationBar.setTitleText(getString(R.string.rates));
		FooterBarFragment footerFragment = FooterBarFragment
				.newInstance(mAppContext);
		showScreen(navigationBar, content, footerFragment, RatesFragment.TAG,
				true, true, true);
	}

	/**
	 * Shows RoomsFragment
	 */
	private void showRoomsFragment() {
		RoomsFragment content = RoomsFragment.newInstance(mAppContext,
				new RoomsFragment.ActionListener() {

					@Override
					public void onCheckAvailabilityClicked() {

						final ReservationPageSettings reservationSettings = mAppContext
								.getParsedApplicationSettings()
								.getReservationPageSettings();
						if (reservationSettings.getReservationContactsList() != null
								&& reservationSettings
										.getReservationContactsList().size() > 0) {

							final String[] menuItems = new String[2];
							menuItems[0] = getString(R.string.phone);
							menuItems[1] = getString(R.string.email);

							final HotelReservationContactItem contact = reservationSettings
									.getReservationContactsList().get(0);

							AlertDialog.Builder builder = new AlertDialog.Builder(
									MainActivity.this);
							builder.setTitle("");
							builder.setCancelable(true);
							builder.setNegativeButton(R.string.cancel, null);
							builder.setItems(menuItems,
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												final int which) {
											String choice = menuItems[which];
											if (choice
													.equalsIgnoreCase(MainActivity.this
															.getString(R.string.email))) {

												Intent i = new Intent(
														Intent.ACTION_SEND);
												i.setType("message/rfc822");
												i.putExtra(
														Intent.EXTRA_EMAIL,
														new String[] { contact
																.getContactEmail() });

												try {
													startActivity(Intent
															.createChooser(
																	i,
																	getResources()
																			.getString(
																					R.string.send_email)));
												} catch (android.content.ActivityNotFoundException ex) {
													Toast.makeText(
															MainActivity.this,
															getString(R.string.no_email_clients),
															Toast.LENGTH_SHORT)
															.show();
												}

											} else {
												try {
													String phoneUri = contact
															.getContactPhone();
													phoneUri = phoneUri
															.replaceAll(" ", "");
													String phone = "tel:"
															+ phoneUri;
													Intent intent = new Intent(
															Intent.ACTION_DIAL);
													intent.setData(Uri
															.parse(phone));
													startActivity(intent);

												} catch (ActivityNotFoundException e) {
													Toast.makeText(
															MainActivity.this,
															getString(R.string.toast_could_not_dial),
															Toast.LENGTH_SHORT)
															.show();
												}
											}

										}
									});
							builder.show();

						}

					}
				});
		NavigationBarFragment navigationBar = NavigationBarFragment
				.newInstance(mAppContext, new NavigationBarActionListner(), true);
		navigationBar.setTitleText(getString(R.string.rooms));

		FooterBarFragment footerFragment = FooterBarFragment
				.newInstance(mAppContext);

		showScreen(navigationBar, content, footerFragment, RoomsFragment.TAG,
				true, true, true);
	}

	/**
	 * Shows LocationsFragment
	 */
	private void showLocationsScreen() {
		LocationsFragment content = LocationsFragment.newInstance(mAppContext);
		NavigationBarFragment navigationBar = NavigationBarFragment
				.newInstance(mAppContext, new NavigationBarActionListner(), true);
		navigationBar.setTitleText(getString(R.string.location));

		FooterBarFragment footerFragment = FooterBarFragment
				.newInstance(mAppContext);

		showScreen(navigationBar, content, footerFragment,
				LocationsFragment.TAG, true, true, true);
	}

	/**
	 * Shows PhotosPreviewFragment
	 */
	private void showPhotosPrevievScreen(int position) {
		PhotosPreviewFragment content = PhotosPreviewFragment.newInstance(
				mAppContext, position);
		NavigationBarFragment navigationBar = NavigationBarFragment
				.newInstance(mAppContext, new NavigationBarActionListner(), true);
		navigationBar.setTitleText(getString(R.string.gallery));
		navigationBar.setShowBack(true);

		FooterBarFragment footerFragment = FooterBarFragment
				.newInstance(mAppContext);

		showScreen(navigationBar, content, footerFragment,
				PhotosPreviewFragment.TAG, true, true, true);
	}
	
	/**
	 * Shows PhotosPreviewFragment
	 */
	private void showReservationFormScreen() {
		ReservationFormFragment content = ReservationFormFragment.newInstance(
				mAppContext);
		
		NavigationBarFragment navigationBar = NavigationBarFragment
				.newInstance(mAppContext, new NavigationBarActionListner(), true);
		navigationBar.setTitleText(getString(R.string.reservation));
		navigationBar.setShowBack(true);

		FooterBarFragment footerFragment = FooterBarFragment
				.newInstance(mAppContext);

		showScreen(navigationBar, content, footerFragment,
				ReservationFormFragment.TAG, true, true, true);
	}

	/**
	 * Shows GalleryFragment
	 */
	private void showGalleryScreen() {
		GalleryFragment content = GalleryFragment.newInstance(mAppContext,
				new GalleryFragment.ActionListener() {

					@Override
					public void onGalleryItemClicked(int position) {
						showPhotosPrevievScreen(position);
					}
				});
		NavigationBarFragment navigationBar = NavigationBarFragment
				.newInstance(mAppContext, new NavigationBarActionListner(), true);
		navigationBar.setTitleText(getString(R.string.gallery));

		FooterBarFragment footerFragment = FooterBarFragment
				.newInstance(mAppContext);

		showScreen(navigationBar, content, footerFragment, GalleryFragment.TAG,
				true, true, true);
	}

	/**
	 * Shows ReservationFragment
	 */
	private void showReservationScreen() {
		ReservationFragment content = ReservationFragment.newInstance(
				mAppContext, new ReservationFragment.ActionListener() {

					@Override
					public void onEmailChosen(String email) {
						Intent i = new Intent(Intent.ACTION_SEND);
						i.setType("message/rfc822");
						i.putExtra(Intent.EXTRA_EMAIL, new String[] { email });

						try {
							startActivity(Intent.createChooser(
									i,
									getResources().getString(
											R.string.send_email)));
						} catch (android.content.ActivityNotFoundException ex) {
							Toast.makeText(MainActivity.this,
									getString(R.string.no_email_clients),
									Toast.LENGTH_SHORT).show();
						}
					}

					@Override
					public void onPhoneChosen(String phone) {
						try {

							phone = phone.replaceAll(" ", "");
							String uri = "tel:" + phone;
							Intent intent = new Intent(Intent.ACTION_DIAL);
							intent.setData(Uri.parse(uri));
							startActivity(intent);

						} catch (ActivityNotFoundException e) {
							Toast.makeText(MainActivity.this,
									getString(R.string.toast_could_not_dial),
									Toast.LENGTH_SHORT).show();
						}
					}

					@Override
					public void onReservationFormChosen() {
						showReservationFormScreen();
					}

				});
		NavigationBarFragment navigationBar = NavigationBarFragment
				.newInstance(mAppContext, new NavigationBarActionListner(), true);
		navigationBar.setTitleText(getString(R.string.reservation));

		FooterBarFragment footerFragment = FooterBarFragment
				.newInstance(mAppContext);

		showScreen(navigationBar, content, footerFragment,
				ReservationFragment.TAG, true, true, true);
	}

	/**
	 * Shows fragment content, navigation bar and social media bottom bar
	 * 
	 * @param navbar
	 *            The fragment used for navigation bar on top
	 * @param content
	 *            The fragment showing the main screen content
	 * @param contentTag
	 *            The unique tag used for the fragment transaction
	 * @param addToBackStack
	 *            boolean flag showing whether the content should be added to
	 *            backstack
	 * @param transitionNavBar
	 *            boolean flag showing if animation is used when showing the
	 *            navigationBar and bottom fragment
	 * 
	 * @param transitionContent
	 *            boolean flag showing if animation is used when showing the
	 *            main content fragment
	 */
	private void showScreen(Fragment navbar, Fragment content, Fragment footer,
			String contentTag, boolean addToBackStack,
			boolean transitionNavBar, boolean transitionContent) {

		FragmentManager fm = MainActivity.this.getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();

		// Navigation bar fade animation
		if (transitionNavBar) {
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		}
		View view = findViewById(R.id.placeholder_navigation_bar);
		if (navbar != null) {
			if (view != null) {
				view.setVisibility(View.VISIBLE);
			}
			ft.replace(R.id.placeholder_navigation_bar, navbar, null);
		} else {
			if (view != null) {
				view.setVisibility(View.GONE);
			}
		}

		// Content area slide animation
		if (transitionContent) {
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		}

		ft.replace(R.id.placeholder_content, content, contentTag);

		if (addToBackStack) {
			ft.addToBackStack(String.valueOf(System.identityHashCode(content)));
		}

		if (transitionNavBar) {
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		}

		View viewFooter = findViewById(R.id.placeholder_social_connection_bar);
		if (footer != null) {
			if (viewFooter != null) {
				viewFooter.setVisibility(View.VISIBLE);
			}
			ft.replace(R.id.placeholder_social_connection_bar, footer,
					contentTag);
			if (addToBackStack) {
				ft.addToBackStack(String.valueOf(System
						.identityHashCode(footer)));
			}
		} else {
			if (viewFooter != null) {
				viewFooter.setVisibility(View.GONE);
			}
		}

		ft.commitAllowingStateLoss();
		fm.executePendingTransactions();
	}

	/**
	 * Used to build ImageLoader configuration
	 * 
	 * @param appContext
	 *            the context of the application
	 * @return ImageLoaderConfiguration used for the UniversalImageLoader
	 * 
	 */
	@SuppressWarnings("deprecation")
	public static ImageLoaderConfiguration createImageLoaderConfiguration(
			Context appContext) {

		WindowManager windowManager = (WindowManager) appContext
				.getSystemService(Context.WINDOW_SERVICE);

		Display display = windowManager.getDefaultDisplay();
		Log.v("Utils",
				"Start building ImageLoader configuration for API level "
						+ Build.VERSION.SDK_INT);
		ImageLoaderConfiguration.Builder b = new ImageLoaderConfiguration.Builder(
				appContext);
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			b.threadPoolSize(1);
		} else {
			b.threadPoolSize(3);
		}
		b.memoryCacheExtraOptions(display.getWidth(), display.getHeight());
		b.discCacheExtraOptions(display.getWidth(), display.getHeight(),
				CompressFormat.PNG, 100, null);
		b.threadPriority(Thread.NORM_PRIORITY - 1);
		b.memoryCacheSize(MEMORY_CACHE_SIZE);
		b.discCacheSize(MAX_DISC_CACHE_SIZE);
		b.discCacheFileNameGenerator(new HashCodeFileNameGenerator());
		b.tasksProcessingOrder(QueueProcessingType.FIFO);
		b.defaultDisplayImageOptions(DisplayImageOptions.createSimple());
		return b.build();
	}
	
	@Override
	public void onBackPressed() {
		if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
			openQuitDialog();
		} else {
			super.onBackPressed();
		}
		
	}
	
	private void openQuitDialog() {
		AlertDialog.Builder quitDialog = new AlertDialog.Builder(
				MainActivity.this);
		quitDialog.setTitle("Leave " + getString(R.string.app_name) );
		quitDialog.setMessage("Are you sure you want to quit?");

		quitDialog.setPositiveButton("Quit", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		quitDialog.setNegativeButton("Cancel", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				getSupportFragmentManager().popBackStack();
			}
		});

		quitDialog.show();
	}

	/**
	 * Used to build DisplayImageOptions for ImageLoader
	 * 
	 * @param scaleType
	 *            {@link ImageScaleType}
	 * @param inMemoryCache
	 *            boolean showing if images loaded should be cached in memory
	 * @param resetViewBeforeLoading
	 *            boolean showing if the imageView should be reset before
	 *            loading
	 * @param roundedPixels
	 *            the pixels used with {@link RoundedBitmapDisplayer} to display
	 *            rounded picture corners
	 * @param emptyUrlImage
	 *            the resource to show when no image url is present
	 * @param stubImage
	 *            the resource to show when no image is yet loaded
	 * @return
	 */
	public static DisplayImageOptions buildImageOptions(
			ImageScaleType scaleType, boolean inMemoryCache,
			boolean resetViewBeforeLoading, int roundedPixels,
			int emptyUrlImage, int stubImage) {

		DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
		builder.bitmapConfig(Config.RGB_565);
		builder.showStubImage(stubImage > 0 ? stubImage : 0);
		builder.showImageForEmptyUri(emptyUrlImage > 0 ? emptyUrlImage : 0);
		if (inMemoryCache)
			builder.cacheInMemory(true);
		if (resetViewBeforeLoading)
			builder.resetViewBeforeLoading(true);
		builder.cacheOnDisc(true);
		builder.imageScaleType(scaleType);
		if (roundedPixels > 0)
			builder.displayer(new RoundedBitmapDisplayer(roundedPixels));
		builder.cacheOnDisc(true);
		return builder.build();
	}

	/**
	 * Used to calculate amount of dp in actual pixels according to the display
	 * 
	 * @param context
	 *            the application context
	 * @param dips
	 *            the amount of dp to represent in pixels
	 * @return
	 */
	public static int dipsToPixels(Context context, float dips) {
		return Math.round(TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, dips, context.getResources()
						.getDisplayMetrics()));
	}

	/**
	 * 
	 * Listener for the navigation bar fragment
	 * 
	 */
	private class NavigationBarActionListner implements
			NavigationBarFragment.ActionListener {

		@Override
		public void onMenuClicked() {
			if (mDrawerLayout != null) {
				mDrawerLayout.openDrawer(Gravity.LEFT);
			}
		}

		@Override
		public void onEmailClicked() {
			ReservationPageSettings reservationSettings = mAppContext
					.getParsedApplicationSettings()
					.getReservationPageSettings();

			if (reservationSettings != null
					&& reservationSettings.getReservationContactsList() != null
					&& reservationSettings.getReservationContactsList().size() > 0) {
				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("message/rfc822");
				i.putExtra(Intent.EXTRA_EMAIL,
						new String[] { reservationSettings
								.getReservationContactsList().get(0)
								.getContactEmail() });

				try {
					startActivity(Intent.createChooser(i, getResources()
							.getString(R.string.send_email)));
				} catch (android.content.ActivityNotFoundException ex) {
					Toast.makeText(MainActivity.this,
							getString(R.string.no_email_clients),
							Toast.LENGTH_SHORT).show();
				}

			}
		}

		@Override
		public void onBackPressed() {
			getSupportFragmentManager().popBackStack();
		}
	}
	
}
