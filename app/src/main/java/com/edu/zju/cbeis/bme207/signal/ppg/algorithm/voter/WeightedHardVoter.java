package com.edu.zju.cbeis.bme207.signal.ppg.algorithm.voter;

public class WeightedHardVoter extends Voter {

    @Override
    public void vote() {
        double probability[] ={0.4,0.4,0.2};
        double temp[] = new double[n];
        
        for(int i=0;i<n;i++){
            discard[i]=0;
            temp[i]=0.0;
            for(int j=0;j<m;j++){
                temp[i]+=original[j][i]*probability[j];
            }
            discard[i]=temp[i]>0.5?1:0;
		}
        displayDiscard(temp);
    }
    
}
