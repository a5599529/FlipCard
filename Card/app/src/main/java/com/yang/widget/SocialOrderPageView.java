package com.yang.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.yang.bean.GCLayer;
import com.yang.bean.GCPage;
import com.yang.util.ImageUtil;
//import com.yang.util.ProductContentLoader;

public class SocialOrderPageView extends ProductMainPageView<GCPage, GCLayer> {


//	private ProductContentLoader contentLoader;
	private Bitmap contenBitmap;
	private RectF mContentRect;
	private GCPage page;

	public SocialOrderPageView(Context context) {
		super(context);
		init();
	}

	public SocialOrderPageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SocialOrderPageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	@Override
	public Bitmap getImageBitmap() {
		return null;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (contenBitmap != null){
			canvas.drawBitmap(contenBitmap, null, mContentRect, null);
		}
	}

	private void init() {
		setClickable(true);
//		contentLoader = ProductContentLoader.getInstance();
	}

	public void resetDrawArea(int w, int h){
		float displayWidth = w;
		float displayHeight = h;
		if (page!=null){
			if (page.width>=page.height){
				displayHeight = (int)(page.height*displayWidth/page.width);
			}else {
				displayWidth = (int)(page.width*displayHeight/page.height);
			}
		}
		float startPointX = (w-displayWidth)/2;
		float startPointY = (h-displayHeight)/2;
		mContentRect = new RectF(startPointX, startPointY, displayWidth+startPointX,displayHeight+startPointY);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		resetDrawArea(w, h);
	}

	public void setContenBitmap(Bitmap contenBitmap) {
		this.contenBitmap = contenBitmap;
	}

	public void loadPageImage(GCPage page,boolean isFont) {
		if (page == null) {
			return;
		}
		this.page = page;
		resetDrawArea(this.getWidth(), this.getHeight());
//		contentLoader.loadImage(page, 800, 800, new ImageLoadRequest.OnResponseListener() {
//
//			@Override
//			public void onResponsed(ImageLoadResponse response) {
				setContenBitmap(ImageUtil.decodeImageIgnorOom(getContext(),null, isFont));
				invalidate();
//			}
//		}, canUseCached);
	}

}
