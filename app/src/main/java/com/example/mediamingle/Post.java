package com.example.mediamingle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ImageButton;

public class Post extends AppCompatActivity {

    public ImageButton homebtn,btnprofile,searchbtn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        searchbtn =(ImageButton) findViewById(R.id.searchbtn);
        homebtn =(ImageButton) findViewById(R.id.homebtn);
        btnprofile =(ImageButton) findViewById(R.id.btnprofile);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Post.this, SearchPage.class);
                startActivity(intent);
            }
        });
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Post.this,Home.class);
                startActivity(intent);
            }
        });
        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Post.this, Profile.class);
                startActivity(intent);
            }
        });
    }
}