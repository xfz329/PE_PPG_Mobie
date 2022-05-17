package com.edu.zju.cbeis.bme207.signal.ppg.features;


import com.edu.zju.cbeis.bme207.math.Point;
import com.edu.zju.cbeis.bme207.math.Points;
import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

public class PPGFeatureOfStandardization extends AbstractPPGFeature{

	public PPGFeatureOfStandardization() {
		
	}

	@Override
	public FeatureStorage calculate(Pulse p) {
		FeatureStorage fs = new FeatureStorage();
		fs.setShortName(getShortName());
		fs.setIndex(getOrder());
		
		double riseCoff,fallCoff;
		double riseB,fallB;
		Point start =p.getStart();
		Point peak=p.getPeak();
		Point end= p.getEnd();
		Point lst= new Point();
		
		int peakIndex = p.getPeakIndex();
		int endIndex = p.getEndIndex();
		int len =endIndex +1;
		Points pointsAdjust 	= new Points(len);
		
		riseCoff = 1000.0/(peak.getY()-start.getY());
		riseB=-riseCoff*start.getY();
		
		for(int i = 0; i<=peakIndex;i++) {
			pointsAdjust.setNthPoint(i, p.getPointsBasic().getNthPoint(i).getX(), 
					riseCoff*p.getPointsBasic().getNthPoint(i).getY()+riseB, Points.SET_BOTH_XY);
		}
		
		if (p.isSpecialAD()){
			
			Points temp = p.getPointsBasic();
			lst =temp.min(peakIndex,endIndex);
			int lstIndex = lst.getIndex();
			double delta = end.getY()-lst.getY();

			for(int i = lstIndex+1;i<=(lstIndex+endIndex)/2;i++){
				int current = lstIndex+1+endIndex-i;
				double x = temp.getNthPoint(current).getX();
				double y = temp.getNthPoint(current).getY();
				temp.setNthPoint(current, temp.getNthPoint(i), Points.SET_BOTH_XY);
				temp.setNthPoint(i, x, y, Points.SET_BOTH_XY);
			}
			for (int i = peakIndex; i <= lstIndex ; i++){
				double y = delta*(i-peakIndex)/(lstIndex-peakIndex)+temp.getNthPoint(i).getY();
				temp.setNthPoint(i, 0, y, Points.SET_Y_ONLY);
			}
			
			fallCoff = 1000.0/(peak.getY()-lst.getY());
			fallB = -fallCoff*lst.getY();
			for(int i = peakIndex+1; i<=endIndex;i++) {
				pointsAdjust.setNthPoint(i, temp.getNthPoint(i).getX(), 
						fallCoff*temp.getNthPoint(i).getY()+fallB, Points.SET_BOTH_XY);
			}
		}
		else{
			fallCoff = 1000.0/(peak.getY()-end.getY());
			fallB = -fallCoff*end.getY();
			for(int i = peakIndex+1; i<=endIndex;i++) {
				pointsAdjust.setNthPoint(i, p.getPointsBasic().getNthPoint(i).getX(), 
						fallCoff*p.getPointsBasic().getNthPoint(i).getY()+fallB, Points.SET_BOTH_XY);
			}
		}

		p.setPointsAdjust(pointsAdjust);
		fs.getValues().add(riseCoff);
		fs.getValues().add(riseB);
		fs.getValues().add(fallCoff);
		fs.getValues().add(fallB);
		return fs;
	}

}
