// Code borrowed from BNRG p. 275

package com.burntime.cost_divider.JSON;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.burntime.cost_divider.Things.AbstractJSONThing;
import com.burntime.cost_divider.Things.Party;
import com.burntime.cost_divider.Things.Payment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by Colten on 12/2/2014.
 */
public class PartyJSONSerializer extends AbstractJSONSerializer{

    public PartyJSONSerializer(){};

    public PartyJSONSerializer(Context c, String f) {
        mContext = c;
        mFilename = f;
    }

    public void saveParties(ArrayList<Party> parties){
        JSONArray array = new JSONArray();
        try {
            for (Party p : parties) {
                array.put(p.toJSON());
            }
            super.saveThings(array);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Party> loadParties() throws IOException, JSONException {
        ArrayList<Party> parties = new ArrayList<Party>();
        JSONArray array = super.loadThings();
        for (int i = 0; i < array.length(); i++) {
            parties.add(new Party(array.getJSONObject(i)));
        }
        return parties;
    }
}
