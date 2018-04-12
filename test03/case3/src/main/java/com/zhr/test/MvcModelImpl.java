package com.zhr.test;

import android.content.Context;
import android.util.Log;
import android.util.Xml;
import android.widget.TextView;
import android.widget.Toast;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MvcModelImpl implements MvcModel {

    private static final String TAG = "MvcModelImpl";

    @Override
    public void saveStuInfo(Context mContext , String name ,String number , String sex) {
        //  <?xml version="1.0" encoding="utf-8"?>
        //  <student>
        //      <name>张三</name>
        //      <number>110001</number>
        //      <sex>male</sex>
        //  </student>
        try {
            XmlSerializer xs = Xml.newSerializer();
            File file = new File(mContext.getCacheDir() , name + ".xml");
            FileOutputStream fos = new FileOutputStream(file);
            xs.setOutput(fos , "utf-8");
            xs.startDocument("utf-8" , true);
            xs.startTag(null , "student");
            xs.startTag(null , "name");
            xs.text(name);
            xs.endTag(null , "name");
            xs.startTag(null , "number");
            xs.text(number);
            xs.endTag(null , "number");
            xs.startTag(null , "sex");
            xs.text(sex);
            xs.endTag(null , "sex");
            xs.endTag(null , "student");
            xs.endDocument();
            fos.close();
            Toast.makeText(mContext, "数据保存成功！", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Toast.makeText(mContext, "数据保存失败！", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void readStuInfo(Context mContext , String name , TextView tv) {
        try {
            XmlPullParser xpp = Xml.newPullParser();
            File file = new File(mContext.getCacheDir(), name + ".xml");
            StringBuilder sb = new StringBuilder();
            // 读取文件信息，首先要保证文件存在，且有可读内存
            if (file.exists() && file.length() > 0) {
                FileInputStream fis = new FileInputStream(file);
                xpp.setInput(fis, "utf-8");
                int eventType = xpp.getEventType();
                while(eventType != XmlPullParser.END_DOCUMENT){
                    if(eventType == XmlPullParser.START_TAG){
                        if("name".equals(xpp.getName())){
                            String nameStr = xpp.nextText();
                            sb.append("姓名:" + nameStr + "\n");
                        }else if("number".equals(xpp.getName())){
                            String numberStr = xpp.nextText();
                            sb.append("学号:" + numberStr + "\n");
                        }else if("sex".equals(xpp.getName())){
                            String sex = xpp.nextText();
                            sb.append("性别:" + sex + "\n");
                        }
                    }
                    eventType = xpp.next();
                    Log.i(TAG , "eventType:" + eventType);
                }
                fis.close();
                if(tv != null && sb != null){
                    tv.setText(sb.toString());
                }
            }else{
                Toast.makeText(mContext, "数据保存成功！", Toast.LENGTH_SHORT).show();
            }
        } catch (XmlPullParserException e) {
            Toast.makeText(mContext, "数据保存成功！", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(mContext, "数据保存成功！", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
