package com.edu.zju.cbeis.bme207.signal.ppg.algorithm.checker;

import java.util.List;

import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;
import com.edu.zju.cbeis.bme207.signal.ppg.features.PPGFeatureOfBaseline;

public class AbsoluteBaselineChecker extends Checker{

    @Override
    public List<Integer> check(List<Pulse> p) {
        len = p.size();
        feature = new PPGFeatureOfBaseline();
        prepareData(p, 0);
        getMean(0, 80);
        select(false);
		return res;
    }

    @Override
    public void setDefaultC() {
        setC(0,10);
    }
    
}
