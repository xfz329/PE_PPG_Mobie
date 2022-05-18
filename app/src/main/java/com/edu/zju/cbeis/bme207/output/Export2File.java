package com.edu.zju.cbeis.bme207.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.DoubleSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.edu.zju.cbeis.bme207.record.PERecord;
import com.edu.zju.cbeis.bme207.record.SimplePERecord;

//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;

public class Export2File {
    private PERecord record;

    private String data,json,json2,detectionjson;
    private String total,detection;
    private String root,sub,pre;
//    private Logger logger;

    public Export2File() {
//        logger= LogManager.getFormatterLogger(getClass().getName());
    }

    public Export2File(PERecord record) {
        setRecord(record);
//        logger= LogManager.getFormatterLogger(getClass().getName());
    }

    public void export(boolean withfeature) {
        prepareDirs();
        prepareFiles();
        if (withfeature)
            output();
        else
            outputDetection();
//        logger.info("exporting success!");
    }

    private void prepareDirs() {
        root = System.getProperty("user.dir") +"\\data_version"+record.getVersion();
        checkDir(root);
        total = root+"\\Total";
        checkDir(total);
        detection = root +"\\Detection";
        checkDir(detection);

        if( 0== record.getPeState() ){
            root+="\\NO";
            total+="\\NO";
        }
        else{
            root+="\\PE";
            total+="\\PE";
        }
        checkDir(root);
        checkDir(total);

        pre = record.getFileName();
        pre = pre.substring(0,pre.lastIndexOf("."));
        sub = root+"\\"+pre;
        checkDir(sub);
    }

    private void checkDir(String s){
        File dir = new File(s);
        if(!dir.exists() && !dir.isDirectory()) {
            dir.mkdir();
        }
    }

    private void prepareFiles() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("_yyyy_MM_dd_HH_mm_ss");
        String time = dateFormat.format(now);
        json = sub+"\\"+pre+time+".json";
        json2 = total+"\\"+pre+time+".json";
        detectionjson = detection+"\\detection_"+pre+time+".json";
        data = sub+"\\"+record.getFileName();
    }

    private void output() {
        File dataFile = new File(data);
        File source = new File(record.getFullPath());
        if(!dataFile.exists()) {
            try{
                Files.copy(source.toPath(), dataFile.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        record.setJsonFile(json);
        SerializeConfig config = new SerializeConfig();
        config.put(Double.class, new DoubleSerializer(new DecimalFormat("#.###")));
        String s =JSON.toJSONString(record,config,SerializerFeature.DisableCircularReferenceDetect);
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(json);
            fos.write(s.getBytes());
            fos.close();
            fos = new FileOutputStream(json2);
            fos.write(s.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        record.setExported(true);
    }

    public void outputDetection(){
        SerializeConfig config = new SerializeConfig();
        config.put(Double.class, new DoubleSerializer(new DecimalFormat("#.###")));
        String s = JSON.toJSONString(new SimplePERecord(record),config,SerializerFeature.DisableCircularReferenceDetect);
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(detectionjson);
            fos.write(s.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PERecord getRecord() {
        return record;
    }

    public void setRecord(PERecord record) {
        this.record = record;
    }

}