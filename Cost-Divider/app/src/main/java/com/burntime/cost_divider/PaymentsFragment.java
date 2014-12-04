package com.burntime.cost_divider;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.burntime.cost_divider.Things.Payment;

import java.util.List;

// import com.burntime.cost_divider.not_needed.MessageAdapter;

/**
 * Created by Colten on 8/21/2014.
 */
public class PaymentsFragment extends ListFragment {

    protected List<Payment> mPayments;
    ArrayAdapter<Payment> mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_payments, container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPayments = Household.get(getActivity()).getPayments();
        mAdapter = new ArrayAdapter<Payment>(getActivity(), android.R.layout.simple_list_item_1, mPayments);
        setListAdapter(mAdapter);
    }

    public void refresh() {
        mPayments = Household.get(getActivity()).getPayments();
        mAdapter.notifyDataSetChanged();
    }
}