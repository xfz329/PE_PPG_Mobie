package com.edu.zju.cbeis.bme207.signal.ppg.algorithm.voter;

public class HardVoter extends Voter{

    @Override
    public void vote() {

        // displayOriginal();
        
        for(int i=0;i<n;i++){
            discard[i]=0;
            for(int j=0;j<m;j++){
                discard[i]+=original[j][i];
            }
            if(discard[i]<=m/2)
                discard[i]=0;
        }

        // displayDiscard();
    }
    
}
