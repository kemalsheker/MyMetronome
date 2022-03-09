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
        mRudimentsList.put("Six Stroke Roll", R.drawable.sixstrokeroll);
        mRudimentsList.put("Seven Stroke Roll", R.drawable.sevenstrokeroll);
        mRudimentsList.put("Nine Stroke Roll", R.drawable.ninestrokeroll);
        mRudimentsList.put("Ten Stroke Roll", R.drawable.tenstrokeroll);
        mRudimentsList.put("Eleven Stroke Roll", R.drawable.elevenstrokeroll);
        mRudimentsList.put("Thirteen Stroke Roll", R.drawable.thirteenstrokeroll);
        mRudimentsList.put("Fifteen Stroke Roll", R.drawable.fifteenstrokeroll);
        mRudimentsList.put("Seventeen Stroke Roll", R.drawable.seventeenstrokeroll);
        mRudimentsList.put("Single Paradiddle", R.drawable.singleparadiddle);
        mRudimentsList.put("Double Paradiddle", R.drawable.doubleparadiddle);
        mRudimentsList.put("Triple Paradiddle", R.drawable.tripleparadiddle);
        mRudimentsList.put("Single Paradiddle-Diddle", R.drawable.singleparadiddlediddle);
        mRudimentsList.put("Flam", R.drawable.flam);
        mRudimentsList.put("Flam Accent", R.drawable.flamaccent);
        mRudimentsList.put("Flam Tap", R.drawable.flamtap);
        mRudimentsList.put("Flamacue", R.drawable.flamacue);
        mRudimentsList.put("Flam Paradiddle", R.drawable.flamparadiddle);
        mRudimentsList.put("Pataflafla", R.drawable.pataflafla);
        mRudimentsList.put("Single Flammed Mill", R.drawable.singleflammedmill);
        mRudimentsList.put("Flam Paradiddle-Diddle", R.drawable.flamparadiddlediddle);
        mRudimentsList.put("Swiss Army Triplet", R.drawable.swissarmytriplet);
        mRudimentsList.put("Inverted Flam Tap", R.drawable.invertedflamtap);
        mRudimentsList.put("Flam Drag", R.drawable.flamdrag);
        mRudimentsList.put("Drag Ruff", R.drawable.dragruff);
        mRudimentsList.put("Single Drag Tap", R.drawable.dragruff);
        mRudimentsList.put("Double Drag Tap", R.drawable.doubledragtap);
        mRudimentsList.put("Lesson 25", R.drawable.lesson25);
        mRudimentsList.put("Single Dragadiddle", R.drawable.singledragadiddle);
        mRudimentsList.put("Dragadiddle 1", R.drawable.dragadiddleone);
        mRudimentsList.put("Dragadiddle 2", R.drawable.dragadiddletwo);
        mRudimentsList.put("Single Ratamacue", R.drawable.singleratamacue);
        mRudimentsList.put("Double Ratamacue", R.drawable.doubleratamacue);
        mRudimentsList.put("Triple Ratamacue", R.drawable.tripleratamacue);


    }


}
