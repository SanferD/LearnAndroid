package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.Date;

/**
 * Created by sanfer on 8/25/17.
 */

public class DatePickerActivity extends SingleFragmentActivity {
    private static final String EXTRA_DATE = "com.bignerdranch.android.criminalintent.date";

    @Override
    protected Fragment createFragment() {
        Date date = (Date) getIntent().getSerializableExtra(EXTRA_DATE);
        return DatePickerFragment.newInstance(date);
    }

    public static Intent newIntent(Context context, Date date) {
        Intent intent = new Intent(context, DatePickerActivity.class);
        intent.putExtra(EXTRA_DATE, date);
        return intent;
    }
}
