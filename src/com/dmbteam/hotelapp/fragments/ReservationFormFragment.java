package com.dmbteam.hotelapp.fragments;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dmbteam.hotelapp.ApplicationContext;
import com.dmbteam.hotelapp.R;
import com.dmbteam.hotelapp.models.ReservationPageSettings;

public class ReservationFormFragment extends Fragment {

	public static final String TAG = ReservationFormFragment.class
			.getSimpleName();

	private ApplicationContext mAppContext;

	private TextView bookButton;
	private TextView nightsCount;
	private TextView roomsCount;
	private TextView adultsCount;
	private TextView childrenCount;

	private EditText checkIn;
	private EditText checkOut;
	private EditText firstName;
	private EditText lastName;
	private EditText email;
	private EditText phone;

	private Spinner rooms;
	private Spinner adults;
	private Spinner children;

	public static ReservationFormFragment newInstance(
			ApplicationContext appContext) {

		ReservationFormFragment fragment = new ReservationFormFragment();
		fragment.mAppContext = appContext;

		return fragment;
	}

	// the callback received when the user "sets" the date in the dialog
	public DatePickerDialog.OnDateSetListener mDateCheckInSetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			updateCheckInDisplay(year, monthOfYear, dayOfMonth);
			updateNightsCount();
		}

	};

	// the callback received when the user "sets" the date in the dialog
	public DatePickerDialog.OnDateSetListener mDateCheckOutSetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			updateCheckOutDisplay(year, monthOfYear, dayOfMonth);
			updateNightsCount();
		}
	};

	private void updateNightsCount() {
		try {
			String daysBetween = getDaysBetweenString(
					createDateFromString(checkIn.getText().toString()),
					createDateFromString(checkOut.getText().toString()));
			nightsCount.setText(daysBetween);
		} catch (Exception e) {
			// TODO: show toast probably?
		}
	}

	private void updateCheckInDisplay(int year, int month, int day) {
		String result = formatNumber(day, 2) + "/" + formatNumber(month + 1, 2)
				+ "/" + String.valueOf(year);
		checkIn.setText(result);
		checkIn.setBackgroundResource(R.drawable.reservation_calendar_field_active);
	}

	private Date createDateFromString(String dateString) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			date = format.parse(dateString);
			System.out.println(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	private void updateCheckOutDisplay(int year, int month, int day) {
		String result = formatNumber(day, 2) + "/" + formatNumber(month + 1, 2)
				+ "/" + String.valueOf(year);
		checkOut.setText(result);
		checkOut.setBackgroundResource(R.drawable.reservation_calendar_field_active);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_reservation_form,
				container, false);

		bookButton = (TextView) view.findViewById(R.id.bookButton);
		bookButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ReservationPageSettings reservationSettings = mAppContext
						.getParsedApplicationSettings()
						.getReservationPageSettings();

				// TODO: validation
				if (reservationSettings != null
						&& reservationSettings.getReservationContactsList() != null
						&& reservationSettings.getReservationContactsList()
								.size() > 0) {
					if (isFormValid()) {
						Intent mailIntent = new Intent(Intent.ACTION_SEND);

						mailIntent.putExtra(Intent.EXTRA_EMAIL,
								new String[] { reservationSettings
										.getReservationContactsList().get(0)
										.getContactEmail() });
						mailIntent.putExtra(Intent.EXTRA_SUBJECT,
								"Reservation Request");
						mailIntent.putExtra(Intent.EXTRA_TEXT,
								formatEmailMessage());
						mailIntent.setType("plain/text");

						getActivity().startActivity(
								Intent.createChooser(mailIntent,
										"Choose your mail client"));
					}

				} else {
					Toast.makeText(getActivity(),
							getString(R.string.no_email_clients),
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		nightsCount = (TextView) view.findViewById(R.id.nightsCount);

		roomsCount = (TextView) view.findViewById(R.id.roomsCount);

		adultsCount = (TextView) view.findViewById(R.id.adultsCount);

		childrenCount = (TextView) view.findViewById(R.id.childrenCount);

		checkIn = (EditText) view.findViewById(R.id.checkIn);

		checkIn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Calendar calendar = Calendar.getInstance();
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				DatePickerDialog dialog = new DatePickerDialog(getActivity(),
						mDateCheckInSetListener, year, month, day);
				dialog.show();
			}
		});

		checkOut = (EditText) view.findViewById(R.id.checkOut);

		checkOut.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Calendar calendar = Calendar.getInstance();
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				DatePickerDialog dialog = new DatePickerDialog(getActivity(),
						mDateCheckOutSetListener, year, month, day);
				dialog.show();
			}
		});

		firstName = (EditText) view.findViewById(R.id.firstName);
		firstName.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				if (firstName.getText() != null && firstName.getText().toString().trim().length() > 0) {
					firstName.setBackgroundResource(R.drawable.reservation_input_field_active);
				} else {
					firstName.setBackgroundResource(R.drawable.reservation_input_field);
				}
				
			}
		});

		lastName = (EditText) view.findViewById(R.id.lastName);
		lastName.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				if (lastName.getText() != null && lastName.getText().toString().trim().length() > 0) {
					lastName.setBackgroundResource(R.drawable.reservation_input_field_active);
				} else {
					lastName.setBackgroundResource(R.drawable.reservation_input_field);
				}
				
			}
		});

		email = (EditText) view.findViewById(R.id.email);
		email.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				if (email.getText() != null && email.getText().toString().trim().length() > 0) {
					email.setBackgroundResource(R.drawable.reservation_input_field_active);
				} else {
					email.setBackgroundResource(R.drawable.reservation_input_field);
				}
				
			}
		});

		phone = (EditText) view.findViewById(R.id.phone);
		phone.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				if (phone.getText() != null && phone.getText().toString().trim().length() > 0) {
					phone.setBackgroundResource(R.drawable.reservation_input_field_active);
				} else {
					phone.setBackgroundResource(R.drawable.reservation_input_field);
				}
				
			}
		});

		rooms = (Spinner) view.findViewById(R.id.rooms);
		rooms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				String roomsSelected = (String) rooms.getSelectedItem();
				if (!roomsSelected.equalsIgnoreCase(getActivity()
						.getResources().getString(R.string.rooms_res))) {
					roomsCount.setText(roomsSelected);
					rooms.setBackgroundResource(R.drawable.reservation_dropdown_field_active);
				} else {
					rooms.setBackgroundResource(R.drawable.reservation_dropdown_field);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		adults = (Spinner) view.findViewById(R.id.adults);
		adults.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				String adultsSelected = (String) adults.getSelectedItem();
				if (!adultsSelected.equalsIgnoreCase(getActivity()
						.getResources().getString(R.string.adults))) {
					adultsCount.setText(adultsSelected);
					adults.setBackgroundResource(R.drawable.reservation_dropdown_field_active);
				} else {
					adults.setBackgroundResource(R.drawable.reservation_dropdown_field);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		children = (Spinner) view.findViewById(R.id.children);
		children.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				String childrenSelected = (String) children.getSelectedItem();
				if (!childrenSelected.equalsIgnoreCase(getActivity()
						.getResources().getString(R.string.children))) {
					childrenCount.setText(childrenSelected);
					children.setBackgroundResource(R.drawable.reservation_dropdown_field_active);
				} else {
					children.setBackgroundResource(R.drawable.reservation_dropdown_field);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		return view;
	}

	/**
	 * Returns formatted number with digidCount length.
	 * 
	 * @param number
	 *            - to be formatted.
	 * @param digidCount
	 *            - the desire length.
	 * @return formatted number.
	 */
	public static String formatNumber(int number, int digidCount) {
		String result = Integer.toString(number);

		if (result.length() > digidCount) {
			result = result.substring(0, digidCount);
		}

		// fill with leading zero.
		for (int i = 0, size = digidCount - result.length(); i < size; i++) {
			result = "0" + result;
		}
		return result;
	}

	private boolean isFormValid() {
		boolean result = true;
		StringBuilder invalidText = new StringBuilder("");
		if (Integer.parseInt(nightsCount.getText().toString()) <= 0) {
			if (!invalidText.toString().equalsIgnoreCase("")) {
				invalidText.append("\n");
			}
			invalidText
					.append("Check-Out is earlier or equal to Check-In!");
			result = false;
		}
		
		String roomsSelected = (String) rooms.getSelectedItem();
		if (roomsSelected.equalsIgnoreCase(getActivity().getResources()
				.getString(R.string.rooms_res))) {
			if (!invalidText.toString().equalsIgnoreCase("")) {
				invalidText.append("\n");
			}
			invalidText.append("Room(s) is mandatory!");
			result = false;
		}
		
		String adultsSelected = (String) adults.getSelectedItem();
		if (adultsSelected.equalsIgnoreCase(getActivity().getResources()
				.getString(R.string.adults))) {
			if (!invalidText.toString().equalsIgnoreCase("")) {
				invalidText.append("\n");
			}
			invalidText.append("Adult(s) is mandatory!");
			result = false;
		}
		
		String childrenSelected = (String) children.getSelectedItem();
		if (childrenSelected.equalsIgnoreCase(getActivity().getResources()
				.getString(R.string.children))) {
			if (!invalidText.toString().equalsIgnoreCase("")) {
				invalidText.append("\n");
			}
			invalidText.append("Kid(s) is mandatory!");
			result = false;
		}
		
		if (firstName.getText() == null
				|| firstName.getText().toString().trim().length() == 0) {
			invalidText.append("First Name is mandatory!");
			result = false;
		}
		
		if (lastName.getText() == null
				|| lastName.getText().toString().trim().length() == 0) {
			if (!invalidText.toString().equalsIgnoreCase("")) {
				invalidText.append("\n");
			}
			invalidText.append("Last Name is mandatory!");
			result = false;
		}
		
		if (email.getText() == null
				|| email.getText().toString().trim().length() == 0) {
			if (!invalidText.toString().equalsIgnoreCase("")) {
				invalidText.append("\n");
			}
			invalidText.append("Email is mandatory!");
			result = false;
		}
		
		if (phone.getText() == null
				|| phone.getText().toString().trim().length() == 0) {
			if (!invalidText.toString().equalsIgnoreCase("")) {
				invalidText.append("\n");
			}
			invalidText.append("Phone is mandatory!");
			result = false;
		}
		
		if (!result) {
			Toast.makeText(getActivity(), invalidText, Toast.LENGTH_SHORT)
					.show();
		}
		return result;
	}

	/**
	 * Returns a string that describes the number of days between dateOne and
	 * dateTwo.
	 * 
	 */

	public String getDaysBetweenString(Date dateOne, Date dateTwo) {
		long timeOne = dateOne.getTime();
		long timeTwo = dateTwo.getTime();
		long oneDay = 1000 * 60 * 60 * 24;
		long delta = (timeTwo - timeOne) / oneDay;
		if (delta > 0) {
			return String.valueOf(delta);
		} else {
			return "0";
		}

	}

	private String formatEmailMessage() {
		StringBuilder message = new StringBuilder("");
		message.append("Arrival/Departure:");
		message.append("\n");
		message.append("	Check-In: " + checkIn.getText().toString());
		message.append("\n");
		message.append("	Check-Out: " + checkOut.getText().toString());
		message.append("\n");
		message.append("	Nights: " + nightsCount.getText().toString());
		message.append("\n");
		message.append("\n");
		message.append("Occupation:");
		message.append("\n");
		message.append("	Rooms: " + roomsCount.getText().toString());
		message.append("\n");
		message.append("	Adults: " + adultsCount.getText().toString());
		message.append("\n");
		message.append("	Children: " + childrenCount.getText().toString());
		message.append("\n");
		message.append("\n");
		message.append("Personal Information:");
		message.append("\n");
		message.append("	First Name: " + firstName.getText().toString());
		message.append("\n");
		message.append("	Last Name: " + lastName.getText().toString());
		message.append("\n");
		message.append("	Email: " + email.getText().toString());
		message.append("\n");
		message.append("	Phone: " + phone.getText().toString());

		return message.toString();
	}

}
