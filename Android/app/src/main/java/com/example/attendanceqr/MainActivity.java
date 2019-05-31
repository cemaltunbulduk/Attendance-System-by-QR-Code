package com.example.attendanceqr;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button scanQrcode;
    public static TextView resultViewer;
    private String student_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        student_number = getIntent().getStringExtra("student_number");
        resultViewer = (TextView)findViewById(R.id.resultView);

        resultViewer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                StringRequest request = new StringRequest(Request.Method.POST, Database.attendanceDomain, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean result = jsonObject.getBoolean("success");

                            if(result){
                                // Kayıt Başarılı
                                Log.w("SUCCESS", "onResponse success" );
                            }

                            else {
                                // Kayıt Başarısız
                                Log.w("ERROR", "onResponse " + jsonObject.getString("message") );

                                new AlertDialog.Builder(MainActivity.this)
                                        .setTitle("Warning")
                                        .setMessage(jsonObject.getString("message"))
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map <String, String> params = new HashMap<>();
                        params.put("student_number", student_number);
                        params.put("date", resultViewer.getText().toString());
                        return params;
                    }
                };

                Database.getmInstance(getApplicationContext()).execute(request);
            }
        });
        scanQrcode = (Button)findViewById(R.id.scanQr);

        scanQrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),ScanQRActivity.class));

            }
        });

    }


}