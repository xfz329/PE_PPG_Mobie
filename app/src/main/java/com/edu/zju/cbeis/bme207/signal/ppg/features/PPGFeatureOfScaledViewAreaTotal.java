package com.edu.zju.cbeis.bme207.signal.ppg.features;

import java.util.List;

import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

public class PPGFeatureOfScaledViewAreaTotal extends AbstractPPGFeature{

	public PPGFeatureOfScaledViewAreaTotal() {
		
	}

	@Override
	public FeatureStorage calculate(Pulse p) {
		FeatureStorage fs = new FeatureStorage();
		fs.setShortName(getShortName());
		fs.setIndex(getOrder());
		
		List<Double> r =p.getNthFeature(getPreCondition0()).getValues();
		List<Double> f =p.getNthFeature(getPreCondition0()).getValues();
		int n = p.getSeparateNum();
		for(int i =0;i<n;i++)
			fs.getValues().add(r.get(i)+f.get(i));
		return fs;
	}

}
