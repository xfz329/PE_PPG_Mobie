package com.edu.zju.cbeis.bme207.signal.ppg.features;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class PPGFeaturesFactory {
	
	
	public static final int  Num_Sum_K_0							=				10		;
	public static final int  Num_Sum_K_1							=				16		;
	public static final int  Num_Sum_K_2							=				9		;
	
	public static final int  Num_Start_K_0							=				1		;
	public static final int  Num_Start_K_1							= Num_Start_K_0	+Num_Sum_K_0;
	public static final int  Num_Start_K_2							= Num_Start_K_1	+Num_Sum_K_1;
	
	public static final int  PGFO_None								=				-1		;	
	
	public static final int  PGFO_Standardization					= 				0		;
	
	public static final int  PGFO_LeftViewAngel						= Num_Start_K_0 +	0	;
	public static final int	 PGFO_LeftViewSlope						= Num_Start_K_0 +	1	;
	public static final int	 PPGO_LeftViewLabelPeak 				= Num_Start_K_0 +	2	;
	public static final int  PGFO_LeftViewLabelRise					= Num_Start_K_0 + 	3	;
	public static final int  PGFO_LeftViewLabelFall					= Num_Start_K_0 + 	4	;
	public static final int  PGFO_LeftViewRadiuRise   				= Num_Start_K_0 +	5	;
	public static final int  PGFO_LeftViewRadiuFall   				= Num_Start_K_0 +	6	;
	public static final int  PGFO_LeftViewDuration   				= Num_Start_K_0 + 	7	;
	public static final int  PGFO_LeftViewArcLengthRise   			= Num_Start_K_0 + 	8	;
	public static final int  PGFO_LeftViewArcLengthFall   			= Num_Start_K_0 + 	9	;
	
	public static final int  PGFO_ScaledViewLabelRise				= Num_Start_K_1	+	0	;
	public static final int  PGFO_ScaledViewLabelFall				= Num_Start_K_1	+	1	;
	public static final int  PGFO_ScaledViewEdgeRise				= Num_Start_K_1	+	2	;
	public static final int  PGFO_ScaledViewEdgeFall				= Num_Start_K_1	+	3	;
	public static final int  PGFO_ScaledViewAreaRise   				= Num_Start_K_1	+	4	;
	public static final int  PGFO_ScaledViewAreaFall   				= Num_Start_K_1	+	5	;
	public static final int  PGFO_ScaledViewAreaTotal   			= Num_Start_K_1	+	6	;
	public static final int  PGFO_ScaledViewRadiuRise  				= Num_Start_K_1	+	7	;
	public static final int  PGFO_ScaledViewRadiuFall   			= Num_Start_K_1	+	8	;
	public static final int  PGFO_ScaledViewDuration   				= Num_Start_K_1	+	9	;
	public static final int  PGFO_ScaledViewArcLengthRise   		= Num_Start_K_1	+	10	;
	public static final int  PGFO_ScaledViewArcLengthFall   		= Num_Start_K_1	+	11	;
	public static final int  PGFO_ScaledViewSlopeRise  				= Num_Start_K_1	+	12	;
	public static final int  PGFO_ScaledViewSlopeFall   			= Num_Start_K_1	+	13	;
	public static final int  PGFO_ScaledViewArcAreaRise  			= Num_Start_K_1	+	14	;
	public static final int  PGFO_ScaledViewArcAreaFall   			= Num_Start_K_1	+	15	;

	
	public static final int  PGFO_CenterViewLabelRise   			= Num_Start_K_2	+ 	0	;
	public static final int  PGFO_CenterViewLabelFall   			= Num_Start_K_2	+ 	1	;
	public static final int  PGFO_CenterViewRadiuRise   			= Num_Start_K_2	+ 	2	;
	public static final int  PGFO_CenterViewRadiuFall   			= Num_Start_K_2	+ 	3	;
	public static final int  PGFO_CenterViewDuration   				= Num_Start_K_2	+ 	4	;
	public static final int  PGFO_CenterViewArcLengthRise   		= Num_Start_K_2	+ 	5	;
	public static final int  PGFO_CenterViewArcLengthFall   		= Num_Start_K_2	+ 	6	;
	public static final int  PGFO_CenterViewArcAreaRise  			= Num_Start_K_2	+	7	;
	public static final int  PGFO_CenterViewArcAreaFall   			= Num_Start_K_2	+	8	;


	
	private List<AbstractPPGFeature> features;
	private ResourceBundle res;
	private static PPGFeaturesFactory ppgf;
	
	private PPGFeaturesFactory() {
		features = new ArrayList<>();
		Locale currentLocale = new Locale("zh","CN");
		res = ResourceBundle.getBundle("com.edu.zju.cbeis.bme207.resource.Feature", currentLocale);
	}
	public static PPGFeaturesFactory getPPGFeaturesFactory() {
		if (ppgf == null){
			ppgf = new PPGFeaturesFactory();
			ppgf.initFeatures();
		}
		return ppgf;
	}
	
	public List<AbstractPPGFeature> getFeatures() {
		return features;
	}

	private void initFeatures(){
		PPGFeatureOfStandardization f00 = new PPGFeatureOfStandardization();
		f00.setParas(PGFO_Standardization, PGFO_None, PGFO_None);
		f00.setAllDescripition(res.getString("PGFO_Standardization"));
		features.add(f00);
		
		addFeatures_0();
		addFeatures_1();
		addFeatures_2();
	}
	private void addFeatures_0() {
		PPGFeatureOfLeftViewAngel	f00 = new PPGFeatureOfLeftViewAngel();
		f00.setParas(PGFO_LeftViewAngel, PGFO_None, PGFO_None);
		f00.setAllDescripition(res.getString("PGFO_LeftViewAngel"));
		features.add(f00);
		
		PPGFeatureOfLeftViewSlope f01 = new PPGFeatureOfLeftViewSlope();
		f01.setParas(PGFO_LeftViewSlope, PGFO_LeftViewAngel, PGFO_None);
		f01.setAllDescripition(res.getString("PGFO_LeftViewSlope"));
		features.add(f01);
		
		PPGFeatureOfLeftViewLabelPeak f02 =new PPGFeatureOfLeftViewLabelPeak();
		f02.setParas(PPGO_LeftViewLabelPeak, PGFO_LeftViewSlope, PGFO_None);
		f02.setAllDescripition(res.getString("PPGO_LeftViewLabelPeak"));
		features.add(f02);
		
		PPGFeatureOfLeftViewLabelRise f03 = new PPGFeatureOfLeftViewLabelRise();
		f03.setParas(PGFO_LeftViewLabelRise, PGFO_LeftViewSlope, PGFO_None);
		f03.setAllDescripition(res.getString("PGFO_LeftViewLabelRise"));
		features.add(f03);
		
		PPGFeatureOfLeftViewLabelFall f04 = new PPGFeatureOfLeftViewLabelFall();
		f04.setParas(PGFO_LeftViewLabelFall, PGFO_LeftViewSlope, PGFO_None);
		f04.setAllDescripition(res.getString("PGFO_LeftViewLabelFall"));
		features.add(f04);
		
		PPGFeatureOfLabelToRadius f05 = new PPGFeatureOfLabelToRadius();
		f05.setParas(PGFO_LeftViewRadiuRise, PGFO_LeftViewLabelRise, PGFO_None);
		f05.setAllDescripition(res.getString("PGFO_LeftViewRadiuRise"));
		features.add(f05);
		
		PPGFeatureOfLabelToRadius f06 = new PPGFeatureOfLabelToRadius();
		f06.setParas(PGFO_LeftViewRadiuFall, PGFO_LeftViewLabelFall, PGFO_None);
		f06.setAllDescripition(res.getString("PGFO_LeftViewRadiuFall"));
		features.add(f06);
		
		PPGFeatureOfLabelToDuration f07 =  new PPGFeatureOfLabelToDuration();
		f07.setParas(PGFO_LeftViewDuration, PGFO_LeftViewLabelRise, PGFO_LeftViewLabelFall);
		f07.setAllDescripition(res.getString("PGFO_LeftViewDuration"));
		features.add(f07);
		
		PPGFeatureOfLabelToArcLength f08 = new PPGFeatureOfLabelToArcLength();
		f08.setParas(PGFO_LeftViewArcLengthRise, PGFO_LeftViewLabelRise, PGFO_None);
		f08.setAllDescripition(res.getString("PGFO_LeftViewArcLengthRise"));
		features.add(f08);
		
		PPGFeatureOfLabelToArcLength f09 =  new PPGFeatureOfLabelToArcLength();
		f09.setParas(PGFO_LeftViewArcLengthFall, PGFO_LeftViewLabelFall, PGFO_None);
		f09.setAllDescripition(res.getString("PGFO_LeftViewArcLengthFall"));
		features.add(f09);
	}
	
	private void addFeatures_1() {
		PPGFeatureOfScaledViewLabelRise f00 = new PPGFeatureOfScaledViewLabelRise();
		f00.setParas(PGFO_ScaledViewLabelRise, PGFO_None, PGFO_None);
		f00.setAllDescripition(res.getString("PGFO_ScaledViewLabelRise"));
		features.add(f00);
		
		PPGFeatureOfScaledViewLabelFall f01 = new PPGFeatureOfScaledViewLabelFall();
		f01.setParas(PGFO_ScaledViewLabelFall, PGFO_None, PGFO_None);
		f01.setAllDescripition(res.getString("PGFO_ScaledViewLabelFall"));
		features.add(f01);
		
		PPGFeatureOfScaledViewEdgeRise f02 = new PPGFeatureOfScaledViewEdgeRise();
		f02.setParas(PGFO_ScaledViewEdgeRise, PGFO_ScaledViewLabelRise, PGFO_None);
		f02.setAllDescripition(res.getString("PGFO_ScaledViewEdgeRise"));
		features.add(f02);
		
		PPGFeatureOfScaledViewEdgeFall f03 = new PPGFeatureOfScaledViewEdgeFall();
		f03.setParas(PGFO_ScaledViewEdgeFall, PGFO_ScaledViewLabelFall, PGFO_None);
		f03.setAllDescripition(res.getString("PGFO_ScaledViewEdgeFall"));
		features.add(f03);
		
		PPGFeatureOfScaledViewAreaRise f04 = new PPGFeatureOfScaledViewAreaRise();
		f04.setParas(PGFO_ScaledViewAreaRise, PGFO_ScaledViewLabelRise, PGFO_None);
		f04.setAllDescripition(res.getString("PGFO_ScaledViewAreaRise"));
		features.add(f04);
		
		PPGFeatureOfScaledViewAreaFall f05 = new PPGFeatureOfScaledViewAreaFall();
		f05.setParas(PGFO_ScaledViewAreaFall, PGFO_ScaledViewLabelFall, PGFO_None);
		f05.setAllDescripition(res.getString("PGFO_ScaledViewAreaFall"));
		features.add(f05);
		
		PPGFeatureOfScaledViewAreaTotal f06 = new PPGFeatureOfScaledViewAreaTotal();
		f06.setParas(PGFO_ScaledViewAreaTotal, PGFO_ScaledViewAreaRise, PGFO_ScaledViewAreaFall);
		f06.setAllDescripition(res.getString("PGFO_ScaledViewAreaTotal"));
		features.add(f06);
		
		PPGFeatureOfLabelToRadius f07 = new PPGFeatureOfLabelToRadius();
		f07.setParas(PGFO_ScaledViewRadiuRise, PGFO_ScaledViewEdgeRise, PGFO_None);
		f07.setAllDescripition(res.getString("PGFO_ScaledViewRadiuRise"));
		features.add(f07);
		
		PPGFeatureOfLabelToRadius f08 = new PPGFeatureOfLabelToRadius();
		f08.setParas(PGFO_ScaledViewRadiuFall, PGFO_ScaledViewEdgeFall, PGFO_None);
		f08.setAllDescripition(res.getString("PGFO_ScaledViewRadiuFall"));
		features.add(f08);
		
		PPGFeatureOfLabelToDuration f09 =  new PPGFeatureOfLabelToDuration();
		f09.setParas(PGFO_ScaledViewDuration, PGFO_ScaledViewEdgeRise, PGFO_ScaledViewEdgeFall);
		f09.setAllDescripition(res.getString("PGFO_ScaledViewDuration"));
		features.add(f09);
		
		PPGFeatureOfLabelToArcLength f10 = new PPGFeatureOfLabelToArcLength();
		f10.setParas(PGFO_ScaledViewArcLengthRise, PGFO_ScaledViewEdgeRise, PGFO_None);
		f10.setAllDescripition(res.getString("PGFO_ScaledViewArcLengthRise"));
		features.add(f10);
		
		PPGFeatureOfLabelToArcLength f11 =  new PPGFeatureOfLabelToArcLength();
		f11.setParas(PGFO_ScaledViewArcLengthFall, PGFO_ScaledViewEdgeFall, PGFO_None);
		f11.setAllDescripition(res.getString("PGFO_ScaledViewArcLengthFall"));
		features.add(f11);
		
		PPGFeatureOfScaledViewSlope f12 = new PPGFeatureOfScaledViewSlope();
		f12.setParas(PGFO_ScaledViewSlopeRise, PGFO_ScaledViewEdgeRise, PGFO_None);
		f12.setAllDescripition(res.getString("PGFO_ScaledViewSlopeRise"));
		features.add(f12);
		
		PPGFeatureOfScaledViewSlope f13 = new PPGFeatureOfScaledViewSlope();
		f13.setParas(PGFO_ScaledViewSlopeFall, PGFO_ScaledViewEdgeFall, PGFO_None);
		f13.setAllDescripition(res.getString("PGFO_ScaledViewSlopeFall"));
		features.add(f13);
		
		PPGFeatureOfLabelToArcArea f14 = new PPGFeatureOfLabelToArcArea();
		f14.setParas(PGFO_ScaledViewArcAreaRise, PGFO_ScaledViewEdgeRise, PGFO_None);
		f14.setAllDescripition(res.getString("PGFO_ScaledViewArcAreaRise"));
		features.add(f14);
		
		PPGFeatureOfLabelToArcArea f15 = new PPGFeatureOfLabelToArcArea();
		f15.setParas(PGFO_ScaledViewArcAreaFall, PGFO_ScaledViewEdgeFall, PGFO_None);
		f15.setAllDescripition(res.getString("PGFO_ScaledViewArcAreaFall"));
		features.add(f15);
		
	}
	
	private void addFeatures_2() {
		PPGFeatureOfCenterViewLabelRise f00 = new PPGFeatureOfCenterViewLabelRise();
		f00.setParas(PGFO_CenterViewLabelRise, PGFO_None, PGFO_None);
		f00.setAllDescripition(res.getString("PGFO_CenterViewLabelRise"));
		features.add(f00);
		
		PPGFeatureOfCenterViewLabelFall f01 = new PPGFeatureOfCenterViewLabelFall();
		f01.setParas(PGFO_CenterViewLabelFall, PGFO_None, PGFO_None);
		f01.setAllDescripition(res.getString("PGFO_CenterViewLabelFall"));
		features.add(f01);
		
		PPGFeatureOfLabelToRadius f02 = new PPGFeatureOfLabelToRadius();
		f02.setParas(PGFO_CenterViewRadiuRise, PGFO_CenterViewLabelRise, PGFO_None);
		f02.setAllDescripition(res.getString("PGFO_CenterViewRadiuRise"));
		features.add(f02);
		
		PPGFeatureOfLabelToRadius f03 = new PPGFeatureOfLabelToRadius();
		f03.setParas(PGFO_CenterViewRadiuFall, PGFO_CenterViewLabelFall, PGFO_None);
		f03.setAllDescripition(res.getString("PGFO_CenterViewRadiuFall"));
		features.add(f03);
		
		PPGFeatureOfLabelToDuration f04 =  new PPGFeatureOfLabelToDuration();
		f04.setParas(PGFO_CenterViewDuration, PGFO_CenterViewLabelRise, PGFO_CenterViewLabelFall);
		f04.setAllDescripition(res.getString("PGFO_CenterViewDuration"));
		features.add(f04);
		
		PPGFeatureOfLabelToArcLength f05 = new PPGFeatureOfLabelToArcLength();
		f05.setParas(PGFO_CenterViewArcLengthRise, PGFO_CenterViewLabelRise, PGFO_None);
		f05.setAllDescripition(res.getString("PGFO_CenterViewArcLengthRise"));
		features.add(f05);
		
		PPGFeatureOfLabelToArcLength f06 =  new PPGFeatureOfLabelToArcLength();
		f06.setParas(PGFO_CenterViewArcLengthFall, PGFO_CenterViewLabelFall, PGFO_None);
		f06.setAllDescripition(res.getString("PGFO_CenterViewArcLengthFall"));
		features.add(f06);
		
		PPGFeatureOfLabelToArcArea f07 = new PPGFeatureOfLabelToArcArea();
		f07.setParas(PGFO_CenterViewArcAreaRise, PGFO_CenterViewLabelRise, PGFO_None);
		f07.setAllDescripition(res.getString("PGFO_CenterViewArcAreaRise"));
		features.add(f07);
		
		PPGFeatureOfLabelToArcArea f08 = new PPGFeatureOfLabelToArcArea();
		f08.setParas(PGFO_CenterViewArcAreaFall, PGFO_CenterViewLabelFall, PGFO_None);
		f08.setAllDescripition(res.getString("PGFO_CenterViewArcAreaFall"));
		features.add(f08);
		
	}
	

}
