package com.edu.zju.cbeis.bme207.signal.ppg.features;

import java.util.List;

import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

public class PPGFeatureOfLeftViewSlope extends AbstractPPGFeature{

	public PPGFeatureOfLeftViewSlope() {

	}

	@Override
	public FeatureStorage calculate(Pulse p) {
		FeatureStorage fs = new FeatureStorage();
		fs.setShortName(getShortName());
		fs.setIndex(getOrder());
		
		List<Double> angle = p.getNthFeature(getPreCondition0()).getValues();
		for(int i=0;i<angle.size();i++) {
			fs.getValues().add(Math.tan(angle.get(i)));
		}
		return fs;
	}

}
