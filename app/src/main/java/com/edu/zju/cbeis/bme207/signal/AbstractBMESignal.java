package com.edu.zju.cbeis.bme207.signal;

import com.alibaba.fastjson.annotation.JSONField;
import com.edu.zju.cbeis.bme207.math.Points;

public class AbstractBMESignal {
	
	@JSONField(name = "SampleRate",	ordinal = 0)
	protected 	int 		sampleRate;
	
	@JSONField(name = "Length",	ordinal = 1)
	protected 	int 		length;
	
	@JSONField(serialize = false)
	protected 	Points		original;
	
//	@JSONField(name = "Points Data",	ordinal = 3)
	@JSONField(serialize = false)
	protected 	Points		array_smooth;
	
	
	public AbstractBMESignal() {
		
	}
	
	public void setOriginal(int[] in) {
		setLength(in.length);
		original 	= new Points(length);
		for(int i = 0;i<length;i++) {
			original.setNthPoint(i, i, in[i],Points.SET_BOTH_XY);
		}
	}
	
	public Points getOriginal() {
		return original;
	}
	
	public Points getArray_smooth() {
		return array_smooth;
	}
	
	public int getSampleRate() {
		return sampleRate;
	}

	public void setSampleRate(int sampleRate) {
		this.sampleRate = sampleRate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
