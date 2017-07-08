package com.nehvin.s07e111langpref;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.txtView);
        final SharedPreferences sharedPreferences = this.getSharedPreferences("com.nehvin.s07e111langpref", Context.MODE_PRIVATE);

        String str = sharedPreferences.getString("lang","");

        if(str.equalsIgnoreCase("")) {
            new AlertDialog.Builder(this)
                    .setTitle("Choose Your Language Preference")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //set the shared pref to english
                            sharedPreferences.edit().putString("lang", "English").apply();
                            Log.i("Language Choosen", "English");
                        }
                    })
                    .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //set the shared pref to Spanish
                            sharedPreferences.edit().putString("lang", "Spanish").apply();
                            Log.i("Language Choosen", "Spanish");
                        }
                    })
                    .show();
        }
        textView.setText(sharedPreferences.getString("lang",""));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.lang_pref, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.nehvin.s07e111langpref", Context.MODE_PRIVATE);
        int itemID = item.getItemId();
        switch (itemID){
            case R.id.english :
                sharedPreferences.edit().putString("lang","English");
                textView.setText("English");
                return true;
            case R.id.spanish :
                sharedPreferences.edit().putString("lang","Spanish");
                textView.setText("Spanish");
                return true;
            default:
                return false;
        }
    }
}