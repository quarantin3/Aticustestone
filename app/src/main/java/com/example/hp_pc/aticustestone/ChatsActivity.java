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

<<<<<<< HEAD
import org.json.JSONArray;
=======
>>>>>>> chatactivity send
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

<<<<<<< HEAD
    String to_userid;
            //SharedPrefManager.getInstance(this).gettoSend();
    String selfid;
=======
    int to_userid = 86;
>>>>>>> chatactivity send

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        rv = (RecyclerView)findViewById(R.id.chats_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);

<<<<<<< HEAD
        selfid = SharedPrefManager.getInstance(this).getDeviceuserid();
        to_userid = SharedPrefManager.getInstance(this).gettoSend();

        Button btn = (Button)findViewById(R.id.buttonSend);
        final EditText editText = (EditText)findViewById(R.id.editTextMessage);

        fetch_messages(false);

=======
        Button btn = (Button)findViewById(R.id.buttonSend);
        final EditText editText = (EditText)findViewById(R.id.editTextMessage);

>>>>>>> chatactivity send
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String jsonstring = extras.getString("json");
            try {
                jsonOb = new JSONObject(jsonstring);
<<<<<<< HEAD
                //parse(jsonOb);
=======
                parse(jsonOb);
>>>>>>> chatactivity send
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

<<<<<<< HEAD
    public void parse(String response, boolean update, int self_or_rec){

        //1 for self 2 for rec

        if (self_or_rec == 2){
            try {

                JSONObject jsonObject = new JSONObject(response);

                JSONArray messagesArrayJSON = jsonObject.getJSONArray("messages");

                for (int i = 0; i < messagesArrayJSON.length(); ++i){

                    JSONObject buffer = messagesArrayJSON.getJSONObject(i);

                    String msg = buffer.getString("message");
                    Log.d("CHAT " + i + "\n", msg);

                    messages.add(new Chat_POJO("other", msg, "NAME"));
                    adapter = new ChatsAdapter(this, messages, "other");
                    adapter.notifyDataSetChanged();
                    rv.setAdapter(adapter);


                }
//            Log.d("messages", messages.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (!update && self_or_rec == 1){
            try {

                JSONObject jsonObject = new JSONObject(response);

                JSONArray messagesArrayJSON = jsonObject.getJSONArray("messages");

                for (int i = 0; i < messagesArrayJSON.length(); ++i){

                    JSONObject buffer = messagesArrayJSON.getJSONObject(i);

                    String msg = buffer.getString("message");
                    Log.d("CHAT " + i + "\n", msg);

                    messages.add(new Chat_POJO(selfid, msg, "NAME"));
                    adapter = new ChatsAdapter(this, messages, selfid);
                    adapter.notifyDataSetChanged();
                    rv.setAdapter(adapter);


                }
//            Log.d("messages", messages.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {

            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray ar = jsonObject.getJSONArray("messages");

                JSONObject last_text = ar.optJSONObject((ar.length()) - 1);
                String msg = last_text.getString("message");

                messages.add(new Chat_POJO(selfid, msg, "NAME"));
                adapter = new ChatsAdapter(this, messages, selfid);


                rv.scrollToPosition(rv.getAdapter().getItemCount() - 1);
                rv.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
//        try {
//            JSONObject msg = jsonObject.getJSONObject("data");
//
//            String str = msg.getString("message");
//            messages.add(new Chat_POJO(123, str, "test"));
//
//            adapter = new ChatsAdapter(this, messages, 1196);
//            adapter.notifyDataSetChanged();
//            rv.setAdapter(adapter);
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }


=======
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

>>>>>>> chatactivity send
    public void sendmsg(final String message){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.URL_SEND_MESSAGE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
<<<<<<< HEAD
                        fetch_messages(true);
                        Log.d("test", response);
=======
                        Log.d("POST : ", response);
>>>>>>> chatactivity send
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
                Map<String, String> params = new HashMap<>();

<<<<<<< HEAD
                params.put("userid", to_userid);
                params.put("sender", selfid);
=======
                params.put("sender", "85");
                params.put("userid", to_userid + "");
>>>>>>> chatactivity send
                params.put("title", "title");
                params.put("message", message);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

<<<<<<< HEAD
    public void fetch_messages(final boolean update){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://ec2.xynocast.com/gcm_chat/v1/index.php/fetch/chats",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Log.d("response", response);
                        if (!update)
                            parse(response, false, 1);
                        else
                            parse(response, true, 1);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("FETCH ERROR : ", error + "");
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("selfid", selfid);
                params.put("reciever", to_userid);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, "https://ec2.xynocast.com/gcm_chat/v1/index.php/fetch/chats",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Log.d("response", response);
                        if (!update)
                            parse(response, false, 2);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("FETCH ERROR : ", error + "");
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("selfid", to_userid);
                params.put("reciever", selfid);

                return params;
            }
        };

        RequestQueue requestQueue2 = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest2);
    }
}
=======
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
>>>>>>> chatactivity send
