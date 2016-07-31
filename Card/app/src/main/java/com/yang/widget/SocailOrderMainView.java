package com.yang.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;

import com.yang.bean.GCPage;
import com.yang.bean.GreetingCard;
import com.yang.card.R;
import com.yang.listener.DragTarget;
import com.yang.listener.IFlippable;


public class SocailOrderMainView extends RelativeLayout implements IFlippable, DragTarget {
	private Context context;
	private SocialOrderPageView socialOrderPageViewfont,socialOrderPageViewback;
	private Bitmap waitBitmap;
	private GreetingCard greetingCard;
	private GCPage page;
	private onFlingListener onFlingListener;
	private float x1;
	private float x2;


	public SocailOrderMainView(Context context) {
		super(context);
		this.context = context;
		init();
	}

	public SocailOrderMainView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	public SocailOrderMainView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		init();
	}


	@Override
	public void swapFrontAndBack() {

	}

	@Override
	public Object[] pointToPosition(float xOnScreen, float yOnScreen) {
		return new Object[0];
	}

	@Override
	public void hideAllFrames() {

	}

	public void init(){
		inflate(context, R.layout.social_order_edit_avtivity_mainview, this);
		socialOrderPageViewfont = (SocialOrderPageView)findViewById(R.id.social_order_fontpage);
		socialOrderPageViewback = (SocialOrderPageView)findViewById(R.id.social_order_backpage);
		waitBitmap = getWaitBitmap();
		socialOrderPageViewfont.setContenBitmap(waitBitmap);
	}

	private Bitmap getWaitBitmap() {
		if (waitBitmap == null) {
			waitBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.imagewait97x97);
		}
		return waitBitmap;
	}

	public SocialOrderPageView getSocialOrderPageViewfont() {
		return socialOrderPageViewfont;
	}

	public void setGreetingCard(GreetingCard greetingCard) {
		setGreetingCard(greetingCard,true);
	}


	public void setGreetingCardCurrentPage() {
			if (socialOrderPageViewback.getVisibility() == View.GONE){
				socialOrderPageViewback.loadPageImage(greetingCard.pages[1],false);
			}else {
				socialOrderPageViewfont.loadPageImage(greetingCard.pages[0],true);
			}
	}

	public void CardOverTurnClockwise(boolean isClockwise){
		if (socialOrderPageViewback.getVisibility() == View.GONE){
			flip(socialOrderPageViewfont,socialOrderPageViewback, isClockwise);
		}else {
			flip(socialOrderPageViewback,socialOrderPageViewfont, isClockwise);
		}
	}

	public void setGreetingCard(GreetingCard greetingCard,boolean canUseCached ){
		if (greetingCard!=null){
			this.greetingCard = greetingCard;
			page = greetingCard.pages[0];
			socialOrderPageViewfont.loadPageImage(greetingCard.pages[0],canUseCached);
		}
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
	}

	public GCPage getPage() {
		return page;
	}

	private void flip(final View hiddenView, final View displayView, boolean isClockwise) {

		final int duration = 800;
		final int degree = isClockwise ? 90 : -90;
		final int degree2 = -degree;
		final ObjectAnimator a, b;
		a = ObjectAnimator.ofFloat(hiddenView, "rotationY", 0, degree);
		b = ObjectAnimator.ofFloat(displayView, "rotationY", degree2, 0);
		a.setDuration(duration);
		b.setDuration(duration);

		a.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {

			}
			@Override
			public void onAnimationEnd(Animator animation) {
				hiddenView.setVisibility(View.GONE);
				displayView.setVisibility(View.VISIBLE);
			}
			@Override
			public void onAnimationCancel(Animator animation) {
			}
			@Override
			public void onAnimationRepeat(Animator animation) {
			}
		});

		AnimatorSet set = new AnimatorSet();
		set.play(a).before(b);
		set.start();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				x1 = event.getX();
				break;
			case MotionEvent.ACTION_MOVE:
				x2 = event.getX();
				break;
			case MotionEvent.ACTION_UP:

				if (x1 - x2 > ViewConfiguration.get(context).getScaledTouchSlop()) {
					if (onFlingListener != null) {
						onFlingListener.onFling(false);
					}
					break;
				}
				if (x1 - x2 < -ViewConfiguration.get(context).getScaledTouchSlop()) {
					if (onFlingListener != null) {
						onFlingListener.onFling(true);
					}
					break;
				}
		}
		return true;
	}

	public void setOnFlingListener(onFlingListener listener){
		this.onFlingListener = listener;
	}

	public interface onFlingListener{
		void onFling(Boolean isClockwise);
		void onClick();
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		getParent().requestDisallowInterceptTouchEvent(true);
		return true;
	}

}
