package com.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pizzaapp.R;
import com.ui.AppView;
import com.ui.Presenter;

public class MainActivity extends AppCompatActivity implements AppView {

    Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigation_activity_layout);
        presenter = new Presenter(this);
    }

    @Override
    public String getPizzaName() {
        return presenter.getName();
    }

    @Override
    public String getPizzaDescription() {
        return presenter.getDescription();
    }
}