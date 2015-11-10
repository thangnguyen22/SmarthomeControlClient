package com.uit.smarthomecontrol.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.uit.smarthomecontrol.ListRoomActivity;
import com.uit.smarthomecontrol.ListSensorActivity;
import com.uit.smarthomecontrol.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tensh on 10/24/2015.
 */
public class ListRoomAdapter extends BaseAdapter {
    int roomColor = 0;
    private int[] roomColorArray;

    private final List<Item> mItems = new ArrayList<Item>();
    private final LayoutInflater mInflater;
    private Context context;
    public ListRoomAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);

        mItems.add(new Item("Red", Color.parseColor("#FFCC0000")));
        mItems.add(new Item("Magenta",   Color.parseColor("#FFCC27AF")));
        mItems.add(new Item("Dark Gray", Color.parseColor("#FF888385")));
        mItems.add(new Item("Gray",      Color.parseColor("#FFCFD3CE")));
        mItems.add(new Item("Green",     Color.parseColor("#FF99CC00")));
        mItems.add(new Item("Cyan",      Color.parseColor("#FF1EDAE5")));

        roomColorArray = context.getResources().getIntArray(R.array.room_color);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Item getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mItems.get(i).drawableId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView name;

        if (v == null) {
            v = mInflater.inflate(R.layout.activity_list_room_item, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, ListSensorActivity.class));
                }
            });
        }

        picture = (ImageView) v.getTag(R.id.picture);
        name = (TextView) v.getTag(R.id.text);

        Item item = getItem(i);

        if(roomColor >= 4)
            roomColor = 0;
        picture.setBackgroundColor(roomColorArray[roomColor]);
        roomColor ++;
        name.setText(item.name);

        return v;
    }

    private static class Item {
        public final String name;
        public final int drawableId;

        Item(String name, int drawableId) {
            this.name = name;
            this.drawableId = drawableId;
        }
    }
}
