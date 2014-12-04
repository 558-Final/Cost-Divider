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
import com.burntime.cost_divider.Things.Purchase;

public class NewPurchaseFragment extends AbstractNewTransactionDialogFragment {
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

        final AlertDialog d = new AlertDialog.Builder(getActivity())
            .setView(layout)
            .setTitle("Add New Purchase")
            .setCancelable(true)
            .setNegativeButton("Cancel",null)
            .setPositiveButton("Save", null)
            .create();

        d.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button b = d.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double amount;
                        try {
                            amount = Double.parseDouble(mAmount.getText().toString());
                        } catch (Exception e){
                            Toast.makeText(getActivity(), getString(R.string.no_amount_toast), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Household h = Household.get(getActivity());
                        h.addPurchase(new Purchase(amount, h.getParties()
                                        .get(mPaidBy.getSelectedItemPosition()).getName()));
                        mListener.newTransaction();
                        d.dismiss();
                    }
                });
            }
        });
        return d;
    }
}
