package com.burntime.ribbit;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Colten on 8/21/2014.
 */
public class RibbitApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "aZbXgEdtkZ9SP3rOdg06xK7tfaEvP8fPl5MeY0C3", "IuG0o3fjaIefnHZH2O04o7guJnjWGOAa3DojYUCT");
    }
}
