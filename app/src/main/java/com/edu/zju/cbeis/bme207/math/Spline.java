package com.edu.zju.cbeis.bme207.math;

import java.util.Vector;

public class Spline {
	public static final int first_deriv		=	1;
	public static final int second_deriv	=	2;
	
	private Vector<Double> m_x,m_y;
	private Vector<Double> m_a,m_b,m_c;
	private double m_b0,m_c0;
	private int m_left,m_right;
	private double m_left_value,m_right_value;
	boolean m_force_linear_extrapolation;

	public Spline() {
		setBoundary(second_deriv,0.0,second_deriv,0.0,false);
	}
	
	public void setBoundary(int left, double left_value,int right,double right_value, boolean force_linear_extrapolation) {
		m_left = left;
		m_left_value = left_value;
		m_right = right;
		m_right_value = right_value;
		m_force_linear_extrapolation = force_linear_extrapolation;
	}
	
	
	public void setPoints(Points pt, boolean cubic_spline) {
		Vector<Double> x = new Vector<>(pt.getLen());
		Vector<Double> y = new Vector<>(pt.getLen());
		for(int i=0;i<pt.getLen();i++) {
			x.add(pt.getNthPoint(i).getX());
			y.add(pt.getNthPoint(i).getY());
		}
		setPoints(x,y,cubic_spline);
	}
	
	public void setPoints(Vector<Double> x, Vector<Double> y, boolean cubic_spline) {
		m_x = x;
		m_y = y;
		int n =x.size();
		double temp;
		
		if(cubic_spline) {
			BandMatrix A = new BandMatrix(n,1,1);
			Vector<Double> rhs = new Vector<Double>(n);
			for(int i=0;i<n;i++)
				rhs.add(i, 0.0);
			for(int i=1;i<n-1;i++) {
				A.write(i, i-1, 1.0/3.0*(x.get(i)-x.get(i-1)));
				A.write(i, i, 2.0/3.0*(x.get(i+1)-x.get(i-1)));
				A.write(i, i+1, 1.0/3.0*(x.get(i+1)-x.get(i)));
				temp= (y.get(i+1)-y.get(i))/(x.get(i+1)-x.get(i)) - (y.get(i)-y.get(i-1))/(x.get(i)-x.get(i-1));
				rhs.set(i, temp);
			}
			
			if(m_left == second_deriv) {
				A.write(0, 0, 2.0);
				A.write(0, 1, 0.0);
				rhs.set(0, m_left_value);
			}else {
				A.write(0, 0, 2.0*(x.get(1)-x.get(0)));
				A.write(0, 1, 1.0*(x.get(1)-x.get(0)));
				temp = 3.0*((y.get(1)-y.get(0))/(x.get(1)-x.get(0))-m_left_value);
				rhs.set(0, temp);
			}
			if(m_right == second_deriv) {
				A.write(n-1, n-1, 2.0);
				A.write(n-1, n-2, 0.0);
				rhs.set(n-1, m_right_value);
			}else {
				temp=x.get(n-1)-x.get(n-2);
				A.write(n-1, n-1, 2.0*temp);
				A.write(n-1, n-2, 1.0*temp);
				temp = 3.0*(m_right - (y.get(n-1)-y.get(n-2))/(x.get(n-1)-x.get(n-2)));
				rhs.set(n-1, temp);
			}
			
			m_b = A.lu_solve(rhs);
			
			m_a = new Vector<Double>();
			m_c = new Vector<Double>();

			m_a.setSize(n);
			m_c.setSize(n);
			
			for(int i=0;i<n-1;i++) {
				temp = 1.0 / 3.0 * ((m_b.get(i+1) - m_b.get(i)) / (x.get(i+1) - x.get(i)));
				m_a.set(i, temp);
				temp = (y.get(i+1) - y.get(i))/ (x.get(i+1) -x.get(i)) -1.0/3.0*(2.0*m_b.get(i)+m_b.get(i+1))*(x.get(i+1)-x.get(i));
				m_c.set(i, temp);
			}
			
		} else {
			
			m_a.setSize(n);
			m_b.setSize(n);
			m_c.setSize(n);
			
			for(int i=0;i<n-1;i++) {
				m_a.set(i, 0.0);
				m_b.set(i, 0.0);
				temp = (m_y.get(i+1) - m_y.get(i))/(m_x.get(i+1)-m_x.get(i));
				m_c.set(i, temp);
			}
		}
		
		m_b0 = m_force_linear_extrapolation?0.0:m_b.get(0);
		m_c0 = m_c.get(0);
		
		double h = x.get(n-1) - x.get(n-2);
		m_a.set(n-1, 0.0);
		temp = 3.0*m_a.get(n-2)*h*h + 2.0*m_b.get(n-2)*h+m_c.get(n-2);
		m_c.set(n-1, temp);
		if(m_force_linear_extrapolation)
			m_b.set(n-1, 0.0);
	}
	
	public int lowerBound(Vector<Double> nums,int l,int r,double target) {

        while(l<r){
            int m = (l+r)/2;
            if(nums.get(m)>=target) 
            	r= m;
            else    l = m +1;
        }
        return l-1;
    }

	public double getValueAt(double x) {
		
		int n = m_x.size();
		int idx = lowerBound(m_x,0,n-1,x);
		idx= idx>0? idx:0;
		double h = x-m_x.get(idx);
		double res;
		
		if(x<m_x.get(0))
			res = (m_b0 * h +m_c0)*h +m_y.get(0);
		else if (x>m_x.get(n-1))
			res = (m_b.get(n-1)*h +m_c.get(n-1))*h +m_y.get(n-1);
		else
			res = ((m_a.get(idx)*h + m_b.get(idx))*h+m_c.get(idx))*h+m_y.get(idx);
		return res;
	}

}
