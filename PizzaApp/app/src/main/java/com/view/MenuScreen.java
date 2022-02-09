package com.view;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.pizzaapp.R;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class MenuScreen extends Fragment implements IPizzaAppMVP.IMenuScreen {

    private ArrayAdapter<String> arrayAdapter;
    private View view;
    private View addView;
    private ListView mListview;
    private MenuScreenScreenPresenter presenter;
    private LinearLayout inputFieldsLayout;
    private EditText nameField;
    private EditText descriptionField;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.menu_screen_layout, container, false);
        addView = inflater.inflate(R.layout.add_new_pizza_window, container, false);
        inputFieldsLayout = addView.findViewById(R.id.linLayout);
        nameField = addView.findViewById(R.id.textInputNameText);
        descriptionField = addView.findViewById(R.id.textInputDescriptionText);
        setHasOptionsMenu(true);
        mListview = (ListView) view.findViewById(R.id.listview);
        presenter = new MenuScreenScreenPresenter(this, getActivity());
        presenter.setDataToListview();
        listViewItemsListener();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_button_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add_btn){
            addPizzaDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view = null;
    }

    @Override
    public void setDataToListview(List<String> categoriesToList) {
        arrayAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, categoriesToList);
        mListview.setAdapter(arrayAdapter);
    }

    private void addPizzaDialog(){
        if(inputFieldsLayout != null && inputFieldsLayout.getParent() != null){
            ((ViewGroup)inputFieldsLayout.getParent()).removeView(inputFieldsLayout);
        }
        nameField.setText("");
        descriptionField.setText("");
        new AlertDialog.Builder(getActivity())
            .setTitle(R.string.addNewPizza)
            .setView(inputFieldsLayout)
            .setMessage(R.string.message)
            .setCancelable(false)
            .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String message = presenter.addPizza((nameField.getText().toString()), (descriptionField.getText().toString()));
                    Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
                    toast.show();
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).show();
    }

    private void listViewItemsListener() {
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), PizzaDescriptionScreen.class);
                intent.putExtra("name", parent.getItemAtPosition(position).toString());
                intent.putExtra("description", presenter.getDescription(parent.getItemAtPosition(position).toString()));
                getActivity().startActivity(intent);
            }
        });
    }
}