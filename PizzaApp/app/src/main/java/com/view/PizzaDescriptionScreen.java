package com.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.pizzaapp.R;

public class PizzaDescriptionScreen extends AppCompatActivity implements IPizzaAppMVP.IPizzaDescriptionScreen{
    private TextView descriptText;
    PizzaDescriptionScreenPresenter presenter;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_description_screen_layout);
        imageView = (ImageView) findViewById(R.id.imageView2);
        descriptText = (TextView) findViewById(R.id.descriptText);
        presenter = new PizzaDescriptionScreenPresenter(this, this);
        showPizzaData(getIntent().getStringExtra("name"), getIntent().getStringExtra("description"), getIntent().getIntExtra("image", R.drawable.ic_baseline_local_pizza_24));
    }

    @SuppressLint("SdCardPath")
    private void showPizzaData(String name, String description, int image) {
        getSupportActionBar().setTitle(name);
        descriptText.setText(description);
        Drawable drawable = getResources().getDrawable(image);
        imageView.setImageDrawable(drawable);
    }
}