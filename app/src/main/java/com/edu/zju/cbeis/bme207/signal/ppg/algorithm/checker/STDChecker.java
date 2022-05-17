package com.edu.zju.cbeis.bme207.signal.ppg.algorithm.checker;

import java.util.List;

import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;
import com.edu.zju.cbeis.bme207.signal.ppg.features.PPGFeatureOfSTD;

public class STDChecker extends Checker{

	@Override
	public List<Integer> check(List<Pulse> p) {
		len = p.size();
        feature = new PPGFeatureOfSTD();
        prepareData(p, 0);
        getMean(0, 100);
		select(true);
		return res;
	}

	@Override
	public void setDefaultC() {
		setC(0.4,1.8);
	}

}
