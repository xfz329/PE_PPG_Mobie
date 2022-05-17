package com.edu.zju.cbeis.bme207.signal.ppg.features;


import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

public class PPGFeatureOfEnergy extends AbstractPPGFeature{

	public PPGFeatureOfEnergy() {
		
	}

	@Override
	public FeatureStorage calculate(Pulse p) {
		FeatureStorage fs = new FeatureStorage();
		fs.setShortName(getShortName());
		fs.setIndex(getOrder());
		
		int len = p.getEndIndex()+1;
		double sum = 0.0,y;
		for(int i=0;i<len;i++) {
			y = p.getPointsBasic().getNthPoint(i).getY();
			sum += y*y;
		}
		sum /=len;
		fs.getValues().add(Math.sqrt(sum));
		return fs;
	}

}
