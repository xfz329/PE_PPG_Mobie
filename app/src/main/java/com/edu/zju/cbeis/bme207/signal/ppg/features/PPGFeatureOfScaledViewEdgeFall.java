package com.edu.zju.cbeis.bme207.signal.ppg.features;

import java.util.List;

import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

public class PPGFeatureOfScaledViewEdgeFall extends AbstractPPGFeature{

	public PPGFeatureOfScaledViewEdgeFall() {
		
	}

	@Override
	public FeatureStorage calculate(Pulse p) {
		FeatureStorage fs = new FeatureStorage();
		fs.setShortName(getShortName());
		fs.setIndex(getOrder());
		
//		int n = p.getSeparateNum()+1;
//		res = new ArrayList<Double>(n);
		List<Double> temp=p.getNthFeature(getPreCondition0()).getValues();
		int index;
		for(int i = temp.size()-2;i>=0;i-=2) {
			index = temp.get(i).intValue();
			fs.getValues().add(0.0);
			fs.getValues().set(index, temp.get(i+1));
		}
		return fs;
	}

}
