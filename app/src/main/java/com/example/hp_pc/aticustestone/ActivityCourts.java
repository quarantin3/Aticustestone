package com.example.hp_pc.aticustestone;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;

public class ActivityCourts extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Spinner spinnerc;
    ArrayAdapter adapterc;
    CheckBox callbtn, chatbtn, emailbtn, courtbtn;
    Button gobtn;
    private BoomMenuButton bmb;

    private Context mContext;
    private View mCustomView;
    private boolean isInit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courts);
        ActionBar mActionBar = getSupportActionBar();

        mContext = this;
        gobtn =(Button) findViewById(R.id.gobtncourts);
        gobtn.setOnClickListener(this);
        bmb = (BoomMenuButton) findViewById(R.id.boomcourts);
        bmb.setButtonEnum(ButtonEnum.TextInsideCircle);

        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            bmb.addBuilder(BuilderManager.getTextInsideCircleButtonBuilder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            switch (index) {
                                case 0:
                                    Intent i = new Intent(getApplicationContext(), PrimaryNav.class);
                                    i.putExtra("frgToLoad", 0);
                                    startActivity(i);
                                    break;
                                case 1:
                                    Intent l = new Intent(getApplicationContext(), PrimaryNav.class);
                                    l.putExtra("frgToLoad", 5);
                                    startActivity(l);
                                    break;
                                case 2:
                                    Intent j = new Intent(getApplicationContext(), PrimaryNav.class);
                                    j.putExtra("frgToLoad", 2);
                                    startActivity(j);
                                    break;
                                case 3:
                                    Intent k = new Intent(getApplicationContext(), PrimaryNav.class);
                                    k.putExtra("frgToLoad", 3);
                                    startActivity(k);
                                    break;



                            }
                        }
                    }));

        }

        adapterc = ArrayAdapter.createFromResource(this, R.array.location, android.R.layout.simple_spinner_dropdown_item);
        spinnerc = (Spinner) findViewById(R.id.spinnerloc);
        spinnerc.setAdapter(adapterc);
        spinnerc.setOnItemSelectedListener(ActivityCourts.this);
        spinnerc.setPrompt("Enter Location");


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        if(view==gobtn){
            Intent i = new Intent(this, PrimaryNav.class);
            i.putExtra("frgToLoad", 2);
            startActivity(i);
        }
    }
}
