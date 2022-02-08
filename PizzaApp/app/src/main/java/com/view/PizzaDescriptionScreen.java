package com.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.pizzaapp.R;

public class PizzaDescriptionScreen extends AppCompatActivity implements IPizzaAppMVP.IPizzaDescriptionScreen{

    private TextView nameText;
    private TextView descriptText;
    PizzaDescriptionScreenPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_description_screen_layout);
        //nameText = (TextView) findViewById(R.id.nameText);

        descriptText = (TextView) findViewById(R.id.descriptText);
        presenter = new PizzaDescriptionScreenPresenter(this, this);
        showPizzaData(getIntent().getStringExtra("name"), getIntent().getStringExtra("description"));
    }

    private void showPizzaData(String name, String description) {
        getSupportActionBar().setTitle(name);
        descriptText.setText(description);
    }
}