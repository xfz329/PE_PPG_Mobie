package com.edu.zju.cbeis.bme207.signal.ppg.algorithm.voter;

import java.util.Arrays;
import java.util.List;

import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;
import com.edu.zju.cbeis.bme207.signal.ppg.algorithm.checker.Checker;
import com.edu.zju.cbeis.bme207.signal.ppg.algorithm.checker.CheckerFactory;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public abstract class Voter {
	
	protected List<Checker> checkers;
	protected int[][] original;
	protected int[] discard;
	protected int m,n;
//	protected Logger logger;
	
	public Voter() {
		checkers = CheckerFactory.getPPGCheckerFactory().getPPGCheckers();
		m=checkers.size();
//		logger = LogManager.getFormatterLogger(getClass().getName());
	}
	
	public List<Checker> getCheckers() {
		return checkers;
	}

	public List<Pulse> decide(List<Pulse> p){
		prepareArray(p);
		vote();
		return remove(p);
	}
	
	public void prepareArray(List<Pulse> p){
		n=p.size();
		original = new int[m][n];
		discard = new int[n];
		List<Integer> tmp;

		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++)
				original[i][j]=0;
			tmp=checkers.get(i).check(p);
			for(int j=0;j<tmp.size();j++)
				original[i][tmp.get(j)]=1;
		}
//		logger.debug("The check result of the pulses has been loaded.");
//		logger.debug("The original vote is \n"+Arrays.deepToString(original));
	}
	
	public abstract void vote();
	
	public List<Pulse> remove(List<Pulse> p) {
		int rn=0;
		for(int i= discard.length-1;i>=0;i--){
			if(discard[i]>0){
				p.remove(i);
				rn++;
			}
		}
//		logger.debug("There is %,d pulses removed, %,d left.", rn, p.size());
		return p;
	}

	public void displayDiscard(double[] temp){
//		logger.debug("The vote detail is \n"+Arrays.toString(temp));
//		logger.debug("The vote result is \n"+Arrays.toString(discard));
	}
	

}
