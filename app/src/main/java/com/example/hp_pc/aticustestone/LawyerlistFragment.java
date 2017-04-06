package com.example.hp_pc.aticustestone;

import android.os.Bundle;
import android.provider.Contacts;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hp-PC on 12/24/2016.
 */
public class LawyerlistFragment extends Fragment {

    private RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    private Toolbar toolbar;
    ArrayList<Contacts> list = new ArrayList<>();

    ArrayList<HashMap<String, String>> lawyerlist = new ArrayList<>();




    public static LawyerlistFragment newInstance() {
        LawyerlistFragment lawyerlistFragment = new LawyerlistFragment();
        return lawyerlistFragment;
    }

    private void searchLawyer(final String law, final String commtry) {

        //String url = "https://ec2.xynocast.com/gcm_chat/v1/index.php/user/search";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.URL_SEARCHLAW,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(obj.getBoolean("error") == false) {


                                JSONArray lawyers = obj.getJSONArray("lawyers");

                                for (int i = 0; i < lawyers.length(); i++) {
                                    JSONObject l = lawyers.getJSONObject(i);

                                    String User_Id = l.getString("User_Id");
                                    String email = l.getString("Email");
                                    String name = l.getString("Name");
                                    String barid = l.getString("barid");
                                    String location = l.getString("location");
                                    String rating = l.getString("rating");
                                    String photo = l.getString("photo");

//                                    lawyerlist.add(User_Id);
                                    HashMap<String, String> testlaw = new HashMap<>();

                                    //testlaw.clear();
                                    testlaw.put("User_Id", User_Id);
                                    testlaw.put("name", name);
                                    testlaw.put("Email", email);
                                    testlaw.put("barid", barid);
                                    testlaw.put("location", location);
                                    testlaw.put("rating", rating);
                                    testlaw.put("photo", photo);


                                    lawyerlist.add(i, testlaw);
                                    Log.d("testlaw" , testlaw + "");

//                                   Toast.makeText(MainActivity.this, testlaw.get("User_Id" + ""), Toast.LENGTH_LONG).show();
                                }
                               Log.d("val", lawyerlist.get(1) + "");

                                recyclerView.setAdapter(new LawyerAdapterRecycler(getActivity(), lawyerlist));
//                                adapter.notifyDataSetChanged();




                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("lawField", law);
                params.put("modeComm", commtry);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_laywerlist, container, false);
        String comm = SharedPrefManager.getInstance(getContext()).getComm();
        int law = SharedPrefManager.getInstance(getContext()).getSearchField();

        searchLawyer(String.valueOf(law), comm);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.lawyerrecycler);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Log.d("something", "" + lawyerlist.size());

        return rootView;
    }




}