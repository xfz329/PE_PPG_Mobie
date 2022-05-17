package com.edu.zju.cbeis.bme207.util;

import java.io.File;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class Path2String {
//    private Logger logger;

    public Path2String(){
//        logger = LogManager.getFormatterLogger(getClass().getName());
    }
    public String process(String path){
        File f = new File(path);
        File2String fs = new File2String();
        return fs.process(f);
    }
}
