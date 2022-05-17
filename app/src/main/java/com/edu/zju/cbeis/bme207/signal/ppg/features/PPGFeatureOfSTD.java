package com.edu.zju.cbeis.bme207.signal.ppg.features;


import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

public class PPGFeatureOfSTD extends AbstractPPGFeature{

	public PPGFeatureOfSTD() {
		
	}

	@Override
	public FeatureStorage calculate(Pulse p) {
		FeatureStorage fs = new FeatureStorage();
		fs.setShortName(getShortName());
		fs.setIndex(getOrder());
		
		double mean = 0.0,temp;
		int len = p.getEndIndex()+1;
		double sum = 0.0,y;
		for(int i=0;i<len;i++) {
			y = p.getPointsBasic().getNthPoint(i).getY();
			sum += y*y;
			mean +=y;
		}
		sum /=len;
		mean/=len;
		temp = Math.sqrt(sum-mean*mean);
		fs.getValues().add(temp);
		return fs;
	}

}
