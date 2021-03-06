package com.mindtwister.mindtwister.sudoku.sudokugrid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.mindtwister.mindtwister.sudoku.sudokugrid.BaseSudokuCell;

/**
 * Created by SomyaMittal on 7/17/2016.
 */
public class SudokuCell extends BaseSudokuCell {
    private Paint nPaint;

    public SudokuCell(Context context) {
        super(context);
        nPaint = new Paint();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawNumber(canvas);
        drawLines(canvas);
    }


    private void drawNumber(Canvas canvas) {
        nPaint.setColor(Color.BLUE);
        nPaint.setTextSize(40);
        nPaint.setStyle(Paint.Style.FILL);
        Rect bounds = new Rect();
        nPaint.getTextBounds(String.valueOf(getValue()), 0, String.valueOf(getValue()).length(), bounds);

        if (getValue() != 0) {
            canvas.drawText(String.valueOf(getValue()), (getWidth() - bounds.width()) / 2, (getHeight() + bounds.height()) / 2, nPaint);
        }
    }

    private void drawLines(Canvas canvas) {
        nPaint.setColor(Color.BLACK);
        nPaint.setStrokeWidth(3);
        nPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), nPaint);
    }

}
