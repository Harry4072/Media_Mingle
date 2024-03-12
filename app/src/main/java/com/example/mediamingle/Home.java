package com.example.mediamingle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private RecyclerView home_post;

    private ArrayList<fetch_post_model> postModelList;
    private PostAdaptor posts;
    private RequestQueue requestQueue;
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
        home_post = findViewById(R.id.post_list);
        home_post.setHasFixedSize(true);
        //recyclerView.setLayoutManager(staggeredGridLayoutManager);
        home_post.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = Volley.newRequestQueue(this);

        getData();
        postModelList = new ArrayList<>();

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
    private void getData() {

        /*String Url = "https://run.mocky.io/v3/6add1a0c-9a1d-49c0-92fd-793d7c1a0f68";*/
        /*String Url = "https://testapi.io/api/Ronak/category";*/
        String Url = "http://192.168.163.254/MediaMingle/project_api/post_api.php";
//        String Url = "https://run.mocky.io/v3/7bd9482b-cf11-4efa-b465-5ce714b3bd8e";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("api","onResponse:============ " +response);
                        try {
                            JSONArray jsonArray = response.getJSONArray("post");
                            for(int i = 0; i <= jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String Name = jsonObject.getString("post_user_name");
                                String Image = jsonObject.getString("post_user_img");
                                String Posts = jsonObject.getString("post_name");
                                String Desc = jsonObject.getString("post_description");

                                postModelList.add(new fetch_post_model(Name,Image,Posts,Desc));
                                Log.e("api","onResponse:============ " +Name);
                                posts = new PostAdaptor(Home.this, postModelList);
                                home_post.setAdapter(posts);
                            }

                        } catch (JSONException e) {
                            Log.e("Api","ErrorResponse???????????: " +e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Api","ErrorResponse+++++++++: " +error.getMessage());
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}