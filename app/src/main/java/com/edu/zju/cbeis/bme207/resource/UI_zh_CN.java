package com.edu.zju.cbeis.bme207.resource;

import java.util.ListResourceBundle;

public class UI_zh_CN extends ListResourceBundle {

	public UI_zh_CN() {
		
	}

	@Override
	protected Object[][] getContents() {
		return data;
	}
	
	private final Object data[][] = {
			
			{"mainTitle","脉搏波特征综合分析系统"},
			{"InputFileTypes","CSV文件(.csv);JSON文件(.json)"},
			{"CSV","CSV文件(.csv)"},
			{"JSON","JSON文件(.json)"},
			
			{"chartTitle","脉搏波数据波形"},
			{"X_Label","数据采样点"},
			{"Y_Label","幅值"},
			{"font","Microsoft YaHei"},
			
			
			{"File","文件(F)"},
			{"Open","打开(O)"},
			{"UploadJson","上传单个Json(J)"},
			{"UploadJsonDir","上传目录下所有Json(D)"},
			{"Exit","退出(X)"},
			
			{"Edit","编辑(E)"},
			{"SetPEState","PE状态（P)"},
			{"Ensure","审核(E)"},
			{"EnsureDialog","带对话框审核(D)"},
			{"ExportDetection","导出检波结果(T)"},
			{"Analyze","分析(A)"},
			{"Upload","导出并上传(U)"},
			{"Copy","复制(C)"},

			{"Batprocess","批处理(B)"},
			{"Readme","操作说明(R)"},
			{"Generate","生成模板(G)"},
			{"BP","开始批处理(S)"},
			
			{"Help","帮助(H)"},
			{"ChangeLogs","更新日志(V)"},
			{"logs","更新日志"},
			{"Software","关于软件(S)"},
			{"software","关于软件"},
			
			{"warn_title","错误警告！"},
			{"warn_PERecord_empty","PERecord为空！\n请先选择需要分析的脉搏波数据再进行后续操作！"},
			{"warn_PERecord_unrevised","PERecord的波形尚未确认，请先确认波形初筛分析结果无误再进行后续操作！"},
			{"warn_PERecord_unanalyzed","PERecord的波形特征尚未提取，请先提取波形特征再进行后续操作！"},
			{"warn_PERecord_unuploaded","PERecord分析文件尚未导出，请先导出再进行后续操作！"},
			

			{"operation_succeess","操作成功"},
			{"export_detection_succeess","成功导出检波结果"},
			{"extract_succeess","特征已成功提取！"},
			{"ensure_succeess","波形已按设置进行了确认！"},
			{"export_succeess","数据已成功导出！"},
			
	};

}
