package com.example.hp_pc.aticustestone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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

public class Testactivity extends AppCompatActivity implements View.OnClickListener {

    //defining views
    private ImageView buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPass;
    private ProgressDialog progressDialog;
    private TextView loginLink;
    FloatingActionButton fabdone;

    //URL to registerdevice
    private static final String URL_REGISTER_DEVICE = "http://ec2-35-154-56-217.ap-south-1.compute.amazonaws.com/gcm_chat/v1/index.php/user/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testactivity);

        ///getting views from xml
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPass = (EditText) findViewById(R.id.registerpass);
        fabdone = (FloatingActionButton) findViewById(R.id.signup);
        loginLink = (TextView) findViewById(R.id.loginlink);

        //adding listener to view
        fabdone.setOnClickListener(this);
        loginLink.setOnClickListener(this);
    }

    public void storeLogin(String logchk) {
        SharedPrefManager.getInstance(getApplicationContext()).setloginbool(logchk);
    }
    //storing token to mysql server
    private void sendTokenToServer() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering Device...");
        progressDialog.show();

        final String token = SharedPrefManager.getInstance(this).getDeviceToken();
        final String email = editTextEmail.getText().toString();
        final String pass = editTextPass.getText().toString();

        if (token == null) {
            progressDialog.dismiss();
            Toast.makeText(this, "Token not generated", Toast.LENGTH_LONG).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER_DEVICE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(obj.getString("error")=="false") {
                                storeEmail(editTextEmail.getText().toString().trim());
                                storeUser(obj.getJSONObject("user").getString("user_id"));
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }else if (obj.getString("error")=="true"){
                                Toast.makeText(Testactivity.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Testactivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {

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
    public void onClick(View view) {
        if (view == fabdone) {
            sendTokenToServer();
            startActivity(new Intent(this, MainActivity.class));
        }
        else if(view == loginLink) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
