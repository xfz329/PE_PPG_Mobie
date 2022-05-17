package com.edu.zju.cbeis.bme207.signal.ppg;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.edu.zju.cbeis.bme207.math.Point;
import com.edu.zju.cbeis.bme207.math.Points;
import com.edu.zju.cbeis.bme207.signal.ppg.features.FeatureStorage;

public class Pulse{
	
	@JSONField(serialize = false)
	private Point start; 
	
	@JSONField(serialize = false)
	private Point peak; 
	
	@JSONField(serialize = false)
	private Point end; // before
	
	@JSONField(serialize = false)
	private int startIndex;
	
	@JSONField(serialize = false)
	private int peakIndex;
	
	@JSONField(serialize = false)
	private int endIndex;
	
	@JSONField(name = "Features", format = "#.000",	ordinal = 0)
	private List<FeatureStorage>  featureValues;
	
	@JSONField(serialize = false)
	private Points pointsBasic; 
	
	@JSONField(serialize = false)
//	@JSONField(name = "Points",	ordinal = 1)
	private Points pointsAdjust;

	@JSONField(serialize = false)
	private boolean spAD;
	
	@JSONField(serialize = false)
	private int		separateNum;
	
	
	public Pulse() {
		constructPoints();
		separateNum = 10;
		featureValues = new ArrayList<FeatureStorage>();
	}
	public Pulse(Point s,Point p, Point e) {
		constructPoints();
		start.setCord(s);
		peak.setCord(p);
		end.setCord(e);
		startIndex = 0;
		peakIndex = peak.getIndex()-start.getIndex();
		endIndex = end.getIndex() - start.getIndex();
		separateNum = 10;
		featureValues = new ArrayList<FeatureStorage>();
	}
	private void constructPoints() {
		start 	= new Point();
		peak 	= new Point();
		end 	= new Point();
		spAD	= false;
	}
	public Point getStart() {
		return start;
	}
	public Point getPeak() {
		return peak;
	}
	public Point getEnd() {
		return end;
	}

	public int getStartIndex() {
		return startIndex;
	}
	public int getPeakIndex() {
		return peakIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public int getSeparateNum() {
		return separateNum;
	}
	
	public Points getPointsBasic() {
		return pointsBasic;
	}
	
	public void setPointsBasic(Points p) {
		int len =endIndex +1;
		pointsBasic 	= new Points(len);
		for(int i =0;i<len;i++) {
			pointsBasic.setNthPoint(i, p.getNthPoint(start.getIndex()+i), Points.SET_BOTH_XY);
		}
	}
	
	public void enableSpecialAD(boolean f){
		spAD = f;
	}

	public boolean isSpecialAD(){
		return spAD;
	}

	public Points getPointsAdjust() {
		return pointsAdjust;
	}
	
	public void setPointsAdjust(Points pointsAdjust) {
		this.pointsAdjust = pointsAdjust;
	}
	
	public List<FeatureStorage> getFeatureValues() {
		return featureValues;
	}
	public void setFeatureValues(List<FeatureStorage> featureValues) {
		this.featureValues = featureValues;
	}
	public void insertNewFeature(FeatureStorage f) {
		featureValues.add(f);
	}
	
	public FeatureStorage getNthFeature(int n) {
		return featureValues.get(n);
	}

	public void clearCurrentFeatures(){
		featureValues.clear();
	}

}
