package com.edu.zju.cbeis.bme207.record;

import com.alibaba.fastjson.annotation.JSONField;
import com.edu.zju.cbeis.bme207.signal.ppg.output.SimplePPG;

public class SimplePERecord {
    @JSONField(name = "Version Num",	ordinal =	0)
	private	String	version;

    @JSONField(name = "File Name",	ordinal =	1)
	private	String	fileName;

	@JSONField(name = "File Full Path",	ordinal =	2)
	private	String	fullPath;
	
	@JSONField(name = "Person Name",	ordinal = 4)
	private String 	personName;

	@JSONField(name = "Sample Time",	ordinal = 5)
	private String  sampleTime;
	
	@JSONField(name = "PE State",	ordinal = 6)
	private int		peState;
	
	@JSONField(name = "PPG Records original",	ordinal = 7)
	private SimplePPG 	original;
	
	@JSONField(name = "PPG Record target",	ordinal = 8)
    private SimplePPG		target;

    public SimplePERecord(PERecord record){
        version = record.getVersion();
        fileName = record.getFileName();
        fullPath = record.getFullPath();
        personName = record.getPersonName();
        sampleTime =record.getSampleTime();
        peState = record.getPeState();
        original = new SimplePPG(record.getOriginal());
        target = new SimplePPG(record.getTarget());
    }
	
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getSampleTime() {
        return sampleTime;
    }

    public void setSampleTime(String sampleTime) {
        this.sampleTime = sampleTime;
    }

    public int getPeState() {
        return peState;
    }

    public void setPeState(int peState) {
        this.peState = peState;
    }

    public SimplePPG getOriginal() {
        return original;
    }

    public void setOriginal(SimplePPG original) {
        this.original = original;
    }

    public SimplePPG getTarget() {
        return target;
    }

    public void setTarget(SimplePPG target) {
        this.target = target;
    }
}
