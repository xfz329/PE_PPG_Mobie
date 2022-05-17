package com.edu.zju.cbeis.bme207.signal.ppg.features;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class FeatureStorage {
	
	@JSONField(serialize = false)
	private String fullName;
	
	@JSONField(name = "Index",	ordinal = 0)
	private int index;
	
	@JSONField(name = "Abbr",	ordinal = 1)
	private String shortName;
	
	@JSONField(name = "Values",	ordinal = 2)
	private List<Double> values;
	
	
	public FeatureStorage() {
		values = new ArrayList<Double>();
	}


	public int getIndex() {
		return index;
	}


	public void setIndex(int id) {
		this.index = id;
	}


	public String getFullName() {
		return fullName;
	}


	public String getShortName() {
		return shortName;
	}


	public void setValues(List<Double> values) {
		this.values = values;
	}


	public List<Double> getValues() {
		return values;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

}
