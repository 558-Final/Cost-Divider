package com.burntime.cost_divider;

import android.app.Activity;
import android.app.DialogFragment;

/**
 * Created by Colten on 12/3/2014.
 */
public abstract class AbstractNewTransactionDialogFragment extends DialogFragment{
    public interface NewTransactionDialogListener{
        public void newTransaction();
    }

    NewTransactionDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (NewTransactionDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnTimeDialogListener");
        }
    }
}
