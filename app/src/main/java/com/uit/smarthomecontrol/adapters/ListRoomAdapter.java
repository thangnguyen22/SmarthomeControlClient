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
import com.uit.smarthomecontrol.models.RoomItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by tensh on 10/24/2015.
 */
public class ListRoomAdapter extends BaseAdapter {
    int roomColor = 0;
    private int[] roomColorArray;

    ArrayList<RoomItem> listRoom;
    private final LayoutInflater mInflater;
    private Context context;
    public ListRoomAdapter(Context context, ArrayList<RoomItem> arrayRoomName) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        listRoom = arrayRoomName;
        roomColorArray = context.getResources().getIntArray(R.array.room_color);
    }

    @Override
    public int getCount() {
        return listRoom.size();
    }

    @Override
    public Object getItem(int i) {
        return listRoom.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        final Holder holder;
        if (v == null) {
            v = mInflater.inflate(R.layout.activity_list_room_item, viewGroup, false);

            holder = new Holder();
            holder.picture = (ImageView) v.findViewById(R.id.picture);
            holder.name = (TextView) v.findViewById(R.id.text);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListSensorActivity.class);
                    intent.putExtra("RoomId", holder.roomItem.getId());
                    context.startActivity(intent);
                }
            });
            v.setTag(holder);
        }else {
            // we've just avoided calling findViewById() on resource everytime
            // just use the viewHolder
            holder = (Holder) v.getTag();
        }
        if(roomColor >= 4)
            roomColor = 0;
        holder.picture.setBackgroundColor(roomColorArray[roomColor]);
        roomColor ++;
        holder.name.setText(listRoom.get(i).getRoomName());
        holder.roomItem = listRoom.get(i);
        return v;
    }

    static class Holder {
        int drawableId;
        RoomItem roomItem;
        ImageView picture;
        TextView name;
    }
}
