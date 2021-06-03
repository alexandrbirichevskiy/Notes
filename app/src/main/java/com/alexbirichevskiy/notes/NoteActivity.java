package com.alexbirichevskiy.notes;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Configuration;
import android.os.Bundle;

public class NoteActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        NoteFragment fragmentNote = new NoteFragment();
        NotesListFragment fragment = new NotesListFragment();
        fragmentNote.setArguments(getIntent().getExtras());
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.note_activity, fragmentNote)
                    .commit();
        } else {
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.note_list_land, fragment)
                    .replace(R.id.note_land, fragmentNote)
                    .commit();
        }
    }
}