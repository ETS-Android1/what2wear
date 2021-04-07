package com.example.what2wear.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.what2wear.R;

public class WearableViewHolder extends RecyclerView.ViewHolder {

    public ImageView wearableImage;
    public TextView nameTextView;

    public WearableViewHolder(@NonNull View itemView) {
        super(itemView);

        wearableImage = itemView.findViewById(R.id.wearable_image);
        nameTextView = itemView.findViewById(R.id.wearable_name);

    }
}
