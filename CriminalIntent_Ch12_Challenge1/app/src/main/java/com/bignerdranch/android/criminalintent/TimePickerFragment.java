package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by sanfer on 8/25/17.
 */

public class TimePickerFragment extends DialogFragment {
    private TimePicker mTimePicker;
    private static final String ARG_HOUR = "hour";
    private static final String ARG_MINUTE = "minute";
    private static final String EXTRA_HOUR = "com.bignerdranch.android.criminalintent.hour";
    private static final String EXTRA_MINUTE = "com.bignerdranch.android.criminalintent.minute";

    public static int getHour(Intent data) {
        return data.getIntExtra(EXTRA_HOUR, 0);
    }

    public static int getMinute(Intent data) {
        return data.getIntExtra(EXTRA_MINUTE, 0);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time, null);

        int hour = getArguments().getInt(ARG_HOUR);
        int minute = getArguments().getInt(ARG_MINUTE);
        mTimePicker = (TimePicker) v.findViewById(R.id.dialog_time_picker);
        if (Build.VERSION.SDK_INT >= 23) {
            mTimePicker.setHour(hour);
            mTimePicker.setMinute(minute);
        } else {
            mTimePicker.setCurrentHour(hour);
            mTimePicker.setCurrentMinute(minute);
        }

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.time_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int hour;
                        int minute;

                        if (Build.VERSION.SDK_INT >= 23) {
                            hour = mTimePicker.getHour();
                            minute = mTimePicker.getMinute();
                        } else {
                            hour = mTimePicker.getCurrentHour();
                            minute = mTimePicker.getCurrentMinute();
                        }

                        sendResponse(Activity.RESULT_OK, hour, minute);
                    }
                })
                .setView(v)
                .create();
    }

    private void sendResponse(int resultCode, int hour, int minute) {
        Intent data = new Intent();
        data.putExtra(EXTRA_HOUR, hour);
        data.putExtra(EXTRA_MINUTE, minute);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, data);
    }

    public static TimePickerFragment newInstance(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        Bundle args = new Bundle();
        args.putInt(ARG_HOUR, hour);
        args.putInt(ARG_MINUTE, minute);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
