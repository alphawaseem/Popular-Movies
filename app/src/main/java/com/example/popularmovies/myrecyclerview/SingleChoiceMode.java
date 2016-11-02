package com.example.popularmovies.myrecyclerview;

/**
 * Created by waseem on 10/26/16.
 */

public class SingleChoiceMode implements ChoiceMode {
    private static final String STATE_CHECKED = "checkedPosition";
    private int checkedPosition = -1;

    @Override
    public boolean isSingleChoice() {
        return (true);
    }

    @Override
    public int getCheckedPosition() {
        return (checkedPosition);
    }

    @Override
    public void setChecked(int position, boolean isChecked) {
        if (isChecked) {
            checkedPosition = position;
        } else if (isChecked(position)) {
            checkedPosition = -1;
        }
    }

    @Override
    public boolean isChecked(int position) {
        return (checkedPosition == position);
    }
}
