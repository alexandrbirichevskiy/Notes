package com.alexbirichevskiy.notes;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = initToolbar();
        NotesListFragment fragment = new NotesListFragment();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            initDrawer(toolbar, R.id.main_activity);
            startPort(fragment);
        } else {
            initDrawer(toolbar, R.id.main_activity_land);
            startLand(fragment);
        }
    }

    private void initDrawer(Toolbar toolbar, int id) {
        DrawerLayout drawerLayout = findViewById(id);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private Toolbar initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

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