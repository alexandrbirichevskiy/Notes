package com.alexbirichevskiy.notes;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NotesListFragment extends Fragment{
    private static final String ARG_INDEX = "index";
    private Intent intent = new Intent();
    private Notes[] notes = {
            new Notes("Note 1", "description for note 1", System.nanoTime()),
            new Notes("Note 2", "description for note 2", System.nanoTime()),
            new Notes("Note 3", "description for note 3", System.nanoTime())};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        LinearLayout linearLayout = view.findViewById(R.id.fragment_notes_list_layout);
        TextView textViewNote1 = createTextView(linearLayout, notes[0]);
        TextView textViewNote2 = createTextView(linearLayout, notes[1]);
        TextView textViewNote3 = createTextView(linearLayout, notes[2]);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            clickOnTextViewPort(textViewNote1,0);
            clickOnTextViewPort(textViewNote2,1);
            clickOnTextViewPort(textViewNote3,2);
        } else {
            fragmentStartTransaction(createNoteFragment(0), R.id.note_land);
            clickOnTextViewLand(textViewNote1,0);
            clickOnTextViewLand(textViewNote2,1);
            clickOnTextViewLand(textViewNote3,2);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    public TextView createTextView(LinearLayout layout, Notes note){
        TextView textView = new TextView(getContext());
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(lparams);
        textView.setTextSize(30);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setBackgroundColor(getResources().getColor(R.color.background_color_textView));
        textView.setText(note.getName());
        layout.addView(textView);
        return textView;
    }

    public void clickOnTextViewPort(TextView textViewNote, int index){
        textViewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentStartTransaction(createNoteFragment(index), R.id.main_activity);
            }
        });
    }

    public void clickOnTextViewLand(TextView textViewNote, int index){
        textViewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentStartTransaction(createNoteFragment(index), R.id.note_land);
            }
        });
    }

    public void fragmentStartTransaction(NoteFragment fragment, int id){
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(id, fragment)
                .addToBackStack(null)
                .commit();
    }

    public NoteFragment createNoteFragment(int index){
        intent.putExtra(ARG_INDEX, notes[index]);
        NoteFragment fragment = new NoteFragment();
        fragment.setArguments(intent.getExtras());
        return fragment;
    }
}