package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        String mainName="";
        List<String> alsoKnownAs = new ArrayList<String>();
        String placeOfOrigin="";
        String description="";
        String image="";
        List<String> ingredients = new ArrayList<String>();

        JSONObject sandwichData;
        try {

            sandwichData = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        try {

            JSONObject sandwichItem = sandwichData.getJSONObject("name");

            mainName = sandwichItem.getString("mainName");
            Log.d("mainName", mainName);
        } catch (JSONException e) {
            e.printStackTrace();
           // return null;
        }
        try {
            JSONArray names = sandwichData.getJSONArray("alsoKnownAs");
            for (int i=0; i<names.length(); i++) {
                String akaStr = names.getString(i);
                Log.d("akaName", mainName);

                alsoKnownAs.add(akaStr);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
                placeOfOrigin = sandwichData.getString("placeOfOrigin");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            description = sandwichData.getString("description");
            Log.d("description", description);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            image = sandwichData.getString("image");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {

            JSONArray ingredientsJson = sandwichData.getJSONArray("ingredients");
            for (int i=0; i<ingredientsJson.length(); i++) {
                String ingr = ingredientsJson.getString(i);
                Log.d("ingr", ingr);
                ingredients.add(ingr);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Sandwich sandwich = new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);
      //  Sandwich(String mainName, List<String> alsoKnownAs, String placeOfOrigin, String description, String image, List<String> ingredients) {


        Log.d("twt", placeOfOrigin);
        Log.d("twt", image);




        return sandwich;
    }
}
