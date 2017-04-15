package com.example.hp_pc.aticustestone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatsActivity extends AppCompatActivity {

    List<Chat_POJO> temp1 = new ArrayList<>();
    List<Chat_POJO> temp2 = new ArrayList<>();

    List<Chat_POJO> messages = new ArrayList<>();
    ChatsAdapter adapter;

    JSONObject jsonOb;

    RecyclerView rv;

    String to_userid;
    String selfid;

    String res, res2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        rv = (RecyclerView)findViewById(R.id.chats_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);

        selfid = SharedPrefManager.getInstance(this).getDeviceuserid();
        to_userid = SharedPrefManager.getInstance(this).gettoSend();

        Button btn = (Button)findViewById(R.id.buttonSend);
        final EditText editText = (EditText)findViewById(R.id.editTextMessage);

//        fetch_messages(false);

        fetch();

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String jsonstring = extras.getString("json");
            try {
                jsonOb = new JSONObject(jsonstring);
                //parse(jsonOb);
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

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Intent i = new Intent(ChatsActivity.this, ChatsActivity.class);
            startActivity(i);
        }
    };

    IntentFilter intentfilter = new IntentFilter("open_chats_activity");


    public void sendmsg(final String message){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.URL_SEND_MESSAGE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //fetch_messages(true);
                        fetch();
                        Log.d("test", response);
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

                params.put("userid", to_userid);
                params.put("sender", selfid);
                params.put("title", "title");
                params.put("message", message);

                Log.d(" SEND :: \nto : " + to_userid, "from :" + selfid);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        Log.d("VOLLEY", stringRequest + "");
    }

//    public void parse(String response, boolean update, int self_or_rec){
//
//        //1 for self 2 for rec
//
//        if (self_or_rec == 2){
//            try {
//
//                JSONObject jsonObject = new JSONObject(response);
//
//                JSONArray messagesArrayJSON = jsonObject.getJSONArray("messages");
//
//                for (int i = 0; i < messagesArrayJSON.length(); ++i){
//
//                    JSONObject buffer = messagesArrayJSON.getJSONObject(i);
//
//                    String msg = buffer.getString("message");
//                    Log.d("CHAT " + i + "\n", msg);
//
//                    messages.add(new Chat_POJO("other", msg, "NAME"));
//                    adapter = new ChatsAdapter(this, messages, "other");
//                    adapter.notifyDataSetChanged();
//                    rv.setAdapter(adapter);
//
//
//                }
////            Log.d("messages", messages.toString());
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//        if (!update && self_or_rec == 1){
//            try {
//
//                JSONObject jsonObject = new JSONObject(response);
//
//                JSONArray messagesArrayJSON = jsonObject.getJSONArray("messages");
//
//                for (int i = 0; i < messagesArrayJSON.length(); ++i){
//
//                    JSONObject buffer = messagesArrayJSON.getJSONObject(i);
//
//                    String msg = buffer.getString("message");
//                    Log.d("CHAT " + i + "\n", msg);
//
//                    messages.add(new Chat_POJO(selfid, msg, "NAME"));
//                    adapter = new ChatsAdapter(this, messages, selfid);
//                    adapter.notifyDataSetChanged();
//                    rv.setAdapter(adapter);
//
//
//                }
////            Log.d("messages", messages.toString());
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }else {
//
//            try {
//                JSONObject jsonObject = new JSONObject(response);
//                JSONArray ar = jsonObject.getJSONArray("messages");
//
//                JSONObject last_text = ar.optJSONObject((ar.length()) - 1);
//                String msg = last_text.getString("message");
//
//                messages.add(new Chat_POJO(selfid, msg, "NAME"));
//                adapter = new ChatsAdapter(this, messages, selfid);
//
//
//                rv.scrollToPosition(rv.getAdapter().getItemCount() - 1);
//                rv.setAdapter(adapter);
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }
////        try {
////            JSONObject msg = jsonObject.getJSONObject("data");
////
////            String str = msg.getString("message");
////            messages.add(new Chat_POJO(123, str, "test"));
////
////            adapter = new ChatsAdapter(this, messages, 1196);
////            adapter.notifyDataSetChanged();
////            rv.setAdapter(adapter);
////
////
////        } catch (JSONException e) {
////            e.printStackTrace();
////        }
//    }



