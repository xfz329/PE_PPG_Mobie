package com.edu.zju.cbeis.bme207.signal.ppg.features;

import java.util.List;

import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

public class PPGFeatureOfLabelToArcArea extends AbstractPPGFeature{

	public PPGFeatureOfLabelToArcArea() {
	
	}

	@Override
	public FeatureStorage calculate(Pulse p) {
		FeatureStorage fs = new FeatureStorage();
		fs.setShortName(getShortName());
		fs.setIndex(getOrder());
		
		List<Double> label = p.getNthFeature(getPreCondition0()).getValues();
		int start = label.get(0).intValue();
		int end;
		double total = 0.0,temp;
		for(int i =1;i<label.size();i++) {
			end=label.get(i).intValue();
			temp=p.getPointsAdjust().calculateArcArea(start, end, p.getPeakIndex());
			fs.getValues().add(temp-total);
			total = temp;
		}
		return fs;
	}	

}
