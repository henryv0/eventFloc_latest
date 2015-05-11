package application.floc.event.eventfloc.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import application.floc.event.eventfloc.DatabaseQueries;
import application.floc.event.eventfloc.Events.Event;
import application.floc.event.eventfloc.R;

/**
 * Created by Vanessa on 12/05/2015.
 */

public class EventItemActivity extends ActionBarActivity {
    private TextView eventName;
    private TextView eventTime;
    private  TextView eventDate;
    private TextView eventLocation;
    private  TextView eventDesc;
    int eventId;
    public static final String EXTRA_EVENT_ID = "event_id";

    private Button attendButton;
    private Button maybeButton;

    private static final SimpleDateFormat parseTime = new SimpleDateFormat("hh:mm a");
    private static final SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");

    DatabaseQueries dq = new DatabaseQueries(EventItemActivity.this);


    public EventItemActivity() throws ParseException {
        Event e = new Event();
        Intent i = getIntent();
        if(i != null){
            String s = i.getStringExtra(EXTRA_EVENT_ID);
            Log.d("What", s);
            eventId = Integer.parseInt(i.getStringExtra(String.valueOf(R.string.event_id)));
        }


        e = dq.getEvent(eventId);


        eventName.setText(e.getEventName());
        eventTime.setText(parseTime.format(e.getEventStartTime()));
        eventDate.setText(parser.format(e.getEventDate()));
        eventLocation.setText(e.getEventLocation());
        eventDesc.setText(e.getEventDescription());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_event_description);
        //set the IDs
        setId();

        attendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EventItemActivity.this, "You are now Attending this event", Toast.LENGTH_LONG);
            }
        });


    }



    public void setId(){
        eventName = (TextView)findViewById(R.id.event_name);
        eventTime = (TextView)findViewById(R.id.event_time);
        eventLocation = (TextView)findViewById(R.id.event_location);
        eventDate = (TextView)findViewById(R.id.event_date);
        eventDesc = (TextView)findViewById(R.id.event_description);
        attendButton = (Button)findViewById(R.id.attend_button);
        maybeButton = (Button)findViewById(R.id.maybe_button);
    }

}
