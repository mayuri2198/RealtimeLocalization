package com.example.realtimelocalization;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button changelang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
        changelang=findViewById(R.id.button3);
        
        changelang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showchangelanguagedialog();
            }
        });
    }

    private void showchangelanguagedialog() {
        final String[] listitems={"English","Español","हिंदी"};
        final AlertDialog.Builder mbuilder=new AlertDialog.Builder((MainActivity.this));
        mbuilder.setTitle("Change Languages");
        mbuilder.setSingleChoiceItems(listitems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i == 0) {
                    setLocale("En");
                } else if (i == 1) {
                    setLocale("Es");
                    recreate();
                } else if (i == 2) {
                    setLocale("Hin");
                    recreate();
                }
                dialog.dismiss();
            }
        });
        AlertDialog malertDialog=mbuilder.create();
        malertDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration config=new Configuration();
        config.locale=locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor=getSharedPreferences("Setting",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }

    private void loadLocale() {
        SharedPreferences preferences=getSharedPreferences("Setting", Activity.MODE_PRIVATE);
        String language= preferences.getString("My_Lang","");
        setLocale(language);
    }

}
