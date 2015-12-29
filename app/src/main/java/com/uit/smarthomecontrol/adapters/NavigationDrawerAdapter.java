package com.uit.smarthomecontrol.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.uit.smarthomecontrol.GroupDeviceActivity;
import com.uit.smarthomecontrol.ListRoomActivity;
import com.uit.smarthomecontrol.ListSensorActivity;
import com.uit.smarthomecontrol.R;
import com.uit.smarthomecontrol.models.NavigationDrawerItem;
import com.uit.smarthomecontrol.util.ActivityAnimator;

import java.util.Collections;
import java.util.List;

/**
 * Created by tensh on 10/24/2015.
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter <NavigationDrawerAdapter.MyViewHolder>{
    List<NavigationDrawerItem> data= Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private Activity activity;
    private String[] _animationList = {"fade", "flipHorizontal", "flipVertical", "disappearTopLeft", "appearTopLeft", "appearBottomRight", "disappearBottomRight", "unzoom"};

    public NavigationDrawerAdapter(Context context, List<NavigationDrawerItem> data, Activity activity){
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
        this.activity = activity;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.navigation_drawer_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavigationDrawerItem current = data.get(position);
        //holder.title.setText(current.getTitle());
        holder.icon.setImageResource(current.getIconId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //title = (TextView) itemView.findViewById(R.id.moduleTitle);
            icon = (ImageView) itemView.findViewById(R.id.moduleIcon);
        }

        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, GroupDeviceActivity.class));
            try
            {
                activity.overridePendingTransition(R.anim.unzoom_in, R.anim.unzoom_out);
            }
            catch (Exception e) {
                Toast.makeText(context, "An error occured " + e.toString(), Toast.LENGTH_LONG).show(); }
        }
    }
}
