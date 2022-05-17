package com.edu.zju.cbeis.bme207.signal.ppg.features;

import java.util.List;

import com.edu.zju.cbeis.bme207.math.Point;
import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

public class PPGFeatureOfLabelToRadius extends AbstractPPGFeature{

	public PPGFeatureOfLabelToRadius() {
		
	}

	@Override
	public FeatureStorage calculate(Pulse p) {
		FeatureStorage fs = new FeatureStorage();
		fs.setShortName(getShortName());
		fs.setIndex(getOrder());
		
		List<Double> label = p.getNthFeature(getPreCondition0()).getValues();
		Point center= new Point();
		center.setCord(p.getPointsAdjust().getNthPoint(p.getPeakIndex()));
		for(int i= 0;i<label.size();i++) {
			fs.getValues().add(Point.getDistance(center, p.getPointsAdjust().getNthPoint(label.get(i).intValue())));
		}
		return fs;
	}

}
