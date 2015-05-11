package application.floc.event.eventfloc.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import application.floc.event.eventfloc.Activities.LoginActivity;
import application.floc.event.eventfloc.Activities.MyEventsActivity;
import application.floc.event.eventfloc.Activities.SettingsActivity;
import application.floc.event.eventfloc.MainActivity;
import application.floc.event.eventfloc.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment implements InfoAdapter.ClickListener {

    private RecyclerView recyclerView;
    private static final String PREF_FILE_NAME = "testpref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private InfoAdapter adapter;

    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private View containerView;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if(savedInstanceState != null) {
            mFromSavedInstanceState = true;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);

        adapter = new InfoAdapter(getActivity(), getData());
        adapter.setClickListener(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return layout;
    }

    public static List<Information> getData() {
        List<Information> data = new ArrayList<>();
        int[] icons = {R.drawable.ic_nav_home,R.drawable.ic_nav_events,R.drawable.ic_nav_settings,R.drawable.ic_nav_logout};
        String[] titles = {"Home", "My Events", "Settings", "Log out"};
        for(int i=0; i<titles.length && i<icons.length; i++) {
            Information info = new Information();
            info.iconId = icons[i];
            info.title = titles[i];
            data.add(info);
        }
        return data;
    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                if(!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer+"");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                getActivity().invalidateOptionsMenu();
            }
        };

        if(!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(containerView);
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName,defaultValue);
    }

    @Override
    public void itemClicked(View view, int position) {
        //startActivity(new Intent(getActivity(), LoginActivity.class));

        Intent intent = null;
        switch(position) {
            case 0:
                intent = new Intent(getActivity(), MainActivity.class);
                break;
            case 1:
                intent = new Intent(getActivity(), MyEventsActivity.class);
                break;
            case 2:
                intent = new Intent(getActivity(), SettingsActivity.class);
                break;
            case 3:
                intent = new Intent(getActivity(), LoginActivity.class);
                break;
            default :
                intent = new Intent(getActivity(), MainActivity.class); // Activity_0 as default
                break;
        }

        startActivity(intent);

    }
}
