package com.yang.bean.element;

import android.util.Log;

import java.io.Serializable;
import java.math.BigDecimal;

public class ROI implements Cloneable, Serializable{
	private static final long serialVersionUID = 1L;
	public double x;
	public double y;
	public double w;
	public double h;

	public double ContainerW;
	public double ContainerH;
	
	public ROI(){
	}
	
	/**
	 * Be careful when using this method. It's calculate by {@link #w}, {@link #h}, {@link #ContainerW} and {@link #ContainerH}. 
	 * <p><strong>{@link #w}, {@link #h} are in percent format, {@link #ContainerW} and {@link #ContainerH} are from server
	 * please make sure your {@link #w}, {@link #h}, {@link #ContainerW} and {@link #ContainerH} are correct before call this method.</strong>
	 * @return If {@link #w} and {@link #h} are correct, it will return the product crop's aspect ratio. Otherwise return 0.
	 */
	public float getAspectRatio(){
		if(h != 0.0){
			return (float) ((w * ContainerW) / (h * ContainerH));
		}
		return 0;
	}
	
	/**
	 * Transfer exact numbers to percent format
	 */
	public void transferToPercentFormat(){
		x = x / ContainerW;
		y = y / ContainerH;
		w = w / ContainerW;
		h = h / ContainerH;
	}

	public void transferToExactFormat(){
		x = x * ContainerW;
		y = y * ContainerH;
		w = w * ContainerW;
		h = h * ContainerH;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		ROI clone = (ROI) super.clone();
		return clone;
	}

	@Override
	public String toString() {
		String toString = "ROI[x:"+x+", y:"+y+", w:"+w+", h:"+h+", ContainerW:"+ContainerW+", ContainerH:"+ContainerH+"]";
		return toString;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(ContainerH);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(ContainerW);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(h);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(w);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ROI other = (ROI) obj;
		if (Double.doubleToLongBits(ContainerH) != Double
				.doubleToLongBits(other.ContainerH))
			return false;
		if (Double.doubleToLongBits(ContainerW) != Double
				.doubleToLongBits(other.ContainerW))
			return false;
		if (Double.doubleToLongBits(h) != Double.doubleToLongBits(other.h))
			return false;
		if (Double.doubleToLongBits(w) != Double.doubleToLongBits(other.w))
			return false;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
	
	public ROI methodMakeROIValueValid() {
		BigDecimal roiX=BigDecimal.valueOf(x);
		BigDecimal roiY=BigDecimal.valueOf(y);
		BigDecimal roiW=BigDecimal.valueOf(w);
		BigDecimal roiH=BigDecimal.valueOf(h);
		BigDecimal num=BigDecimal.valueOf(1.0);
		if (roiX.add(roiW).compareTo(num)==1) {//"-1" means "<","0" means "==","1" means ">"
			Log.e("ROI", "x+w="+(roiX.add(roiW)));
			Log.e("ROI", "pre-->w="+w);
			w=num.subtract(roiX).doubleValue();
			Log.e("ROI", "late-->w="+w);
		}
		if (roiY.add(roiH).compareTo(num)==1) {
			Log.e("ROI", "y+h="+(roiY.add(roiH)));
			Log.e("ROI", "pre=-->h="+h);
			h=num.subtract(roiY).doubleValue();
			Log.e("ROI", "late-->h="+h);
		}
		if (x <= 0.0){
			x = 0.0;
		}
		if (y <= 0.0){
			y = 0.0;
		}
		return this;
	}
	
//	/*
//	 *  ratioValue = w / h;
//	 */
//	public ROI adjustRoiByRatio(float ratioValue) {
//		double tempW = w*ContainerW;
//		double tempH = h*ContainerH;
//		double maxW = CounterUtil.sub(1,x);
//		if (w > maxW){
//			w = maxW;
//		}
//		if (Math.abs(tempW / tempH - ratioValue) <= Math.abs(tempH / tempW - ratioValue)){
//			//h= (w*ContainerW )/(ContainerH* ratioValue);
//			h=CounterUtil.div(CounterUtil.mul(w, ContainerW),CounterUtil.mul(ContainerH,ratioValue));
//		}else {
//			//h= (w*ContainerW * ratioValue)/ContainerH;
//			h=CounterUtil.div(CounterUtil.mul(CounterUtil.mul(w, ContainerW),ratioValue),ContainerH);
//		}
//		double maxH = CounterUtil.sub(1,y);
//		if (h > maxH) {
//			h = maxH;
//			if (Math.abs(tempW / tempH - ratioValue) <= Math.abs(tempH / tempW - ratioValue)){
//				//w= (h*ContainerH * ratioValue)/ContainerW;
//				w=CounterUtil.div(CounterUtil.mul(CounterUtil.mul(h, ContainerH),ratioValue),ContainerW);
//			}else {
//				//w= (h*ContainerH )/(ContainerW* ratioValue);
//				w=CounterUtil.div(CounterUtil.mul(h, ContainerH),CounterUtil.mul(ContainerW,ratioValue));
//			}
//		}
//
//		return this;
//	}
}
