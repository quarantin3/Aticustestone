package com.example.hp_pc.aticustestone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatsActivity extends AppCompatActivity {

    List<Chat_POJO> messages = new ArrayList<>();
    ChatsAdapter adapter;

    JSONObject jsonOb;

    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        rv = (RecyclerView)findViewById(R.id.chats_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);

        Button btn = (Button)findViewById(R.id.buttonSend);
        final EditText editText = (EditText)findViewById(R.id.editTextMessage);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String jsonstring = extras.getString("json");
            try {
                jsonOb = new JSONObject(jsonstring);
                parse(jsonOb);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = editText.getText().toString();
                sendmsg(str);

            }
        });

    }

    public void parse(JSONObject json){
        JSONObject jsonObject = json;

        Log.d("JSON : chats", jsonObject.toString() + "");

        try {
            JSONObject msg = jsonObject.getJSONObject("data");

            String str = msg.getString("message");
            messages.add(new Chat_POJO(123, str, "test"));

            adapter = new ChatsAdapter(this, messages, 1196);
            adapter.notifyDataSetChanged();
            rv.setAdapter(adapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void sendmsg(final String message){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.URL_SEND_MESSAGE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("POST : ", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("POST ERROR", error.toString());
                    }
                })
        {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();

                params.put("email", "85");
                params.put("title", "title");
                params.put("message", message);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void fetch_messages(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.URL_FETCH_MSGS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                    }
                });
    }
}
