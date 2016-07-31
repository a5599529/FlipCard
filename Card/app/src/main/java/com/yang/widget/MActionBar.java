package com.yang.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.yang.card.R;

public class MActionBar extends RelativeLayout{
	public static final int THEME_LIGHT = 0;
	public static final int THEME_DARK = 1;
	public static final int THEME_TRANSPARENT = 2;
	public static final int TITLE_GRAVITY_CENTER = 0;
	public static final int TITLE_GRAVITY_LEFT = 1;
	public static final int TITLE_GRAVITY_RIGHT = 2;

	protected int xmlActionBarTheme = 0;
	private String mTitle;
	private int mTitleLogo;
	private int mLeftButtonRes;
	private int mLeftTextRes;
	private int mRightButtonRes;
	private int mRightTextRes;
	private boolean mRightTextAllCaps = false;
	private int mTitleGravity = TITLE_GRAVITY_CENTER;
	protected boolean isSocial = false;
	private int mTitleSizeRes;
	private String mTitleFontPath;
	private int mRightTxtSizeRes;
	private String mRightTxtFontPath;
	private int mTitleStyleRes;
	private int mRightTxtStyleRes;


	private RelativeLayout mRelaLyActionBar;
	private TextView vTxtTitle;
	private ImageView vIvTitleLogo;
	private ImageButton vIbtnLeft;
	private TextView vTxtLeft;
	private ImageButton vIbtnRight;
	private TextView vTxtRight;
    private Context mContext;
    private  String titleText = "",rightText = "",leftText = "";

	private Resources mResources;

	private int titleTxtSize = -1;
	private int rightTxtSize = -1;
	private float titleStringSize;
	private float rightStringSize;


