package com.edu.zju.cbeis.bme207.signal.ppg.features;


import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;


public abstract class AbstractPPGFeature {
//	protected Logger logger;
	private int order;
	private int preCondition0;
	private int preCondition1;
	private String shortName;
	private String meanDescripition;
	private String valueDescripition;
	private String figurePath;
	
	public AbstractPPGFeature() {
		setShortName("");
//		logger = LogManager.getFormatterLogger(getClass().getName());
	}
	
	public void setParas(int order, int pre0, int pre1) {
		setOrder(order);
		setPreCondition0(pre0);
		setPreCondition1(pre1);
	}
	public void setAllDescripition(String confs) {
		String[] conf = confs.split("\\|");
		setShortName(conf[0]);
		setMeanDescripition(conf[1]);
		setValueDescripition(conf[2]);
		setFigurePath(conf[3]);
	}
	
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getPreCondition0() {
		return preCondition0;
	}

	public void setPreCondition0(int preCondition0) {
		this.preCondition0 = preCondition0;
	}

	public int getPreCondition1() {
		return preCondition1;
	}

	public void setPreCondition1(int preCondition1) {
		this.preCondition1 = preCondition1;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getMeanDescripition() {
		return meanDescripition;
	}

	public void setMeanDescripition(String meanDescripition) {
		this.meanDescripition = meanDescripition;
	}

	public String getValueDescripition() {
		return valueDescripition;
	}

	public void setValueDescripition(String valueDescripition) {
		this.valueDescripition = valueDescripition;
	}

	public String getFigurePath() {
		return figurePath;
	}

	public void setFigurePath(String figurePath) {
		this.figurePath = figurePath;
	}

	public abstract FeatureStorage calculate(Pulse p);

}
