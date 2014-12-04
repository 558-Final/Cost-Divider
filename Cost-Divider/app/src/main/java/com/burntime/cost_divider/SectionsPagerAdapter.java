package com.burntime.cost_divider;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.Locale;

/**
 * A {@link android.support.v13.app.FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public final int BALANCES = 0;
    public final int PURCHASES = 1;
    public final int PAYMENTS = 2;

    protected Context mContext;
    SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

    BalancesFragment mBalances;
    PurchasesFragment mPurchases;
    PaymentsFragment mPayments;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    public Fragment getFragment(int position){
        return registeredFragments.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        switch (position) {
            case BALANCES:
                mBalances = (BalancesFragment) super.instantiateItem(container, position);
                return mBalances;
            case PURCHASES:
                mPurchases = (PurchasesFragment) super.instantiateItem(container, position);
                return mPurchases;
            case PAYMENTS:
                mPayments = (PaymentsFragment) super.instantiateItem(container, position);
                return mPayments;
            default:
                return (Fragment) super.instantiateItem(container, position);
        }
        //registeredFragments.put(position, frag);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position){
            case BALANCES:
                return new BalancesFragment();
            case PURCHASES:
                return new PurchasesFragment();
            case PAYMENTS:
                return new PaymentsFragment();
            default:
                return new BalancesFragment();
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
            case BALANCES:
                return mContext.getString(R.string.title_balances).toUpperCase(l);
            case PURCHASES:
                return mContext.getString(R.string.title_purchases).toUpperCase(l);
            case PAYMENTS:
                return mContext.getString(R.string.title_payments).toUpperCase(l);
        }
        return null;
    }

    public void refreshFragment(int position) {
        switch (position) {
            case BALANCES:
                if (mBalances != null) mBalances.refresh();
                break;
            case PURCHASES:
                if (mPurchases != null) mPurchases.refresh();
                break;
            case PAYMENTS:
                if (mPayments != null) mPayments.refresh();
                break;
        }
    }
}
