package com.example.mediamingle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.List;

public class Home extends AppCompatActivity {

    RecyclerView rv_retrofit;
//    List<ModelData> postList;
//    AdapterCategory adapterCategory;
    public ImageButton searchbtn,addpostbtn,btnprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        searchbtn =(ImageButton) findViewById(R.id.searchbtn);
        addpostbtn =(ImageButton) findViewById(R.id.addpostbtn);
        btnprofile =(ImageButton) findViewById(R.id.btnprofile);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, SearchPage.class);
                startActivity(intent);
            }
        });
        addpostbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,Post.class);
                startActivity(intent);
            }
        });
        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Profile.class);
                startActivity(intent);
            }
        });

    }
}