package com.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.pizzaapp.R;

public class DescriptionScreen extends AppCompatActivity implements IPizzaAppMVP.IPizzaDescriptionScreen{
    private TextView descriptionText;
    private DescriptionScreenPresenter presenter;
    private ImageView imageView;
    private ImageButton like;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_description_screen_layout);
        imageView = (ImageView) findViewById(R.id.imageView2);
        descriptionText = (TextView) findViewById(R.id.descriptText);
        presenter = new DescriptionScreenPresenter(this, this);
        like = findViewById(R.id.like);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        showPizzaData(getIntent().getStringExtra("name"), getIntent().getIntExtra("id", 0), getIntent().getStringExtra("description"));
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

    //TODO Die getImageId Fixen!!!
    @SuppressLint("SdCardPath")
    private void showPizzaData(String name, int id, String description) {
        getSupportActionBar().setTitle(name);
        descriptionText.setText(description);
        if(presenter.getImage(id) != null){
            imageView.setImageBitmap(presenter.getImage(id));
        }
    }
}