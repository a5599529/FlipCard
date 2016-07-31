package com.yang.bean.element;

import java.io.Serializable;
import java.util.List;


public class Page<L extends Layer> implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final String FLAG_BASE_URI = "BaseURI";
	public static final String FLAG_ID = "Id";
	public static final String FLAG_SEQUENCE_NUMBER = "SequenceNumber";
	public static final String FLAG_WIDTH = "Width";
	public static final String FLAG_HEIGHT = "Height";
	public static final String FLAG_MIN_NUM_OF_IMAGES = "MinNumberOfImages";
	public static final String FLAG_MAX_NUM_OF_IMAGES = "MaxNumberOfImages";
	public static final String FLAG_LAYERS = "Layers";
	public static final String FLAG_MARGIN = "Margin";
	
	public static final String FLAG_MARGIN_TOP = "Top";
	public static final String FLAG_MARGIN_LEFT = "Left";
	public static final String FLAG_MARGIN_BOTTOM = "Bottom";
	public static final String FLAG_MARGIN_RIGHT = "Right";
	
	public String baseURI = "";
	public String id = "";
	public int sequenceNumber;
	public float width;
	public float height;
	public int minNumberOfImages;
	public int maxNumberOfImages;
	public List<L> layers;
	
	/**
	 * margin
	 * 		{top, left, bottom, right}
	 */
	public float[] margin = new float[4];
	
	public float getMarginTop(){
		return margin[0];
	}
	
	public float getMarginLeft(){
		return margin[1];
	}
	
	public float getMarginBottom(){
		return margin[2];
	}
	
	public float getMarginRight(){
		return margin[3];
	}

	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof Page) {
			return id.equals(((Page)o).id);
		}
		return super.equals(o);
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
