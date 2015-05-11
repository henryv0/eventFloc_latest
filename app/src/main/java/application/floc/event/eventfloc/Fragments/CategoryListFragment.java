package application.floc.event.eventfloc.Fragments;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;

import application.floc.event.eventfloc.Adapters.eventItemAdapter;
import application.floc.event.eventfloc.DatabaseQueries;
import application.floc.event.eventfloc.Events.Event;
import application.floc.event.eventfloc.R;

/**
 * Created by Vanessa on 11/05/2015.
 */
public class CategoryListFragment extends ActionBarActivity {
    DatabaseQueries dq = new DatabaseQueries(this);
    private ArrayList<Event> eventDetails;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home_explore_category);





        //TRY TO FILL THE ARRAYLIST WITH ALL EVENTS
        try {
            //fil the arraylist with all events query from database
            eventDetails = (ArrayList<Event>) dq.getAllEvents();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final ListView lv1 = (ListView)findViewById(R.id.listViewCategory);
        lv1.setAdapter(new eventItemAdapter(this, eventDetails));

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                Event objEvent = (Event)o;
                Toast.makeText(CategoryListFragment.this, "You have chosen : " + " " + objEvent.getEventName(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}
