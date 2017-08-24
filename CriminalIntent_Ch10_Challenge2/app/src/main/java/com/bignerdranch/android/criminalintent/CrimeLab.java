package com.bignerdranch.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

/**
 * Created by sanfer on 8/22/17.
 */

public class CrimeLab {

    private static CrimeLab sCrimeLab;

    private List<Crime> mCrimes;
    private Hashtable<UUID, Crime> mCrimesTable;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mCrimes = new ArrayList<>();
        mCrimesTable = new Hashtable<>();
        
        for (int i=0; i<100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0); // Every other one
            mCrimes.add(crime);
            mCrimesTable.put(crime.getId(), crime);
        }

    }

    public Crime getCrime(UUID id) {
        return mCrimesTable.get(id);
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

}
