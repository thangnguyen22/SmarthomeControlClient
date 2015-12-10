package com.uit.smarthomecontrol.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.uit.smarthomecontrol.AlarmActivity;
import com.uit.smarthomecontrol.R;
import com.uit.smarthomecontrol.models.Alarm;
import com.uit.smarthomecontrol.util.CustomAlarm;

import java.util.Collections;
import java.util.List;

/**
 * Created by tensh on 11/14/2015.
 */
public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.MyViewHolder> {

    List<Alarm> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public AlarmAdapter(Context context, List<Alarm> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_alarm_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (data != null && data.size() > position) {
            String current = data.get(position).getTime();
            boolean state = data.get(position).getState();
            holder.customButton.setValue(current);
            holder.customButton.setStateAlarm(state);
            holder.customButton.setTextSize(35);
        } else {
            holder.customButton.setValue("+");
            holder.customButton.setStateAlarm(true);
            holder.customButton.setTextSize(60);
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    public void changeState(int position) {
        if (position < data.size()) {
            if (data.get(position).getState()) {
                data.get(position).setState(false);
            } else {
                data.get(position).setState(true);
            }
            notifyDataSetChanged();
        } else {
            add();
        }
    }

    public void remove(int position) {
        if (position < data.size()) {
            data.remove(position);
            notifyItemRemoved(position);
        } else {
            add();
        }
    }

    public void add() {
        Alarm alarm = new Alarm("new", false);
        data.add(alarm);
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CustomAlarm customButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            customButton = (CustomAlarm) itemView.findViewById(R.id.alarm_item);
            //itemView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
