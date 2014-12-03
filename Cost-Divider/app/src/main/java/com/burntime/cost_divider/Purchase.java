package com.burntime.cost_divider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Formatter;

/**
 * Created by Colten on 12/2/2014.
 */
public class Purchase {
    private static final String JSON_TYPE = "type";
    private static final String JSON_BY = "by";
    private static final String JSON_AMOUNT = "amount";

    private String type;
// private String date;
    private Double amount;
    private String paidBy;
// private String description;
// private String category;
// private String dateEntered;



    public Purchase()
    {
    }

    public Purchase(String type, Double amount, String paidBy)//, String desc,String category, String date)
    {
        this.type = type;
        //this.date = date;
        this.amount = amount;
        this.paidBy = paidBy;
        //this.description = desc;
        //this.category = category;
        //this.dateEntered = DateTime.Now.ToString("MM/dd/yyyy hh:mm tt");
    }

    public Purchase(JSONObject json) throws JSONException {
        type = json.getString(JSON_TYPE);
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

    /*        public String Date
                {
                    get { return date; }
                    set { date = value; }
                }
                public String Description
                {
                    get { return description; }
                    set { description = value; }
                }

                public String Category
                {
                    get { return category; }
                    set { category = value; }
                }

    public int CompareTo(object obj)
    {
        return DateTime.Parse(this.date).CompareTo(DateTime.Parse(((Trans)obj).date));
    }

    public String DateEntered
    {
        get { return dateEntered; }
        set { dateEntered = value; }
    }*/

}
