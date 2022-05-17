package com.edu.zju.cbeis.bme207.signal.ppg.algorithm.checker;

import java.util.ArrayList;
import java.util.List;

public class CheckerFactory {
	
	private List<Checker> c;
	private List<String> name;
	private static CheckerFactory ppgf;
	private CheckerFactory() {
		c = new ArrayList<>();
		name = new ArrayList<>();
		addCheckers();
	}
	
	private void addCheckers() {
		STDChecker sc = new STDChecker();
		c.add(sc);
		name.add("STD");
		EnergyChecker ec = new EnergyChecker();
		c.add(ec);
		name.add("Energy");
		AbsoluteBaselineChecker abc = new AbsoluteBaselineChecker();
		c.add(abc);
		name.add("AbsoluteBaseline");
		RelativeBaselineChecker rbc = new RelativeBaselineChecker();
		c.add(rbc);
		name.add("RelativeBaseline");
		DeltaSEChecker dc = new DeltaSEChecker();
		c.add(dc);
		name.add("DeltaSE");
		TimeChecker tc = new TimeChecker();
		c.add(tc);
		name.add("Time");
	}
	
	public static CheckerFactory getPPGCheckerFactory() {
		if (ppgf == null) {
			ppgf = new CheckerFactory();
		}
		return ppgf;
	}
	
	public List<Checker> getPPGCheckers() {
		return c;
	}

	public List<String> getPPGCheckerNames() {
		return name;
	}

}
