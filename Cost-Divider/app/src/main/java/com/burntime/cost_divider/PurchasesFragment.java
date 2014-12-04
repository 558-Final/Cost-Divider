package com.burntime.cost_divider;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.burntime.cost_divider.Things.Purchase;

import java.util.List;

/**
 * Created by Colten on 8/21/2014.
 */
public class PurchasesFragment extends ListFragment{

    protected List<Purchase> mPurchases;
    protected ArrayAdapter<Purchase> mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_purchases, container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPurchases = Household.get(getActivity()).getPurchases();
        mAdapter = new ArrayAdapter<Purchase>(getActivity(), android.R.layout.simple_list_item_1, mPurchases);
        setListAdapter(mAdapter);
    }

    public void refresh() {
        mPurchases = Household.get(getActivity()).getPurchases();
        mAdapter.notifyDataSetChanged();
    }
}
