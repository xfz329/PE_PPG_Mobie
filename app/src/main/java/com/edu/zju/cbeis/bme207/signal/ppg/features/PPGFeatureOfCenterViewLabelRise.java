package com.edu.zju.cbeis.bme207.signal.ppg.features;


import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

public class PPGFeatureOfCenterViewLabelRise extends AbstractPPGFeature{

	public PPGFeatureOfCenterViewLabelRise() {
		
	}

	@Override
	public FeatureStorage calculate(Pulse p) {
		
		FeatureStorage fs = new FeatureStorage();
		fs.setShortName(getShortName());
		fs.setIndex(getOrder());
		
		int	n = 12;
		double y,k,b;
		double least,t;
		int label=p.getPeakIndex();
		for(int i =1;i < n; i++) {
			k = Math.tan(Math.PI-Math.PI/2 *i/n);
			b = -k*p.getPeakIndex();
			least = p.getPointsAdjust().getNthPoint(p.getPeakIndex()).getY();
			for(int j = p.getStartIndex()+1;j<p.getPeakIndex();j++) {
				y = k*j+b;
				t = Math.abs(y-p.getPointsAdjust().getNthPoint(j).getY());
				if(least>t) {
					least = t;
					label = j;
				}
			}
			fs.getValues().add((double)label);
		}
		return fs;
	}

}
