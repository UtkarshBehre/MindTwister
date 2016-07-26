package com.mindtwister.mindtwister.sudoku.buttongrid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.mindtwister.mindtwister.sudoku.GameEngine;

/**
 * Created by SomyaMittal on 7/18/2016.
 */
public class NumberButton extends Button implements View.OnClickListener {
    private int number;

    public NumberButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        GameEngine.getInstance().setNumber(number);

    }

    public void setNumber(int number) {
        this.number = number;

    }
}
