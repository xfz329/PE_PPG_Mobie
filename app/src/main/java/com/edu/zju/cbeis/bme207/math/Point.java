package com.edu.zju.cbeis.bme207.math;

import com.alibaba.fastjson.annotation.JSONField;

public class Point {
	
	@JSONField(serialize = false)
	private double x;
	
	@JSONField(name = "Values",	ordinal = 1)
	private double y;
	
	@JSONField(serialize = false)
	private int index;
	
	public Point( ) {
		setCord(0,0,0);
	}
	public Point(int i) {
		setCord(0,0,i);
	}
	public Point(double x, double y,int index) {
		setCord(x,y,index);
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public void setCord(double x, double y) {
		setX(x);
		setY(y);
	}
	public void setCord(double x, double y, int index) {
		setX(x);
		setY(y);
		setIndex(index);
	}
	public void setCord(Point p) {
		setCord(p.getX(),p.getY(),p.getIndex());
	}
	
	public static double getDistance(Point p1, Point p2) {
		double x = p1.getX()-p2.getX();
		double y = p1.getY()-p2.getY();
		return Math.sqrt(x*x + y*y);
	}

	public static boolean isSame(Point p1, Point p2){
		return (p1.x==p2.x )&&(p1.y==p2.y)&&(p1.index==p2.index);
	}

}
