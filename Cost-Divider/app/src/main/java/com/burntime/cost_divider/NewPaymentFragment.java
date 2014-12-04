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

/**
 * Created by Colten on 12/3/2014.
 */
public class NewPaymentFragment extends DialogFragment {
    private Spinner mPaidFrom;
    private EditText mAmount;
    private Spinner mPaidTo;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.new_payment_dialog,null);

        ArrayAdapter<Party> adapter = new ArrayAdapter<Party>(getActivity(),
                android.R.layout.simple_list_item_1, Household.get(getActivity()).getParties());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mAmount = (EditText) layout.findViewById(R.id.purchase_amount);
        mPaidFrom = (Spinner) layout.findViewById(R.id.payer);
        mPaidTo = (Spinner) layout.findViewById(R.id.payee);
        mPaidFrom.setAdapter(adapter);
        mPaidTo.setAdapter(adapter);

        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
        b.setView(layout)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Household h = Household.get(getActivity());
                        h.addPayment(new Payment(
                                Double.parseDouble(mAmount.getText().toString()),
                                h.getParties().get(mPaidFrom.getSelectedItemPosition()).getName(),
                                h.getParties().get(mPaidTo.getSelectedItemPosition()).getName()));
                    }
                });

        return b.create();
    }
}