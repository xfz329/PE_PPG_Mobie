package com.edu.zju.cbeis.bme207.signal.ppg.algorithm.checker;

import java.util.List;

import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

public class TimeChecker extends Checker{

    @Override
    public List<Integer> check(List<Pulse> p) {
        len = p.size();
        prepareData(p);
        getMean(20, 80);
		select(true);
		return res;
    }

    @Override
    public void setDefaultC() {
        setC(0.8,1.2);
    }

    private void prepareData(List<Pulse> p){
        double t;
        clearCache();
        for(int i=0;i<len;i++){
            t=1.0*p.get(i).getEndIndex()/p.get(i).getPeakIndex();
            values.add(t);
            values2.add(t);
        }
    }

}
