package application.floc.event.eventfloc.Activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import application.floc.event.eventfloc.DatabaseQueries;
import application.floc.event.eventfloc.Events.Event;
import application.floc.event.eventfloc.MainActivity;
import application.floc.event.eventfloc.R;
import application.floc.event.eventfloc.Users.Society;


public class AddActivity extends ActionBarActivity {

    EditText eventName;
    EditText eventLocation;
    EditText eventDescription;
    EditText eventDate;
    EditText eventLink;
    EditText eventStartTime;
    EditText eventEndTime;

    Spinner staticSpinner;



    Button saveButton;
    Button backButton;
    Date mEventDate;
    Date mEventStart;
    Date mEventEnd;
    int mEventType;
    DatabaseQueries dq;
    private int currentUser = 0;
    private int currentUserStore = 0;



    String eName;
    String eLocation;
    String eDate;
    String eDescription;
    String eStartTime;


    //EEE MMM dd HH:mm:ss zzz yyyy
    private static final SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy"); //dd-MM-yyyy
    private static final SimpleDateFormat parseTime = new SimpleDateFormat("hh:mm a");//hh:mm a
    private static final SimpleDateFormat inputParse = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Intent i = getIntent();
        currentUser = Integer.parseInt(i.getStringExtra(String.valueOf(R.string.currentUser)));
        currentUserStore = currentUser;
        Log.d("current user 1: ", currentUser + " ");

        Toolbar mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //sets the variables to the actual text fields in XML
        setTextField();

        //create new database queries object
        dq = new DatabaseQueries(AddActivity.this);

        eName = eventName.getText().toString();
        eLocation = eventLocation.getText().toString();
        eDate = eventDate.getText().toString();
        eDescription = eventDescription.getText().toString();
        eStartTime = eventStartTime.getText().toString();


