package com.edu.zju.cbeis.bme207.record.reader;

import com.edu.zju.cbeis.bme207.record.PERecord;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public abstract class PPGReader {
//	protected Logger logger;
	
	public PPGReader(){
//		logger = LogManager.getFormatterLogger(getClass().getName());
	}
	public abstract PERecord readFromFile(String file);
}
