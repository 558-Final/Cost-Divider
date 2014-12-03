package com.burntime.cost_divider;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Colten on 8/21/2014.
 */
public class CostDividerApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "WelQ3kllma9lGOk1hqUqXaHP0rXHxZIFBRwMs4Tc", "kyJTuoUY9ZmYYEPqKftbjWhPYA4u0nyDD6Fk6lIG");
    }
}
