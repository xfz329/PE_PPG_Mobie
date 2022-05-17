package com.edu.zju.cbeis.bme207.signal.ppg.features;

import java.util.List;

import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

public class PPGFeatureOfScaledViewAreaRise extends AbstractPPGFeature{

	public PPGFeatureOfScaledViewAreaRise() {
		
	}

	@Override
	public FeatureStorage calculate(Pulse p) {
		FeatureStorage fs = new FeatureStorage();
		fs.setShortName(getShortName());
		fs.setIndex(getOrder());
		
		List<Double> l =p.getNthFeature(getPreCondition0()).getValues();
		int n = p.getSeparateNum();
		for(int i =0;i<n;i++)
			fs.getValues().add(0.0);
		int start,end=l.get(l.size()-1).intValue();
		double temp,sum=0.0;
		for(int i= n-1;i>=0;i--) {
			start	=	l.get(i).intValue();
			temp 	= 	p.getPointsAdjust().calculateHorizontalSectionArea(start, end) ;
			fs.getValues().set(i, temp-sum);
			sum 	=	temp;
		}
		return fs;
	}

}
