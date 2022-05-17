package com.edu.zju.cbeis.bme207.signal.ppg.features;


import com.edu.zju.cbeis.bme207.math.Point;
import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

public class PPGFeatureOfScaledViewLabelFall extends AbstractPPGFeature{

	public PPGFeatureOfScaledViewLabelFall() {
		
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
		fs.getValues().add((double) n);
		fs.getValues().add((double)p.getPeakIndex());
		int low, high, current = 	n	-	2	;
		
		Point cp,np;
		
		for(int i =p.getPeakIndex()+1;i<p.getEndIndex();i++) {
			cp = p.getPointsAdjust().getNthPoint(i);
			np = p.getPointsAdjust().getNthPoint(i+1);
			high 	=	current	+	1;
			low 	=	current	-	1;
			if( (cp.getY() >= scales[current]) && (np.getY() < scales[current])) {
				fs.getValues().add((double) current+1);
				fs.getValues().add((double)cp.getIndex());
				continue;
			}
			if( (cp.getY() <= scales[current]) && (np.getY() > scales[current])) {
				fs.getValues().add((double) current+1);
				fs.getValues().add((double)cp.getIndex());
				continue;
			}
			if( (low >=0) && (cp.getY() >= scales[low]) && (np.getY() < scales[low])) {
				fs.getValues().add((double) low+1);
				fs.getValues().add((double)cp.getIndex());
				current = low;
				continue;
			}
			if( (high <=n) &&(cp.getY() <= scales[high]) && (np.getY() > scales[high])) {
				fs.getValues().add((double) high+1);
				fs.getValues().add((double)cp.getIndex());
				current =high;
				continue;
			}
			
		}
		fs.getValues().add((double) 0);
		fs.getValues().add((double)p.getEndIndex());
		return fs;
	}

}
