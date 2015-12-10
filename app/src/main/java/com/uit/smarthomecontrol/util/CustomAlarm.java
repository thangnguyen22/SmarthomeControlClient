/*
 * Copyright 2012 Lars Werkman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uit.smarthomecontrol.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.uit.smarthomecontrol.R;

/**
 * Displays a holo-themed circular seek bar.
 */
public class CustomAlarm extends View{

    /*
        * Constants used to save/restore the instance state.
        */
    private static final String STATE_PARENT = "parent";
    private static final String STATE_ANGLE = "angle";
    private static final int TEXT_SIZE_DEFAULT_VALUE = 25;
    private static final int END_WHEEL_DEFAULT_VALUE = 360;
    public static final int COLOR_WHEEL_STROKE_WIDTH_DEF_VALUE = 3;
    public static final float POINTER_RADIUS_DEF_VALUE = 8;
    public static final int MAX_POINT_DEF_VALUE = 100;
    public static final int START_ANGLE_DEF_VALUE = 0;

    /**
     * {@code Paint} instance used to draw the color wheel.
     */
    private Paint mColorWheelPaint;

    /**
     * The stroke width used to paint the color wheel (in pixels).
     */
    private int mColorWheelStrokeWidth;

    /**
     * The radius of the pointer (in pixels).
     */
    private float mPointerRadius;

    /**
     * The rectangle enclosing the color wheel.
     */
    private RectF mColorWheelRectangle = new RectF();

    /**
     * {@code true} if the user clicked on the pointer to start the move mode.
     * {@code false} once the user stops touching the screen.
     *
     * @see #onTouchEvent(MotionEvent)
     */

    private float mTranslationOffset;

    /**
     * Radius of the color wheel in pixels.
     * <p/>
     * <p>
     * Note: (Re)calculated in {@link #onMeasure(int, int)}.
     * </p>
     */
    private float mColorWheelRadius;

    /**
     * The pointer's position expressed as angle (in rad).
     */
    private float mAngle;
    private Paint textPaint;
    private String text;
    private int max = 100;
    private SweepGradient s;
    private Paint mArcColor;
    private int alarm_active_color, alarm_unactive_color, text_size;

    private int arc_finish_radians = 360;
    private int start_arc = 270;

    private RectF mColorCenterHaloRectangle = new RectF();
    private int end_wheel;

    private boolean show_text = true;
    private Rect bounds = new Rect();

    public CustomAlarm(Context context) {
        super(context);
        init(null, 0);
    }

    public CustomAlarm(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CustomAlarm(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.HoloCircleSeekBar, defStyle, 0);

        initAttributes(a);

        a.recycle();
        // mAngle = (float) (-Math.PI / 2);

        mColorWheelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mColorWheelPaint.setShader(s);
        mColorWheelPaint.setColor(alarm_unactive_color);
        mColorWheelPaint.setStyle(Style.STROKE);
        mColorWheelPaint.setStrokeWidth(mColorWheelStrokeWidth);

        Paint mColorCenterHalo = new Paint(Paint.ANTI_ALIAS_FLAG);
        mColorCenterHalo.setColor(Color.CYAN);
        mColorCenterHalo.setAlpha(0xCC);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG);
        textPaint.setColor(alarm_active_color);
        textPaint.setStyle(Style.FILL_AND_STROKE);
        textPaint.setTextAlign(Align.LEFT);
        // canvas.drawPaint(textPaint);
        textPaint.setTextSize(text_size);

        mArcColor = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcColor.setColor(alarm_active_color);
        mArcColor.setStyle(Style.STROKE);
        mArcColor.setStrokeWidth(mColorWheelStrokeWidth);

        Paint mCircleTextColor = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircleTextColor.setColor(Color.WHITE);
        mCircleTextColor.setStyle(Style.FILL);

