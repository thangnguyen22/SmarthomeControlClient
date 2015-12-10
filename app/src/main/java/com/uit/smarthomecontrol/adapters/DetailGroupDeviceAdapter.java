package com.uit.smarthomecontrol.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.uit.smarthomecontrol.MainActivity;
import com.uit.smarthomecontrol.R;
import com.uit.smarthomecontrol.models.SensorItem;

import java.util.ArrayList;

public class DetailGroupDeviceAdapter extends BaseAdapter {
    Context context;
    ArrayList<SensorItem> listSensor;

    public DetailGroupDeviceAdapter(Context context, ArrayList<SensorItem> prgmNameList) {
        listSensor = prgmNameList;
        this.context = context;
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
        Switch swStateDevice;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.activity_group_device_detail_item, parent, false);

            holder = new Holder();
            holder.tvSensor = (TextView) convertView.findViewById(R.id.tvSensor);
            holder.tvRoom = (TextView) convertView.findViewById(R.id.tvRoom);
            holder.swStateDevice = (Switch) convertView.findViewById(R.id.swStateDevice);

            convertView.setTag(holder);

        } else {
            // we've just avoided calling findViewById() on resource everytime
            // just use the viewHolder
            holder = (Holder) convertView.getTag();
        }
        holder.tvSensor.setText(listSensor.get(position).getSensorName());
        holder.tvRoom.setText(listSensor.get(position).getId());
        if(listSensor.get(position).getStateCurrent().equals("On")) {
            holder.swStateDevice.setChecked(true);
        }else{
            holder.swStateDevice.setChecked(false);
        }

        holder.swStateDevice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                if (holder.swStateDevice.isChecked() == true) {
                    listSensor.get(position).setStateCurrent("On");
                    Toast.makeText(context,
                            String.valueOf("Your Selected is On"),
                            Toast.LENGTH_SHORT).show();
                } else {
                    listSensor.get(position).setStateCurrent("Off");
                    Toast.makeText(context,
                            String.valueOf("Your Selected is Off"),
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
        return convertView;
    }
    public ArrayList<SensorItem> getListSensor(){
        return listSensor;
    }
}