package com.edu.zju.cbeis.bme207.signal.ppg.algorithm.checker;

import java.util.List;

import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;
import com.edu.zju.cbeis.bme207.signal.ppg.features.PPGFeatureOfBaseline;

public class DeltaSEChecker extends Checker{

    @Override
    public void setDefaultC() {
        setC(0.5, 2);
    }

    @Override
    public List<Integer> check(List<Pulse> p) {
        len = p.size();
        feature = new PPGFeatureOfBaseline();
        prepareData(p);
        mean=1;
        select(true);
		return res;
    }

    private void prepareData(List<Pulse> p){
        double start,peak,end,t;
        clearCache();
        for(int i=0;i<len;i++){
            start = p.get(i).getStart().getY();
            peak = p.get(i).getPeak().getY();
            end = p.get(i).getEnd().getY();
            t= (peak-start)/(peak-end);
            values.add(t);
            values2.add(t);
        }
    }
    
}
