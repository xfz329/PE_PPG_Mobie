package com.edu.zju.cbeis.bme207.signal.ppg.features;


import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

public class PPGFeatureOfScaledViewEdgeRise extends AbstractPPGFeature{

	public PPGFeatureOfScaledViewEdgeRise() {
		
	}

	@Override
	public FeatureStorage calculate(Pulse p) {
		FeatureStorage fs = new FeatureStorage();
		fs.setShortName(getShortName());
		fs.setIndex(getOrder());
		
		fs.setValues(p.getNthFeature(getPreCondition0()).getValues());
		return fs;
	}

}
