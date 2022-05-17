package com.edu.zju.cbeis.bme207.resource;

import java.util.ListResourceBundle;

public class Feature_zh_CN extends ListResourceBundle {

	public Feature_zh_CN() {

	}

	@Override
	protected Object[][] getContents() {
		return data;
	}
	
	private final Object data[][] = {

			// format :
			// Name : Short | Description |  Return Value Description | Figure Path.
			
			{"PGFO_Standardization",
			"STDZ"+"|"
			+"将原始脉搏波波形按上升支下降支分别标准化，其中，峰值标准化至1000，始末值标准化至0。返回标准化过程中所使用的系数（上升支与下降支线性变化的k与b数值）。\n"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_LeftViewAngel",
			"LVA"+"|"
			+"连接脉搏波的起始点与波峰，将其与基线所构成的夹角作为基准。做该夹角的N等分线，记录每条等分线的角度（弧度）。\n"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_LeftViewSlope",
			"LVS"+"|"
			+"连接脉搏波的起始点与波峰，将其与基线所构成的夹角作为基准。做该夹角的N等分线，记录每条等分线的斜率（正切值）。\n"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PPGO_LeftViewLabelPeak",
			"LVLP"+"|"
			+"连接脉搏波的起始点与波峰，将其与基线所构成的夹角作为基准。做该夹角的N等分线，记录每条等分线与脉搏波波峰所在垂线的交点位置。\n"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},
			
			{"PGFO_LeftViewLabelRise",
			"LVLR"+"|"
			+"连接脉搏波的起始点与波峰，将其与基线所构成的夹角作为基准。做该夹角的N等分线，记录每条等分线与脉搏波上升支的交点位置。\n"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_LeftViewLabelFall",
			"LVLF"+"|"
			+"连接脉搏波的起始点与波峰，将其与基线所构成的夹角作为基准。做该夹角的N等分线，记录每条等分线与脉搏波下降支的交点位置。\n"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_LeftViewRadiuRise",
			"LVRR"+"|"
			+"Description"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_LeftViewRadiuFall",
			"LVRF"+"|"
			+"Description"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_LeftViewDuration",
			"LVD"+"|"
			+"Description"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_LeftViewArcLengthRise",
			"LVALR"+"|"
			+"Description"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_LeftViewArcLengthFall",
			"LVALF"+"|"
			+"Description"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},
			



			{"PGFO_ScaledViewLabelRise",
			"SVLR"+"|"
			+"将脉搏波沿波峰所在铅垂线均分成N分，过其中每一等分点做水平垂线，记录各水平垂线与脉搏波上升支交点位置。\n"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_ScaledViewLabelFall",
			"SVLF"+"|"
			+"将脉搏波沿波峰所在铅垂线均分成N分，过其中每一等分点做水平垂线，记录各水平垂线与脉搏波下降支交点位置。\n"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_ScaledViewEdgeRise",
			"SVER"+"|"
			+"将脉搏波沿波峰所在铅垂线均分成N分，过其中每一等分点做水平垂线，记录各水平垂线与脉搏波上升支交点位置后，记录每条水平线与脉搏波相交点最左的位置。\n"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_ScaledViewEdgeFall",
			"SVEF"+"|"
			+"将脉搏波沿波峰所在铅垂线均分成N分，过其中每一等分点做水平垂线，记录各水平垂线与脉搏波上升支交点位置后，记录每条水平线与脉搏波相交点最右的位置。\n"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_ScaledViewAreaRise",
			"SVAR"+"|"
			+"根据PPGFeatureOfScaledViewLabelRise特征值，计算被等分线切割的脉搏波上升支各部分面积值。\n"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_ScaledViewAreaFall",
			"SVAF"+"|"
			+"Description"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_ScaledViewAreaTotal",
			"SVAT"+"|"
			+"Description"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_ScaledViewRadiuRise",
			"SVRR"+"|"
			+"Description"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_ScaledViewRadiuFall",
			"SVRF"+"|"
			+"Description"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_ScaledViewDuration",
			"SVD"+"|"
			+"Description"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_ScaledViewArcLengthRise",
			"SVALR"+"|"
			+"Description"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_ScaledViewArcLengthFall",
			"SVALF"+"|"
			+"Description"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_ScaledViewSlopeRise",
			"SVSR"+"|"
			+"Description"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_ScaledViewSlopeFall",
			"SVSF"+"|"
			+"Description"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_ScaledViewArcAreaRise",
			"SVAAR"+"|"
			+"Description"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_ScaledViewArcAreaFall",
			"SVAAF"+"|"
			+"Description"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},





			{"PGFO_CenterViewLabelRise",
			"CVLR"+"|"
			+"将脉搏波从波峰在水平线上投射点为原点，做脉搏波的角度等分线，记录各等分线与脉搏波上升支的交点坐标。\n"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_CenterViewLabelFall",
			"CVLF"+"|"
			+"将脉搏波从波峰在水平线上投射点为原点，做脉搏波的角度等分线，记录各等分线与脉搏波下降支的交点坐标。\n"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_CenterViewRadiuRise",
			"CVRR"+"|"
			+"Description"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_CenterViewRadiuFall",
			"CVRF"+"|"
			+"Description"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_CenterViewDuration",
			"CVD"+"|"
			+"Description"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_CenterViewArcLengthRise",
			"CVALR"+"|"
			+"Description"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_CenterViewArcLengthFall",
			"CVALF"+"|"
			+"Description"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_CenterViewArcAreaRise",
			"CVAAR"+"|"
			+"Description"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},

			{"PGFO_CenterViewArcAreaFall",
			"CVAAF"+"|"
			+"Description"+"|"
			+"Return Value Description"+"|"
			+"Figure Path"},
			
	};

}
