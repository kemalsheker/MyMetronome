package com.blackstart.mymetronome;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.LinkedHashMap;
import java.util.Map;

public class RudimentListAdapter extends RecyclerView.Adapter<RudimentListAdapter.ViewHolder> {
    private static final String TAG = "RudimentListAdapter";

    private static LinkedHashMap<String, Integer> mDataSet = null;


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView textView;
        private final CardView rudimentItem;
        private final ImageButton openRudimentButton;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textViewRudimentName);
            rudimentItem = itemView.findViewById(R.id.rudimentItem);
            openRudimentButton = itemView.findViewById(R.id.openRudimentButton);

            rudimentItem.setOnClickListener(this);
            openRudimentButton.setOnClickListener(this);

        }

        public TextView getTextView() {
            return textView;
        }

        public CardView getCardView() {
            return rudimentItem;
        }

        public ImageButton getImageView() {
            return openRudimentButton;
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: starts, Clicking element: " + getAdapterPosition());

            Bundle bundle = new Bundle();
            String key =  mDataSet.keySet().toArray()[getAdapterPosition()].toString();
            bundle.putString("key", key);
            bundle.putInt("value", mDataSet.get(key));

            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            NoteFragment myFragment = new NoteFragment();
            myFragment.setArguments(bundle);
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, myFragment).addToBackStack(null).commit();

        }



    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public RudimentListAdapter(LinkedHashMap<String, Integer> dataSet) {
        mDataSet = dataSet;
    }


    @NotNull
    @Override
    public RudimentListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rudiment_item, parent, false);
        

        return new ViewHolder(v);
    }


    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RudimentListAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: position" + position);


        holder.getTextView().setText(mDataSet.keySet().toArray()[position].toString());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }



}
