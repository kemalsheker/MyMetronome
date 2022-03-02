package com.blackstart.mymetronome;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import static java.util.Map.entry;

public class RudimentListFragment extends Fragment {

    private static final String TAG = "RudimentListFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";

    protected RecyclerView mRecyclerView;
    protected RudimentListAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected static LinkedHashMap<String, Integer> mRudimentsList;


    public RudimentListFragment() {
        super(R.layout.rudiments_list);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        initDataset();
    }



    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.rudiments_list, container, false);
        rootView.setTag(TAG);

        mRecyclerView = rootView.findViewById(R.id.recyclerRudimentView);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RudimentListAdapter(mRudimentsList);

        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    private void initDataset() {

        mRudimentsList=new LinkedHashMap<String, Integer>();

        mRudimentsList.put("Single Stroke Roll", R.drawable.singlestrokeroll);
        mRudimentsList.put("Single Stroke Four", R.drawable.singlestrokefour);
        mRudimentsList.put("Single Stroke Seven", R.drawable.singlestrokeseven);
        mRudimentsList.put("Multiple Bounce Roll", R.drawable.multiplebounceroll);
        mRudimentsList.put("Double Stroke Roll", R.drawable.doublestrokeroll);
        mRudimentsList.put("Triple Stroke Roll", R.drawable.triplestrokeroll);
        mRudimentsList.put("Five Stroke Roll", R.drawable.fivestrokeroll);

    }

}
