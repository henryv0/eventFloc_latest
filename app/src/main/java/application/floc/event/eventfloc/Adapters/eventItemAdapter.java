package application.floc.event.eventfloc.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import application.floc.event.eventfloc.Events.Event;
import application.floc.event.eventfloc.R;

/**
 * Created by Vanessa on 11/05/2015.
 */
public class eventItemAdapter extends BaseAdapter{
    private static ArrayList<Event> eventDetails;
    private LayoutInflater mInflater;
    private static final SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy"); //dd-MM-yyyy
    private static final SimpleDateFormat parseTime = new SimpleDateFormat("hh:mm a");//hh:mm a

    public eventItemAdapter(Context context, ArrayList<Event> results) {
        eventDetails = results;
        Log.d("fIRST ITEM", "" + eventDetails.get(0));
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return eventDetails.size();

    }

    @Override
    public Object getItem(int i) {
        return eventDetails.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;


        if(convertView == null){
            ;
            convertView =  mInflater.inflate(R.layout.event_list_item, null);

            holder = new ViewHolder();

            holder.eventName = (TextView)convertView.findViewById(R.id.textViewEventName);

            holder.eventLocation = (TextView)convertView.findViewById(R.id.textViewEventLocation);

            holder.eventDate = (TextView)convertView.findViewById(R.id.textViewEventDate);



            convertView.setTag(holder);

        } else{
            holder = (ViewHolder)convertView.getTag();

        }

        holder.eventName.setText(eventDetails.get(position).getEventName());

        holder.eventLocation.setText(eventDetails.get(position).getEventLocation());

        Date temp = new Date();
        temp = eventDetails.get(position).getEventDate();

        holder.eventDate.setText(parser.format(temp));


        //Return the current view

        return convertView;
    }


    static class ViewHolder{
        TextView eventName;
        TextView eventLocation;
        TextView eventDate;

    }

}
