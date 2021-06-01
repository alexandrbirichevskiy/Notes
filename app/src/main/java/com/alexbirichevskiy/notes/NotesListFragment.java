package com.alexbirichevskiy.notes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NotesListFragment extends Fragment{

    private static final String ARG_INDEX = "index";
    private static final int DEFAULT_INDEX = 0;
    private int index = DEFAULT_INDEX;
    private Publisher publisher;
    private TextView noteTextView1;
    private TextView noteTextView2;
    private TextView noteTextView3;

    public NotesListFragment() {
    }

    public static NotesListFragment newInstance(int index) {
        NotesListFragment fragment = new NotesListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        publisher = ((PublisherGetter) context).getPublisher();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_INDEX, DEFAULT_INDEX);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        LinearLayout linearLayout = view.findViewById(R.id.fragment_notes_list_layout);
        Notes note1 = new Notes("Note 1", "description for note 1", "01.01.01");
        Notes note2 = new Notes("Note 2", "description for note 1", "01.01.01");
        Notes note3 = new Notes("Note 3", "description for note 1", "01.01.01");
        noteTextView1 = createTextView(linearLayout, note1);
        noteTextView2 = createTextView(linearLayout, note2);
        noteTextView3 = createTextView(linearLayout, note3);

        openNoteActivity(noteTextView1, note1);
//        openNoteActivity(noteTextView2);
//        openNoteActivity(noteTextView3);
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

    public void openNoteActivity(TextView textView, Notes note){
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publisher.notify(note.getDescription());
                Intent intent = new Intent();
                intent.setClass(getActivity(), NoteActivity.class);
                intent.putExtra(ARG_INDEX, index);
                startActivity(intent);
            }
        });
    }
}