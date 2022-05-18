package com.edu.zju.cbeis.bme207.mobie.transformer;

import android.graphics.Color;

import com.edu.zju.cbeis.bme207.math.Point;
import com.edu.zju.cbeis.bme207.record.PERecord;
import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class Record2LineData {
    private Points2Entry p2e;
    public Record2LineData(){
        p2e = new Points2Entry();
    }
    public LineData convert(PERecord record){
        List<Entry> points = p2e.convert(record.getOriginal().getArray_smooth());

        List<Entry> peaks = new ArrayList<>();
        List<Entry> starts = new ArrayList<>();
        List<Entry> ends = new ArrayList<>();
        for(int i=0;i<record.getOriginal().getPulse().size();i++){
            Pulse pulse = record.getOriginal().getPulse().get(i);
            peaks.add(p2e.convert(pulse.getPeak()));
            starts.add(p2e.convert(pulse.getStart()));
            ends.add(p2e.convert(pulse.getEnd()));
        }

        LineDataSet lineDataSet = new LineDataSet(points,"PPG");

        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setColor(Color.BLACK);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);

        LineDataSet lineDataSet_peak = new LineDataSet(peaks,"波峰");

        lineDataSet_peak.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet_peak.setColor(Color.RED);
        lineDataSet_peak.enableDashedLine(0,1,0);
        lineDataSet_peak.setDrawCircles(true);
        lineDataSet_peak.setCircleColor(Color.RED);
        lineDataSet_peak.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);

        LineDataSet lineDataSet_start = new LineDataSet(starts,"起点");

        lineDataSet_start.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet_start.setColor(Color.BLUE);
        lineDataSet_start.enableDashedLine(0,1,0);
        lineDataSet_start.setDrawCircles(true);
        lineDataSet_start.setCircleColor(Color.BLUE);
        lineDataSet_start.setCircleRadius(4);
        lineDataSet_start.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);

        LineDataSet lineDataSet_end = new LineDataSet(ends,"终点");

        lineDataSet_end.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet_end.setColor(Color.GREEN);
        lineDataSet_end.enableDashedLine(0,1,0);
        lineDataSet_end.setDrawCircles(true);
        lineDataSet_end.setCircleColor(Color.GREEN);
        lineDataSet_end.setCircleRadius(3);
        lineDataSet_end.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);

        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        dataSets.add(lineDataSet_peak);
        dataSets.add(lineDataSet_start);
        dataSets.add(lineDataSet_end);

        LineData lineData = new LineData(dataSets);


        return  lineData;
    }
}
