package com.uit.smarthomecontrol.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
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

public class ListSensorAdapter extends BaseAdapter {
    Context context;
    int[] colors;
    int[] imageId;
    ArrayList<SensorItem> listSensor;
    String roomId;

    public ListSensorAdapter(Context context, ArrayList<SensorItem> prgmNameList, int[] prgmImages, int[] prgmColors, String roomId) {
        // TODO Auto-generated constructor stub
        colors = prgmColors;
        listSensor = prgmNameList;
        this.context = context;
        imageId = prgmImages;
        this.roomId = roomId;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listSensor.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    static class Holder {
        TextView tv;
        ImageView img;
        LinearLayout layout;
        SensorItem sensorItem;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.activity_list_sensor_item, parent, false);

            holder = new Holder();
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
            holder = (Holder) convertView.getTag();
        }
        holder.sensorItem = listSensor.get(position);
        holder.tv.setText(listSensor.get(position).getSensorName());
        holder.img.setImageResource(imageId[position]);
        holder.layout.setBackgroundColor(colors[position]);
        if(listSensor.get(position).getStateCurrent().equals("On")){
            holder.img.setBackgroundColor(Color.parseColor("#973A3A3A"));
        }
        return convertView;
    }
}