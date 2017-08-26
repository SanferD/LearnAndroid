package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by sanfer on 8/25/17.
 */

public class DatePickerFragment extends DialogFragment {

    public static final String EXTRA_YEAR = "com.bignerdranch.android.criminalintent.year";
    public static final String EXTRA_MONTH = "com.bignerdranch.android.criminalintent.month";
    public static final String EXTRA_DAYOFMONTH = "com.bignerdranch.android.criminalintent.dayOfMonth";
    private static final String ARG_DATE = "date";

    private DatePicker mDatePicker;
    private Button mOkButton;

    public static int getYear(Intent data) {
        return data.getIntExtra(EXTRA_YEAR, 0);
    }

    public static int getMonth(Intent data) {
        return data.getIntExtra(EXTRA_MONTH, 0);
    }

    public static int getDayOfMonth(Intent data) {
        return data.getIntExtra(EXTRA_DAYOFMONTH, 0);
    }

    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void sendResult(int resultCode, int year, int month, int dayOfMonth) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_YEAR, year);
        intent.putExtra(EXTRA_MONTH, month);
        intent.putExtra(EXTRA_DAYOFMONTH, dayOfMonth);

        Fragment frag = getTargetFragment();
        if (frag == null)
            getActivity().setResult(Activity.RESULT_OK, intent);
        else
            frag.onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Date date = (Date) getArguments().getSerializable(ARG_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);

        mDatePicker = (DatePicker) v.findViewById(R.id.dialog_date_picker);
        mDatePicker.init(year, month, day, null);

        mOkButton = (Button) v.findViewById(R.id.ok_button);
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = mDatePicker.getYear();
                int month = mDatePicker.getMonth();
                int day = mDatePicker.getDayOfMonth();
                sendResult(Activity.RESULT_OK, year, month, day);

                Fragment fragment = getTargetFragment();
                if (fragment == null)
                    getActivity().finish();
                else
                    DatePickerFragment.this.onDestroyView();
            }
        });

        return v;
    }
}
