package com.example.iottry2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<Item> itemList;

    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.nameTextView.setText(item.getName());
        holder.emailTextView.setText(item.getEmail());
//        holder.locationTextView.setText(item.getLocation());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView emailTextView;
//        TextView locationTextView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.item_name);
            emailTextView = itemView.findViewById(R.id.item_email);
//            locationTextView = itemView.findViewById(R.id.item_location);
        }
    }
}
