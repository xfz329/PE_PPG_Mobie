package com.edu.zju.cbeis.bme207.math;

import com.alibaba.fastjson.annotation.JSONField;

public class Points {
	public static final int SET_X_ONLY 		= 0;
	public static final int SET_Y_ONLY 		= 1;
	public static final int SET_BOTH_XY 	= 2;
	
	@JSONField(name = "Sample Values",	ordinal = 1)
	private Point[] p;
	
	@JSONField(serialize = false)
	private int len;
	
	public Points() {
		
	}
	
	public Points(int n) {
		len = n;
		p 	= new Point[len];
		for(int i= 0;i<len;i++) {
			p[i]= new Point(i);
		}
	}

	public double[] toArray(){
		double[] array = new double[len];
		for(int i =0;i<len;i++){
			array[i] = p[i].getY();
		}
		return array;
	}
	
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	
	public Point[] getPoint() {
		return p;
	}

	public void setPoint(Point[] p) {
		this.p = p;
	}

	public Points copy(Points p) {
		Points n 	= new Points(p.getLen());
		for(int i= 0;i<len;i++) {
			n.setNthPoint(i,p.getNthPoint(i),SET_BOTH_XY);
		}
		return n;
	}
	public Point getNthPoint(int n) {
		return p[n];
	}
	
	public void setNthPoint(int n, double x, double y,int type) {
		switch(type) {
		case SET_X_ONLY:
			p[n].setX(x);
			break;
		case SET_Y_ONLY:
			p[n].setY(y);
			break;
		case SET_BOTH_XY:
			p[n].setCord(x, y);
			default:
		}
	}
	
	public void setNthPoint(int n,Point pt,int type) {
		switch(type) {
		case SET_X_ONLY:
			p[n].setX(pt.getX());
			break;
		case SET_Y_ONLY:
			p[n].setY(pt.getY());
			break;
		case SET_BOTH_XY:
			p[n].setCord(pt.getX(), pt.getY());
			default:
		}
	}
	
	
	public Points smooth() {
		Points out=new Points(len);
		double temp;
		out.setNthPoint(0, p[0], SET_BOTH_XY);
		temp=(p[0].getY()+p[1].getY()+p[2].getY())/3.0;
		out.setNthPoint(1, p[1].getX(), temp,SET_BOTH_XY);
		for(int i = 2; i < len-2;i++)
		{
			temp=(p[i-2].getY()+p[i-1].getY()+p[i].getY()+
					p[i+1].getY()+p[i+2].getY())/5.0;
			out.setNthPoint(i, p[i].getX(), temp,SET_BOTH_XY);
		}
		temp=(p[len-3].getY()+p[len-2].getY()+p[len-1].getY())/3.0;
		out.setNthPoint(len-2, p[len-2].getX(), temp,Points.SET_BOTH_XY);
		out.setNthPoint(len-1, p[len-1], Points.SET_BOTH_XY);
		return out;
	}
	
	
	public Points differ() {
		Points out = new Points(len-1);
		for(int i=0;i<len-1;i++) {
			out.setNthPoint(i, p[i].getX(), p[i+1].getY()-p[i].getY(), SET_BOTH_XY);
		}
		return out;
//		double temp;
//		out.setNthPoint(0, p[0].getX(), p[1].getY()-p[0].getY(),SET_BOTH_XY);
//		out.setNthPoint(1, p[1].getX(), p[2].getY()-p[1].getY(),SET_BOTH_XY);
//		out.setNthPoint(2, p[2].getX(), p[3].getY()-p[2].getY(),SET_BOTH_XY);
//		out.setNthPoint(3, p[3].getX(), p[4].getY()-p[3].getY(),SET_BOTH_XY);
//		for(int i=4;i<len-1;i++) {
//			temp = (2*p[i].getY()+p[i-1].getY()-p[i-3].getY()-2*p[i-4].getY())/8.0;
//			out.setNthPoint(i, p[i].getX(), temp, SET_BOTH_XY);
//		}
//		return out;
	}
	public Point max(int start,int end) {
		Point pt = new Point(0,-100000000,0);
		for(int i=start;i<end;i++) {
			if(p[i].getY()>pt.getY()) {
				pt.setCord(p[i]);
			}
		}
		return pt;
	}
	
	public Point max(int center,int scale, int len) {
		Point pt 	= new Point(0,-100000000,0);
		int start 	= (center*scale-len)<0?0:(center*scale-len);
		int end		= (center*scale+len)>getLen()?getLen():(center*scale+len);
		for(int i=start;i<end;i++) {
			if(p[i].getY()>pt.getY()) {
				pt.setCord(p[i]);
			}
		}
		return pt;
	}
	
	public Point min(int start,int end) {
		Point pt = new Point(0,10000000,0);
		for(int i=start;i<=end;i++) {
			if(p[i].getY()<pt.getY()) {
				pt.setCord(p[i]);
			}
		}
		return pt;
	}
	
	public Point min(int center,int scale, int len) {
		Point pt 	= new Point(0,10000000,0);
		int start 	= (center*scale-len)<0?0:(center*scale-len);
		int end		= (center*scale+len)>getLen()?getLen():(center*scale+len);
		for(int i=start;i<end;i++) {
			if(p[i].getY()<pt.getY()) {
				pt.setCord(p[i]);
			}
		}
		return pt;
	}
	
	public double calculateSectionArea(int start, int end) {
		double res = 0.0 ;
		double temp;
		for(int i =start; i< end-1;i++) {
			temp = (p[i].getY() + p[i+1].getY()) *(p[i+1].getX() - p[i].getX())/2;
			res += temp;
		}
		return res;
	}
	
	public double calculateHorizontalSectionArea(int start, int end) {
		double square = 0.0 ;
		double total = calculateSectionArea(start,end);
		double height = p[start].getY()>p[end].getY()?p[end].getY():p[start].getY();
		square = height*(p[end].getX()-p[start].getX());
		return  total-square;
	}

	public double calculateHorizontalSquareSectionArea(int base,int start, int end) {
		double a = p[start].getX()-p[base].getX();
		double s = p[start].getY()>p[base].getY()?p[base].getY(): p[start].getY();
		double b = s-p[end].getY();
		return  a*b;
	}

	public double calculateHorizontalSquareSectionArea(int xs,int xe, int ys,int ye) {
		double a = p[xe].getX()-p[xs].getX();
		double b = p[ys].getY()-p[ye].getY();
		return  a*b;
	}
	
	public double calculateArcLength(int start, int end) {
		int temp;
		if(end <start) {
			temp = end;
			end = start;
			start =temp;
		}
		double res = 0.0;
		for(int i= start;i<end-1;i++) {
			res+=Point.getDistance(p[i], p[i+1]);
		}
		return res;
	}
	
	public double calculateArcArea(int start, int end, int center) {
		double res = 0.0;
		int temp;
		if(end <start) {
			temp = end;
			end = start;
			start =temp;
		}
		res += calculateSectionArea(start , end);
		
		double height,length;
		if(start<center) {
			height = p[end].getY();
			length = p[center].getX() - p[end].getX();
		}
		else {
			height = p[start].getY();
			length = p[start].getX() - p[center].getX();
		}
		res += 0.5*height*length;
		return res;
	}

}
