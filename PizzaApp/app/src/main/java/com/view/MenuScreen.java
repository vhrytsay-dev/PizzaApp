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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.model.helpers.CustomListViewAdapter;
import com.model.helpers.RowItem;
import com.pizzaapp.R;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;

import zendesk.belvedere.Belvedere;
import zendesk.belvedere.Callback;
import zendesk.belvedere.MediaResult;

public class MenuScreen extends Fragment implements IPizzaAppMVP.IMenuScreen {

    private View menuView;
    private View addNewPizzaView;
    private ListView mListView;
    private CustomListViewAdapter adapter;
    private MenuScreenPresenter presenter;
    private LinearLayout inputFieldsLayout;
    private EditText nameField;
    private EditText descriptionField;
    private Button addButton;
    private String imagePath;
    private Callback<List<MediaResult>> callback;
    private TextView imageFileName;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        menuView = inflater.inflate(R.layout.menu_screen_layout, container, false);
        addNewPizzaView = inflater.inflate(R.layout.add_new_pizza_window, container, false);
        inputFieldsLayout = addNewPizzaView.findViewById(R.id.linLayout);
        nameField = addNewPizzaView.findViewById(R.id.textInputNameText);
        descriptionField = addNewPizzaView.findViewById(R.id.textInputDescriptionText);
        setHasOptionsMenu(true);
        mListView = (ListView) menuView.findViewById(R.id.listview);
        presenter = new MenuScreenPresenter(this, getActivity(), getContext());
        presenter.setDataToListview();
        addButton = addNewPizzaView.findViewById(R.id.addButton);
        listViewItemsListener();
        imageFileName = addNewPizzaView.findViewById(R.id.imageFileName);
        if (((AppCompatActivity)getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        }
        return menuView;
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
    public void onDestroy() {
        super.onDestroy();
        if(callback != null){
            callback.cancel();
        }
        menuView = null;
        imagePath = null;
        imageFileName.setText(StringUtils.EMPTY);
    }

    @Override
    public void setDataToListview(List<RowItem> categoriesToList) {
        adapter = new CustomListViewAdapter(menuView.getContext(), R.layout.image_text_layout, categoriesToList);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callback = new Callback<List<MediaResult>>() {
            @Override
            public void success(List<MediaResult> result) {
                if(result != null && result.size() > 0){
                    MediaResult mediaResult = (MediaResult) result.get(0);
                    File imageFile = mediaResult.getFile();
                    imagePath = imageFile.getPath();
                    if(imageFileName != null){
                        imageFileName.setText(imageFile.getName());
                    } else {
                        imageFileName = (TextView) addNewPizzaView.findViewById(R.id.imageFileName);
                    }
                }
            }
        };
        Belvedere.from(getContext()).getFilesFromActivityOnResult(requestCode, resultCode, data, callback);
    }

    private void addPizzaDialog(){
        if(inputFieldsLayout != null && inputFieldsLayout.getParent() != null){
            ((ViewGroup)inputFieldsLayout.getParent()).removeView(inputFieldsLayout);
        }
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Belvedere.from(getContext())
                        .document()
                        .open(MenuScreen.this);
            }
        });
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
                        imageFileName.setText(StringUtils.EMPTY);
                    }else {
                        message = presenter.addPizza((nameField.getText().toString()), (descriptionField.getText().toString()));
                        imageFileName = null;
                    }
                    Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
                    toast.show();
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                imagePath = null;
                imageFileName.setText(StringUtils.EMPTY);
            }
        }).show();
    }

    private void listViewItemsListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DescriptionScreen.class);
                intent.putExtra("name", parent.getItemAtPosition(position).toString());
                intent.putExtra("description", presenter.getDescription(((RowItem) parent.getItemAtPosition(position)).getTitle()));
                getActivity().startActivity(intent);
            }
        });
    }
    }