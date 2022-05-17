package com.edu.zju.cbeis.bme207.signal.ppg.features;

import java.util.List;

import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

public class PPGFeatureOfLabelToDuration extends AbstractPPGFeature{

	public PPGFeatureOfLabelToDuration() {
		
	}

	@Override
	public FeatureStorage calculate(Pulse p) {
		FeatureStorage fs = new FeatureStorage();
		fs.setShortName(getShortName());
		fs.setIndex(getOrder());
		
		List<Double> r = p.getNthFeature(getPreCondition0()).getValues();
		List<Double> f = p.getNthFeature(getPreCondition1()).getValues();
		int n = r.size();
		double d;
		for(int i =0;i<n;i++) {
			d = f.get(i)-r.get(i);
			fs.getValues().add(d);
		}
		return fs;
	}

}
