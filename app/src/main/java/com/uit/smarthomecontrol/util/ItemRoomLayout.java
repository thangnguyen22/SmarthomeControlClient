package com.uit.smarthomecontrol.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by tensh on 10/28/2015.
 */
public class ItemRoomLayout extends FrameLayout {
    public ItemRoomLayout(Context context) {
        super(context);
    }
    public ItemRoomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemRoomLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension(
                parentWidth, parentWidth/2);
    }
}
