package com.example.prm392;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class InventoryAdaptor extends BaseAdapter {
    private List<Item> itemList;
    private Player player;
    private Context context;

    public InventoryAdaptor(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.item_layout, null);
        }

        Item currentItem = itemList.get(position);

        ImageView itemImage = itemView.findViewById(R.id.item_image);
        TextView itemName = itemView.findViewById(R.id.item_name);

        // Set the item image and name
        itemImage.setImageResource(currentItem.getImageResource());
        itemName.setText(currentItem.getName());

        return itemView;
    }
}
