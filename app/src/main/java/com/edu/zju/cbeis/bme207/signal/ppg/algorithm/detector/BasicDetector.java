package com.edu.zju.cbeis.bme207.signal.ppg.algorithm.detector;

import java.util.ArrayList;
import java.util.List;

import com.edu.zju.cbeis.bme207.math.Point;
import com.edu.zju.cbeis.bme207.math.Points;
import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

public class BasicDetector extends Detector {

	public BasicDetector() {
		
	}

	@Override
	public List<Pulse> locate(Points in , int sampleRate) {
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
			if( (d1.getNthPoint(i).getY() <= 0 ) && (d1.getNthPoint(i+1).getY() >= 0) ) {
				shortP 	= in.min(i,1,shortDuration);
				longP	= in.min(i,1,longDuration);
				if (shortP.getY() != longP.getY() )
					continue;
				if ((shortP.getX() == longP.getX() ) && (shortP.getX() !=lastT.getX()) ) {
					troughs.add(shortP);
					lastT.setCord(shortP);
				}
			}
			if( (d1.getNthPoint(i).getY() >= 0 ) && (d1.getNthPoint(i+1).getY() <= 0) ) {
				shortP 	= in.max(i,1,shortDuration);
				longP	= in.max(i,1,longDuration);
				if (shortP.getY() != longP.getY() )
					continue;
				if ((shortP.getX() == longP.getX() ) && (shortP.getX() !=lastP.getX()) ) {
					if(troughs.size()!=0) {
						peaks.add(shortP);
						lastP.setCord(shortP);
					}
				}
			}
			
		}
		
		double len;
		double s,p,e;
		Pulse ps;
		double leastPulseDurationS2E = 0.4 * sampleRate;
		double leastPulseDurationP2E = 0.3 * sampleRate;
		
		for(int i = 0, j = 0 ; i <troughs.size()-1;i++) {
			s = troughs.get(i).getX();
			e = troughs.get(i+1).getX();
			
			while(peaks.get(j).getX()<s) 
				j++;
			p=peaks.get(j).getX();
			
			if(e<p) {
				continue;
			}
			
			len=e-s;
			if(len<leastPulseDurationS2E) {
//				logger.debug(" S2E length is not enough at peak %,d! i is %,d , len is %,f.", p, i, len);
				j++;
				continue;
			}
			len=e-p;
			if(len<leastPulseDurationP2E) {
//				logger.debug(" P2E length is not enough at peak %,d! i is %,d , len is %,f.", p, i, len);
				j++;
				continue;
			}
			ps = new Pulse(troughs.get(i),peaks.get(j),troughs.get(i+1));
			pulse.add(ps);
			j++;
		}
		
		return pulse;
	}

	@Override
	public List<Pulse> locate(Points in , int sampleRate,List<Pulse> pulse, int coefficient) {
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
			result.add(ps);
		}
		return result;
	}
	

}
