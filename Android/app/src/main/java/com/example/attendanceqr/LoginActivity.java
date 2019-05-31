package com.example.attendanceqr;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private String username, password;
    private EditText et_username, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        et_username = findViewById(R.id.etUserName);
        et_password = findViewById(R.id.etPassword);
    }


    public void OnLogin(View view) {
        username = et_username.getText().toString();
        password = et_password.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST, Database.loginDomain, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    boolean result = jsonObject.getBoolean("success");

                    if(!result){
                        new AlertDialog.Builder(LoginActivity.this)
                                .setTitle("Warning")
                                .setMessage(jsonObject.getString("message"))
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                        return;
                    }

                    // giriş başarılı

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("student_number", username);
                    getApplicationContext().startActivity(intent);

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map <String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);

                return params;
            }
        };

        Database.getmInstance(getApplicationContext()).execute(request);

    }
}
