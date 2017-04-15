package com.example.hp_pc.aticustestone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener {

    private EditText enteremaillogin;
    private EditText enterpasslogin;
    private Button registerlink;
    private FloatingActionButton fabtwo;
    private TextView registerlinktext;

    private static final String URL_LOGIN_DEVICE = "http://ec2-35-154-56-217.ap-south-1.compute.amazonaws.com/gcm_chat/v1/index.php/user/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        enteremaillogin = (EditText) findViewById(R.id.editTextlogin);
        enterpasslogin = (EditText) findViewById(R.id.edittextPass);
        fabtwo = (FloatingActionButton) findViewById(R.id.login);
        //registerlinktext = (TextView) findViewById(R.id.registerlink);
        registerlink = (Button) findViewById(R.id.signuplink);

        fabtwo.setOnClickListener(this);
        //registerlinktext.setOnClickListener(this);
        registerlink.setOnClickListener(this);


    }


    private void loginUser() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging In...");
        progressDialog.show();

        final String email = enteremaillogin.getText().toString().trim();
        final String pass = enterpasslogin.getText().toString().trim();
        final String token = SharedPrefManager.getInstance(this).getDeviceToken();

        if (token == null) {
            progressDialog.dismiss();
            Toast.makeText(this, "Token not generated", Toast.LENGTH_LONG).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN_DEVICE ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(obj.getString("error")=="false") {
                                storeEmail(enteremaillogin.getText().toString().trim());
                                storeUser(obj.getString("id"));
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }else if (obj.getString("error")=="true"){
                                Toast.makeText(LoginActivity.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("token", token);
                params.put("pass", pass);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public void storeEmail(String email) {
        SharedPrefManager.getInstance(getApplicationContext()).saveEmail(email);
    }

    public void storeUser(String userid) {
        SharedPrefManager.getInstance(getApplicationContext()).saveUserid(userid);
    }

    @Override
    public void onClick(View v) {
        if(v==fabtwo ) {

            loginUser();
        } else if (v==registerlink) {
            startActivity(new Intent(this, Testactivity.class));
        }
    }
}
