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

public class NewPartyFragment extends AbstractNewTransactionDialogFragment {
    private EditText mName;
    private EditText mCount;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.new_party_dialog,null);

        mName = (EditText) layout.findViewById(R.id.party_name);
        mCount = (EditText) layout.findViewById(R.id.party_count);

        final AlertDialog d = new AlertDialog.Builder(getActivity())
            .setView(layout)
            .setTitle("Create New Party")
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
                        int count;
                        try{
                            count = Integer.parseInt(mCount.getText().toString());
                        } catch (Exception e){
                            Toast.makeText(getActivity(), getString(R.string.no_amount_toast), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        String name = mName.getText().toString();
                        if (name.isEmpty()) {
                            Toast.makeText(getActivity(), "Please enter a name.", Toast.LENGTH_SHORT).show();
                        }else {
                            Household.get(getActivity()).addParty(new Party(name, count));
                            d.dismiss();
                            mListener.newTransaction();
                        }
                    }
                });
            }
        });
        return d;
    }
}
