package com.alexbirichevskiy.notes;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        RecyclerView recyclerView = getActivity().findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        MyAdapter adapter = new MyAdapter(notes);
        recyclerView.setAdapter(adapter);
        adapter.setListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                fragmentStartTransaction(createNoteFragment(position), R.id.fragments_container);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
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