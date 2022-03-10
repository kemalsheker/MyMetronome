package com.blackstart.mymetronome;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.github.chrisbanes.photoview.PhotoView;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;

public class NoteFragment extends Fragment {

    private static final String TAG = "NoteFragment";

    protected ImageView noteImage;
    protected ImageView zoomedNoteImage;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected ConstraintLayout layout;
    private String noteName;
    private Integer noteImageInt;
    private Context context;
    private PhotoView photoView;
    private ConstraintLayout draweeViewLayout;
    private ImageButton cancelButton;


    public NoteFragment() {
        super(R.layout.note_layout);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            noteName = bundle.getString("key");
            noteImageInt = bundle.getInt("value");
            ((MainActivity) Objects.requireNonNull(getActivity())).updateToolbar(noteName);
        }

    }

    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.note_layout, container, false);
        rootView.setTag(TAG);

        layout = rootView.findViewById(R.id.noteImageLayout);
        photoView = getActivity().findViewById(R.id.draweeView);
        draweeViewLayout = getActivity().findViewById(R.id.draweeViewLayout);
        cancelButton = getActivity().findViewById(R.id.cancelButton);
        noteImage = rootView.findViewById(R.id.noteImageView);
        noteImage.setImageResource(noteImageInt);


        noteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: noteImage");
                photoView.setImageResource(noteImageInt);
                draweeViewLayout.setVisibility(View.VISIBLE);


            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: cancelButton");

                draweeViewLayout.setVisibility(View.INVISIBLE);
            }
        });


        return rootView;
    }


}
