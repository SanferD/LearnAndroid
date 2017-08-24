package com.bignerdranch.android.criminalintent;

/**
 * Created by sanfer on 8/24/17.
 */

public interface PageJumper {
    public boolean canJumpToFirstPage();
    public boolean canJumpToLastPage();
    public void JumpToFirstPage();
    public void JumpToLastPage();
}
