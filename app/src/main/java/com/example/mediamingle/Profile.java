package com.example.mediamingle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import android.view.View;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    private ImageButton postbtn,searchbtn,homebtn,logoutbtn ;
    private Button editprofile;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        postbtn =(ImageButton) findViewById(R.id.postbtn);
        searchbtn = (ImageButton) findViewById(R.id.searchbtn);
        homebtn = (ImageButton) findViewById(R.id.homebtn);
        editprofile = (Button) findViewById(R.id.btn_edit_profile);
        logoutbtn = findViewById(R.id.logoutbtn);

        sp = getSharedPreferences("loginfile",MODE_PRIVATE);
        editor = sp.edit();

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("isloggedin","false");
                editor.putString("unm","");
                editor.commit();
                Toast.makeText(Profile.this, "logout successfully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Profile.this, MainActivity.class);
                startActivity(i);
                finishAffinity();
            }
        });

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, EditProfile.class);
                startActivity(i);
            }
        });

        postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Post.class);
                startActivity(intent);
            }
        });
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, SearchPage.class);
                startActivity(intent);
            }
        });
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Home.class);
                startActivity(intent);
            }
        });
    }
}