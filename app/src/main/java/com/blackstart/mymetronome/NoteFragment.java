package com.blackstart.mymetronome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class NoteFragment extends Fragment {

    private static final String TAG = "NoteFragment";

    protected ImageView noteImage;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected ConstraintLayout layout;
    private String noteName;
    private Integer noteImageInt;

    public NoteFragment(){
        super(R.layout.note_layout);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();

        if(bundle != null){
            noteName = bundle.getString("key");
            noteImageInt = bundle.getInt("value");
            ((MainActivity) Objects.requireNonNull(getActivity())).updateToolbar(noteName);
        }

    }

    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.note_layout, container, false);
        rootView.setTag(TAG);

        noteImage = rootView.findViewById(R.id.noteImageView);
        noteImage.setImageResource(noteImageInt);


        return rootView;
    }




}
