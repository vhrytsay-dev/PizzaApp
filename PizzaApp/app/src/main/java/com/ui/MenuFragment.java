package com.ui;

import android.content.DialogInterface;
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

import java.util.List;

public class MenuFragment extends Fragment implements IMenuFragmentView{

    private ArrayAdapter<String> arrayAdapter;
    private View view;
    private ListView mListview;
    private Presenter presenter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu, container, false);
        setHasOptionsMenu(true);
        mListview = (ListView) view.findViewById(R.id.listview);
        presenter = new Presenter(this, getActivity());
        presenter.setDataToListview();
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), presenter.getDescription(parent.getItemAtPosition(position).toString()), Toast.LENGTH_LONG).show();
            }
        });
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
            //
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

    //TODO Update ListView after adding list item
    @Override
    public void update() {
        onResume();
    }

    private void addPizzaDialog(){
        EditText textInputNameText = new EditText(getActivity());
        textInputNameText.setHint(R.string.name);
        TextInputEditText textInputDescriptionText = new TextInputEditText(getActivity());
        textInputDescriptionText.setHint(R.string.description);
        LinearLayout linLay = new LinearLayout(getActivity());
        linLay.setOrientation(LinearLayout.VERTICAL);
        linLay.addView(textInputNameText);
        linLay.addView(textInputDescriptionText);
        new AlertDialog.Builder(getActivity())
            .setTitle(R.string.addNewPizza)
            .setView(linLay)
            .setMessage(R.string.message)
            .setCancelable(false)
            .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String message = presenter.addPizza((textInputNameText.getText().toString()), (textInputDescriptionText).getText().toString());
                    Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
                    toast.show();
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).show();
    }
}