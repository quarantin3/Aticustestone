package com.example.hp_pc.aticustestone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
}