	public MActionBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MActionBar);  
		mTitle = a.getString(R.styleable.MActionBar_titleText);
		mTitleLogo = a.getResourceId(R.styleable.MActionBar_title_logo, 0);

		mTitleGravity = a.getInteger(R.styleable.MActionBar_title_gravity, TITLE_GRAVITY_CENTER);
		mLeftButtonRes = a.getResourceId(R.styleable.MActionBar_left_button_image_src, 0);
		mLeftTextRes = a.getResourceId(R.styleable.MActionBar_left_button_text_src , 0);
		mRightButtonRes = a.getResourceId(R.styleable.MActionBar_right_button_image_src, 0);
		mRightTextRes = a.getResourceId(R.styleable.MActionBar_right_button_text_src, 0);
		mRightTextAllCaps = a.getBoolean(R.styleable.MActionBar_right_button_text_all_caps, false);
		isSocial = a.getBoolean(R.styleable.MActionBar_social,false);
		mTitleSizeRes = a.getResourceId(R.styleable.MActionBar_social_title_size,0);
		mTitleFontPath = a.getString(R.styleable.MActionBar_social_title_fontPath);
		mRightTxtSizeRes = a.getResourceId(R.styleable.MActionBar_social_right_txt_size,0);
		mRightTxtFontPath = a.getString(R.styleable.MActionBar_social_right_txt_fontPath);
        mContext = context;
		a.recycle();
		
		init(context);
	}

	public MActionBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MActionBar(Context context) {
		super(context);
		
		init(context);
	}
	
	private void findViews() {
		mRelaLyActionBar = (RelativeLayout)findViewById(R.id.relaly_actionbar);
		vTxtTitle = (TextView) findViewById(R.id.txt_title);
		vIvTitleLogo = (ImageView) findViewById(R.id.iv_title_logo);
		vIbtnLeft = (ImageButton) findViewById(R.id.ibtn_left);
		vTxtLeft = (TextView) findViewById(R.id.tv_left);
		vIbtnRight = (ImageButton) findViewById(R.id.ibtn_right);
		vTxtRight = (TextView) findViewById(R.id.tv_right);
		vTxtRight.setAllCaps(mRightTextAllCaps);
	}
	
	public void init(Context context) {
		mResources = context.getResources();
		inflate(context, R.layout.view_m_action_bar_light, this);
		
		findViews();
		
		setTitle(mTitle);
		setTitleLogo(mTitleLogo);
		if(mLeftButtonRes != 0){
			setLeftButtonImage(mLeftButtonRes);
		}else if(mLeftTextRes != 0){
			setLeftText(mLeftTextRes);
		}

		if(mRightButtonRes != 0){
			setRightButtonImage(mRightButtonRes);
		} else if(mRightTextRes != 0) {
			setRightText(mRightTextRes);
		}
		
		
		switch (mTitleGravity) {
		case TITLE_GRAVITY_CENTER:
			//Because when set it in center, maybe the right button or left button is null(0 width)
			//So we need to put it in middle right
			LayoutParams params = (LayoutParams) vTxtTitle.getLayoutParams();
			params.addRule(RIGHT_OF, 0);
			params.addRule(LEFT_OF, 0);
			vTxtTitle.setGravity(Gravity.CENTER);
			break;
		case TITLE_GRAVITY_LEFT:
			vTxtTitle.setGravity(Gravity.LEFT);
			break;
		case TITLE_GRAVITY_RIGHT:
			vTxtTitle.setGravity(Gravity.RIGHT);
			break;
		default:
			break;
		}
	}
	
	public void setRightButtonVisiable(boolean isVisible){
		vIbtnRight.setVisibility(isVisible ? View.VISIBLE : View.GONE);
		vTxtRight.setVisibility(isVisible ? View.VISIBLE : View.GONE);
	}
	
	public void setLeftButtonVisible(boolean isVisible) {
		vIbtnLeft.setVisibility(isVisible ? View.VISIBLE : View.GONE);
		vTxtLeft.setVisibility(isVisible ? View.VISIBLE : View.GONE);
	}
	
	
	public void setOnLeftButtonClickListener(OnClickListener onClickListener) {
		vIbtnLeft.setOnClickListener(onClickListener);
		vTxtLeft.setOnClickListener(onClickListener);
	}
	
	public void setOnRightButtonClickListener(OnClickListener onClickListener) {
		vIbtnRight.setOnClickListener(onClickListener);
		vTxtRight.setOnClickListener(onClickListener);
	}

	public void setOnTitleLogoTouchListener(OnTouchListener onTouchListener){
		vIvTitleLogo.setOnTouchListener(onTouchListener);
	}
	
	public void setTitle(String text) {
        titleText = text;
		vTxtTitle.setText(text == null ? "" : text);
		if(isSocial){
			setSocialTitleSize();
		}else {
			setTitleSize();
		}
	}


	public void setTitle(int resId) {
        titleText = mContext.getString(resId);
		vTxtTitle.setText(resId);
		if(isSocial){
			setSocialTitleSize();
		}else {
			setTitleSize();
		}
	}

	public void setTitleTextAllCaps(boolean isShowAllCaps){
		vTxtTitle.setAllCaps(isShowAllCaps);
	}
	
	public void setLeftButtonImage(int resId) {
		if (resId != 0) {
			vIbtnLeft.setImageResource(resId);
			vTxtLeft.setVisibility(View.GONE);
		}
	}

	public void setLeftText(int resId) {
		if (resId != 0) {
			leftText = mContext.getString(resId);
			vTxtLeft.setText(resId);
			vIbtnLeft.setVisibility(View.GONE);
			if(isSocial){
				setSocialTitleSize();
			}else {
				setTitleSize();
			}
		}
	}
	
	public void setRightButtonImage(int resId) {
		if (resId != 0) {
			vIbtnRight.setImageResource(resId);
			vTxtRight.setVisibility(View.GONE);
		}
	}
	
	public void setRightBtnEnabled(boolean enabled) {
		if (vIbtnRight != null) {
			vIbtnRight.setEnabled(enabled);
		}
		if (vTxtRight != null) {
			vTxtRight.setEnabled(enabled);
		}
	}
	
	public void setRightText(int resId) {
		if (resId != 0) {
            rightText = mContext.getString(resId);
			vTxtRight.setText(resId);
			vIbtnRight.setVisibility(View.GONE);
			if(isSocial){
				setSocialTitleSize();
			}else {
				setTitleSize();
			}
		}
	}
	
	public void setTitleLogo(int resId){
		if(resId != 0){
			vIvTitleLogo.setImageResource(resId);
		}
	}

    public void setRightButtonTextColor(int colorId){
        if(vTxtRight != null){
            vTxtRight.setTextColor(mResources.getColor(colorId));
        }
    }

	public void setActionBarBackground(int colorId){
		mRelaLyActionBar.setBackgroundResource(colorId);
	}

    private void setTitleSize(){
		titleText = titleText == null ? "" : titleText;
		rightText = rightText == null ? "" : rightText;
		Paint titlePaint = new Paint();
		titlePaint.setTextSize(vTxtTitle.getTextSize());
		titleStringSize = titlePaint.measureText(titleText);
		Paint rightPaint = new Paint();
		rightPaint.setTextSize(vTxtRight.getTextSize());
		rightStringSize = rightPaint.measureText(rightText);
	}

	private void setSocialTitleSize(){
		vTxtRight.setMaxWidth((int) getResources().getDimension(R.dimen.ke_55pt));
		if(mTitleSizeRes!=0){
			vTxtTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(mTitleSizeRes));
		}
		if(mTitleFontPath!=null){
			Typeface typeface = Typeface.createFromAsset(getContext().getAssets(),"fonts/"+mTitleFontPath);
			vTxtTitle.setTypeface(typeface);
		}
		if(mTitleStyleRes!=0){
			vTxtTitle.setTextAppearance(mContext,mTitleStyleRes);
		}
		if(mRightTxtSizeRes!=0){
			vTxtRight.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(mRightTxtSizeRes));
		}
		if(mRightTxtFontPath!=null){
			Typeface typeface = Typeface.createFromAsset(getContext().getAssets(),"fonts/"+mRightTxtFontPath);
			vTxtRight.setTypeface(typeface);
		}
		if(mRightTxtStyleRes!=0){
			vTxtRight.setTextAppearance(mContext,mRightTxtStyleRes);
		}

	}

	public ImageButton getvIbtnRight() {
		return vIbtnRight;
	}
}
