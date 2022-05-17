package com.edu.zju.cbeis.bme207.math;


import java.util.Vector;


public class BandMatrix {
	private Vector<Vector<Double>>  upper,lower;
	
	public BandMatrix() {
		
	}
	public BandMatrix(int dim, int n_u, int n_l) {
		upper = new Vector<Vector<Double>>();
		lower = new Vector<Vector<Double>>();
		resize(dim, n_u, n_l);
	}
	public void resize(int dim,int n_u, int n_l) {
		upper.setSize(n_u+1);
		lower.setSize(n_l+1);
		for(int i=0; i<upper.size();i++) {
			upper.set(i,new Vector<Double>());
			upper.get(i).setSize(dim);
			for(int j=0;j<dim;j++)
				upper.get(i).set(j, 0.0);
		}
			
		for(int i=0; i<lower.size();i++) {
			lower.set(i,new Vector<Double>());
			lower.get(i).setSize(dim);
			for(int j=0;j<dim;j++)
				lower.get(i).set(j, 0.0);
		}
	}
	
	public int dim() {
		if(upper.size()>0)
			return upper.get(0).size();
		return 0;
	}
	public int num_upper() {
		return upper.size()-1;
	}
	public int num_lower() {
		return lower.size()-1;
	}
	public double read(int i, int j) {
		int k = j-i;
		if(k>=0)
			return upper.get(k).get(i);
		return lower.get(-k).get(i);
	}
	
	public void write(int i, int j, double value) {
		int k = j-i;
		if(k>=0) 
			upper.get(k).set(i,value);
		else 
			lower.get(-k).set(i,value);
	}
	
	public double read_saved_diag(int i) {
		return lower.get(0).get(i);
	}
	
	public void write_saved_diag(int i, double value) {
		lower.get(0).set(i,value);
	}
	
	
	public int max(int a, int b) {
		return a>b?a:b;
	}
	
	public int min(int a, int b) {
		return a<b?a:b;
	}
	
	public void lu_decompose() {
		int i_max,j_max;
		int j_min;
		double x,temp;
		
		for(int i=0; i<dim();i++) {
			temp = 1.0/read(i,i);
			write_saved_diag(i,temp);
			j_min = max(0,i-num_lower());
			j_max = min(dim()-1, i+num_upper());
			for(int j= j_min;j<=j_max;j++) {
				temp= read(i,j)*read_saved_diag(i);
				write(i,j,temp);
			}
			write(i,i,1.0);
		}
		
		
		for(int k=0;k<dim();k++) {
			i_max = min(dim()-1,k+num_lower());
			for(int i=k+1;i<=i_max;i++) {
				x = -read(i,k)/read(k,k);
				write(i,k,-x);
				j_max = min(dim()-1,k+num_upper());
				for(int j=k+1;j<=j_max;j++) {
					temp=read(i,j)+x*read(k,j);
					write(i,j,temp);
				}
			}
		}
	}
	
	public Vector<Double> l_solve(Vector<Double> b) {
		Vector<Double> x = new Vector<>(dim());
		for(int i=0;i<dim();i++)
			x.add(i, 0.0);
		int j_start;
		double sum,temp;
		for(int i=0;i<dim();i++) {
			sum = 0;
			j_start = max(0,i-num_lower());
			for(int j=j_start;j<i;j++) {
				sum += read(i,j)*x.get(j);
			}
			temp=b.get(i)*read_saved_diag(i)-sum;
			x.set(i,temp);
		}
		
		return x;
	}
	
	public Vector<Double> r_solve(Vector<Double> b) {
		Vector<Double> x = new Vector<>(dim());
		for(int i=0;i<dim();i++)
			x.add(i, 0.0);
		int j_stop;
		double sum;
		for(int i=dim()-1;i>=0;i--) {
			sum=0;
			j_stop = min(dim()-1,i+num_upper());
			for(int j=i+1;j<=j_stop;j++)
				sum += read(i,j) * x.get(j);
			x.set(i, (b.get(i)-sum)/read(i,i));
		}
		return x;
	}
	
	public Vector<Double> lu_solve(Vector<Double> b) {
		Vector<Double> x = new Vector<>();
		Vector<Double> y = new Vector<>();
		lu_decompose();
		y = l_solve(b);
		x = r_solve(y);
		return x;
	}
	

}
