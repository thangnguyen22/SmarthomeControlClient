package com.uit.smarthomecontrol.util;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.uit.smarthomecontrol.adapters.AlarmAdapter;
import com.uit.smarthomecontrol.adapters.DetailGroupDeviceAdapter;

/**
 * Created by adammcneilly on 9/8/15.
 */
public class GroupDeviceTouchHelper extends ItemTouchHelper.SimpleCallback {
    private DetailGroupDeviceAdapter detailGroupDeviceAdapter;

    public GroupDeviceTouchHelper(DetailGroupDeviceAdapter detailGroupDeviceAdapter) {
        super(0, ItemTouchHelper.RIGHT);
        this.detailGroupDeviceAdapter = detailGroupDeviceAdapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        detailGroupDeviceAdapter.remove(viewHolder.getAdapterPosition());
    }
}
