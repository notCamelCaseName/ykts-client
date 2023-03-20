package com.example.youknowthatsong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.material3.SnackbarKt;
import androidx.core.widget.TextViewOnReceiveContentListener;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {

/*
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.helloBtn:
                Toast.makeText(this, "Hello, button clicked !", Toast.LENGTH_SHORT).show();
                helloText.setText("Yeah that's more like it "+ editTextName.getText().toString() + ".");
                break;
            case R.id.editTxtName:

                Toast.makeText(this, "Attempting to write text.", Toast.LENGTH_SHORT).show();
            default:
                break;
        }
    }
 */

    private MaterialCardView btnRecord;
    private MaterialCardView btnChooseFile;
    private int what;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        what = 0;

        /*
        Button helloBtn = findViewById(R.id.helloBtn);

        helloBtn.setOnClickListener(this);
        helloBtn.setOnLongClickListener(new View.OnLongClickListener(){
            
            @Override
            public boolean onLongClick(View v){
                Toast.makeText(MainActivity.this, "Long press !", Toast.LENGTH_LONG).show();
                return true;
            }

        });

        editTextName = findViewById(R.id.editTxtName);
        helloText = findViewById(R.id.helloTxt);

        editTextName.setOnClickListener(this);
         */

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
            case R.id.menu_settings:
                // On menu settings clicked
                Toast.makeText(this, "Settings requested !", Toast.LENGTH_SHORT).show();
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content),"Settings requested", Snackbar.LENGTH_SHORT).show();
                //Intent launchNewIntent = new Intent(MainActivity.this,SettingsActivity.class);
                //startActivityForResult(launchNewIntent, 0);

                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);

                break;
            case R.id.menu_about:
                // On menu about clicked
                Toast.makeText(this, "About requested !", Toast.LENGTH_SHORT).show();
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content),"About requested", Snackbar.LENGTH_SHORT).show();

            default:

                break;
        }

        return super.onOptionsItemSelected(item);
    }
    // Left to create 2 new pages for settings and about



    public void requestRecording(View view){
        Snackbar.make(view,"Requested recording", Snackbar.LENGTH_SHORT).show();
        //createAlert();
    }
    public void requestFileSeeking(View view){
        Snackbar.make(view,"Requested file choosing", Snackbar.LENGTH_SHORT).show();
    }

    public void mysterious(View v){
        what ++;
        if (what>20){
            Snackbar.make(v,"Never gonna give you up, never gonna let you down.", Snackbar.LENGTH_LONG).show();
            what = 0;
        }
    }



    private boolean checkConnection(){
        return true;
    }


    private void createAlert(){
        new MaterialAlertDialogBuilder(this)
                .setMessage("On dirait que tu n'as pas beaucoup de connexion, la recherche risque d'être lente.\nVeux-tu continuer ?")
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Respond to negative button press
                        Toast.makeText(MainActivity.this, "Annulé", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("Continuer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Respond to positive button press
                        Toast.makeText(MainActivity.this, "Il a continué le con.", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

}