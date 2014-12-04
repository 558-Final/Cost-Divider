package com.burntime.cost_divider;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.content.Context;

import java.util.Locale;

/**
 * A {@link android.support.v13.app.FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {


    /*TODO: Update this to show the appropriate fragment. Need to add one to the switch and the count.
    * */


     protected Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position){
            case 0:
                return new BalancesFragment();
            case 1:
                return new PurchasesFragment();
            case 2:
                return new PaymentsFragment();
            default:
                return new PaymentsFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return mContext.getString(R.string.title_balances).toUpperCase(l);
            case 1:
                return mContext.getString(R.string.title_purchases).toUpperCase(l);
            case 2:
                return mContext.getString(R.string.title_payments).toUpperCase(l);
        }
        return null;
    }
}
