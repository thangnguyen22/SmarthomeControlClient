package com.uit.smarthomecontrol;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.uit.smarthomecontrol.adapters.AlarmAdapter;
import com.uit.smarthomecontrol.models.Alarm;
import com.uit.smarthomecontrol.util.AlarmTouchHelper;
import com.uit.smarthomecontrol.util.CustomAlarm;
import com.uit.smarthomecontrol.util.CustomClock;

import java.util.ArrayList;
import java.util.List;

public class AlarmActivity extends AppCompatActivity {

    private CustomClock seekBar;
    private CustomAlarm customButton;
    private TextView textView;
    private TextView txtMinutes;
    private RecyclerView recyclerView;
    String[] data = {"09:00", "01:10", "10:10", "09:00", "01:10", "10:10"};
    //int[] data = {1,1,1,1,Color.parseColor("#0174DF"), Color.parseColor("#D50000"), Color.parseColor("#880174DF"), Color.parseColor("#BDBDBD")};
    List<Alarm> data1 = new ArrayList<>();
    private boolean isHour = true;
    private AlarmAdapter alarmAdapter;

    public AlarmActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        recyclerView = (RecyclerView) findViewById(R.id.recycle_alarm);
        seekBar = (CustomClock) findViewById(R.id.clock);
        // customButton = (CustomButton) rootView.findViewById(R.id.timeitem);
        textView = (TextView) findViewById(R.id.textview);
        txtMinutes = (TextView) findViewById(R.id.textview2);

        //customButton.setValue("09:00");


        for (int i = 0; i < data.length; i++) {

            Alarm alarm = new Alarm(data[i], false);
            data1.add(alarm);
        }
        alarmAdapter = new AlarmAdapter(this, data1);
        recyclerView.setAdapter(alarmAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Code edit
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        // Setup ItemTouchHelper
        ItemTouchHelper.Callback callback = new AlarmTouchHelper(alarmAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);

        seekBar.setOnSeekBarChangeListener(new CustomClock.OnCircleSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CustomClock seekBar, int progress, boolean fromUser) {
                if (isHour) {
                    textView.setText(String.format("%02d", progress));
                    //value.setText(String.valueOf(progress));
                } else {
                    txtMinutes.setText(String.format("%02d", progress));
                }
                if (progress == seekBar.getMaxValue()) {
                    seekBar.setValue(0);
                }

            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isHour) {
                    isHour = true;
                    seekBar.setMax(24);
                }
                Toast.makeText(getBaseContext(), "Da nhan", Toast.LENGTH_SHORT).show();
            }
        });
        txtMinutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHour) {
                    isHour = false;
                    seekBar.setMax(60);
                }
                Toast.makeText(getBaseContext(), "Da nhan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public interface ClickListener {

        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    Log.d("Congthuc", "Single up" + e.toString());
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    Log.d("Congthuc", "longTouchEvent" + e.toString());
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            Log.d("Congthuc", "onIntercapTouchEvent" + e.toString());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            Log.d("Congthuc", "onTouchEvent" + e.toString());

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
