package com.uit.smarthomecontrol.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
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
import com.uit.smarthomecontrol.util.CustomAlarm;

import java.util.ArrayList;

public class DetailGroupDeviceAdapter extends RecyclerView.Adapter<DetailGroupDeviceAdapter.MyViewHolder> {
    Context context;
    ArrayList<SensorItem> listSensor;
    private LayoutInflater inflater;

    public DetailGroupDeviceAdapter(Context context, ArrayList<SensorItem> prgmNameList) {
        inflater = LayoutInflater.from(context);
        listSensor = prgmNameList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_group_device_detail_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tvSensor.setText(listSensor.get(position).getSensorName());
        holder.tvRoom.setText(listSensor.get(position).getId());
        if (listSensor.get(position).getStateCurrent().equals("On")) {
            holder.swStateDevice.setChecked(true);
        } else {
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
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return listSensor.size();
    }

    public void remove(int position) {
        listSensor.remove(position);
        notifyItemRemoved(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvSensor;
        TextView tvRoom;
        Switch swStateDevice;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvSensor = (TextView) itemView.findViewById(R.id.tvSensor);
            tvRoom = (TextView) itemView.findViewById(R.id.tvRoom);
            swStateDevice = (Switch) itemView.findViewById(R.id.swStateDevice);
        }
    }

    public ArrayList<SensorItem> getListSensor() {
        return listSensor;
    }
}