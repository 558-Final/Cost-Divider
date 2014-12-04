// Code borrowed from BNRG p. 275

package com.burntime.cost_divider.JSON;

import android.content.Context;

import com.burntime.cost_divider.Things.Party;
import com.burntime.cost_divider.Things.Payment;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Colten on 12/2/2014.
 */
public class PaymentJSONSerializer extends AbstractJSONSerializer{

    public PaymentJSONSerializer(){};

    public PaymentJSONSerializer(Context c, String f) {
        mContext = c;
        mFilename = f;
    }

    public void savePayments(ArrayList<Payment> payments){
        JSONArray array = new JSONArray();
        try {
            for (Payment p : payments) {
                array.put(p.toJSON());
            }
            super.saveThings(array);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Payment> loadPayments() throws IOException, JSONException {
        ArrayList<Payment> payments = new ArrayList<Payment>();
        JSONArray array = super.loadThings();
        for (int i = 0; i < array.length(); i++) {
            payments.add(new Payment(array.getJSONObject(i)));
        }
        return payments;
    }
}
