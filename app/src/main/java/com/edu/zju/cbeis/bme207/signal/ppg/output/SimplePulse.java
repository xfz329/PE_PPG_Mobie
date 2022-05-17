package com.edu.zju.cbeis.bme207.signal.ppg.output;

import com.alibaba.fastjson.annotation.JSONField;
import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

public class SimplePulse {

    @JSONField(name = "startIndexAbs",ordinal = 0)
    private int startIndexAbs;

    @JSONField(name = "peakIndexRel",ordinal = 1)
    private int peakIndexRel;

    @JSONField(name = "pointsBasic",ordinal = 3)
    private double[] pointsBasic;

    @JSONField(name = "pointsAdjust",ordinal = 4)
    private double[] pointsAdjust;

    public SimplePulse(Pulse pulse){
        startIndexAbs = pulse.getStart().getIndex();
        peakIndexRel = pulse.getPeakIndex();
        pointsBasic = pulse.getPointsBasic().toArray();
        if (pulse.getPointsAdjust()!= null)
            pointsAdjust = pulse.getPointsAdjust().toArray();
        else
            pointsAdjust = null;
    }

    public int getStartIndexAbs() {
        return startIndexAbs;
    }

    public void setStartIndexAbs(int startIndexAbs) {
        this.startIndexAbs = startIndexAbs;
    }

    public int getPeakIndexRel() {
        return peakIndexRel;
    }

    public void setPeakIndexRel(int peakIndexRel) {
        this.peakIndexRel = peakIndexRel;
    }

    public double[] getPointsBasic() {
        return pointsBasic;
    }

    public void setPointsBasic(double[] pointsBasic) {
        this.pointsBasic = pointsBasic;
    }

    public double[] getPointsAdjust() {
        return pointsAdjust;
    }

    public void setPointsAdjust(double[] pointsAdjust) {
        this.pointsAdjust = pointsAdjust;
    }

    

}
