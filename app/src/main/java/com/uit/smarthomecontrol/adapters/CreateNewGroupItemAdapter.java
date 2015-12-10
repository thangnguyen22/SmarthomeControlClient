package com.uit.smarthomecontrol.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uit.smarthomecontrol.R;
import com.uit.smarthomecontrol.models.SensorItem;
import com.uit.smarthomecontrol.util.XmlReader;

import java.util.ArrayList;
import java.util.List;

public class CreateNewGroupItemAdapter extends BaseAdapter {
    Context context;
    ArrayList<SensorItem> listSensor;
    private SparseBooleanArray mSelectedItemsIds;

    public CreateNewGroupItemAdapter(Context context, ArrayList<SensorItem> prgmNameList) {
        listSensor = prgmNameList;
        this.context = context;

        mSelectedItemsIds = new SparseBooleanArray();

    }

    @Override
    public int getCount() {
        return listSensor.size();
    }

    @Override
    public SensorItem getItem(int position) {
        return listSensor.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class Holder {
        TextView tvSensor;
        TextView tvRoom;
        SensorItem sensorItem;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.activity_create_new_group_item, parent, false);

            holder = new Holder();
            holder.tvSensor = (TextView) convertView.findViewById(R.id.tvSensor);
            holder.tvRoom = (TextView) convertView.findViewById(R.id.tvRoom);

            convertView.setTag(holder);

        } else {
            // we've just avoided calling findViewById() on resource everytime
            // just use the viewHolder
            holder = (Holder) convertView.getTag();
        }
        holder.sensorItem = listSensor.get(position);
        holder.tvSensor.setText(listSensor.get(position).getSensorName());
        holder.tvRoom.setText(listSensor.get(position).getId());

        convertView
                .setBackgroundColor(mSelectedItemsIds.get(position) ? 0x9934B5E4
                        : Color.TRANSPARENT);
        return convertView;
    }

    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }
    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);
        notifyDataSetChanged();
    }
    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }

    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }
}