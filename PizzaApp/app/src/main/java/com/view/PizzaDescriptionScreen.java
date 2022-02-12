package com.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.pizzaapp.R;

public class PizzaDescriptionScreen extends AppCompatActivity implements IPizzaAppMVP.IPizzaDescriptionScreen{
    private TextView descriptionText;
    private PizzaDescriptionScreenPresenter presenter;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_description_screen_layout);
        imageView = (ImageView) findViewById(R.id.imageView2);
        descriptionText = (TextView) findViewById(R.id.descriptText);
        presenter = new PizzaDescriptionScreenPresenter(this, this);
        showPizzaData(getIntent().getStringExtra("name"), getIntent().getStringExtra("description"));
    }

    @SuppressLint("SdCardPath")
    private void showPizzaData(String name, String description) {
        getSupportActionBar().setTitle(name);
        descriptionText.setText(description);
        if(presenter.getImage(name) != null){
            imageView.setImageBitmap(presenter.getImage(name));
        }
    }
}