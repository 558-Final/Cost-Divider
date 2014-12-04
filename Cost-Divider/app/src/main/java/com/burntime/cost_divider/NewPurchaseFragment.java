package com.burntime.cost_divider;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class NewPurchaseFragment extends DialogFragment {
    private Spinner mPaidBy;
    private EditText mAmount;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.new_purchase_dialog,null);

        ArrayAdapter<Party> adapter = new ArrayAdapter<Party>(getActivity(),
                android.R.layout.simple_list_item_1, Household.get(getActivity()).getParties());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mAmount = (EditText) layout.findViewById(R.id.purchase_amount);
        mPaidBy = (Spinner) layout.findViewById(R.id.purchaser);
        mPaidBy.setAdapter(adapter);

        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
           b.setView(layout)
            .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Household.get(getActivity()).addPurchase(
                            new Purchase(
                                    Double.parseDouble(mAmount.getText().toString()),
                                    Household.get(getActivity()).getParties()
                                            .get(mPaidBy.getSelectedItemPosition()).getName()));
                }
            });

        return b.create();
    }
}
