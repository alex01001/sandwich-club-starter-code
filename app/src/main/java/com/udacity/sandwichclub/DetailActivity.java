package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    TextView also_known_tv;
    TextView origin_tv;
    TextView ingredients_tv;
    TextView description_tv;

    TextView also_known_l;
    TextView origin_l;
    TextView ingredients_l;
    TextView description_l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        also_known_tv = findViewById(R.id.also_known_tv);
        also_known_l = findViewById(R.id.also_known_label);
        origin_tv = findViewById(R.id.origin_tv);
        ingredients_tv = findViewById(R.id.ingredients_tv);
        description_tv = findViewById(R.id.description_tv);
        origin_l = findViewById(R.id.origin_label);
        ingredients_l = findViewById(R.id.ingredients_label);
        description_l = findViewById(R.id.description_label);


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
        Log.d("tt", json);
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
        origin_tv.setText((CharSequence) sandwich.getPlaceOfOrigin());
        description_tv.setText((CharSequence) sandwich.getDescription());
        List<String> alsoKnownAs = sandwich.getAlsoKnownAs();
        List<String> ingredients = sandwich.getIngredients();
        String aka = alsoKnownAs.toString();
        aka = aka.substring(1,aka.length()-1);
        if(alsoKnownAs.size()==0){
            also_known_tv.setVisibility(View.INVISIBLE);
            also_known_l.setVisibility(View.INVISIBLE);
        }
        else{
            also_known_l.setVisibility(View.VISIBLE);
            also_known_tv.setVisibility(View.VISIBLE);
            also_known_tv.setText((CharSequence)aka);
        }



        String ingr = ingredients.toString();
        ingr = ingr.substring(1,ingr.length()-1);

        if(ingredients.size()==0){
            ingredients_tv.setVisibility(View.INVISIBLE);
            ingredients_l.setVisibility(View.INVISIBLE);
        }
        else{
            ingredients_l.setVisibility(View.VISIBLE);
            ingredients_tv.setVisibility(View.VISIBLE);
            ingredients_tv.setText((CharSequence)ingr);
        }


    }
}
