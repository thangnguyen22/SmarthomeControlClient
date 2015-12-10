package com.uit.smarthomecontrol.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uit.smarthomecontrol.GroupDeviceActivity;
import com.uit.smarthomecontrol.R;
import com.uit.smarthomecontrol.models.GroupItem;
import com.uit.smarthomecontrol.models.NavigationDrawerItem;
import com.uit.smarthomecontrol.models.SensorItem;
import com.uit.smarthomecontrol.util.XmlReader;

import java.util.ArrayList;

public class ListGroupDeviceAdapter extends RecyclerView.Adapter <ListGroupDeviceAdapter.MyViewHolder>{
    Context context;
    ArrayList<GroupItem> listSensor;
    private LayoutInflater inflater;

    public ListGroupDeviceAdapter(Context context, ArrayList<GroupItem> prgmNameList) {
        // TODO Auto-generated constructor stub
        inflater = LayoutInflater.from(context);
        listSensor = prgmNameList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.content_group_device_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GroupItem current = listSensor.get(position);
        holder.txtGroupName.setText(current.getGroupName());
        holder.groupItem = current;
        holder.layout.setBackgroundColor(0x9934B5E4);
/*        if(holder.sensorItem.getStateCurrent().equals("On")){
            holder.img.setBackgroundColor(Color.parseColor("#973A3A3A"));
        }*/
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public int getItemCount() {
        return listSensor.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtGroupName;
        ImageView img;
        LinearLayout layout;
        GroupItem groupItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtGroupName = (TextView) itemView.findViewById(R.id.textView1);
            img = (ImageView) itemView.findViewById(R.id.imageView1);
            layout = (LinearLayout) itemView.findViewById(R.id.layoutContainer);
        }
        @Override
        public void onClick(View v) {
/*            if (sensorItem.getStateCurrent().equals("Off")) {
                img.setBackgroundColor(Color.parseColor("#973A3A3A"));
                sensorItem.setStateCurrent("On");
                XmlReader xmlReader = new XmlReader("datasmarthome.xml");
                xmlReader.ModifyStateDevice(context, roomId, sensorItem.getId(),"On");
            } else {
                img.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                sensorItem.setStateCurrent("Off");
                XmlReader xmlReader = new XmlReader("datasmarthome.xml");
                xmlReader.ModifyStateDevice(context, roomId, sensorItem.getId(), "Off");
            }*/
        }
    }

    /*@Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final MyViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.activity_list_sensor_item, parent, false);

            holder = new MyViewHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.textView1);
            holder.img = (ImageView) convertView.findViewById(R.id.imageView1);
            holder.layout = (LinearLayout) convertView.findViewById(R.id.layoutContainer);

            convertView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //holder.layout.setBackgroundColor(Color.WHITE);
                    //colors[position] = Color.WHITE;
                    if (holder.sensorItem.getStateCurrent().equals("Off")) {
                        holder.img.setBackgroundColor(Color.parseColor("#973A3A3A"));
                        holder.sensorItem.setStateCurrent("On");
                        XmlReader xmlReader = new XmlReader("datasmarthome.xml");
                        xmlReader.ModifyStateDevice(context, roomId, holder.sensorItem.getId(),"On");
                        // TODO Auto-generated method stub
                        Toast.makeText(context, "You Clicked " + listSensor.get(position).getSensorName(), Toast.LENGTH_SHORT).show();
                    } else {
                        holder.img.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                        holder.sensorItem.setStateCurrent("Off");
                        XmlReader xmlReader = new XmlReader("datasmarthome.xml");
                        xmlReader.ModifyStateDevice(context, roomId, holder.sensorItem.getId(), "Off");
                        // TODO Auto-generated method stub
                        Toast.makeText(context, "You turn off " + listSensor.get(position).getSensorName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            convertView.setTag(holder);

        } else {
            // we've just avoided calling findViewById() on resource everytime
            // just use the viewHolder
            holder = (MyViewHolder) convertView.getTag();
        }
        holder.sensorItem = listSensor.get(position);
        holder.tv.setText(listSensor.get(position).getSensorName());
        //holder.img.setImageResource(imageId[position]);
        holder.layout.setBackgroundColor(colors[1]);
        if(listSensor.get(position).getStateCurrent().equals("On")){
            holder.img.setBackgroundColor(Color.parseColor("#973A3A3A"));
        }
        return convertView;
    }*/
}