package com.blackstart.mymetronome;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.LinkedHashMap;
import java.util.Map;

public class RudimentListAdapter extends RecyclerView.Adapter<RudimentListAdapter.ViewHolder> {
    private static final String TAG = "RudimentListAdapter";

    private final LinkedHashMap<String, Integer> mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;


        public ViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textViewRudimentName);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public RudimentListAdapter(LinkedHashMap<String,Integer> dataSet) {
        mDataSet = dataSet;
    }



    @Override
    public RudimentListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rudiment_item, parent, false);

        return new ViewHolder(v);
    }


    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder( RudimentListAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: position" + position);


        holder.getTextView().setText(mDataSet.keySet().toArray()[position].toString());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
