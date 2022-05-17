package com.edu.zju.cbeis.bme207.signal;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.edu.zju.cbeis.bme207.math.Points;
import com.edu.zju.cbeis.bme207.math.Spline;
import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;
import com.edu.zju.cbeis.bme207.signal.ppg.algorithm.detector.Detector;
import com.edu.zju.cbeis.bme207.signal.ppg.algorithm.detector.DetectorFactory;
import com.edu.zju.cbeis.bme207.signal.ppg.algorithm.voter.Voter;
import com.edu.zju.cbeis.bme207.signal.ppg.algorithm.voter.VoterFactory;
import com.edu.zju.cbeis.bme207.signal.ppg.features.AbstractPPGFeature;
import com.edu.zju.cbeis.bme207.signal.ppg.features.FeatureStorage;
import com.edu.zju.cbeis.bme207.signal.ppg.features.PPGFeaturesFactory;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class PPG extends AbstractBMESignal{
	
	@JSONField(name = "Pulses")
	List<Pulse>			pulse;
	
	@JSONField(serialize = false)
	List<AbstractPPGFeature> features;
	
	private Detector detector;
	private Voter	voter;
//	private Logger logger;

	public PPG() {
		pulse = new ArrayList<>();
		detector = DetectorFactory.getPPGDetector();
		voter = VoterFactory.getPPGVoter();
//		logger = LogManager.getFormatterLogger(getClass().getName());
	}

	public void setOriginal(Points in, int coefficient) {
		Spline s= new Spline();
		s.setPoints(in, true);
		double x,y;
		int index;
		double pace = 1.0/coefficient;
		int oldLen	=	in.getLen();
		int newLen	=	oldLen*coefficient +1;
		setLength(newLen);
		original 	= new Points(newLen);
		for(int i = 0;i<oldLen;i++) {
			for(int j =0;j<coefficient;j++) {
				index = i*coefficient+j;
				x	=	in.getNthPoint(i).getX()+j*pace;
				y = s.getValueAt(x);
				original.setNthPoint(index,x, y,Points.SET_BOTH_XY);
			}
		}
		index = newLen-1;
		x	=	in.getNthPoint(oldLen-1).getX();
		y = s.getValueAt(x);
		original.setNthPoint(index,x, y,Points.SET_BOTH_XY);
	}
	
	public void setPulse(List<Pulse> pulse) {
		this.pulse = pulse;
	}
	
	public List<Pulse> getPulse() {
		return pulse;
	}
	
	public void locate() {
		array_smooth = original.smooth();
//		logger.info("Finishing smoothing the pulse");
		pulse = detector.locate(array_smooth, sampleRate);
//		logger.info("Finishing locating the original pulse");
	}
	
	public void locate(List<Pulse> pulse, int coefficient) {
		this.pulse = detector.locate(original, sampleRate, pulse, coefficient);
//		logger.info("Relocated the pulses after revising");
	}
	
	public void preparePulse() {
		for(int i = 0 ; i<pulse.size();i++) {
			pulse.get(i).setPointsBasic(original);
		}
	}
	
	public void revisePulse() {
//		logger.info("Befor auto revising, the num of pulse is "+pulse.size());
		pulse = voter.decide(pulse);
//		logger.info("After auto revising, the num of pulse is "+pulse.size());
	}
	
	public void extractFeatures() {
//		logger.info("Starting extracting the features based on the pulses detected.");
		features = PPGFeaturesFactory.getPPGFeaturesFactory().getFeatures();
//		logger.info("Starting Loading %,d kinds of features.",features.size());
//		logger.info("Loading features finished. Caculation begins");
		FeatureStorage temp = new FeatureStorage();
		for(int i = 0;i<pulse.size();i++) {
//			logger.debug("Calulating the features for pulse (%,d of %,d) peak at %f.",i+1,pulse.size(),pulse.get(i).getPeak().getX());
			for(int j = 0; j<features.size();j++) {
				temp = features.get(j).calculate(pulse.get(i));
				pulse.get(i).insertNewFeature(temp);
			}
		}
//		logger.info("Finished all the features caculation for the pulses.");
	}

	public void clearFeatures(){
//		logger.info("Clear all  features's values if any existed.");
		for(int i=0;i<pulse.size();i++){
			pulse.get(i).clearCurrentFeatures();
		}
	}

}
