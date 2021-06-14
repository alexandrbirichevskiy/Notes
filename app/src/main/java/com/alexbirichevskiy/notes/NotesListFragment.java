package com.alexbirichevskiy.notes;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class NotesListFragment extends Fragment{
    private static final String ARG_INDEX = "index";
    private Intent intent = new Intent();
    private Notes[] notes = GenerateData.getNotes();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        FrameLayout frameLayoutLayout = view.findViewById(R.id.fragment_notes_list_layout);
        RecyclerView recyclerView = getActivity().findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new MyAdapter(notes));
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    public void clickOnTextViewPort(TextView textViewNote, int index){
        textViewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentStartTransaction(createNoteFragment(index), R.id.fragments_container);
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