package com.example.moreminx.proyectobote;

import android.content.Context;
import android.opengl.EGLExt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;

import java.util.Collections;

public class CheckedLinearLayout extends LinearLayout implements Checkable {

    private boolean mChecked= false;

    private final int[] CHECKED_STATE_SET ={

            android.R.attr.state_checked
    };

    public CheckedLinearLayout(Context context) {
        super(context);
    }

    public CheckedLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void setChecked(boolean b) {
        mChecked = b;
        refreshDrawableState();
        invalidate();
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
setChecked(!mChecked);
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }
}
