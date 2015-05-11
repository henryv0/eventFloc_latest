package application.floc.event.eventfloc.Fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import application.floc.event.eventfloc.Fragments.MyEvents.FeedFragment;

/**
 * Created by henryvo on 11/05/15.
 */
public class TabFragmentPagerAdapterMy extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[]{"Feed", "Coming", "Maybe", "Past"};
    private Context context;
    private Fragment[] mFragments = {
            new FeedFragment(),
            new SocietiesFragment(),
            new EndingFragment(),
            new NewestFragment()
    };

    public TabFragmentPagerAdapterMy(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        //return PageFragment.newInstance(position + 1);

        switch (position) {
            case 0:
                FeedFragment fragment1 = new FeedFragment();
                return fragment1;
            case 1:
                SocietiesFragment fragment2 = new SocietiesFragment();
                return fragment2;
            case 2:
                EndingFragment fragment3 = new EndingFragment();
                return fragment3;
            case 3:
                NewestFragment fragment4 = new NewestFragment();
                return fragment4;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}