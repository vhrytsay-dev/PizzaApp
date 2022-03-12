package com.model.database;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.model.helpers.RowItem;
import com.pizzaapp.R;
import com.view.DescriptionScreen;

import java.util.List;

public class RowItemAdapter extends RecyclerView.Adapter<RowItemAdapter.ViewHolder> {

    private List<RowItem> rowItemList;
    private Activity activity;
    private RoomDB database;

    public RowItemAdapter(Activity activity, List<RowItem> rowItemList){
        this.activity = activity;
        this.rowItemList = rowItemList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_text_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        RowItem rowItem = rowItemList.get(position);
        database = RoomDB.getInstance(activity);
        holder.textView.setText(rowItem.getTitle());
        if(rowItem.getImageId() != null){
            holder.imageView.setImageBitmap(BitmapFactory.decodeByteArray(rowItem.getImageId(), 0, rowItem.getImageId().length));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DescriptionScreen.class);
                intent.putExtra("name", position);
                intent.putExtra("description", database.mainDao().getDescription(position+1));
                intent.putExtra("imageId", position+1);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rowItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
