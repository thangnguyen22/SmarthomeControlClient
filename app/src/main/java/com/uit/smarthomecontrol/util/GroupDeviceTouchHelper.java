package com.uit.smarthomecontrol.util;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.uit.smarthomecontrol.adapters.AlarmAdapter;
import com.uit.smarthomecontrol.adapters.ListGroupDeviceAdapter;

/**
 * Created by adammcneilly on 9/8/15.
 */
public class GroupDeviceTouchHelper extends ItemTouchHelper.SimpleCallback {
    private ListGroupDeviceAdapter mMovieAdapter;

    public GroupDeviceTouchHelper(ListGroupDeviceAdapter movieAdapter){
        super(0 , ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.mMovieAdapter = movieAdapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
    }
}
