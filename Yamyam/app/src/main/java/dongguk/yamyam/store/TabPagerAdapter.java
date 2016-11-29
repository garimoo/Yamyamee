package dongguk.yamyam.store;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import dongguk.yamyam.fragment.GpsFragment;
import dongguk.yamyam.fragment.LikeFragment;
import dongguk.yamyam.fragment.MainFragment;
import dongguk.yamyam.fragment.SearchFragment;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    // Count number of tabs
    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                MainFragment tabFragment1 = new MainFragment();
                return tabFragment1;
            case 1:
                SearchFragment tabFragment2 = new SearchFragment();
                return tabFragment2;
            case 2:
                GpsFragment tabFragment3 = new GpsFragment();
                return tabFragment3;
            case 3:
                LikeFragment tabFragment4=new LikeFragment();
                return tabFragment4;
            default:
                return null;
        }
    }
    private static int NUM_OF_VIEWS = 4;
    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public int getItemPosition(Object object) {
        // POSITION_NONE makes it possible to reload the PagerAdapter
        return POSITION_NONE;
    }

}