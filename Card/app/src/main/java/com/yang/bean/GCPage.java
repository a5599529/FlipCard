package com.yang.bean;


import com.yang.bean.element.Page;

public class GCPage extends Page<GCLayer> {
	private static final long serialVersionUID = 1L;

	public static final String FLAG_PAGE_TYPE = "PageType";
	public static final String FLAG_LAYOUT_TYPE = "LayoutType";
	
	public String pageType = "";
	public String layoutType = "";
	public int minNumberOfImages;
	public int maxNumberOfImages;
	
	//mainRefreshCount big mainRefreshSucCount then to load main photo   	
	private int mainRefreshCount;
	private int mainRefreshSucCount;
	
	public int getMainRefreshCount() {
		return mainRefreshCount;
	}

	public void setMainRefreshCount(int mainRefreshCount) {
		this.mainRefreshCount = mainRefreshCount;
	}

	public int getMainRefreshSucCount() {
		return mainRefreshSucCount;
	}

	public void setMainRefreshSucCount(int mainRefreshSucCount) {
		this.mainRefreshSucCount = mainRefreshSucCount;
	}

	public void setPageRefresh(){
		mainRefreshCount += 1;		
	}
	
	//get the new GCPage structure,must call this function to set is want to refresh the page photo
	public void setPageRefresh(int mainCount,int mainSucCount){
		mainRefreshCount = mainCount;
		mainRefreshSucCount = mainSucCount;
	}
	
	public int getHoleIndex(GCLayer layer){
		if(layers != null){
			for(int i = 0; i<layers.size(); i++){
				if(layers.get(i).location.equals(layer.location)){
					return i;
				}
			}
		}
		return -1;
	}
		
}
