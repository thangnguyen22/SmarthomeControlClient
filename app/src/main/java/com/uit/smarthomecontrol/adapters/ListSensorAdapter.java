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

public class ListSensorAdapter extends BaseAdapter{
    String [] result;
    Context context;
    int [] colors;
    int [] imageId;
    public ListSensorAdapter(Context context, String[] prgmNameList, int[] prgmImages, int[] prgmColors) {
        // TODO Auto-generated constructor stub
        colors=prgmColors;
        result=prgmNameList;
        this.context=context;
        imageId=prgmImages;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
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

    static class Holder
    {
        TextView tv;
        ImageView img;
        LinearLayout layout;
        boolean isOn = false;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder;

        if(convertView== null) {
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
                    if(!holder.isOn) {
                        holder.img.setBackgroundColor(Color.parseColor("#973A3A3A"));
                        holder.isOn = true;
                        // TODO Auto-generated method stub
                        Toast.makeText(context, "You Clicked " + result[position], Toast.LENGTH_SHORT).show();
                    }else{
                        holder.img.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                        holder.isOn = false;
                        // TODO Auto-generated method stub
                        Toast.makeText(context, "You turn off " + result[position], Toast.LENGTH_SHORT).show();
                    }
                }
            });
            convertView.setTag(holder);

        }else{
            // we've just avoided calling findViewById() on resource everytime
            // just use the viewHolder
            holder = (Holder) convertView.getTag();
        }
        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);
        holder.layout.setBackgroundColor(colors[position]);


        return convertView;
    }
}