package com.example.mediamingle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import java.util.HashMap;
import java.util.Map;

public class RegisterPage extends AppCompatActivity {

    EditText name,usr_name,password,email,txt_Date;
    RadioGroup radioGroup;

    String name_ans,usr_name_ans,password_ans,email_ans,gender,txt_Date_ans;

    Button submit;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        name = findViewById(R.id.name);
        usr_name = findViewById(R.id.usr_name);
        password = findViewById(R.id.password);
        radioGroup = findViewById(R.id.radioGroup);
        email = findViewById(R.id.email);
        txt_Date = findViewById(R.id.txt_Date);
        submit = findViewById(R.id.btn_reg);

        name.addTextChangedListener(check);
        usr_name.addTextChangedListener(check);
        email.addTextChangedListener(check);
        txt_Date.addTextChangedListener(check);
        password.addTextChangedListener(check);

        sp = getSharedPreferences("loginfile",MODE_PRIVATE);
        editor = sp.edit();

        if(sp.getString("isloggedin","").equals("true")){
            Intent i = new Intent(RegisterPage.this, Home.class);
            startActivity(i);
            finishAffinity();
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name_ans = name.getText().toString();
                password_ans = password.getText().toString();
                email_ans = email.getText().toString();
                txt_Date_ans = txt_Date.getText().toString();
                usr_name_ans = usr_name.getText().toString();

                int id = radioGroup.getCheckedRadioButtonId();
                RadioButton rb = findViewById(id);
                gender = rb.getText().toString();

//                Toast.makeText(getApplicationContext(),""+password_ans,Toast.LENGTH_LONG).show();


                RequestQueue requestQueue = Volley.newRequestQueue(RegisterPage.this);
                String url = "http://192.168.163.254/MediaMingle/project_api/register.php";
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")) {
                            editor.putString("isloggedin","true");
                            editor.putString("unm",usr_name_ans);
                            editor.commit();
                            Toast.makeText(RegisterPage.this, "Registration successfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(RegisterPage.this, Home.class);
                            startActivity(i);
                            finish();

                        } else {
                            Toast.makeText(RegisterPage.this, "something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterPage.this, "Error in registration ", Toast.LENGTH_LONG).show();
                    }
                }){
                    protected Map<String, String> getParams() {
                        Map<String, String> paramV = new HashMap<>();

                        paramV.put("name", name_ans);
                        paramV.put("usr_name", usr_name_ans);
                        paramV.put("email", email_ans);
                        paramV.put("password", password_ans);
                        paramV.put("txt_Date", txt_Date_ans);
                        paramV.put("gender", gender);

                        return paramV;
                    }
                };
                requestQueue.add(request);
            }
        });
    }
    TextWatcher check = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String nm = name.getText().toString().trim();
            String unm = usr_name.getText().toString().trim();
            String mail = email.getText().toString().trim();
            String date = txt_Date.getText().toString().trim();
            String pass = password.getText().toString();
            submit.setEnabled(!unm.isEmpty() && !pass.isEmpty() && !nm.isEmpty() && !mail.isEmpty() && !date.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}