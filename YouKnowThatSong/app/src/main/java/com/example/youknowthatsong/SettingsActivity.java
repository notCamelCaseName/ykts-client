package com.example.youknowthatsong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){

            case R.id.menu_main:
                // On home clicked
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_about:
                // On menu about clicked

                //Toast.makeText(this, "About requested !", Toast.LENGTH_SHORT).show();
                //Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content),"About requested", Snackbar.LENGTH_SHORT).show();

                //Intent intent2 = new Intent(this, MainActivity.class);
                //startActivity(intent2);

            default:

                break;
        }

        return super.onOptionsItemSelected(item);
    }

}