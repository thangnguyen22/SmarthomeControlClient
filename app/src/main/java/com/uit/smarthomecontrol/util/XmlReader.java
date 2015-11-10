package com.uit.smarthomecontrol.util;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
    public XmlReader(){
        String sdcard = Environment.
                getExternalStorageDirectory().
                getAbsolutePath();
        filePath = sdcard + "/test/testxml.xml";      //Đọc file xml có sẵn trong sdcard
    }
    public void saxparser(Context context) {
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
            String datashow = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                eventType = parser.next();
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        nodeName = parser.getName();
                        if (nodeName.equals("employee")) {
                            datashow = parser.getAttributeValue(0);
                            Toast.makeText(context,parser.getAttributeValue(0), Toast.LENGTH_SHORT).show();
                        } else if (nodeName.equals("name")) {
                            datashow += parser.nextText() + "-";
                        } else if (nodeName.equals("phone")) {
                            datashow += parser.nextText() + "-";
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        nodeName = parser.getName();
                        if (nodeName.equals("employee")) {
                            datashow += "\n----------------\n";
                        }
                        break;
                }
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void ModifyingXml(Context context){
        //saxparser(context);
        try {
            String filePaths = Environment.getExternalStorageDirectory()+"/testxml.xml";
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
            FileOutputStream _stream=context.openFileOutput("NewDom.xml", Context.MODE_WORLD_WRITEABLE);

            StreamResult result = new StreamResult(_stream);
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);
            saxparser(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
