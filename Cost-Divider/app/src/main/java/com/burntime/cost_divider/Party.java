package com.burntime.cost_divider;

/**
 * Created by Colten on 12/3/2014.
 */
public class Party {
    private String mName;
    private int mCount;

    public Party(){}

    public Party (String name, int count){
        mName = name;
        mCount = count;
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
}
