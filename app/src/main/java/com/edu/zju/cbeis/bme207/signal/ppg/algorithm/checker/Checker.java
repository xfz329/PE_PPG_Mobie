package com.edu.zju.cbeis.bme207.signal.ppg.algorithm.checker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;
import com.edu.zju.cbeis.bme207.signal.ppg.features.AbstractPPGFeature;
import com.edu.zju.cbeis.bme207.signal.ppg.features.FeatureStorage;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public abstract class Checker {
//	protected Logger logger;
	protected List<Integer> res;
	protected List<Double> values,values2;
	protected int len;
	protected double mean;
	private FeatureStorage fs;
	protected AbstractPPGFeature feature;
	protected double highC,lowC;


	public Checker(){
//		logger = LogManager.getFormatterLogger(getClass().getName());
		res= new ArrayList<Integer>();
		values = new ArrayList<Double>();
        values2 = new ArrayList<Double>();
		fs = new FeatureStorage();
		mean = 0;
		setDefaultC();
	}

	public void setC(double l,double h){
		lowC = l;
		highC = h;
//		logger.info("The coefficients for the checker has been set as : high ( c %f ) ; low ( %f )",highC,lowC);
	}

	public double getHighC() {
		return highC;
	}

	public double getLowC() {
		return lowC;
	}

	public abstract void setDefaultC();

	public abstract List<Integer> check(List<Pulse> p);

	public void prepareData(List<Pulse> p, int index){
		double t;
//		logger.debug("The length of pulse is \n"+len);
		clearCache();
		for(int i = 0;i<len;i++) {
			fs = feature.calculate(p.get(i));
			t=fs.getValues().get(index);
			values.add(t);
            values2.add(t);
		}
	}

	public void getMean(int pl,int ph){
//		logger.debug("The values of the basic checker are \n"+values.toString());
		if((0!=pl) && (100!=ph)){
			values2.sort(Comparator.naturalOrder());
		}
		int low = len*pl/100;
		int high = len*ph/100;
		mean = 0;
		for(int i=low;i<high;i++){
			mean+= values2.get(i);
		}
		mean/=(high-low);
//		logger.debug("The mean values of the check is %f using data ranging from %,1d %% - %,2d %% in naturalOrder.",mean,pl,ph);
	}

	public void select(boolean enableLow){
		double t;
		double low = mean * lowC;
		double high = mean * highC;
//		logger.debug("The coefficients for the checker : high ( c %f ; v %f ) ; low ( b %s %f ; v %f )",highC,high,Boolean.toString(enableLow),lowC,low);
		for(int i =0;i<len;i++) {
			t=values.get(i);
			if((enableLow)&&(t<low)) {
				res.add(i);
			}
			if(t>high) {
				res.add(i);
			}
		}
//		logger.debug("The index of the final checker are \n"+res.toString());
	}
	
	protected void clearCache(){
		values.clear();
		values2.clear();
		res.clear();
	}

}