        invalidate();
    }

    private void setText(String text) {
        this.text = text;
    }

    private void initAttributes(TypedArray a) {
        mColorWheelStrokeWidth = a.getInteger(
                R.styleable.HoloCircleSeekBar_wheel_size, COLOR_WHEEL_STROKE_WIDTH_DEF_VALUE);
        mPointerRadius = a.getDimension(
                R.styleable.HoloCircleSeekBar_pointer_size, POINTER_RADIUS_DEF_VALUE);
        max = a.getInteger(R.styleable.HoloCircleSeekBar_max, MAX_POINT_DEF_VALUE);

        String alarm_active_color_attr = a
                .getString(R.styleable.HoloCircleSeekBar_alarm_active_color);
        String alarm_unactive_color_attr = a
                .getString(R.styleable.HoloCircleSeekBar_alarm_unactive_color);

        text_size = a.getDimensionPixelSize(R.styleable.HoloCircleSeekBar_text_size, TEXT_SIZE_DEFAULT_VALUE);

        start_arc = a.getInteger(R.styleable.HoloCircleSeekBar_start_angle, START_ANGLE_DEF_VALUE);
        end_wheel = a.getInteger(R.styleable.HoloCircleSeekBar_end_angle, END_WHEEL_DEFAULT_VALUE);

        show_text = a.getBoolean(R.styleable.HoloCircleSeekBar_show_text, true);

        // mAngle = (float) calculateAngleFromText(init_position);

        if (alarm_active_color_attr != null) {
            try {
                alarm_active_color = Color.parseColor(alarm_active_color_attr);
            } catch (IllegalArgumentException e) {
                alarm_active_color = Color.DKGRAY;
            }

        } else {
            alarm_active_color = Color.DKGRAY;
        }
        if (alarm_unactive_color_attr != null) {
            try {
                alarm_unactive_color = Color
                        .parseColor(alarm_unactive_color_attr);
            } catch (IllegalArgumentException e) {
                alarm_unactive_color = Color.CYAN;
            }

        } else {
            alarm_unactive_color = Color.CYAN;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // All of our positions are using our internal coordinate system.
        // Instead of translating
        // them we let Canvas do the work for us.

        canvas.translate(mTranslationOffset, mTranslationOffset);

        // Draw the color wheel.
        canvas.drawArc(mColorWheelRectangle, start_arc + 270, end_wheel
                - (start_arc), false, mColorWheelPaint);

        canvas.drawArc(mColorWheelRectangle, start_arc + 270,
                (arc_finish_radians) > (end_wheel) ? end_wheel - (start_arc)
                        : arc_finish_radians - start_arc, false, mArcColor);

        // canvas.drawCircle(mColorWheelRectangle.centerX(),
        // mColorWheelRectangle.centerY(), (bounds.width() / 2) + 5,
        // mCircleTextColor);

        textPaint.getTextBounds(text, 0, text.length(), bounds);

        if (show_text)
            canvas.drawText(
                    text,
                    (mColorWheelRectangle.centerX())
                            - (textPaint.measureText(text) / 2),
                    mColorWheelRectangle.centerY() + bounds.height() / 2,
                    textPaint);


        // last_radians = calculateRadiansFromAngle(mAngle);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = getDefaultSize(getSuggestedMinimumHeight(),
                heightMeasureSpec);
        int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int min = Math.min(width, height);
        setMeasuredDimension(min, min);

        mTranslationOffset = min * 0.5f;
        mColorWheelRadius = mTranslationOffset - mPointerRadius;

        mColorWheelRectangle.set(-mColorWheelRadius, -mColorWheelRadius,
                mColorWheelRadius, mColorWheelRadius);

        mColorCenterHaloRectangle.set(-mColorWheelRadius / 2,
                -mColorWheelRadius / 2, mColorWheelRadius / 2,
                mColorWheelRadius / 2);
    }

    /**
     * Get the selected value
     *
     * @return the value between 0 and max
     */
    public int getValue() {
        return Integer.valueOf(text);
        // return conversion;
    }

    public void setValue(String newValue) {
        setText(newValue);
    }

    public void setStateAlarm(boolean state){
        if(state){
            mArcColor.setColor(alarm_active_color);
            textPaint.setColor(alarm_active_color);
        }else{
            mArcColor.setColor(alarm_unactive_color);
            textPaint.setColor(alarm_unactive_color);
        }
    }
    public void setTextSize(int size){
        textPaint.setTextSize(size);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        // Fix scrolling
        if (event.getAction() == MotionEvent.ACTION_MOVE && getParent() != null) {
            //getParent().requestDisallowInterceptTouchEvent(true);
        }
        return true;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        Bundle state = new Bundle();
        state.putParcelable(STATE_PARENT, superState);
        state.putFloat(STATE_ANGLE, mAngle);

        return state;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle savedState = (Bundle) state;

        Parcelable superState = savedState.getParcelable(STATE_PARENT);
        super.onRestoreInstanceState(superState);
    }
    public int getMaxValue() {
        return max;
    }

}