        String[] items = new String[]{"Careers", "Faculty", "Interests", "Cultural", "Food"
                , "Free Food", "Sports", "Fundraisers", "Learning", "Festivals", "Performing Arts"
                , "Political"};

        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this , R.array.categories, android.R.layout.simple_spinner_item);

        staticSpinner.setAdapter(staticAdapter);

        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch(position){
                    case 0:
                        mEventType = 0;
                    case 1:
                        mEventType = 1;
                    case 2:
                        mEventType = 2;
                    case 3:
                        mEventType = 3;
                    case 4:
                        mEventType = 4;
                    case 5:
                        mEventType = 5;
                    case 6:
                        mEventType = 6;
                    case 7:
                        mEventType = 7;
                    case 8:
                        mEventType = 8;
                    case 9:
                        mEventType = 9;
                    case 10:
                        mEventType = 10;
                    case 11:
                        mEventType = 11;



                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start the main activity after clicking back button
                Intent i = new Intent(AddActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


        eventDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddActivity.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();


            }
        });

        eventStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(AddActivity.this, dateStartTime,
                        myStartTimeCalendar.get(Calendar.HOUR), myStartTimeCalendar.get(Calendar.MINUTE),
                        false).show();
            }
        });

        eventEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(AddActivity.this, dateEndTime, myEndTimeCalendar.get(Calendar.HOUR),
                        myEndTimeCalendar.get(Calendar.MINUTE), false).show();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("NAME: ", eventName.getText().toString());
                Log.d("Location: ", eLocation + "");
                Log.d("date: ", eventDate.getText().toString());
                Log.d("Description: ", eventDescription.getText().toString());
                Log.d("Start Time: ", eventStartTime.getText().toString());

                //Checks if any fields are empty
                if (!eventName.getText().toString().trim().equals("")
                        || !eventLocation.getText().toString().trim().equals("")
                        || !eventDate.getText().toString().trim().equals("")
                        || !eventDescription.getText().toString().trim().equals("")
                        || !eventStartTime.getText().toString().trim().equals("")) {
                    Event event = null;

                    try {
                        event = fillEvent();
                        Log.d("ADDING EVENT", event.toString());
                        dq.insertEvent(event, mEventType);

                        Toast.makeText(AddActivity.this, R.string.successful_event_add, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(AddActivity.this, MainActivity.class);
                        startActivity(i);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                } else {
                    //Not all necessary fields completed, show toast
                    Toast.makeText(AddActivity.this, R.string.empty_fields, Toast.LENGTH_LONG).show();
                }


            }
        });


    }

    /**
     * Date picker for event date
     */
    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            updateEventDate();
        }

    };

    /**
     * Time picker dialog popup for start time
     */
    Calendar myStartTimeCalendar = Calendar.getInstance();
    TimePickerDialog.OnTimeSetListener dateStartTime = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myStartTimeCalendar.set(Calendar.HOUR, hourOfDay);
            myStartTimeCalendar.set(Calendar.MINUTE, minute);
            updateEventStartTime();
        }
    };


    /**
     * Time picker dialog popup for end time
     */
    Calendar myEndTimeCalendar = Calendar.getInstance();
    TimePickerDialog.OnTimeSetListener dateEndTime = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myEndTimeCalendar.set(Calendar.HOUR, hourOfDay);
            myEndTimeCalendar.set(Calendar.MINUTE, minute);
            updateEventEndTime();
        }
    };

    /**
     * Method to set the Text field of EventDate after dialog popup
     */
    private void updateEventDate() {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        mEventDate = myCalendar.getTime();
        eventDate.setText(sdf.format(myCalendar.getTime()));
    }

    /**
     * Method to set the text field of start time after dialog popup
     */
    private void updateEventStartTime() {
        SimpleDateFormat parseTime = new SimpleDateFormat("hh:mm a");
        mEventStart = myStartTimeCalendar.getTime();
        eventStartTime.setText(parseTime.format(myStartTimeCalendar.getTime()));
    }

    /**
     * Method to set the text field of end time after dialog pop up
     */
    private void updateEventEndTime() {
        SimpleDateFormat parseTime = new SimpleDateFormat("hh:mm a");
        mEventEnd = myEndTimeCalendar.getTime();
        eventEndTime.setText(parseTime.format(myEndTimeCalendar.getTime()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
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

        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }

    public void setTextField() {
        eventName = (EditText) findViewById(R.id.editEventName);
        eventLocation = (EditText) findViewById(R.id.editEventLocation);
        eventDescription = (EditText) findViewById(R.id.editEventDesription);
        eventLink = (EditText) findViewById(R.id.editEventLink);
        eventDate = (EditText) findViewById(R.id.editEventDate);
        eventStartTime = (EditText) findViewById(R.id.editEventStartTime);
        eventEndTime = (EditText) findViewById(R.id.editEventEndTime);
        saveButton = (Button) findViewById(R.id.saveEventButton);
        backButton = (Button) findViewById(R.id.backEventButton);
        staticSpinner = (Spinner)findViewById(R.id.static_spinner);
    }


    /**
     * Fill an event object with entered details
     *
     * @return event object
     * @throws ParseException
     */
    public Event fillEvent() throws ParseException {
        Event e = new Event();

        e.setEventName(eventName.getText().toString());
        e.setEventLocation(eventLocation.getText().toString());
        e.setEventDescription(eventDescription.getText().toString());
        e.setEventLink(eventLink.getText().toString());

        Date date = inputParse.parse(String.valueOf(mEventDate));

        e.setEventDate(date);
        Log.d("FILLING EVENT DATE ", parser.format(date));

        //set the society ID for the event
        if(currentUser != 0) {
            Society temp = dq.getSocietyUserId(currentUser);
            e.setSocietyID(temp.getSocietyID());
            Log.d("SETTING SOCIETY ID ", "" + temp.getSocietyID());
        } else {
            Society temp = dq.getSocietyUserId(currentUserStore);
            e.setSocietyID(temp.getSocietyID());
            Log.d("Setting SOCID 2nd: ", "" + temp.getSocietyID());
        }

        Date startTime = inputParse.parse(String.valueOf(mEventStart));
        Log.d("FILLING EVENT START ", startTime.toString());
        e.setEventStartTime(startTime);

        //if there is an end event time set, then parse the date
        if(mEventEnd != null){
            Date endTime = inputParse.parse(String.valueOf(mEventEnd));
            Log.d("FILLING EVENT ", endTime.toString());
            e.setEventEndTime(endTime);
        }
        return e;
    }


}