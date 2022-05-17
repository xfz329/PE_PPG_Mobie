package com.edu.zju.cbeis.bme207.signal.ppg.algorithm.voter;

public class NewVoter extends Voter {

    @Override
    public void vote() {
        double probability[] ={0.3,0.5,0.2};
        double factor[]={0,0,0};
        double temp[] = new double[n];
        double std,eng,re,ab,de,time;
        
        for(int i=0;i<n;i++){
            discard[i]=0;
            temp[i]=0.0;

            std=original[0][i];
            eng=original[1][i];
            re=original[2][i];
            ab=original[3][i];
            de=original[4][i];
            time=original[5][i];

            factor[0]=std+eng;
            factor[1]=re+ab+de;
            factor[2]=time;

            if( (factor[0]==2) || (factor[1]>=2)){
                temp[i]=1;
            }
            else{
                for(int j=0;j<3;j++){
                    temp[i]+=factor[j]*probability[j];
                }
            }
            discard[i]=temp[i]>=0.5?1:0;
		}
        displayDiscard(temp);
    }
    
}
