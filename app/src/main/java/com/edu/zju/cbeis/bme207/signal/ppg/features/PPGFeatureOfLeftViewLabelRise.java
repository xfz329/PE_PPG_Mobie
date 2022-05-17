package com.edu.zju.cbeis.bme207.signal.ppg.features;

import java.util.List;

import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

public class PPGFeatureOfLeftViewLabelRise extends AbstractPPGFeature{

	public PPGFeatureOfLeftViewLabelRise() {
		
	}

	@Override
	public FeatureStorage calculate(Pulse p) {
		FeatureStorage fs = new FeatureStorage();
		fs.setShortName(getShortName());
		fs.setIndex(getOrder());
		
		List<Double> slope = p.getNthFeature(getPreCondition0()).getValues();
		double temp, t,least;
		double label;
		for(int i=0;i<slope.size();i++) {
			least	=	1000;
			label	=	0;
			for(int j = 1;j<p.getPeakIndex();j++) {
				t = j*slope.get(i);
				temp =Math.abs(t-p.getPointsAdjust().getNthPoint(j).getY());
				if(least>temp) {
					least=temp;
					label = j;
				}
			}
			fs.getValues().add(label);
		}
		return fs;
	}

}
