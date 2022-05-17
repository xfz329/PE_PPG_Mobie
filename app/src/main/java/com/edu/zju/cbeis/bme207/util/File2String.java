package com.edu.zju.cbeis.bme207.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.Reader;
import java.io.InputStreamReader;


//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class File2String {
//    private Logger logger;

    public File2String(){
//        logger = LogManager.getFormatterLogger(getClass().getName());
    }
    public String process(File f){
        String result = "";
//        logger.info("Start reading " + f.getPath());
        try {
            FileReader fileReader = new FileReader(f);
            Reader reader = new InputStreamReader(new FileInputStream(f), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            result = sb.toString();
//            logger.info("Read " + f.getPath() + " finished");
        } catch (Exception e) {
//            logger.info("Read " + f.getPath() + " failed");
            e.printStackTrace();
        }
        return result;
    }
}
