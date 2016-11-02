package com.example.popularmovies.myrecyclerview;

/**
 * Created by waseem on 10/26/16.
 */

public interface ChoiceMode {
    boolean isSingleChoice();

    int getCheckedPosition();

    void setChecked(int position, boolean isChecked);

    boolean isChecked(int position);

}