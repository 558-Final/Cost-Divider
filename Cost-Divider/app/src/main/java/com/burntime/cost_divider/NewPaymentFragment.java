package com.burntime.cost_divider;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.burntime.cost_divider.Things.Party;
import com.burntime.cost_divider.Things.Payment;

/**
 * Created by Colten on 12/3/2014.
 */
public class NewPaymentFragment extends AbstractNewTransactionDialogFragment {
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

        final AlertDialog d = new AlertDialog.Builder(getActivity())
                .setView(layout)
                .setTitle("Add New Payment")
                .setCancelable(true)
                .setNegativeButton("Cancel",null)
                .setPositiveButton("Save", null)
                .create();

        d.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button b = d.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double amount;
                        try {
                            amount = Double.parseDouble(mAmount.getText().toString());
                        } catch (Exception e) {
                            Toast.makeText(getActivity(), getString(R.string.no_amount_toast), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (mPaidFrom.getSelectedItemPosition() == mPaidTo.getSelectedItemPosition()) {
                            Toast.makeText(getActivity(), getString(R.string.same_party_toast), Toast.LENGTH_SHORT).show();
                        } else {
                            Household h = Household.get(getActivity());
                            h.addPayment(new Payment(amount,
                                    h.getParties().get(mPaidFrom.getSelectedItemPosition()).getName(),
                                    h.getParties().get(mPaidTo.getSelectedItemPosition()).getName()));
                            mListener.newTransaction();
                            d.dismiss();
                        }
                    }
                });
            }
        });
        return d;
    }
}