package com.burntime.cost_divider;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Colten on 12/3/2014.
 *
 * This is based on BNRG's CrimeLab.java
 */
public class Household {
    private static final String TAG = "TransList";
    private static final String PURCHASES_FN = "payments.json";
    private static final String PAYMENTS_FN = "purchases.json";

    private ArrayList<Payment> mPayments;
    private ArrayList<Purchase> mPurchases;
    private ArrayList<Party> mParties;
    private int sumOfParties;

    private PaymentJSONSerializer mPaymentSerializer;
    private PurchaseJSONSerializer mPurchaseSerializer;

    private static Household sHousehold;
    private Context mAppContext;
    private double[][] owes;

    private Household(Context appContext){
        mAppContext = appContext;
        mPayments = new ArrayList<Payment>();
        mPurchases = new ArrayList<Purchase>();
        mParties = new ArrayList<Party>();
        try {
            mPayments = mPaymentSerializer.loadTrans();
            for(Payment p : mPayments)
            {
                recalcOwesForPayment(p);
            }
        } catch (Exception e) {
            mPayments = new ArrayList<Payment>();
            Log.e(TAG, "Error loading payments: ", e);
        }
        try {
            mPurchases = mPurchaseSerializer.loadTrans();
            for(Purchase p : mPurchases)
            {
                recalcOwesForPurchase(p);
            }
        } catch (Exception e) {
            mPurchases = new ArrayList<Purchase>();
            Log.e(TAG, "Error loading purchases: ", e);
        }


        addParties();

        owes = new double[mParties.size()][mParties.size()];
        // Calculate the sum of all party members
        for (Party p : mParties){
            sumOfParties = p.getCount();
        }
    }

    private void addParties() {
        // Manually add parties
        mParties = new ArrayList<Party>();
        mParties.add(new Party("Colten", 1));
        mParties.add(new Party("Michael", 1));
        mParties.add(new Party("Roy", 1));
    }

    public static Household get(Context c) {
        if (sHousehold == null) {
            sHousehold = new Household(c.getApplicationContext());
        }
        return sHousehold;
    }

    public ArrayList<Payment> getPayments(){
        return mPayments;
    }

    public ArrayList<Purchase> getPurchases(){
        return mPurchases;
    }

    public ArrayList<String> getBalances(){
        ArrayList<String> balances = new ArrayList<String>();
        double amtOwed;
        for (int i = 0; i < mParties.size(); i++)
        {
            for (int j = 0; j < mParties.size(); j++)
            {
                if (i != j)
                {
                    amtOwed = owes[j][i] - owes[i][j];
                    if (amtOwed > 0)
                    {
                        balances.add(mParties.get(i).getName() + " owes " + mParties.get(j).getName() + " " + MainActivity.currencyFormatter.format(amtOwed));
                    }
                }
            }
        }
        return balances;
    }

    public ArrayList<Party> getParties(){
        return mParties;
    }

    public void addPurchase(Purchase t){
        recalcOwesForPurchase(t);
        mPurchases.add(t);
        savePurchases();
    }

    private void recalcOwesForPurchase(Purchase t) {
        int pPaidBy = -1;
        for( int i = 0; i < mParties.size(); i++)
        {
            if (t.getPaidBy() == mParties.get(i).getName()){
                pPaidBy = i;
                break;
            }
        }
        for (int i = 0; i < mParties.size(); i++)
        {
            if (i != pPaidBy)
                owes[pPaidBy][i] += (t.getAmount() / sumOfParties) * mParties.get(i).getCount();
        }
    }

    private void recalcOwesForPayment(Payment t) {
        int pPaidFrom = -1;
        int pPaidTo = -1;
        for( int i = 0; i < mParties.size(); i++)
        {
            if (t.getPaidFrom() == mParties.get(i).getName()){
                pPaidFrom = i;
            }
            if (t.getPaidTo() == mParties.get(i).getName()){
                pPaidTo = i;
            }
        }
        owes[pPaidTo][pPaidFrom] -= t.getAmount();
    }

    public void addPayment(Payment t){
        recalcOwesForPayment(t);
        mPayments.add(t);
        savePayments();
    }

    private boolean savePurchases(){
        try {
            mPurchaseSerializer.saveTrans(mPurchases);
            Log.d(TAG, "Purchases saved.");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving purchases.");
            return false;
        }
    }

    private boolean savePayments(){
        try {
            mPaymentSerializer.saveTrans(mPayments);
            Log.d(TAG, "Payments saved.");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving payments.");
            return false;
        }
    }


}
