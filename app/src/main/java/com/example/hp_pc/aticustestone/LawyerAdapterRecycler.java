package com.example.hp_pc.aticustestone;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Hp-PC on 12/24/2016.
 */

public class LawyerAdapterRecycler extends RecyclerView.Adapter<LawyerAdapterRecycler.RecyclerVH> {

    private LayoutInflater inflater;
    Context ctx;
    ArrayList<HashMap<String, String>> prime = new ArrayList<>();

//    ArrayList<HashMap<String, String>> lawyerlist = new ArrayList();
//    HashMap<String, String> testlaw = new HashMap<String, String >();
////    List<Information> data = Collections.emptyList();
//
//    private void searchLawyer(final String law, final String commtry) {
//
//        //String url = "https://ec2.xynocast.com/gcm_chat/v1/index.php/user/search";
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.URL_SEARCHLAW,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject obj = new JSONObject(response);
//                            if(obj.getBoolean("error") == false) {
//
//
//                                JSONArray lawyers = obj.getJSONArray("lawyers");
//
//                                for (int i = 0; i < lawyers.length(); i++) {
//                                    JSONObject l = lawyers.getJSONObject(i);
//
//                                    String User_Id = l.getString("User_Id");
//                                    String email = l.getString("Email");
//                                    String name = l.getString("Name");
//                                    String barid = l.getString("barid");
//                                    String location = l.getString("location");
//                                    String rating = l.getString("rating");
//                                    String photo = l.getString("photo");
//
////                                    lawyerlist.add(User_Id);
//                                    Log.d("lawyerlist", "String " + lawyerlist);
//
//
//                                    testlaw.put("User_Id", User_Id);
//                                    testlaw.put("name", name);
//                                    testlaw.put("Email", email);
//                                    testlaw.put("barid", barid);
//                                    testlaw.put("location", location);
//                                    testlaw.put("rating", rating);
//                                    testlaw.put("photo", photo);
//
//
//                                    lawyerlist.add(i, testlaw);
////                                   Toast.makeText(MainActivity.this, testlaw.get("User_Id" + ""), Toast.LENGTH_LONG).show();
//                                }
//                                Log.d("map ","string " + testlaw);
//
//
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(ctx, error.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("lawField", law);
//                params.put("modeComm", commtry);
//                return params;
//            }
//        };
//        RequestQueue requestQueue = Volley.newRequestQueue(ctx);
//        requestQueue.add(stringRequest);
//    }


    public LawyerAdapterRecycler(Context ctx, ArrayList<HashMap<String, String>> primeList){
        this.ctx = ctx;
        prime = primeList;
//
    }


    public RecyclerVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.recyclerlawyerview,parent,false);
        RecyclerVH holder = new RecyclerVH(v, ctx);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerVH holder, int position) {

//
        HashMap <String,String> tempMap;
        tempMap = prime.get(position);

        String temp = tempMap.get("User_Id");
        Log.d("key", temp + "");

        String tempname = tempMap.get("name");
        String temprate = tempMap.get("rating");

        holder.lawyerBrief.setText(temp);
        holder.lawyerName.setText(tempname);
        holder.lawyerRating.setText(temprate);

//        Iterator it = tempMap.entrySet().iterator();
//        while (it.hasNext()){
//
//        }



    }

    @Override
    public int getItemCount() {
        return prime.size();
    }

    public class RecyclerVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView lawyerName, lawyerBrief, lawyerRating;
        public CircleImageView profilePhoto;

        Context ctx;
        public RecyclerVH(View v, Context ctx) {
            super(v);
            this.ctx = ctx;

            lawyerName = (TextView) v.findViewById(R.id.lawyername);
            lawyerBrief = (TextView) v.findViewById(R.id.lawyerbrief);
            lawyerRating = (TextView) v.findViewById(R.id.lawyerrating);

            v.setClickable(true);
            v.setOnClickListener(this);

        }



        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Intent k = new Intent(this.ctx, PrimaryNav.class);
            k.putExtra("frgToLoad", 4);
            this.ctx.startActivity(k);



        }
    }


    }



