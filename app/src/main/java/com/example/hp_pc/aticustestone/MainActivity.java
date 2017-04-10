package com.example.hp_pc.aticustestone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener, Toolbar.OnMenuItemClickListener {

    Spinner spinner, spinnercourts;
    ArrayAdapter adapter, adapterc;

    private BoomMenuButton boomMenuButton;
    private BoomMenuButton boomMenuInActionBar;
    private BoomMenuButton boomInfo;



    private Context mContext;
    private View mCustomView;
    private boolean isInit = false;
    private Button btn;
    private boolean  courts;
    private int lawchoice, testrloc;
    String spinnval, email, comm, spinnvalcourts;

    ArrayList<HashMap<String, String>> lawyerlist = new ArrayList();
    HashMap<String, String> testlaw = new HashMap<String, String >();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbarser = (Toolbar) findViewById(R.id.toolbarone);
       // toolbarser.setTitle("Atticus");
        setSupportActionBar(toolbarser);
        getSupportActionBar().setTitle("Atticus");
        getSupportActionBar().setIcon(R.drawable.ic_logosize);

        RadioGroup rgg = (RadioGroup) findViewById(R.id.Radiorexgroup);
        rgg.check(R.id.radioboth);
        comm = "both";
        rgg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radiocall:
                        comm = "call";
                        break;
                    case R.id.radiochat:
                        comm = "chat";
                        break;
                    case R.id.radioboth:
                        comm = "both";
                        break;
                }
            }
        });






        mContext = this;
        btn = (Button) findViewById(R.id.gobtn);
        btn.setOnClickListener(this);


        String chklog = SharedPrefManager.getInstance(this).getDeviceEmail();
        if(chklog==null){
            startActivity(new Intent(this, Testactivity.class));
        }



        boomMenuButton = (BoomMenuButton) findViewById(R.id.boom);
        boomMenuButton.setButtonEnum(ButtonEnum.TextInsideCircle);

        Button btn = (Button)findViewById(R.id.testbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ChatsActivity.class);
                startActivity(i);
            }
        });


        for (int i = 0; i < boomMenuButton.getPiecePlaceEnum().pieceNumber(); i++) {
            boomMenuButton.addBuilder(BuilderManager.getTextInsideCircleButtonBuilder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            switch (index) {
                                case 0:
                                    Intent i = new Intent(getApplicationContext(), PrimaryNav.class);
                                    i.putExtra("frgToLoad", 0);//dashboard fragment reffer primaryNav.class
                                    startActivity(i);
                                    break;
                                case 1:
                                    Intent l = new Intent(getApplicationContext(), PrimaryNav.class);
                                    l.putExtra("frgToLoad", 5);//chatsapp fragment
                                    startActivity(l);
                                    break;
                                case 2:
                                    Intent j = new Intent(getApplicationContext(), PrimaryNav.class);
                                    j.putExtra("frgToLoad", 2); //lawyerlistfragment
                                    startActivity(j);
                                    break;
                                case 3:
                                    Intent k = new Intent(getApplicationContext(), Aboutatticus.class);
                                    //k.putExtra("frgToLoad", 3);//directs to About Atticus page
                                    startActivity(k);
                                    break;
                            }
                        }
                    }));

        }


        adapter = ArrayAdapter.createFromResource(this, R.array.field_option, android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Field of Law");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnval = (String) parent.getItemAtPosition(position);
                switch (spinnval) {
                    case "Adoption":
                        lawchoice = 1;
                        Toast.makeText(getBaseContext(), String.valueOf(lawchoice), Toast.LENGTH_LONG).show();
                        break;
                    case "Banking & Insurance":
                        lawchoice = 2;
                        Toast.makeText(getBaseContext(), String.valueOf(lawchoice), Toast.LENGTH_LONG).show();
                        break;
                    case "Business & Commercial":
                        lawchoice = 3;
                        Toast.makeText(getBaseContext(), String.valueOf(lawchoice), Toast.LENGTH_LONG).show();
                        break;
                    case "Law Procedure":
                        lawchoice = 4;
                        break;
                    case "Consumer Law":
                        lawchoice = 5;
                        break;
                    case "Corporate":
                        lawchoice = 6;
                        break;
                    case "Criminal":
                        lawchoice = 7;
                        break;
                    case "Divorce":
                        lawchoice = 8;
                        break;
                    case "Enviroment":
                        lawchoice = 9;
                        break;
                    case "Government Policies":
                        lawchoice = 10;
                        break;
                    case "Inheritance":
                        lawchoice = 11;
                        break;
                    case "Labour Law":
                        lawchoice = 12;
                        break;
                    case "Marriage":
                        lawchoice = 13;
                        break;
                    case "Personal":
                        lawchoice = 14;
                        break;
                    case "Property":
                        lawchoice = 15;
                        break;
                    case "Real Estate":
                        lawchoice = 16;
                        break;
                }
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapterc = ArrayAdapter.createFromResource(this, R.array.location, android.R.layout.simple_spinner_dropdown_item);
        spinnercourts = (Spinner) findViewById(R.id.spinnercourts2);
        spinnercourts.setAdapter(adapterc);
        spinnercourts.setPrompt("Enter Location");
        spinnercourts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                spinnvalcourts = (String) parent.getItemAtPosition(position);
                switch (spinnvalcourts) {
                    case "New Delhi":
                        testrloc = 1;
                        Toast.makeText(getBaseContext(), String.valueOf(lawchoice), Toast.LENGTH_LONG).show();
                        break;
                    case "Mumbai":
                        testrloc = 2;
                        Toast.makeText(getBaseContext(), String.valueOf(lawchoice), Toast.LENGTH_LONG).show();
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });




}


    private void initViews() {

        }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;

    }

//    public void onRadioButtonClicked(View view) {
//        boolean checked = ((RadioButton) view).isChecked();
//        switch (view.getId()) {
//            case R.id.radiocall:
//                if(checked){
//                    comm = "call";
//                }
//                break;
//            case R.id.radiochat:
//                if(checked){
//                    comm = "chat";
//                }
//                break;
//            case R.id.radioboth:
//                if(checked){
//                    comm = "both";
//                    Toast.makeText(this, comm, Toast.LENGTH_SHORT).show();
//                }
//                break;
//        }
//        return;
//    }



    public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();

        if(view.getId()==R.id.high) {
            if (checked) {
                courts = true;
            }
            else {
                courts = false;
            }
        }
    }




    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if(isInit) return;
        isInit = true;

        initBoom();

    }



    public void storeComm(String commm) {
        SharedPrefManager.getInstance(getApplicationContext()).saveComm(commm);
    }

    public void storeSearchField(int field) {
        SharedPrefManager.getInstance(getApplicationContext()).saveSearchfield(field);
    }




    @Override
    public void onClick(View view) {
        if(view == btn) {
            String email = SharedPrefManager.getInstance(this).getDeviceEmail();
            if (courts) {
                storeComm(comm);
                storeSearchField(lawchoice);
                startActivity(new Intent(this, ActivityCourts.class));
            } else {
                storeComm(comm);
                storeSearchField(lawchoice);
                Intent i = new Intent(this, PrimaryNav.class);
                i.putExtra("frgToLoad", 2);
                startActivity(i);
                }
            }

        }



    private void initBoom() {

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



}

}


