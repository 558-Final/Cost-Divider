package com.burntime.cost_divider.Things;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Colten on 12/3/2014.
 */
public class Party extends AbstractJSONThing{
    private static final String JSON_NAME = "name";
    private static final String JSON_COUNT = "count";

    private String mName;
    private int mCount;

    public Party(){}

    public Party (String name, int count){
        mName = name;
        mCount = count;
    }

    public Party(JSONObject json) throws JSONException {
        mName = json.getString(JSON_NAME);
        mCount = json.getInt(JSON_COUNT);
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int mCount) {
        this.mCount = mCount;
    }

    @Override
    public String toString() {
        return mName + " (" + mCount + ")";
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_NAME, mName);
        json.put(JSON_COUNT, mCount);
        return json;
    }
}
