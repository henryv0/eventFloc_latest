package application.floc.event.eventfloc.Fragments.MyEvents;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import application.floc.event.eventfloc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends android.support.v4.app.Fragment {


    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }


}
