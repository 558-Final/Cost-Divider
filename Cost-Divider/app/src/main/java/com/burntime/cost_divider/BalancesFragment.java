package com.burntime.cost_divider;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import android.util.Log;
// import com.burntime.cost_divider.not_needed.MessageAdapter;

/**
 * Created by Colten on 8/21/2014.
 */
public class BalancesFragment extends ListFragment {

    protected ArrayList<String> mBalances;
    ArrayAdapter<String> mAdapter;

    public ArrayList<String> getmBalances() {
        return mBalances;
    }
    public void refresh(){
        Log.d("refresh", "refreshing BalanceFragment");
        this.onResume();
        mBalances = Household.get(getActivity()).getBalances();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("onCreateView", "create view.");
        View rootView = inflater.inflate(R.layout.fragment_balances, container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResume","resuming Balance Fragment");
        mBalances = Household.get(getActivity()).getBalances();
        mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mBalances);
        setListAdapter(mAdapter);
    }
}