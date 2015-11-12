package com.uit.smarthomecontrol.util;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.uit.smarthomecontrol.models.RoomItem;
import com.uit.smarthomecontrol.models.SensorItem;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by tensh on 11/3/2015.
 */
public class XmlReader {

    String filePath = null;

    public XmlReader(String fileName) {
        String sdcard = Environment.
                getExternalStorageDirectory().
                getAbsolutePath();
        filePath = sdcard + "/" + fileName;      //Đọc file xml có sẵn trong sdcard
    }

    public ArrayList<RoomItem> XMLParserGetRoom(Context context) {
        ArrayList<RoomItem> outputParser = new ArrayList<>();
        try {
            XmlPullParserFactory fc = XmlPullParserFactory
                    .newInstance();
            XmlPullParser parser = fc.newPullParser();


            FileInputStream fIn = new
                    FileInputStream(filePath);
/*            InputStream inputStream = null;
            inputStream = context.getResources().getAssets().open("xml/testxml.xml");*/

            parser.setInput(fIn, "UTF-8");

            int eventType = -1;
            String nodeName;
            String[] datashow = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                eventType = parser.next();
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        nodeName = parser.getName();
                        if (nodeName.equals("Room")) {

                            outputParser.add(new RoomItem(parser.getAttributeValue(0), parser.getAttributeValue(1)));
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        nodeName = parser.getName();
                        if (nodeName.equals("employee")) {
                        }
                        break;
                }
            }
            return outputParser;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<SensorItem> XMLParserGetDevice(Context context, String roomID) {
        ArrayList<SensorItem> outputParser = new ArrayList<>();
        try {
            DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = fac.newDocumentBuilder();

            FileInputStream fIn = new FileInputStream(filePath);
            Document doc = builder.parse(fIn);
            Element root = doc.getDocumentElement(); //lấy tag Root ra
            NodeList listRoom = root.getElementsByTagName("Room"); // lấy toàn bộ node con của Root
            for (int i = 0; i < listRoom.getLength(); i++) // duyệt từ node đầu tiên cho tới node cuối cùng
            {
                Element eRoom = (Element) listRoom.item(i);// mỗi lần duyệt thì lấy ra 1 node
                if (eRoom.getAttribute("id").equals(roomID)) {
                    NodeList listDevice = eRoom.getElementsByTagName("Device");
                    for (int j = 0; j < listDevice.getLength(); j++) // duyệt từ node đầu tiên cho tới node cuối cùng
                    {
                        Element eDevice = (Element) listDevice.item(j);// mỗi lần duyệt thì lấy ra 1 node
                        String deviceName = eDevice.getAttribute("name");
                        String deviceId = eDevice.getAttribute("id");
                        Element eState = (Element) eDevice.getElementsByTagName("StateCurrent").item(0);
                        String stateCurrent = eState.getTextContent();
                        SensorItem sensorItem = new SensorItem(deviceId, deviceName, "", "", stateCurrent, false, "");
                        outputParser.add(sensorItem);
                    }
                }
            }
            return outputParser;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean ModifyStateDevice(Context context, String roomId, String deviceId, String state) {
        try {
            DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = fac.newDocumentBuilder();

            File file = new File(filePath);
            Document doc = builder.parse(file);
            Element root = doc.getDocumentElement(); //lấy tag Root ra
            NodeList listRoom = root.getElementsByTagName("Room"); // lấy toàn bộ node con của Root
            for (int i = 0; i < listRoom.getLength(); i++) // duyệt từ node đầu tiên cho tới node cuối cùng
            {
                Element eRoom = (Element) listRoom.item(i);// mỗi lần duyệt thì lấy ra 1 node
                if (eRoom.getAttribute("id").equals(roomId)) {
                    NodeList listDevice = eRoom.getElementsByTagName("Device");
                    for (int j = 0; j < listDevice.getLength(); j++) // duyệt từ node đầu tiên cho tới node cuối cùng
                    {
                        Element eDevice = (Element) listDevice.item(j);// mỗi lần duyệt thì lấy ra 1 node
                        if (eDevice.getAttribute("id").equals(deviceId)) {
                            Element eState = (Element) eDevice.getElementsByTagName("StateCurrent").item(0);
                            eState.setTextContent(state);

                            Transformer transformer = TransformerFactory.newInstance().newTransformer();
                            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                            StreamResult result = new StreamResult(file);
                            DOMSource source = new DOMSource(doc);
                            transformer.transform(source, result);

                            return true;
                        }
                    }
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void ModifyingXml(Context context, String fileName) {
        try {
            String filePaths = Environment.getExternalStorageDirectory() + "/testxml.xml";
            File file = new File(filePath);
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);
            // Change the content of node
            Node nodes = doc.getElementsByTagName("id").item(0);
            // I changed the below line form nodes.setNodeValue to nodes.setTextContent
            nodes.setTextContent("new 123");

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // initialize StreamResult with File object to save to file
            FileOutputStream _stream = context.openFileOutput("NewDom.xml", Context.MODE_WORLD_WRITEABLE);

            StreamResult result = new StreamResult(_stream);
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
