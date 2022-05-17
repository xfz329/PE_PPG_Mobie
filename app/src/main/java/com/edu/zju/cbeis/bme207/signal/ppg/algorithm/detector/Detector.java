package com.edu.zju.cbeis.bme207.signal.ppg.algorithm.detector;

import java.util.List;

import com.edu.zju.cbeis.bme207.math.Points;
import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public abstract class Detector {
//	protected Logger logger;

	public Detector(){
//		logger = LogManager.getFormatterLogger(getClass().getName());
	}
	
	public abstract List<Pulse> locate(Points in, int sampleRate);
	
	public abstract List<Pulse> locate(Points in , int sampleRate, List<Pulse> pulse, int coefficient);
	
}
