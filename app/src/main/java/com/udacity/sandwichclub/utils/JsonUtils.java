package com.udacity.sandwichclub.utils;

import android.text.TextUtils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class JsonUtils {


    public static Sandwich parseSandwichJson(String json) {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(json)) {
            return null;
        }

        // Try to parse the values
        try {
            // Create a root JSONObject
            JSONObject jsonRootObject = new JSONObject(json);
            // Get name into a JSONObject
            JSONObject name = jsonRootObject.optJSONObject("name");

            // Get mainName of sandwich
            String mainName = name.optString("mainName");
            // Get alsoKnownAs array
            JSONArray alsoKnownAs = name.optJSONArray("alsoKnownAs");
            // Create a new ArrayList
            ArrayList<String> alsoKnownAsList = new ArrayList<>();
            // Iterate the alsoKnownAs array
            for (int i = 0; i < alsoKnownAs.length(); i++) {
                alsoKnownAsList.add(alsoKnownAs.optString(i));
            }
            // Get placeOfOrigin
            String placeOfOrigin = jsonRootObject.optString("placeOfOrigin");
            // Get description
            String description = jsonRootObject.optString("description");
            // Get image path
            String image = jsonRootObject.optString("image");

            // Get ingredients array
            JSONArray ingredients = jsonRootObject.optJSONArray("ingredients");
            // Create a new ArrayList
            ArrayList<String> ingredientsList = new ArrayList<>();
            // Iterate the alsoKnownAs array
            for (int j = 0; j < ingredients.length(); j++) {
                ingredientsList.add(ingredients.optString(j));
            }

            // Return sandwich
            return new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash.
            e.printStackTrace();
        }
        return null;
    }
}