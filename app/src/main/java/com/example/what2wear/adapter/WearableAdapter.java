package com.example.what2wear.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.what2wear.R;
import com.example.what2wear.models.clothing.Wearable;

import java.util.List;

public class WearableAdapter extends RecyclerView.Adapter<WearableViewHolder> {

    private List<? extends Wearable> wearablesList;
    private Context mContext;

    public WearableAdapter(Context context, List<? extends Wearable> wearables) {
        this.mContext= context;
        this.wearablesList = wearables;
    }

    @NonNull
    @Override
    public WearableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View wearableView = inflater.inflate(R.layout.recycler_row, parent, false);

        WearableViewHolder viewHolder = new WearableViewHolder(wearableView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull WearableViewHolder holder, int position) {
        Wearable wearable = wearablesList.get(position);

        ImageView outwearImage = holder.wearableImage;
        int resourceId = mContext.getResources().getIdentifier(
                wearable.getFileName(),
                "drawable",
                mContext.getPackageName());
        outwearImage.setImageResource(resourceId);

        TextView textViewName = holder.nameTextView;
        textViewName.setText(wearable.getName());
    }

    @Override
    public int getItemCount() {
        return wearablesList.size();
    }

}
