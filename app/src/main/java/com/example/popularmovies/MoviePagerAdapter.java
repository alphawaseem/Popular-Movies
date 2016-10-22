package com.example.popularmovies;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


class MoviePagerAdapter extends FragmentStatePagerAdapter {

    private Context context;

    MoviePagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return PopularFragment.newInstance();
        else if (position == 1)
            return TopRatedFragment.newInstance();
        return FavFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        switch (position) {
            case 0:
                title = "Popular";
                break;
            case 1:
                title = "Top Rated";
                break;
            case 2:
                title = "My Favourite";
        }
        return title;
    }
}
