package com.edu.zju.cbeis.bme207.mobie.transformer;

import com.edu.zju.cbeis.bme207.math.Point;
import com.edu.zju.cbeis.bme207.math.Points;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

public class Points2Entry {
    public Points2Entry(){

    }

    public List<Entry> convert(Points pts){
        List<Entry> ans = new ArrayList<>();
        for(int i=0;i<pts.getLen();i++){
            ans.add(convert(pts.getNthPoint(i)));
        }
        return ans;
    }

    public Entry convert(Point p){
        float x = (float) p.getX();
        float y = (float) p.getY();
        return new Entry(x,y);
    }
}
