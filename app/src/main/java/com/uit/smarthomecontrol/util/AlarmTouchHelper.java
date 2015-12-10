package com.uit.smarthomecontrol.util;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.uit.smarthomecontrol.adapters.AlarmAdapter;

/**
 * Created by adammcneilly on 9/8/15.
 */
public class AlarmTouchHelper extends ItemTouchHelper.SimpleCallback {
    private AlarmAdapter mMovieAdapter;

    public AlarmTouchHelper(AlarmAdapter movieAdapter){
        super(0 , ItemTouchHelper.DOWN | ItemTouchHelper.UP);
        this.mMovieAdapter = movieAdapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if(direction == 1) {
            mMovieAdapter.changeState(viewHolder.getAdapterPosition());
        }else if(direction == 2){
            mMovieAdapter.remove(viewHolder.getAdapterPosition());
        }
    }
}
