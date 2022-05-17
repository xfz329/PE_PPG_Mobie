package com.edu.zju.cbeis.bme207.signal.ppg.features;

import java.util.List;

import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

public class PPGFeatureOfLabelToArcLength  extends AbstractPPGFeature{

	public PPGFeatureOfLabelToArcLength() {
		
	}

	@Override
	public FeatureStorage calculate(Pulse p) {
		FeatureStorage fs = new FeatureStorage();
		fs.setShortName(getShortName());
		fs.setIndex(getOrder());
		
		List<Double> label = p.getNthFeature(getPreCondition0()).getValues();
		int n = label.size()-1;
		double temp;
		for(int i=0;i<n;i++) {
			temp = p.getPointsAdjust().calculateArcLength(label.get(i).intValue(), label.get(i+1).intValue());
			fs.getValues().add(temp);
		}
		return fs;
	}
	
}
