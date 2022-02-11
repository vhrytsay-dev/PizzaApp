package com.view;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.model.helpers.CustomListViewAdapter;
import com.model.helpers.RowItem;
import com.pizzaapp.R;

import java.io.File;
import java.util.List;

import zendesk.belvedere.Belvedere;
import zendesk.belvedere.Callback;
import zendesk.belvedere.MediaResult;

public class MenuScreen extends Fragment implements IPizzaAppMVP.IMenuScreen {

    private View view;
    private View addView;
    private ListView mListview;
    private MenuScreenPresenter presenter;
    private LinearLayout inputFieldsLayout;
    private EditText nameField;
    private EditText descriptionField;
    private CustomListViewAdapter adapter;
    private Button addButton;
    private String imagePath;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.menu_screen_layout, container, false);
        addView = inflater.inflate(R.layout.add_new_pizza_window, container, false);
        inputFieldsLayout = addView.findViewById(R.id.linLayout);
        nameField = addView.findViewById(R.id.textInputNameText);
        descriptionField = addView.findViewById(R.id.textInputDescriptionText);
        setHasOptionsMenu(true);
        mListview = (ListView) view.findViewById(R.id.listview);
        presenter = new MenuScreenPresenter(this, getActivity());
        presenter.setDataToListview();
        addButton = addView.findViewById(R.id.addButton);
        listViewItemsListener();
        pickImage();
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
    public void setDataToListview(List<RowItem> categoriesToList) {
        adapter = new CustomListViewAdapter(view.getContext(), R.layout.image_text_layout, categoriesToList);
        mListview.setAdapter(adapter);
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
                    String message;
                    if(imagePath != null){
                        message = presenter.addPizzaWithImage((nameField.getText().toString()), (descriptionField.getText().toString()), imagePath);
                        imagePath = null;
                    }else {
                        message = presenter.addPizza((nameField.getText().toString()), (descriptionField.getText().toString()));
                    }
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
                intent.putExtra("description", presenter.getDescription(((RowItem) parent.getItemAtPosition(position)).getTitle()));
                getActivity().startActivity(intent);
            }
        });
    }

    private void pickImage(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Belvedere.from(getContext())
                        .document()
                        .open(MenuScreen.this);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Callback<List<MediaResult>> callback = new Callback<List<MediaResult>>() {
            @Override
            public void success(List<MediaResult> result) {
                if(result != null && result.size() > 0){
                    MediaResult mediaResult = (MediaResult) result.get(0);
                    File imageFile = mediaResult.getFile();
                    imagePath = imageFile.getPath();
                }
            }
        };
        Belvedere.from(getActivity()).getFilesFromActivityOnResult(requestCode, resultCode, data, callback);
    }
    }