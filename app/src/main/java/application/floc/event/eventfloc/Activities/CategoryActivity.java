package application.floc.event.eventfloc.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
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
public class CategoryActivity extends ActionBarActivity {
    DatabaseQueries dq = new DatabaseQueries(this);
    private ArrayList<Event> eventDetails;
    public String typeSearchTerm;
    private ListView lv1;
    private int currentUser;
    public static final String EXTRA_EVENT_ID = "event_id";




    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


       // Intent i = getIntent();
       //typeSearchTerm = i.getStringExtra(String.valueOf(R.string.event_type_extra));
       // Log.d("Search Term", typeSearchTerm);


        //TRY TO FILL THE ARRAYLIST WITH ALL EVENTS
        DatabaseQueries dq = new DatabaseQueries(this);
        try {
            //eventDetails = dq.getAllEventsCategory(typeSearchTerm);
            eventDetails = dq.getAllEvents();
            Log.d("ARRAY SIZE", eventDetails.size() + "");

        } catch (ParseException e) {
            e.printStackTrace();
        }


        lv1 = (ListView)findViewById(R.id.categoryListView);

        lv1.setAdapter(new eventItemAdapter(this, eventDetails));
        Log.d("debug", 20 + "");

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                Event e = (Event) o;
                Intent i = new Intent(CategoryActivity.this, EventItemActivity.class);
                Toast.makeText(CategoryActivity.this, "you have clicked " + e.getEventName(), Toast.LENGTH_LONG);
                Log.d("EVENT HAS", e.getEventName());
                Log.d(" EVENT ID", String.valueOf(e.getEventID()));
                i.putExtra(EXTRA_EVENT_ID, e.getEventID() + "");

                startActivity(i);


            }
        });


    }





}
