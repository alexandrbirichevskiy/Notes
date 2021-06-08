package com.alexbirichevskiy.notes;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        NotesListFragment fragment = new NotesListFragment();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            startPort(fragment);
        } else {
            startLand(fragment);
        }
    }
    public void startPort(NotesListFragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity, fragment)
                .commit();
    }

    public void startLand(NotesListFragment fragment){
        NoteFragment noteFragment = new NoteFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.note_list_land, fragment)
                .replace(R.id.note_land, noteFragment)
                .commit();
    }
}