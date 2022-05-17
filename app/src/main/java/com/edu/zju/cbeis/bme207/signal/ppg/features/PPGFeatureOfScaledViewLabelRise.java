package com.edu.zju.cbeis.bme207.signal.ppg.features;


import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

public class PPGFeatureOfScaledViewLabelRise extends AbstractPPGFeature{

	public PPGFeatureOfScaledViewLabelRise() {
		
	}

	@Override
	public FeatureStorage calculate(Pulse p) {
		FeatureStorage fs = new FeatureStorage();
		fs.setShortName(getShortName());
		fs.setIndex(getOrder());
		
		double peakValue = p.getPointsAdjust().getNthPoint(p.getPeakIndex()).getY();
		int n = p.getSeparateNum();
		double[] scales = new double[n];
		for(int i =0; i<n; i++) {
			scales[i]= peakValue/n *(i+1);
		}
		fs.getValues().add(0.0);
		int current = 0;
		for(int i = 1; i<p.getPeakIndex()-1;i++) {
			if(( p.getPointsAdjust().getNthPoint(i).getY() >= scales[current]) 
					&& (p.getPointsAdjust().getNthPoint(i+1).getY() <scales[current+1] ) ) {
				current +=1;
				fs.getValues().add((double) p.getPointsAdjust().getNthPoint(i).getIndex());
			}
			if(current == n) {
				break;
			}
		}
		fs.getValues().add((double)p.getPeakIndex());
		return fs;
	}

}
