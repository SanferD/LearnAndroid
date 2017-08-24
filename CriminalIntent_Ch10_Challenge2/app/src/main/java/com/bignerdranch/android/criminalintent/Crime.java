package com.bignerdranch.android.criminalintent;

import android.support.annotation.NonNull;

import java.util.Date;
import java.util.UUID;

/**
 * Created by sanfer on 8/21/17.
 */

public class Crime implements Comparable<Crime> {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    @Override
    public int compareTo(@NonNull Crime crime) {
        return mId.compareTo(crime.getId());
    }
}
