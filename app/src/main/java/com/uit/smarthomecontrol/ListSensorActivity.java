package com.uit.smarthomecontrol;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListView;

import com.uit.smarthomecontrol.adapters.ListRoomAdapter;
import com.uit.smarthomecontrol.adapters.ListSensorAdapter;
import com.uit.smarthomecontrol.models.SensorItem;
import com.uit.smarthomecontrol.util.XmlReader;

import java.util.ArrayList;

public class ListSensorActivity extends AppCompatActivity {

    private Toolbar toolbar;

    public static int [] prgmImages={R.drawable.light,R.drawable.lighticon,R.drawable.lighticon,R.drawable.lighticon,R.drawable.lighticon,R.drawable.lighticon,R.drawable.lighticon,R.drawable.lighticon,R.drawable.lighticon};
    public static  int[] colors = {Color.parseColor("#FF197F88"), Color.GREEN, Color.CYAN, Color.LTGRAY,Color.RED, Color.MAGENTA, Color.YELLOW, Color.GRAY, Color.GREEN};
    private ArrayList<SensorItem> arrayDevice;
    public String roomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sensor);

        Intent intent = getIntent();
        roomId = intent.getExtras().getString("RoomId");

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#212121"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Danh Sách Thiết Bị");

        XmlReader xmlReader = new XmlReader("datasmarthome.xml");
        arrayDevice = xmlReader.XMLParserGetDevice(this, roomId);
        ListView listSensor = (ListView)findViewById(R.id.list_sensor);
        listSensor.setAdapter(new ListSensorAdapter(this.getBaseContext(), arrayDevice, prgmImages, colors, roomId));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sensor, menu);
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
