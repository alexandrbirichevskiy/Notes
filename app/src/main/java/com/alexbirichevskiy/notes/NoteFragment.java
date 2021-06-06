package com.alexbirichevskiy.notes;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NoteFragment extends Fragment{

    private static final String ARG_INDEX = "index";
    private Parcelable note;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = getArguments().getParcelable(ARG_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        fillingNote(view, note);
        super.onViewCreated(view, savedInstanceState);
    }

    public void fillingNote(View view, Parcelable note){
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewDescription = view.findViewById(R.id.textViewDescription);
        TextView textViewDate = view.findViewById(R.id.textViewDate);
        textViewName.setText(((Notes) note).getName());
        textViewDescription.setText(((Notes) note).getDescription());
        textViewDate.setText(String.valueOf(((Notes) note).getDate()));
    }
}