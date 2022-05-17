package com.edu.zju.cbeis.bme207.signal.ppg.features;

import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

public class PPGFeatureOfBaseline extends AbstractPPGFeature{

    @Override
    public FeatureStorage calculate(Pulse p) {
        FeatureStorage fs = new FeatureStorage();
		fs.setShortName(getShortName());
		fs.setIndex(getOrder());

        double less,greater,peak;

        if(p.getStart().getY()>p.getEnd().getY()){
            less=p.getEnd().getY();
            greater=p.getStart().getY();
        }
        else{
            less=p.getStart().getY();
            greater=p.getEnd().getY();
        }
        peak=p.getPeak().getY();

        double r,a;
        a=greater-less;
        r=a/(peak-less);
        fs.getValues().add(a);
        fs.getValues().add(r);
        return fs;
    }
    
}
