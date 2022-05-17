package com.edu.zju.cbeis.bme207.record;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;
import com.edu.zju.cbeis.bme207.resource.help.Updatelog;
import com.edu.zju.cbeis.bme207.signal.PPG;


public class PERecord {
	
	@JSONField(name = "Version Num",	ordinal =	1)
	private	String	version;
	
	@JSONField(name = "File Name",	ordinal =	2)
	private	String	fileName;

	@JSONField(name = "File Full Path",	ordinal =	3)
	private	String	fullPath;
	
	@JSONField(name = "Json Full Path",	ordinal =	4)
	private String  jsonFile;
	
	@JSONField(name = "Person Name",	ordinal = 5)
	private String 	personName;

	@JSONField(name = "Sample Time",	ordinal = 6)
	private String  sampleTime;
	
	@JSONField(name = "PE State",	ordinal = 7)
	private int		peState;
	
	@JSONField(name = "PPG Records",	ordinal = 8)
	private PPG		target;
	
	@JSONField(serialize = false)
//	@JSONField(name = "PPG Record O",	ordinal = 6)
	private PPG 	original;
	
	@JSONField(serialize = false)
	private int 	targetLeastSampleRate;
	
	@JSONField(serialize = false)
	private int     coefficient;
	
	@JSONField(serialize = false)
	private boolean loaded;
	
	@JSONField(serialize = false)
	private boolean revised;
	
	@JSONField(serialize = false)
	private boolean analyzed;

	@JSONField(serialize = false)
	private boolean exported;
	
	public PERecord() {
		original = new PPG();
		target = new PPG();
		targetLeastSampleRate = 2000;
		setVersion();
		setLoaded(false);
		setRevised(false);
		setAnalyzed(false);
		setExported(false);
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}
	
	public void setVersion() {
		String version = Updatelog.getCurrentVersion();
		this.version = version;
	}

	public int getCoefficient() {
		return coefficient;
	}
	
	public boolean isLoaded() {
		return loaded;
	}


	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}


	public boolean isRevised() {
		return revised;
	}


	public void setRevised(boolean revised) {
		this.revised = revised;
	}


	public boolean isAnalyzed() {
		return analyzed;
	}


	public void setAnalyzed(boolean analyzed) {
		this.analyzed = analyzed;
	}


	public String getPersonName() {
		return personName;
	}


	public void setPersonName(String personName) {
		this.personName = personName;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		setFullPath(fileName);
		if(fileName.contains("\\")) {
			this.fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
		}
		else
			this.fileName = fileName;
	}
	
	public int getPeState() {
		return peState;
	}


	public void setPeState(int peState) {
		this.peState = peState;
	}
	
	public String getSampleTime() {
		return sampleTime;
	}


	public void setSampleTime(String sampleTime) {
		this.sampleTime = sampleTime;
	}
	

	public void setSampleRate(int sampleRate) {
		original.setSampleRate(sampleRate);
		coefficient = targetLeastSampleRate/sampleRate;
		if( coefficient*sampleRate <targetLeastSampleRate) {
			coefficient++;
		}
		target.setSampleRate(coefficient * sampleRate);
	}
	
	public PPG getOriginal() {
		return original;
	}
	
	public PPG getTarget() {
		return target;
	}

	public void setOriginal(int[] values) {
		original.setOriginal(values);
		original.locate();
		original.preparePulse();
		setLoaded(true);
//		setTarget();
	}

	public void setTarget() {
		original.revisePulse();
		target.setOriginal(original.getArray_smooth(), coefficient);
		target.locate(original.getPulse(), coefficient);
		target.preparePulse();
		setRevised(true);
	}
	
	public void extractFeatures() {
		target.clearFeatures();
		target.extractFeatures();
		setAnalyzed(true);
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public String getJsonFile() {
		return jsonFile;
	}

	public void setJsonFile(String jsonFile) {
		this.jsonFile = jsonFile;
	}

	public boolean isExported() {
		return exported;
	}

	public void setExported(boolean exported) {
		this.exported = exported;
	}

	public Map<String,String> convert2Map(){
		Map<String,String> map = new HashMap<String,String>();
        map.put("source_file", getFullPath());
		map.put("source", getFileName());
		map.put("json_file",getJsonFile());
		map.put("project", "PE");
		map.put("version", getVersion());
		map.put("state", String.valueOf(getPeState()));
		map.put("person", getPersonName());
		map.put("sample_time", getSampleTime());
		return map;
	}


}
