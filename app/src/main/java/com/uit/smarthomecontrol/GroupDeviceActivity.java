package com.uit.smarthomecontrol;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator;
import jp.wasabeef.recyclerview.animators.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;

import com.daimajia.swipe.util.Attributes;
import com.uit.smarthomecontrol.adapters.ListGroupDeviceAdapter;
import com.uit.smarthomecontrol.adapters.ListSensorAdapter;
import com.uit.smarthomecontrol.models.GroupItem;
import com.uit.smarthomecontrol.models.SensorItem;
import com.uit.smarthomecontrol.util.AlarmTouchHelper;
import com.uit.smarthomecontrol.util.DividerItemDecoration;
import com.uit.smarthomecontrol.util.GroupDeviceTouchHelper;
import com.uit.smarthomecontrol.util.XmlReader;

import java.security.acl.Group;
import java.util.ArrayList;

public class GroupDeviceActivity extends AppCompatActivity {

    private ArrayList<GroupItem> arrayGroup;
    /*
    private ListGroupDeviceAdapter listGroupDeviceAdapter;
*/
    private RecyclerView listGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_device);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                startActivity(new Intent(getBaseContext(), CreateNewGroupActivity.class));
                overridePendingTransition(R.anim.unzoom_in, R.anim.unzoom_out);
            }
        });

        toolbar.setTitleTextColor(Color.parseColor("#212121"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Danh Sách Group");

        XmlReader xmlReader = new XmlReader(getBaseContext());
        arrayGroup = xmlReader.XMLParserGetGroup(this.getBaseContext()); //Get group device adapter

        listGroup = (RecyclerView) findViewById(R.id.drawerList); //Create list group device
        // Layout Managers:
        listGroup.setLayoutManager(new LinearLayoutManager(this));
        // Item Decorator:
        listGroup.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider)));

        ListGroupDeviceAdapter listGroupDeviceAdapter = new ListGroupDeviceAdapter(this, arrayGroup);
        listGroupDeviceAdapter.setMode(Attributes.Mode.Single);

        //set adapter
        listGroup.setAdapter(listGroupDeviceAdapter);
        /* Scroll Listeners */
        listGroup.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e("RecyclerView", "onScrollStateChanged");
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}
