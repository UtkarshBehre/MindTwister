package com.mindtwister.mindtwister.sudoku.sudokugrid;

import android.content.Context;
import android.view.View;

/**
 * Created by SomyaMittal on 7/17/2016.
 */
public class BaseSudokuCell extends View {
    private int value;
    private boolean modifiable = true;

    public BaseSudokuCell(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    public void setNotModifiable() {
        modifiable = false;
    }

    public void setInitValue(int value) {
        this.value = value;
        invalidate();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (modifiable) {
            this.value = value;
        }

        invalidate();
    }

}
