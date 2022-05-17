package com.edu.zju.cbeis.bme207.signal.ppg.output;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.edu.zju.cbeis.bme207.signal.PPG;

public class SimplePPG {
    
    @JSONField(name = "sampleRate",ordinal = 0)
    private int sampleRate;

    @JSONField(name = "Pulses")
	List<SimplePulse>			spulse;

    public SimplePPG(PPG ppg){
        sampleRate = ppg.getSampleRate();
        spulse = new ArrayList<>();
        int n = ppg.getPulse().size();
        for (int i =0;i<n;i++){
            spulse.add(new SimplePulse(ppg.getPulse().get(i)));
        }
    }

    public int getSampleRate() {
        return sampleRate;
    }

    public void setSampleRate(int sampleRate) {
        this.sampleRate = sampleRate;
    }

    public List<SimplePulse> getSpulse() {
        return spulse;
    }

    public void setSpulse(List<SimplePulse> spulse) {
        this.spulse = spulse;
    }

    
}
