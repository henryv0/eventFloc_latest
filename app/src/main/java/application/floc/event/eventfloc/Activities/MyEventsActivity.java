package application.floc.event.eventfloc.Activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import application.floc.event.eventfloc.Fragments.NavigationDrawerFragment;
import application.floc.event.eventfloc.Fragments.TabFragmentPagerAdapterMy;
import application.floc.event.eventfloc.R;
import application.floc.event.eventfloc.Tabs.SlidingTabLayout;

public class MyEventsActivity extends ActionBarActivity {

    private Toolbar mToolbar;
    private ViewPager mPager;
    private SlidingTabLayout mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_events);

        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);


        drawerFragment.setUp(R.id.fragment_navigation_drawer,(DrawerLayout)findViewById(R.id.drawer_layout), mToolbar);

        // mPager = (ViewPager) findViewById(R.id.pager);
        // mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        // mTabs = (SlidingTabLayout) findViewById(R.id.tabs);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new TabFragmentPagerAdapterMy(getSupportFragmentManager(),
                MyEventsActivity.this));

        // Give the SlidingTabLayout the ViewPager
        SlidingTabLayout mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        // Center the tabs in the layout
        mTabs.setDistributeEvenly(true);

        //mTabs.setCustomTabView(R.layout.custom_tab_view, R.id.tabText);
        //mTabs.setDistributeEvenly(true);
        mTabs.setBackgroundColor(getResources().getColor(R.color.primaryColor));
        mTabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {

            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.icons);
            }
        });

        mTabs.setViewPager(viewPager);

        // mTabs.setViewPager(mPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_events, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
