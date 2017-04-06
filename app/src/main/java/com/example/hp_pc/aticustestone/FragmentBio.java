package com.example.hp_pc.aticustestone;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBio extends Fragment implements View.OnClickListener {

    CardView chatbtn, callbtna;

    public static FragmentBio newInstance() {

        FragmentBio fragmentbio = new FragmentBio();
        return fragmentbio;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview = inflater.inflate(R.layout.fragment_fragment_bio, container, false);
        chatbtn = (CardView) myview.findViewById(R.id.chtbtna);
        callbtna = (CardView) myview.findViewById(R.id.callbtna);
        chatbtn.setOnClickListener(this);
        callbtna.setOnClickListener(this);
        return myview;
    }

    @Override
    public void onClick(View view) {
        if (view==chatbtn){
            Intent i = new Intent(getContext(), PrimaryNav.class);
            i.putExtra("frgToLoad", 5);
            startActivity(i);
        }
        if (view==callbtna){
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:8147022356"));
            startActivity(intent);
        }

    }
}
