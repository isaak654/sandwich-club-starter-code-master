package com.udacity.sandwichclub.utils;

import android.text.TextUtils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Helper method related to requesting and receiving sandwich data.
 */
public class JsonUtils {

    // List of JSON constants
    public static final String JSON_NAME_KEY = "name";
    public static final String JSON_MAIN_NAME_KEY = "mainName";
    public static final String JSON_ALSO_KNOWN_AS_KEY = "alsoKnownAs";
    public static final String JSON_PLACE_OF_ORIGIN_KEY = "placeOfOrigin";
    public static final String JSON_DESCRIPTION_KEY = "description";
    public static final String JSON_IMAGE_KEY = "image";
    public static final String JSON_INGREDIENTS_KEY = "ingredients";

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
            JSONObject name = jsonRootObject.optJSONObject(JSON_NAME_KEY);

            // Get mainName of sandwich
            String mainName = name.optString(JSON_MAIN_NAME_KEY);
            // Get alsoKnownAs array
            JSONArray alsoKnownAs = name.optJSONArray(JSON_ALSO_KNOWN_AS_KEY);
            // Create a new ArrayList
            ArrayList<String> alsoKnownAsList = new ArrayList<>();
            // Iterate the alsoKnownAs array
            for (int i = 0; i < alsoKnownAs.length(); i++) {
                alsoKnownAsList.add(alsoKnownAs.optString(i));
            }
            // Get placeOfOrigin
            String placeOfOrigin = jsonRootObject.optString(JSON_PLACE_OF_ORIGIN_KEY);
            // Get description
            String description = jsonRootObject.optString(JSON_DESCRIPTION_KEY);
            // Get image path
            String image = jsonRootObject.optString(JSON_IMAGE_KEY);

            // Get ingredients array
            JSONArray ingredients = jsonRootObject.optJSONArray(JSON_INGREDIENTS_KEY);
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