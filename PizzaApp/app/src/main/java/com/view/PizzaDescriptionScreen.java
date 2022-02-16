package com.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.pizzaapp.R;

public class PizzaDescriptionScreen extends AppCompatActivity implements IPizzaAppMVP.IPizzaDescriptionScreen{
    private TextView descriptText;
    DescriptionScreenPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_description_screen_layout);

        descriptText = (TextView) findViewById(R.id.descriptText);
        presenter = new DescriptionScreenPresenter(this, this);
        showPizzaData(getIntent().getStringExtra("name"), getIntent().getStringExtra("description"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showPizzaData(String name, String description) {
        getSupportActionBar().setTitle(name);
        descriptText.setText(description);
    }
}