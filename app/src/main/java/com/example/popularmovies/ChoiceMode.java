package com.example.popularmovies;

/**
 * Created by waseem on 10/26/16.
 */

import android.os.Bundle;

public interface ChoiceMode {
    boolean isSingleChoice();

    int getCheckedPosition();

    void setChecked(int position, boolean isChecked);

    boolean isChecked(int position);

    void onSaveInstanceState(Bundle state);

    void onRestoreInstanceState(Bundle state);
}