package com.uit.smarthomecontrol;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.uit.smarthomecontrol.adapters.CreateNewGroupItemAdapter;
import com.uit.smarthomecontrol.adapters.DetailGroupDeviceAdapter;
import com.uit.smarthomecontrol.models.SensorItem;
import com.uit.smarthomecontrol.util.XmlReader;

import java.util.ArrayList;

public class GroupDeviceDetailActivity extends AppCompatActivity {
    DetailGroupDeviceAdapter detailGroupDeviceAdapter;
    ListView listSensor;
    String groupName;
    String keyWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_device_detail);

        ArrayList<SensorItem> arrayDevice = getIntent().getParcelableArrayListExtra("LIST_DEVICE");
        groupName = getIntent().getStringExtra("GROUP_NAME");
        keyWord = getIntent().getStringExtra("KEYWORD");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        EditText txtGroupName = (EditText) findViewById(R.id.txtGroupName);
        EditText txtKeyWord = (EditText) findViewById(R.id.txtKeyword);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Chi Tiết Kịch Bản");

        txtGroupName.setText(groupName);
        txtKeyWord.setText(keyWord);

        XmlReader xmlReader = new XmlReader("datasmarthome.xml");
        //ArrayList<SensorItem> arrayDevice = xmlReader.XMLParserGetDevice(this, "1");
        listSensor = (ListView) findViewById(R.id.list_all_sensor);
        detailGroupDeviceAdapter = new DetailGroupDeviceAdapter(this.getBaseContext(), arrayDevice);

        listSensor.setAdapter(detailGroupDeviceAdapter);
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
        if (id == R.id.accept) {
            XmlReader xmlReader = new XmlReader();

            ArrayList<SensorItem> sdf = detailGroupDeviceAdapter.getListSensor();
            xmlReader.CreateGroupDevice(groupName, keyWord, detailGroupDeviceAdapter.getListSensor());

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
