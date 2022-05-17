package com.edu.zju.cbeis.bme207.signal.ppg.features;

import java.util.List;

import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

public class PPGFeatureOfScaledViewSlope extends AbstractPPGFeature{

	public PPGFeatureOfScaledViewSlope() {
		
	}

	@Override
	public FeatureStorage calculate(Pulse p) {
		FeatureStorage fs = new FeatureStorage();
		fs.setShortName(getShortName());
		fs.setIndex(getOrder());
		
		List<Double> label = p.getNthFeature(getPreCondition0()).getValues();
		double x = p.getPeakIndex();
		double y = 0.0;
		double s;
		for(int i =0 ;i<label.size()-1;i++) {
			s = (y- p.getPointsAdjust().getNthPoint(label.get(i).intValue()).getY())
					/(x-p.getPointsAdjust().getNthPoint(label.get(i).intValue()).getIndex());
			fs.getValues().add(s);
		}
		return fs;
	}	

}