//    public void fetch_messages(final boolean update){
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://ec2.xynocast.com/gcm_chat/v1/index.php/fetch/chats",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
////                        Log.d("response", response);
//                        if (!update)
//                            //parse(response, false, 1);
//                        else
//                            //parse(response, true, 1);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d("FETCH ERROR : ", error + "");
//                    }
//                })
//        {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//
//                params.put("selfid", selfid);
//                params.put("reciever", to_userid);
//
//                return params;
//            }
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//
//        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, "https://ec2.xynocast.com/gcm_chat/v1/index.php/fetch/chats",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
////                        Log.d("response", response);
//                        if (!update)
//                            //parse(response, false, 2);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d("FETCH ERROR : ", error + "");
//                    }
//                })
//        {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//
//                params.put("selfid", to_userid);
//                params.put("reciever", selfid);
//
//                return params;
//            }
//        };
//
//        RequestQueue requestQueue2 = Volley.newRequestQueue(this);
//        requestQueue2.add(stringRequest2);
//    }

    public void fetch(){

        Log.d("ok", "ok3");

        boolean halt = false;
        final boolean[] f1 = new boolean[1];
        final boolean[] f2 = new boolean[1];
        f1[0] = false;
        f2[0] = false;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.URL_FETCH_MSGS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        res = response;
                        f1[0] = true;

                        if (f1[0] && f2[0]) {
                            parsev2(res, res2);
                            Log.d("ok", "ok2");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FETCH ERROR ", error + "");
                    }
                })
        {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();

                params.put("selfid", selfid);
                params.put("reciever", to_userid);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        //fetching from the other end
        StringRequest stringRequest_2 = new StringRequest(Request.Method.POST, Endpoints.URL_FETCH_MSGS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        res2 = response;
                        f2[0] = true;


                        Log.d("RES2 ", res2 + "");

                        try {
                            JSONObject err = new JSONObject(res2);
                            boolean is_err = err.getBoolean("error");

                            if (is_err) {
                                res2 = "{\n" +
                                        "  \"error\": false,\n" +
                                        "  \"messages\": [\n" +
                                        "    {\n" +
                                        "      \"message\": \"Conversation initiated : \",\n" +
                                        "      \"msgid\": 0,\n" +
                                        "      \"time\": \"2017-01-01 01:01:01\"\n" +
                                        "    }\n" +
                                        "  ]\n" +
                                        "}";

                                parsev2(res, res2);

                            }else {
                                if (f1[0] && f2[0]) {
                                    parsev2(res, res2);
                                    Log.d("ok", "ok2");
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FETCH ERROR ", error + "");
                    }
                })
        {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();

                params.put("selfid", to_userid);
                params.put("reciever", selfid);

                Log.d("to : " + to_userid, "from :" + selfid);

                return params;
            }
        };

        RequestQueue requestQueue_2 = Volley.newRequestQueue(this);
        requestQueue_2.add(stringRequest_2);

//        while (!halt){
//            if (f1[0] && f2[0]) {
//                parsev2(res, res2);
//                Log.d("ok", "ok2");
//                halt = true;
//            }
//        }


    }

    public void parsev2(String response, String response_2){

        Log.d("ok", "ok");
        boolean p1,p2;
        p1 = false;
        p2 = false;

        try {

            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("messages");

            temp1.clear();

            for (int i = 0; i < jsonArray.length(); ++i){

                JSONObject buffer = jsonArray.getJSONObject(i);
                String msg = buffer.getString("message");
                int msg_id = buffer.getInt("msgid");

                temp1.add(new Chat_POJO(selfid, msg, "NAME", msg_id));
                p1 = true;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {

            JSONObject jsonObject2 = new JSONObject(response_2);
            JSONArray jsonArray2 = jsonObject2.getJSONArray("messages");

            temp2.clear();

            for (int i = 0; i < jsonArray2.length(); ++i){
                JSONObject buffer = jsonArray2.getJSONObject(i);
                String msg = buffer.getString("message");
                int msg_id = buffer.getInt("msgid");

                temp2.add(new Chat_POJO(to_userid, msg, "NAME2", msg_id));
                p2 = true;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (p1 && p2){

            Log.d("parsev2 msg", temp1.get(0).msg.toString());
            Log.d("parsev2 msg", temp2.get(0).msg.toString());

            int max = (temp1.size() + temp2.size());
//            int start = (temp1.get(0).msg_id < temp2.get(0).msg_id ? temp1.get(0).msg_id : temp2.get(0).msg_id);
            int start = temp1.get(0).msg_id;

            Chat_POJO buffer = temp1.get(0);

            List<Chat_POJO> temp = temp1;

            temp.addAll(temp2);

            for (int i = 0; i < temp.size(); ++i)
                Log.d("temp", temp.get(i).msg_id + "");

            Chat_POJO buf;


            Collections.sort(temp, new Comparator<Chat_POJO>() {
                @Override
                public int compare(Chat_POJO t1, Chat_POJO t2) {
                    if (t1.msg_id > t2.msg_id) return 1;
                    if (t1.msg_id < t2.msg_id) return -1;
                    return 0;
                }
            });

//            for (int i = 0; i < temp.size() - 1; ++i)
//                for (int j = 0; j < ((temp.size() - 1) - i); ++j){
////                    if (temp.get(j).msg_id > temp.get(j+1).msg_id){
////                        buf = temp.get(j);
////                        temp.remove(j);
////                        temp.add(j, temp.get(j+1));
////                        temp.remove(j+1);
////                        temp.add(j+1, buf);
////
////                    }
//
//                }

            messages.clear();
            for (int i = 0; i < temp.size(); ++i){
                messages.add(temp.get(i));
                adapter = new ChatsAdapter(this, messages, selfid);
                adapter.notifyDataSetChanged();
                rv.setAdapter(adapter);
                rv.scrollToPosition(rv.getAdapter().getItemCount() - 1);

                Log.d("MESSAGES", messages.get(i).msg);
            }


//            for (int i = 0, j = 0; i < temp1.size() || j < temp2.size(); ){
//
////
//
//                if (temp1.get(i).msg_id - start < temp2.get(j).msg_id - start){
//                    Log.d("test", temp2.get(j).msg_id + "");
//                    buffer = temp1.get(i);
//                    start = buffer.msg_id;
//                    messages.add(temp1.get(i));
//                    ++i;
//
//                }else if(temp2.get(j).msg_id - start < buffer.msg_id - start){
//                    Log.d("temp2", temp2.get(j).msg_id + "");
//                    buffer = temp2.get(j);
//                    start = buffer.msg_id;
//                    messages.add(temp2.get(j));
//                    ++j;
//                }
//
//            }
//
//            for (int q = 0; q < messages.size(); ++q){
//
//                Log.d("PARSEV2", messages.get(q).msg.toString());
//            }

        }

    }

    @Override
    protected void onResume(){
        super.onResume();
        registerReceiver(receiver, intentfilter);
    }

    @Override
    protected void onPause() {
        if(receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
        super.onPause();
    }
}