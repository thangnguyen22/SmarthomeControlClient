package com.uit.smarthomecontrol;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.Xml;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.uit.smarthomecontrol.adapters.CreateNewGroupItemAdapter;
import com.uit.smarthomecontrol.models.SensorItem;
import com.uit.smarthomecontrol.util.XmlReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class CreateNewGroupActivity extends AppCompatActivity {
    CreateNewGroupItemAdapter createNewGroupItemAdapter;
    ListView listSensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_group);

        XmlReader xmlReader = new XmlReader("datasmarthome.xml");
        ArrayList<SensorItem> arrayDevice = xmlReader.XMLParserGetDevice(this, "1");
        listSensor = (ListView) findViewById(R.id.list_all_sensor);
        createNewGroupItemAdapter = new CreateNewGroupItemAdapter(this.getBaseContext(), arrayDevice);

        listSensor.setAdapter(createNewGroupItemAdapter);
        listSensor.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        // Capture ListView item click
        listSensor.setMultiChoiceModeListener(new MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode,
                                                  int position, long id, boolean checked) {
                // Capture total checked items
                final int checkedCount = listSensor.getCheckedItemCount();
                // Set the CAB title according to total checked items
                mode.setTitle(checkedCount + " Selected");
                // Calls toggleSelection method from ListViewAdapter Class
                createNewGroupItemAdapter.toggleSelection(position);
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.menu_create_group, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.accept:
                        // Calls getSelectedIds method from ListViewAdapter Class
                        SparseBooleanArray selected = createNewGroupItemAdapter
                                .getSelectedIds();
                        // Captures all selected ids with a loop
                        ArrayList<SensorItem> sensorItemArrayList = new ArrayList<SensorItem>();
                        for (int i = (selected.size() - 1); i >= 0; i--) {
                            if (selected.valueAt(i)) {
                                SensorItem selecteditem = createNewGroupItemAdapter
                                        .getItem(selected.keyAt(i));
                                // TODO create group
                                selecteditem.setStateCurrent("On");
                                sensorItemArrayList.add(selecteditem);
                                //CreateGroupDevice();
                            }
                        }
                        //XmlReader xmlReader = new XmlReader();
                        TextView txtGroupName = (TextView)findViewById(R.id.txtGroupName);
                        TextView txtKeyWord = (TextView)findViewById(R.id.txtKeyword);

                        String groupName = txtGroupName.getText().toString();
                        String keyWord = txtKeyWord.getText().toString();
                        //xmlReader.CreateGroupDevice(groupName, keyWord, sensorItemArrayList);
                        // Close CAB
                        mode.finish();

                        //Navigate to detail activity
                        Intent intent = new Intent(getBaseContext(), GroupDeviceDetailActivity.class);
                        intent.putParcelableArrayListExtra("LIST_DEVICE", sensorItemArrayList);
                        intent.putExtra("GROUP_NAME",groupName);
                        intent.putExtra("KEYWORD",keyWord);
                        startActivity(intent);
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                createNewGroupItemAdapter.removeSelection();
            }
        });
    }


}
