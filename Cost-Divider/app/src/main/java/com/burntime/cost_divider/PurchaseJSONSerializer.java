// Code borrowed from BNRG p. 275

package com.burntime.cost_divider;

import android.content.Context;

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
public class PurchaseJSONSerializer{

    private Context mContext;
    private String mFilename;
    public PurchaseJSONSerializer(Context c, String f) {
        mContext = c;
        mFilename = f;
    }

    public void saveTrans(ArrayList<Purchase> trans)
            throws JSONException, IOException {
        // Build an array in JSON
        JSONArray array = new JSONArray();
        for (Purchase t : trans)
            array.put(t.toJSON());
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

    public ArrayList<Purchase> loadTrans() throws IOException, JSONException {
        ArrayList<Purchase> purchases = new ArrayList<Purchase>();
        BufferedReader reader = null;
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
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString())
                    .nextValue();
    // Build the array of purchases from JSONObjects
            for (int i = 0; i < array.length(); i++) {
                purchases.add(new Purchase(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
    // Ignore this one; it happens when starting fresh
        } finally {
            if (reader != null)
                reader.close();
        }
        return purchases;
    }
}
