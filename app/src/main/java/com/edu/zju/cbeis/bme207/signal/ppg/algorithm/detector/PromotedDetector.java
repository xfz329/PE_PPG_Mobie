package com.edu.zju.cbeis.bme207.signal.ppg.algorithm.detector;

import java.util.ArrayList;
import java.util.List;

import com.edu.zju.cbeis.bme207.math.Point;
import com.edu.zju.cbeis.bme207.math.Points;
import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

public class PromotedDetector extends Detector{

    @Override
    public List<Pulse> locate(Points in, int sampleRate) {
        List<Pulse> pulse = new ArrayList<>();
		List<Point> troughs = new ArrayList<>();
		List<Point> peaks = new ArrayList<>();
		
		Points d1 =	in.differ();
		Point shortP,longP;
		Point lastP = new Point(-1,0,0);
        Point lastT = new Point(-1,0,0);
		int shortDuration = (int) (0.1 * sampleRate);
		int longDuration = (int) (0.3 * sampleRate);
		
		for(int i=1;i<d1.getLen()-100;i++) {
			if( (d1.getNthPoint(i).getY() >= 0 ) && (d1.getNthPoint(i+1).getY() <= 0) ) {
				shortP 	= in.max(i,1,shortDuration);
				longP	= in.max(i,1,longDuration);
				if (shortP.getY() != longP.getY() )
					continue;
				if ((shortP.getX() == longP.getX() ) && (shortP.getX() !=lastP.getX()) ) {
					peaks.add(shortP);
					lastP.setCord(shortP);
				}
			}
		}
		// find the least point in the later part of the pulse
        for(int i=0;i<peaks.size()-1;i++){
            int  leastX = (peaks.get(i).getIndex()+peaks.get(i+1).getIndex())/2;
			for(int j=peaks.get(i+1).getIndex()-1;j>=leastX;j--){
				if( (d1.getNthPoint(j-1).getY() < 0 ) && (d1.getNthPoint(j).getY() >= 0) ) {
					lastT=in.getNthPoint(j);
					break;
				}
			}
			troughs.add(lastT);
		}   
		
		double len;
		Point s,p,e;
		Pulse ps;
		double leastPulseDurationS2E = 0.4 * sampleRate;
		double leastPulseDurationP2E = 0.3 * sampleRate;
		
		
		for(int i = 0, j = 0 ; i <troughs.size()-1;i++) {
			s = troughs.get(i);
			e = troughs.get(i+1);
			
			while(peaks.get(j).getX()<s.getX()) 
				j++;
			p=peaks.get(j);			
			
			Point leasty=in.min(p.getIndex(),e.getIndex());
			Point most=in.max(p.getIndex(),e.getIndex());
			if(most.getY()>p.getY())
				continue;
			double delta=e.getY()-leasty.getY();
			double rate;
			boolean spad = false;
            if(delta>0){
				rate=delta/(p.getY()-e.getY());
				if(rate>0.05){
//					logger.debug("Negelected the pulse starting at x %,f ,delta is %,f ,rate is %,f.",e.getX(),delta,rate);
					continue;
				}
//				logger.debug("Located a pulse starting at x %,f ,delta is %,f ,rate is %,f.",e.getX(),delta,rate);
				spad = true;
			}
                
			if(e.getX()<p.getX()) {
				continue;
			}

			len=e.getX()-s.getX();
			if(len<leastPulseDurationS2E) {
//				logger.debug("S2E length is not enough at start %,f, end %,f , len is %,f.", s.getX(), e.getX(), len);
				j++;
				continue;
			}

			len=e.getX()-p.getX();
			if(len<leastPulseDurationP2E) {
//				logger.debug("P2E length is not enough at peak %,f, end %,f , len is %,f.", p.getX(), e.getX(), len);
				j++;
				continue;
			}

			ps = new Pulse(s,p,e);
			ps.enableSpecialAD(spad);
			pulse.add(ps);
			j++;
		}
		
		return pulse;
    }

    @Override
    public List<Pulse> locate(Points in, int sampleRate, List<Pulse> pulse, int coefficient) {
        List<Pulse> result = new ArrayList<>();
		Point s,p,e;
		Pulse ps;
		int index;
		for(int i= 0; i<pulse.size();i++) {
			s = pulse.get(i).getStart();
			p = pulse.get(i).getPeak();
			e = pulse.get(i).getEnd();
			
			index = s.getIndex() * coefficient;
			s = in.min(index, 1, coefficient*3);
			
			index = p.getIndex() * coefficient;
			p = in.max(index, 1, coefficient*3);
			
			index = e.getIndex() * coefficient;
			e = in.min(index, 1, coefficient*3);
			ps = new Pulse(s,p,e);
			ps.enableSpecialAD(pulse.get(i).isSpecialAD());
			result.add(ps);
		}
		return result;
    }
    
}
