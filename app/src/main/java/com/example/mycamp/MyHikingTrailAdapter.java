package com.example.mycamp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.DrawableRes;
import androidx.recyclerview.widget.RecyclerView;

public class MyHikingTrailAdapter extends RecyclerView.Adapter<MyHikingTrailAdapter.MyViewHolder> {
    public HikingTrail[] m_dataSet;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View m_v;
        public MyViewHolder(View v) {
            super(v);
            m_v= v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyHikingTrailAdapter(HikingTrail[] dataSet) {
        m_dataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyHikingTrailAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_layout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Context c = holder.m_v.getContext();

        ((TextView)holder.m_v.findViewById(R.id.text1)).setText(m_dataSet[position].GetName());
        //((Button)holder.m_v.findViewById(R.id.button1)).setText(m_dataSet[position].GetAddress());
        ((ImageView)holder.m_v.findViewById(R.id.imageView1)).setImageResource(m_dataSet[position].GetImageId());
        ((TextView)holder.m_v.findViewById(R.id.text2)).setText(((Integer)m_dataSet[position].number).toString());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return m_dataSet.length;
    }
}
