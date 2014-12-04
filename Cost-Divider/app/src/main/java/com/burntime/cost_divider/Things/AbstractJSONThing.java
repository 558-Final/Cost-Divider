package com.burntime.cost_divider.Things;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Colten on 12/4/2014.
 */
public abstract class AbstractJSONThing {
    public abstract JSONObject toJSON() throws JSONException;
}
