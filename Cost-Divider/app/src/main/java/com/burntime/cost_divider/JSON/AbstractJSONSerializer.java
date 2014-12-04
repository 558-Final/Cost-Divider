// Code borrowed from BNRG p. 275

package com.burntime.cost_divider.JSON;

import android.content.Context;

import com.burntime.cost_divider.Things.AbstractJSONThing;

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
public abstract class AbstractJSONSerializer {

    protected Context mContext;
    protected String mFilename;

    public AbstractJSONSerializer(Context c, String f) {
        mContext = c;
        mFilename = f;
    }

    public AbstractJSONSerializer(){};

    public void saveThings(JSONArray array)
            throws JSONException, IOException {
        // Write the file to disk
        Writer writer = null;
        try {
            OutputStream out = mContext
                    .openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    protected JSONArray loadThings() throws IOException, JSONException {
        BufferedReader reader = null;
        JSONArray array = null;
        try {
    // Open and read the file into a StringBuilder
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
    // Line breaks are omitted and irrelevant
                jsonString.append(line);
            }
    // Parse the JSON using JSONTokener
            array = (JSONArray) new JSONTokener(jsonString.toString())
                    .nextValue();
        } catch (FileNotFoundException e) {
    // Ignore this one; it happens when starting fresh
        } finally {
            if (reader != null)
                reader.close();
            return array;
        }
    }
/*    ArrayList<AbstractJSONThing> things = new ArrayList<AbstractJSONThing>();
// Build the array of things from JSONObjects
    for (int i = 0; i < array.length(); i++) {
        things.add(new AbstractJSONThing(array.getJSONObject(i)));*/
}
