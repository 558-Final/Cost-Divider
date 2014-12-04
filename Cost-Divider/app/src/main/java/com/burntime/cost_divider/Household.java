package com.burntime.cost_divider;

import android.content.Context;
import android.util.Log;

import com.burntime.cost_divider.JSON.PartyJSONSerializer;
import com.burntime.cost_divider.JSON.PaymentJSONSerializer;
import com.burntime.cost_divider.JSON.PurchaseJSONSerializer;
import com.burntime.cost_divider.Things.AbstractJSONThing;
import com.burntime.cost_divider.Things.Party;
import com.burntime.cost_divider.Things.Payment;
import com.burntime.cost_divider.Things.Purchase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Colten on 12/3/2014.
 *
 * This is based on BNRG's CrimeLab.java
 */
public class Household {
    private static final String TAG = Household.class.getSimpleName();
    private static final String PURCHASES_FN = "purchases.json";
    private static final String PAYMENTS_FN = "payments.json";
    private static final String PARTIES_FN = "parties.json";
    private static final double MINIMUM_BALANCE = 0.01;

    private ArrayList<Payment> mPayments;
    private ArrayList<Purchase> mPurchases;
    private ArrayList<Party> mParties;
    private int sumOfParties;

    private PaymentJSONSerializer mPaymentSerializer;
    private PurchaseJSONSerializer mPurchaseSerializer;
    private PartyJSONSerializer mPartySerializer;

    private static Household sHousehold;
    private Context mAppContext;
    private double[][] owes;

    private Household(Context appContext){
        mAppContext = appContext;

        mPayments = new ArrayList<Payment>();
        mPurchases = new ArrayList<Purchase>();
        mParties = new ArrayList<Party>();

        mPurchaseSerializer = new PurchaseJSONSerializer(mAppContext, PURCHASES_FN);
        mPaymentSerializer = new PaymentJSONSerializer(mAppContext, PAYMENTS_FN);
        mPartySerializer = new PartyJSONSerializer(mAppContext, PARTIES_FN);

        try {
            mParties = mPartySerializer.loadParties();
            owes = new double[mParties.size()][mParties.size()];
            // Calculate the sum of all party members
            for (Party p : mParties){
                sumOfParties += p.getCount();
            }
        } catch (Exception e) {
            mParties = new ArrayList<Party>();
            Log.e(TAG, "Error loading parties: ", e);
        }
        try {
            mPayments = mPaymentSerializer.loadPayments();
            for(Payment p : mPayments)
            {
                recalcOwesForPayment(p);
            }
        } catch (Exception e) {
            mPayments = new ArrayList<Payment>();
            Log.e(TAG, "Error loading payments: ", e);
        }
        try {
            mPurchases = mPurchaseSerializer.loadPurchases();
            for(Purchase p : mPurchases)
            {
                recalcOwesForPurchase(p);
            }
        } catch (Exception e) {
            mPurchases = new ArrayList<Purchase>();
            Log.e(TAG, "Error loading purchases: ", e);
        }
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
        if(owes != null) {
            for (int i = 0; i < mParties.size(); i++) {
                for (int j = 0; j < mParties.size(); j++) {
                    if (i < j) {
                        amtOwed = owes[j][i] - owes[i][j];
                        if (amtOwed > MINIMUM_BALANCE) {
                            balances.add(mParties.get(i).getName() + " owes " + mParties.get(j).getName() + " " + MainActivity.currencyFormatter.format(amtOwed));
                        }
                        else if(amtOwed < -MINIMUM_BALANCE){
                            balances.add(mParties.get(j).getName() + " owes " + mParties.get(i).getName() + " " + MainActivity.currencyFormatter.format(-amtOwed));
                        }
                        else{
                            balances.add(mParties.get(i).getName() + " and " + mParties.get(j).getName() + " are square.");
                        }
                    }
                }
            }
        }
        return balances;
    }

    public ArrayList<Party> getParties(){
        return mParties;
    }

    public void addParty(Party p){
        mPurchases.clear();
        mPayments.clear();
        owes = null;
        savePayments();
        savePurchases();
        mParties.add(p);
        saveParties();
        owes = new double[mParties.size()][mParties.size()];
        sumOfParties = 0;
        for (Party z : mParties){
            sumOfParties += z.getCount();
        }
    }

    public void addPurchase(Purchase t){
        Log.d("addPurchase", "Adding Purchase");
        mPurchases.add(t);
        recalcOwesForPurchase(t);
        savePurchases();
    }

    public void addPayment(Payment t){
        mPayments.add(t);
        recalcOwesForPayment(t);
        savePayments();
    }

    private void recalcOwesForPurchase(Purchase t) {
        Log.d("recalcOwesForPurchase","Recalculating balances due to new purchase");
        int pPaidBy = -1;
        for( int i = 0; i < mParties.size(); i++)
        {
            if (t.getPaidBy().equals(mParties.get(i).getName())){
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
            if (t.getPaidFrom().equals(mParties.get(i).getName())){
                pPaidFrom = i;
            }
            if (t.getPaidTo().equals(mParties.get(i).getName())){
                pPaidTo = i;
            }
        }
        owes[pPaidTo][pPaidFrom] -= t.getAmount();
    }

    private boolean saveParties(){
        try {
            mPartySerializer.saveParties(mParties);
            Log.d(TAG, "Parties saved.");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving parties.");
            return false;
        }
    }

    private boolean savePurchases(){
        try {
            mPurchaseSerializer.savePurchases(mPurchases);
            Log.d(TAG, "Purchases saved.");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving purchases.");
            return false;
        }
    }

    private boolean savePayments(){
        try {
            mPaymentSerializer.savePayments(mPayments);
            Log.d(TAG, "Payments saved.");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving payments.");
            return false;
        }
    }

    public void clearData() {
        mParties.clear();
        mPurchases.clear();
        mPayments.clear();
        owes = null;
        savePayments();
        savePurchases();
        saveParties();
    }
}
