package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        // Find the TextView AlsoKnownAs
        TextView alsoKnownAs = findViewById(R.id.also_known_tv);
        // AlsoKnownAs array
        List<String> alsoKnownAsList = sandwich.getAlsoKnownAs();
        // If AlsoKnownAsList size is zero then show 'no data' warning.
        if (alsoKnownAsList.size() == 0) {
            alsoKnownAs.setText(R.string.no_data);
        } else {
        /*
         * Iterate through the array and append the String to the TextView. The reason why we add
         * the "," after the String is to give visual separation between each String in the
         * TextView.
         * SOURCE: https://dzone.com/articles/converting-list-comma
         */
            for (int i = 0; i < alsoKnownAsList.size(); i++) {
                alsoKnownAs.append(alsoKnownAsList.get(i));

                //if the value is not the last element of the list
                //then append the comma(,) as well
                if (i != alsoKnownAsList.size() - 1) {
                    alsoKnownAs.append(", ");
                } else {
                    alsoKnownAs.append(".");
                }
            }
        }

        // Find the TextView Place of Origin
        TextView origin = findViewById(R.id.origin_tv);
        // If there is no Place of Origin then show 'no data' warning.
        if (origin.length() == 0) {
            origin.setText(R.string.no_data);
        } else {
            // Display the Place of Origin
            origin.setText(sandwich.getPlaceOfOrigin());
        }

        // Find the TextView Description
        TextView description = findViewById(R.id.description_tv);
        // If there is no description then show 'no data' warning.
        if (description.length() == 0) {
            description.setText(R.string.no_data);
        } else {
            // Display the Description
            description.setText(sandwich.getDescription());
        }

        // Find the TextView Ingredients
        TextView ingredients = findViewById(R.id.ingredients_tv);
        // Ingredients array
        List<String> ingredientsList = sandwich.getIngredients();
        // If ingredientsList size is zero then show 'no data' warning.
        if (ingredientsList.size() == 0) {
            ingredients.setText(R.string.no_data);
        } else {
        /*
         * Iterate through the array and append the String to the TextView. The reason why we add
         * the "," after the String is to give visual separation between each String in the
         * TextView.
         * SOURCE: https://dzone.com/articles/converting-list-comma
         */
            for (int i = 0; i < ingredientsList.size(); i++) {
                ingredients.append(ingredientsList.get(i));

                //if the value is not the last element of the list
                //then append the comma(,) as well
                if (i != ingredientsList.size() - 1) {
                    ingredients.append(", ");
                } else {
                    ingredients.append(".");
                }
            }
        }
    }
}