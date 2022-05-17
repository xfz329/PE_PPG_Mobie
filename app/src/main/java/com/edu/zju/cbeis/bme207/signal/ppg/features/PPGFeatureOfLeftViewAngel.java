package com.edu.zju.cbeis.bme207.signal.ppg.features;


import com.edu.zju.cbeis.bme207.math.Point;
import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

public class PPGFeatureOfLeftViewAngel extends AbstractPPGFeature{

	public PPGFeatureOfLeftViewAngel() {
		
	}

	@Override
	public FeatureStorage calculate(Pulse p) {
		
		FeatureStorage fs = new FeatureStorage();
		fs.setShortName(getShortName());
		fs.setIndex(getOrder());
		
		Point peak=p.getPointsAdjust().getNthPoint(p.getPeakIndex());
		double slope = peak.getY()/peak.getIndex();
		double maxAngle = Math.atan(slope);
//		System.out.printf("max angle is %f\n", maxAngle);
		int n = p.getSeparateNum();
		for(int i=1;i<n;i++) {
			fs.getValues().add(maxAngle*i/n);
//			System.out.printf("%d th angle is %f\n", i,maxAngle*i/n);
		}
		return fs;
	}

}
