// Code borrowed from BNRG p. 275

package com.burntime.cost_divider.JSON;

import android.content.Context;

import com.burntime.cost_divider.Things.Payment;
import com.burntime.cost_divider.Things.Purchase;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Colten on 12/2/2014.
 */
public class PurchaseJSONSerializer extends AbstractJSONSerializer{

    public PurchaseJSONSerializer(){};

    public PurchaseJSONSerializer(Context c, String f) {
        mContext = c;
        mFilename = f;
    }

    public void savePurchases(ArrayList<Purchase> purchases){
        JSONArray array = new JSONArray();
        try {
            for (Purchase p : purchases) {
                array.put(p.toJSON());
            }
            super.saveThings(array);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Purchase> loadPurchases() throws IOException, JSONException {
        ArrayList<Purchase> purchases = new ArrayList<Purchase>();
        JSONArray array = super.loadThings();
        for (int i = 0; i < array.length(); i++) {
            purchases.add(new Purchase(array.getJSONObject(i)));
        }
        return purchases;
    }
}
