package com.alexbirichevskiy.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initToolbar();
        NotesListFragment fragment = new NotesListFragment();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            startPort(fragment);
        } else {
            startLand(fragment);
        }
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        switch (id){
//            case R.id.action_favorites:
//                return true;
//            case R.id.action_settings:
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    public void startPort(NotesListFragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragments_container, fragment)
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