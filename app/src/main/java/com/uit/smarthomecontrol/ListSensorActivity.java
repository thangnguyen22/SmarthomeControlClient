package com.uit.smarthomecontrol;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListView;

import com.uit.smarthomecontrol.adapters.ListRoomAdapter;
import com.uit.smarthomecontrol.adapters.ListSensorAdapter;

public class ListSensorActivity extends AppCompatActivity {

    private Toolbar toolbar;

    public static int [] prgmImages={R.drawable.light,R.drawable.lighticon,R.drawable.lighticon,R.drawable.lighticon,R.drawable.lighticon,R.drawable.lighticon,R.drawable.lighticon,R.drawable.lighticon,R.drawable.lighticon};
    public static String [] prgmNameList={"Đèn 1","Đèn 2","Đèn 3","TV","Máy Lạnh","Tủ lạnh","Cửa","Radio","Stereo"};
    public static  int[] colors = {Color.parseColor("#FF197F88"), Color.GREEN, Color.CYAN, Color.LTGRAY,Color.RED, Color.MAGENTA, Color.YELLOW, Color.GRAY, Color.GREEN};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sensor);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Danh Sách Thiết Bị");

        ListView listSensor = (ListView)findViewById(R.id.list_sensor);
        listSensor.setAdapter(new ListSensorAdapter(this.getBaseContext(), prgmNameList, prgmImages, colors));
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
