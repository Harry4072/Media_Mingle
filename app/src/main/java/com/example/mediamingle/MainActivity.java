package com.example.mediamingle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button loginbtn ;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    EditText user_name,password;
    String user_nam_ans,password_ans;
    TextView txt_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginbtn = findViewById(R.id.btn_login);
        txt_reg = findViewById(R.id.txt_reg);
        user_name = findViewById(R.id.name);
        password = findViewById(R.id.password);

        sp = getSharedPreferences("loginfile",MODE_PRIVATE);
        editor = sp.edit();

        if(sp.getString("isloggedin","").equals("true")){
            Intent i = new Intent(MainActivity.this, Home.class);
            startActivity(i);
            finishAffinity();
        }

        user_name.addTextChangedListener(check);
        password.addTextChangedListener(check);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password_ans = password.getText().toString();
                user_nam_ans = user_name.getText().toString();

//                Toast.makeText(getApplicationContext(),""+password_ans,Toast.LENGTH_LONG).show();


                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                String url = "http://192.168.163.254/MediaMingle/project_api/login.php";
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")) {
                            editor.putString("isloggedin","true");
                            editor.putString("unm",user_nam_ans);
                            editor.commit();
                            Toast.makeText(MainActivity.this, "login successfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MainActivity.this, Home.class);
                            startActivity(i);
                            finish();

                        } else {
                            Toast.makeText(MainActivity.this, "User not axist", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error in Log in ", Toast.LENGTH_LONG).show();
                    }
                }){
                    protected Map<String, String> getParams() {
                        Map<String, String> paramV = new HashMap<>();

                        paramV.put("user_name", user_nam_ans);
                        paramV.put("password", password_ans);

                        return paramV;
                    }
                };
                requestQueue.add(request);
            }
        });
        txt_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,RegisterPage.class);
                startActivity(i);
            }
        });
    }
    TextWatcher check = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String unm = user_name.getText().toString().trim();
            String pass = password.getText().toString();
            loginbtn.setEnabled(!unm.isEmpty() && !pass.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}