package com.example.mediamingle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class SearchPage extends AppCompatActivity {

    public ImageButton homebtn,btnprofile,addpostbtn ;

        ListView simpleList;
//        String userList[] = {"harry", "marry", "tom", "jarry", "viru", "demo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

//        simpleList = (ListView)findViewById(R.id.search_list);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.search_user, R.id.textView9, userList);
//        simpleList.setAdapter(arrayAdapter);

        homebtn =(ImageButton) findViewById(R.id.homebtn);
        addpostbtn =(ImageButton) findViewById(R.id.addpostbtn);
        btnprofile =(ImageButton) findViewById(R.id.btnprofile);

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchPage.this, Home.class);
                startActivity(intent);
            }
        });
        addpostbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchPage.this,Post.class);
                startActivity(intent);
            }
        });
        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchPage.this, Profile.class);
                startActivity(intent);
            }
        });
    }
}