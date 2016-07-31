package com.yang.bean.element;

//import com.kodakalaris.kodakmomentslib.culumus.bean.text.TextBlock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Layer implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final String FLAG_TYPE = "Type";
	public static final String FLAG_LOCATION = "Location";
	public static final String FLAG_ANGLE = "Angle";
	public static final String FLAG_PINNED = "Pinned";
	public static final String FLAG_CONTENT_BASE_URI = "ContentBaseURI";
	public static final String FLAG_CONTENT_Id = "ContentId";
	public static final String FLAG_DATA = "Data";
	
	public static final String TYPE_IMAGE = "Image";
	public static final String TYPE_TEXT_BLOCK = "TextBlock";	
	
	/*
	 * for location ROI
	 */
	public static final String FLAG_LOCATION_X = "X";
	public static final String FLAG_LOCATION_Y = "Y";
	public static final String FLAG_LOCATION_W = "W";
	public static final String FLAG_LOCATION_H = "H";
	public static final String FLAG_LOCATION_CONTAINER_W = "ContainerW";
	public static final String FLAG_LOCATION_CONTAINER_H = "ContainerH";
	
	public String type;
	public ROI location;
	public int angle;
	public boolean pinned;
	public String contentBaseURI = "";
	public String contentId = "";
	public ArrayList<Data> data;
	public List<String> copyIds;
	
	public String getCaptionText(){
		String caption = "";;
		if(data != null){
			for(Data d : data){
				String name = d.name==null?"":d.name;
				if(Data.TYPE_CAPTIONTEXT.equals(name)){
					caption = d.value + "";
				}
			}
		}
		return caption;
	}
	
	public boolean isCaptionable() {
		boolean result = false;
		if (data != null) {
			for(Data d : data){
				String name = d.name==null?"":d.name;
				if(Data.VALUE_TYPE_IS_CAPTIONABLE.equals(name)){
					result = (Boolean) d.value;
					break;
				}
			}
		}
		
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o instanceof Layer) {
			return ((Layer) o).contentId.equals(this.contentId);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return contentId.hashCode();
	}
}
