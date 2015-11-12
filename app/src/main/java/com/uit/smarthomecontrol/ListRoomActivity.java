package com.uit.smarthomecontrol;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.uit.smarthomecontrol.adapters.ListRoomAdapter;
import com.uit.smarthomecontrol.models.RoomItem;
import com.uit.smarthomecontrol.util.XmlReader;

import java.util.ArrayList;

public class ListRoomActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ArrayList<RoomItem> arrayRoomName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_room);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Danh Sách Phòng");

        XmlReader xmlReader = new XmlReader("datasmarthome.xml");
        arrayRoomName = xmlReader.XMLParserGetRoom(this);
        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new ListRoomAdapter(this, arrayRoomName));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_room, menu);
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
