package application.floc.event.eventfloc.Fragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import application.floc.event.eventfloc.R;

/**
 * Created by henryvo on 9/05/15.
 */
public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<Information> data = Collections.emptyList();
    private Context context;
    private ClickListener clickListener;

    public InfoAdapter(Context context, List<Information> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information tmp = data.get(position);

        Log.d("Info", "onBindViewHolder called "+position);

        holder.title.setText(tmp.title);
        holder.icon.setImageResource(tmp.iconId);

        /* holder.icon.setOnClickListener(new View.OnClickListener() {


            //NOTE: we want to change item click.
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Item clicked!"+position, Toast.LENGTH_SHORT).show();
            }
        });
        */

    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;

    }

    @Override
    public int getItemCount() {
        int count = data.size();
        return count;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);
        }

        @Override
        public void onClick(View v) {
            if(clickListener!=null) {
                clickListener.itemClicked(v, getAdapterPosition());
                //getPosition is deprecated
            }
        }
    }

    public interface ClickListener {
        public void itemClicked(View view, int position);
    }
}
