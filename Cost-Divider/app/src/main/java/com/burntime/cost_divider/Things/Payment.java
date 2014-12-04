package com.burntime.cost_divider.Things;

import com.burntime.cost_divider.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Formatter;

/**
 * Created by Colten on 12/2/2014.
 */
public class Payment extends AbstractJSONThing{
    private static final String JSON_FROM = "by";
    private static final String JSON_TO = "to";
    private static final String JSON_AMOUNT = "amount";

    private Double amount;
    private String paidFrom;
    private String paidTo;

    public Payment()
    {
    }

    public Payment(Double amount, String paidFrom, String paidTo)
    {
        this.amount = amount;
        this.paidFrom = paidFrom;
        this.paidTo = paidTo;
    }

    public Payment(JSONObject json) throws JSONException {
        paidFrom = json.getString(JSON_FROM);
        paidTo = json.getString(JSON_TO);
        amount = json.getDouble(JSON_AMOUNT);
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaidFrom() {
        return paidFrom;
    }

    public void setPaidFrom(String paidFrom) {
        this.paidFrom = paidFrom;
    }

    public String getPaidTo() {
        return paidTo;
    }

    public void setPaidTo(String paidTo) {
        this.paidTo = paidTo;
    }

    // This part borrowed from BNRG p. 276
    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_FROM, paidFrom);
        json.put(JSON_TO, paidTo);
        json.put(JSON_AMOUNT, amount);
        return json;
    }

    @Override
    public String toString() {
        Formatter f = new Formatter();
        return paidFrom + " paid " + paidTo + " " +
                MainActivity.currencyFormatter.format(amount);
    }
}
