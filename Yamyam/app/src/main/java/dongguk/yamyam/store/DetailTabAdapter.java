package dongguk.yamyam.store;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import dongguk.yamyam.app.AppController;
import dongguk.yamyam.fragment.DetailFragment;
import dongguk.yamyam.fragment.DetailGpsFragment;
import dongguk.yamyam.fragment.ReviewFragment;

public class DetailTabAdapter extends FragmentStatePagerAdapter {

    // Count number of tabs
    private int tabCount;
    AppController appController;
    Context context;

    public DetailTabAdapter(FragmentManager fm, int tabCount, Context context) {
        super(fm);
        this.tabCount = tabCount;
        this.context = context;
        appController = (AppController)context;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                DetailFragment tabFragment1 = new DetailFragment();
                return tabFragment1;
            case 1:
                ReviewFragment tabFragment2 = new ReviewFragment();
                return tabFragment2;
            case 2:
                DetailGpsFragment tabFragment3 = new DetailGpsFragment();
                return tabFragment3;
            default:
                return null;
        }
    }

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