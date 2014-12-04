package com.burntime.cost_divider.Things;

import com.burntime.cost_divider.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Formatter;

/**
 * Created by Colten on 12/2/2014.
 */
public class Purchase extends AbstractJSONThing{
    private static final String JSON_TYPE = "type";
    private static final String JSON_BY = "by";
    private static final String JSON_AMOUNT = "amount";

    private String type;
    private Double amount;
    private String paidBy;



    public Purchase()
    {
    }

    public Purchase(Double amount, String paidBy)
    {
        this.amount = amount;
        this.paidBy = paidBy;
    }

    public Purchase(JSONObject json) throws JSONException {
        paidBy = json.getString(JSON_BY);
        amount = json.getDouble(JSON_AMOUNT);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    // This part borrowed from BNRG p. 276
    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_TYPE, type);
        json.put(JSON_BY, paidBy);
        json.put(JSON_AMOUNT, amount);
        return json;
    }

    @Override
    public String toString() {
        Formatter f = new Formatter();
        return paidBy + " made a purchase of " + MainActivity.currencyFormatter.format(amount);
    }
}
