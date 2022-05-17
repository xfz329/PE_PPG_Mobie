package com.edu.zju.cbeis.bme207.signal.ppg.algorithm.voter;

public class VoterFactory {
	private static Voter vf;
	private VoterFactory() {
		
	}
	
	public static Voter getPPGVoter() {
		if(vf == null){
			vf = new NewVoter();
		}
		return vf;
	}
}
