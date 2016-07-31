package com.yang.widget;


import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yang.card.R;


public class KMTabWidget extends LinearLayout {
    private int mCurrentPosition;
    private int mInitialPosition;
    private boolean mInit;
    private OnItemSelectedListener mOnItemSelectedListener;

    private int mSelectorHeight;
    private int mSelectorWidth;
    private int mSelectorColor;
    private int mSelectorX;
    private Paint mSelectorPaint;


    public KMTabWidget(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KMTabWidget(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.KMTabWidget);
        mSelectorHeight = a.getDimensionPixelSize(R.styleable.KMTabWidget_selectorHeight, getResources().getDimensionPixelSize(R.dimen.km_tab_widget_selector_height));
        mSelectorColor = a.getColor(R.styleable.KMTabWidget_selectorColor, getResources().getColor(R.color.yellow));
        mInitialPosition = a.getInteger(R.styleable.KMTabWidget_initialPosition, 1) - 1;
        a.recycle();

        mSelectorWidth = getResources().getDimensionPixelSize(R.dimen.km_tab_widget_selector_width);
        setOrientation(HORIZONTAL);
        mSelectorPaint = new Paint();
        mSelectorPaint.setColor(mSelectorColor);
        mSelectorPaint.setAntiAlias(true);
        mSelectorPaint.setStrokeWidth(mSelectorHeight);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mSelectorHeight > 0) {
            int height = MeasureSpec.getSize(heightMeasureSpec);
            int heightMode = MeasureSpec.getMode(heightMeasureSpec);
            height = height + mSelectorHeight;

            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, heightMode);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(getChildCount()>0){
            for(int i = 0 ; i < getChildCount() ; i++){
                if(getChildAt(i) instanceof TextView) {
                    TextView tv = (TextView) getChildAt(i);
                    Paint p = new Paint();
                    p.setTextSize(tv.getTextSize());
                    float strSize =  p.measureText(tv.getText().toString());
                    int tabWidth = tv.getMeasuredWidth() - tv.getPaddingLeft() - tv.getPaddingRight();
                    while (strSize > tabWidth) {
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) 0.8 * tv.getTextSize());
                        tv.invalidate();
                        strSize = (float)0.8*strSize;
                    }
                }
            }
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        if (getChildCount() > 0) {
            canvas.save();
            canvas.drawLine(mSelectorX, getHeight() - mSelectorHeight / 2, mSelectorX + mSelectorWidth, getHeight() - mSelectorHeight / 2, mSelectorPaint);
            canvas.restore();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (getChildCount() > 0) {
            for (int i = 0; i < getChildCount(); i++) {
                final int position = i;
                View v = getChildAt(i);
                if (!mInit && mInitialPosition == i) {
                    mCurrentPosition = mInitialPosition;
                    mSelectorX = getSelectorX(mCurrentPosition);
                    v.setSelected(true);
                } else {
                    if (v.isSelected()) {
                        mCurrentPosition = i;
                        mSelectorX = getSelectorX(mCurrentPosition);
                    }
                }

                v.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectCurrentItem(position, true);
                    }
                });
            }

            mInit = true;

        }
    }

    public void setSelection(int position) {
        selectCurrentItem(position, true);
    }

    public void selectCurrentItem(int position) {
        selectCurrentItem(position, true);
    }

    public void selectCurrentItem(int position, boolean smoothScroll) {
        if (getChildCount() > 0) {
            for (int i = 0; i < getChildCount(); i++) {
                boolean checked = i == position;
                View v = getChildAt(i);
                if (v instanceof Checkable) {
                    ((Checkable) v).setChecked(checked);
                }

                v.setSelected(checked);
            }
        }

        if (mCurrentPosition != position) {
            mCurrentPosition = position;

            scrollSelectorTo(position, smoothScroll);

            if (mOnItemSelectedListener != null) {
                mOnItemSelectedListener.onItemSelected(this, position);
            }
        }

    }

    private int getSelectorX(int position) {
        View selectedView = getChildAt(position);
        return selectedView.getLeft() + selectedView.getWidth() / 2 - mSelectorWidth / 2;
    }

    private void scrollSelectorTo(int position, boolean smooth) {
        final int targetX = getSelectorX(position);

        if (smooth) {
            ValueAnimator anim = ValueAnimator.ofInt(0, 100);
            anim.setDuration(300);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int f = (Integer) animation.getAnimatedValue();
                    mSelectorX = mSelectorX + (targetX - mSelectorX) * f / 100;
                    postInvalidateOnAnimation();
                }
            });

            anim.start();
        } else {
            mSelectorX = targetX;
            invalidate();
        }

    }

    public int getSelectedItemPosition() {
        return mCurrentPosition;
    }


    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        mOnItemSelectedListener = onItemSelectedListener;
    }

    public OnItemSelectedListener getOnItemSelectedListener() {
        return mOnItemSelectedListener;
    }

    public interface OnItemSelectedListener {
        void onItemSelected(KMTabWidget parent, int position);
    }
}
