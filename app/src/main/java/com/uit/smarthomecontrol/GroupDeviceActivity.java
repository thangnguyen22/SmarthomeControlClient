package com.uit.smarthomecontrol;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.uit.smarthomecontrol.adapters.ListGroupDeviceAdapter;
import com.uit.smarthomecontrol.adapters.ListSensorAdapter;
import com.uit.smarthomecontrol.models.GroupItem;
import com.uit.smarthomecontrol.models.SensorItem;
import com.uit.smarthomecontrol.util.AlarmTouchHelper;
import com.uit.smarthomecontrol.util.GroupDeviceTouchHelper;
import com.uit.smarthomecontrol.util.XmlReader;

import java.security.acl.Group;
import java.util.ArrayList;

public class GroupDeviceActivity extends AppCompatActivity {

    public static int [] prgmImages={R.drawable.light,R.drawable.lighticon,R.drawable.lighticon,R.drawable.lighticon,R.drawable.lighticon,R.drawable.lighticon,R.drawable.lighticon,R.drawable.lighticon,R.drawable.lighticon};
    public static  int[] colors = {Color.parseColor("#FF197F88"), Color.GREEN, Color.CYAN, Color.LTGRAY,Color.RED, Color.MAGENTA, Color.YELLOW, Color.GRAY, Color.GREEN};
    private ArrayList<GroupItem> arrayGroup;
    public String roomId;

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
                //CreateNewGroup();
                startActivity(new Intent(getBaseContext(), CreateNewGroupActivity.class));
                finish();
            }
        });

        roomId = "1";

        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Danh Sách Group");

        XmlReader xmlReader = new XmlReader();
        arrayGroup = xmlReader.XMLParserGetGroup(this.getBaseContext());
        RecyclerView listGroup = (RecyclerView)findViewById(R.id.drawerList); //Create list group device
        ListGroupDeviceAdapter listGroupDeviceAdapter = new ListGroupDeviceAdapter(this.getBaseContext(), arrayGroup);
        listGroup.setAdapter(listGroupDeviceAdapter);
        listGroup.setLayoutManager(new LinearLayoutManager(this));

        // Setup ItemTouchHelper
        ItemTouchHelper.Callback callback = new GroupDeviceTouchHelper(listGroupDeviceAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(listGroup);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_group_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
