package com.edu.zju.cbeis.bme207.signal.ppg.features;


import java.util.List;

import com.edu.zju.cbeis.bme207.signal.ppg.Pulse;

public class PPGFeatureOfScaledViewAreaFall extends AbstractPPGFeature{

	public PPGFeatureOfScaledViewAreaFall() {
		
	}

	@Override
	public FeatureStorage calculate(Pulse p) {
		FeatureStorage fs = new FeatureStorage();
		fs.setShortName(getShortName());
		fs.setIndex(getOrder());
//		logger.debug("this is info from feature");
		
		List<Double> l =p.getNthFeature(getPreCondition0()).getValues();
		int n = p.getSeparateNum();
		for(int i =0;i<n;i++)
			fs.getValues().add(0.0);

		
		int len = l.size()/2;
		if(len>n+1){
//			logger.debug("Index > 22 , is %,d at %,f .",l.size(),p.getPeak().getX());
		}

		int start = l.get(1).intValue(),end=l.get(len*2-1).intValue();
		double sectionA=0.0,sectionB,sectionC;
		int[] apperance= new int[n+1];
		int[] lock= new int[n+1];
		int lockendP;
		for(int i=0;i<n;i++){
			apperance[i]=0;
			lock[i]=0;
		}
		apperance[10]=1;

		int lastP,currentP;
		int lastV,currentV,tempV,mV;
		for(int i=1;i<len;i++){
			lastP = l.get(2*i-2).intValue();
			lastV = l.get(2*i-1).intValue();
			currentP = l.get(2*i).intValue();
			currentV = l.get(2*i+1).intValue();
			apperance[currentP]++;
			double t;

			if(apperance[currentP]==1) {
				// out of the lock
				sectionA = p.getPointsAdjust().calculateHorizontalSquareSectionArea(start, lastV, currentV);
				sectionB = p.getPointsAdjust().calculateSectionArea(lastV,currentV+1)-p.getPointsAdjust().calculateHorizontalSquareSectionArea(lastV,currentV,end);
				if(lock[lastP]==0){
					t=fs.getValues().get(currentP)+sectionA+sectionB;
				}
				else{
					t=fs.getValues().get(currentP)+sectionB;
					lock[lastP]=0;
				}
				fs.getValues().set(currentP,t);
				continue;
			}
			if(apperance[currentP]%2==0){
				// lock appearred, find the end of the lock
				lock[currentP]=1;
				lockendP=0;
				while(lock[lockendP]==0)
					lockendP++;
				lockendP--;

				int j=i+1;

				// the bottom of the lock
				if(currentP == lastP){
					while(l.get(j*2).intValue()!=lockendP)
						j++;
					tempV = l.get(2*j+1).intValue();
					
					sectionA = p.getPointsAdjust().calculateHorizontalSquareSectionArea(start, lastV, tempV);
					sectionB = p.getPointsAdjust().calculateSectionArea(lastV,currentV+1)-p.getPointsAdjust().calculateHorizontalSquareSectionArea(lastV,currentV,tempV,end);
					sectionC = p.getPointsAdjust().calculateHorizontalSquareSectionArea(lastV, currentV, lastV, tempV);
					// concave or convex
					if(sectionB<=sectionC){
						t=fs.getValues().get(currentP-1)+sectionA+sectionB;
						fs.getValues().set(currentP-1,t);
					}
					else{
						t=fs.getValues().get(currentP-1)+sectionA+sectionC;
						fs.getValues().set(currentP-1,t);
						t=fs.getValues().get(currentP)+sectionC-sectionB;
						fs.getValues().set(currentP,t);
					}
					continue;
				}
				// the left of the lock
				sectionB = p.getPointsAdjust().calculateSectionArea(lastV,currentV+1)-p.getPointsAdjust().calculateHorizontalSquareSectionArea(lastV,currentV,end);
				t=fs.getValues().get(currentP-1)+sectionB;
				fs.getValues().set(currentP-1,t);

				for(j=lockendP;j<lastP;j++){
					int k=i;
					while(l.get(k*2).intValue()!=j)
						k++;
					tempV = l.get(2*k+1).intValue();
					mV = l.get(2*k-1).intValue();
					mV = p.getPointsAdjust().getNthPoint(mV).getY()>p.getPointsAdjust().getNthPoint(lastV).getY()?lastV:mV;
					sectionA = p.getPointsAdjust().calculateHorizontalSquareSectionArea(lastV, currentV, mV, tempV);
					t=fs.getValues().get(j)+sectionA;
					fs.getValues().set(j,t);
				}
				continue;
			}
			if(apperance[currentP]%2==1){
				// lock appearred, find the end of the lock
				lockendP=0;
				while(lock[lockendP]==0)
					lockendP++;
				lockendP--;

				// the top of the lock
				if(currentP == lastP){
					int j=i+1;
					sectionA = p.getPointsAdjust().calculateSectionArea(lastV,currentV+1);
					sectionB = p.getPointsAdjust().calculateHorizontalSquareSectionArea(lastV, currentV, end);

					while(l.get(j*2).intValue()!=currentP-1)
						j++;
					tempV = l.get(2*j+1).intValue();

					// concave or convex
					if(sectionA > sectionB){
						//convex
						sectionC = sectionA-sectionB;
						t=fs.getValues().get(currentP)+sectionC;
						fs.getValues().set(currentP,t);
						mV = p.getPointsAdjust().getNthPoint(lastV).getY()>p.getPointsAdjust().getNthPoint(currentV).getY()?currentV:lastV;
						sectionA = p.getPointsAdjust().calculateHorizontalSquareSectionArea(lastV, currentV, mV, tempV);
						t=fs.getValues().get(currentP-1)+sectionA;
						fs.getValues().set(currentP-1,t);
					}
					else{
						sectionB = p.getPointsAdjust().calculateHorizontalSquareSectionArea(lastV, currentV,tempV,end);
						t=fs.getValues().get(currentP-1)+sectionA-sectionB;
						fs.getValues().set(currentP-1,t);
					}
					for(j=lockendP;j<currentP-1;j++){
						int k=i;
						while(l.get(k*2).intValue()!=j)
							k++;
						tempV = l.get(2*k+1).intValue();
						mV = l.get(2*k-1).intValue();
						sectionA = p.getPointsAdjust().calculateHorizontalSquareSectionArea(lastV, currentV, mV, tempV);
						t=fs.getValues().get(j)+sectionA;
						fs.getValues().set(j,t);
					}
					continue;
				}
				// the right of the lock

				sectionB = p.getPointsAdjust().calculateSectionArea(lastV,currentV+1)-p.getPointsAdjust().calculateHorizontalSquareSectionArea(lastV,currentV,end);
				t=fs.getValues().get(currentP)+sectionB;
				fs.getValues().set(currentP,t);

				for(int j=lockendP;j<currentP;j++){
					int k=i;
					while(l.get(k*2).intValue()!=j)
						k++;
					tempV = l.get(2*k+1).intValue();
					mV = l.get(2*k-1).intValue();
					sectionA = p.getPointsAdjust().calculateHorizontalSquareSectionArea(lastV, currentV, mV, tempV);
					t=fs.getValues().get(j)+sectionA;
					fs.getValues().set(j,t);
				}
				continue;
			}
		}
		return fs;
	}

}
