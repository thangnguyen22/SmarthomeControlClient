package com.uit.smarthomecontrol;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.uit.smarthomecontrol.adapters.CreateNewGroupItemAdapter;
import com.uit.smarthomecontrol.adapters.DetailGroupDeviceAdapter;
import com.uit.smarthomecontrol.models.SensorItem;
import com.uit.smarthomecontrol.util.AlarmTouchHelper;
import com.uit.smarthomecontrol.util.GroupDeviceTouchHelper;
import com.uit.smarthomecontrol.util.XmlReader;

import java.util.ArrayList;

public class GroupDeviceDetailActivity extends AppCompatActivity {
    DetailGroupDeviceAdapter detailGroupDeviceAdapter;
    RecyclerView listSensor;
    String newGroupName;
    String keyWord;
    String option;
    EditText txtGroupName;
    EditText txtKeyWord;
    TextView tvGroupName;
    TextView tvKeyWord;
    String oldGroupName;
    boolean canAddItem = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_device_detail);

        ArrayList<SensorItem> arrayDevice = getIntent().getParcelableArrayListExtra("LIST_DEVICE");
        oldGroupName = getIntent().getStringExtra("GROUP_NAME");
        keyWord = getIntent().getStringExtra("KEYWORD");
        option = getIntent().getStringExtra("OPTION");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        txtGroupName = (EditText) findViewById(R.id.txtGroupName);
        txtKeyWord = (EditText) findViewById(R.id.txtKeyword);

        tvGroupName = (TextView) findViewById(R.id.tvGroupName);
        tvKeyWord = (TextView) findViewById(R.id.tvKeyword);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Chi Tiết Kịch Bản");

        txtGroupName.setText(oldGroupName);
        txtKeyWord.setText(keyWord);
        tvGroupName.setText(oldGroupName);
        tvKeyWord.setText(keyWord);

        if(option.equals("EDIT")){
            tvGroupName.setVisibility(View.GONE);
            tvKeyWord.setVisibility(View.GONE);
            txtGroupName.setVisibility(View.VISIBLE);
            txtKeyWord.setVisibility(View.VISIBLE);
        }else if(option.equals("PREVIEW")){
            txtGroupName.setVisibility(View.GONE);
            txtKeyWord.setVisibility(View.GONE);
            tvGroupName.setVisibility(View.VISIBLE);
            tvKeyWord.setVisibility(View.VISIBLE);
        }
        //ArrayList<SensorItem> arrayDevice = xmlReader.XMLParserGetDevice(this, "1");
        listSensor = (RecyclerView) findViewById(R.id.list_all_sensor);
        detailGroupDeviceAdapter = new DetailGroupDeviceAdapter(this.getBaseContext(), arrayDevice);

        listSensor.setAdapter(detailGroupDeviceAdapter);
        listSensor.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Setup ItemTouchHelper
        ItemTouchHelper.Callback callback = new GroupDeviceTouchHelper(detailGroupDeviceAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(listSensor);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_group_detail, menu);
        MenuItem itemEdit = menu.findItem(R.id.edit);
        MenuItem itemSave = menu.findItem(R.id.save);

        if(option.equals("EDIT")){
            itemEdit.setVisible(true);
            itemSave.setVisible(false);
        }else if(option.equals("PREVIEW")){
            itemEdit.setVisible(true);
            itemSave.setVisible(false);
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        ContextMenu.ContextMenuInfo sdfj =    item.getMenuInfo();
        //noinspection SimplifiableIfStatement
        if (id == R.id.save) {
            XmlReader xmlReader = new XmlReader(getBaseContext());
            newGroupName = txtGroupName.getText().toString();
            keyWord = txtKeyWord.getText().toString();
            xmlReader.EditGroupDevice(oldGroupName, newGroupName, keyWord, detailGroupDeviceAdapter.getListSensor());
            Intent intent = new Intent(getBaseContext(), GroupDeviceActivity.class);
            startActivity(intent);
            finish();
            return true;
        }else if(id == R.id.edit){
            invalidateOptionsMenu();
            tvGroupName.setVisibility(View.GONE);
            tvKeyWord.setVisibility(View.GONE);
            txtGroupName.setVisibility(View.VISIBLE);
            txtKeyWord.setVisibility(View.VISIBLE);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if(canAddItem){
            menu.getItem(0).setVisible(false);
           menu.getItem(1).setVisible(true);
            canAddItem = false;
        }
        else{
            menu.getItem(0).setVisible(true);
            menu.getItem(1).setVisible(false);
            canAddItem = true;
        }
        return super.onPrepareOptionsMenu(menu);
    }
}
