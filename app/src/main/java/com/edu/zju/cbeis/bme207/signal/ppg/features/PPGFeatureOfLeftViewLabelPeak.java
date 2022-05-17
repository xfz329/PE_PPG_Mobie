package com.edu.zju.cbeis.bme207.signal.ppg.features;

import java.util.List;

import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

public class PPGFeatureOfLeftViewLabelPeak extends AbstractPPGFeature{

	public PPGFeatureOfLeftViewLabelPeak() {
		
	}

	@Override
	public FeatureStorage calculate(Pulse p) {
		FeatureStorage fs = new FeatureStorage();
		fs.setShortName(getShortName());
		fs.setIndex(getOrder());
		
		
		List<Double> para = p.getNthFeature(getPreCondition0()).getValues();
		for(int i=0;i<para.size();i++) {
			fs.getValues().add(para.get(i)*p.getPeakIndex());
		}
		return fs;
	}

}
