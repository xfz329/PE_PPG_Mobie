package com.edu.zju.cbeis.bme207.signal.ppg.algorithm.voter;

public class BasicVoter extends Voter{
	
	public BasicVoter() {
		
	}

	@Override
	public void vote() {
		discard = original[0];
	}


}
