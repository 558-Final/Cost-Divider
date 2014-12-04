package com.burntime.cost_divider;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.burntime.cost_divider.not_needed.ParseConstants;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by Colten on 8/21/2014.
 */
public class PurchasesFragment extends ListFragment{

    protected List<Purchase> mPurchases;

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
        ArrayAdapter<Purchase> adapter = new ArrayAdapter<Purchase>(getActivity(), android.R.layout.simple_list_item_1, mPurchases);
        setListAdapter(adapter);
    }
}
