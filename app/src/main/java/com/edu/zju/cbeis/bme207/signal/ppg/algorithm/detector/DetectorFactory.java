package com.edu.zju.cbeis.bme207.signal.ppg.algorithm.detector;

public class DetectorFactory {
	private static Detector df;
	private DetectorFactory() {
	
	}
	
	public static Detector getPPGDetector() {
		if(df == null){
			df = new PromotedDetector();
		}
		return df;
	}

}
