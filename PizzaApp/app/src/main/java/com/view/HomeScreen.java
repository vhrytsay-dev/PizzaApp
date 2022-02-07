package com.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.pizzaapp.R;

public class HomeScreen extends Fragment {

    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.home_screen_layout, container, false);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        root = null;
    }
}