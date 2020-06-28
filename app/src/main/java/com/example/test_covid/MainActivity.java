package com.example.test_covid;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.test_covid.FAQ.FAQActivity;
import com.example.test_covid.Helpline.HelpLineActivity;
import com.example.test_covid.Hospitals.HospitalActivity;
import com.example.test_covid.MedicalStores.MedicalStoresActivity;
import com.example.test_covid.Model.AboutApp_dialog;
import com.example.test_covid.Model.About_dialog;
import com.example.test_covid.OnlineDoctorAppoinment.OnlineDoctor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    ImageView menu;
    private String status;
    private static final int REQUEST_CALL=1;
    private static final int MAKE_CALL_PERMISSION_REQUEST_CODE=1;

    ImageView faq,back,map;

    TextView title,invisible_title;

    TextView confirmed,recovered,deceased;
    TextView plus_confirmed,plus_recovered,plus_deceased;

    TextView tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTotalDeaths;



    public static Button test;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        firebaseDatabase= FirebaseDatabase.getInstance();

        title=findViewById(R.id.invisible_title);
        back=findViewById(R.id.back);
        invisible_title=findViewById(R.id.title);

        tvCases=findViewById(R.id.con);
        tvRecovered=findViewById(R.id.rec);
        tvTotalDeaths=findViewById(R.id.dec);


        menu=findViewById(R.id.menu);
        faq=findViewById(R.id.faq);
        map=findViewById(R.id.icon1);
        test=findViewById(R.id.testme);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AcceptMessageActivity.class));
            }
        });
        title.setVisibility(View.VISIBLE);
        back.setVisibility(View.INVISIBLE);
        invisible_title.setVisibility(View.INVISIBLE);


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup=new PopupMenu(MainActivity.this,v);
                popup.setOnMenuItemClickListener(MainActivity.this);
                popup.inflate(R.menu.menu);
                popup.show();

            }


        });
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FAQActivity.class));
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MapsActivity.class));
            }
        });
        fetchData();
    }

    public void status(String status){
        databaseReference = firebaseDatabase.getReference("Users").child(auth.getUid());

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status",status);
        databaseReference.updateChildren(hashMap);
    }

    @Override
    protected void onResume() {
        super.onResume();
        status("online");
    }

    @Override
    protected void onPause() {
        super.onPause();
        status("offline");
    }

    private void fetchData() {
        String url  = "https://corona.lmao.ninja/v2/countries/india";

        status="Online";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());

                            tvCases.setText(jsonObject.getString("cases"));
                            tvRecovered.setText(jsonObject.getString("recovered"));
                            tvTotalDeaths.setText(jsonObject.getString("deaths"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.profile:
                startActivity(new Intent(MainActivity.this,profile.class));
                return true;
            case R.id.aboutus:
                openaboutapp();
                return true;
            case R.id.Contact:

                openaboutdialog();
                return true;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;
        }
        return true;
    }

    private void openaboutapp() {
        AboutApp_dialog aboutApp_dialog=new AboutApp_dialog();
        aboutApp_dialog.show(getSupportFragmentManager(),"example about app");
    }

    private void openaboutdialog() {
        About_dialog about_dialog=new About_dialog();
        about_dialog.show(getSupportFragmentManager(),"example dialog");
    }


    public void card1(View view) {
        startActivity(new Intent(MainActivity.this, HospitalActivity.class));
    }

    public void card3(View view) {
        startActivity(new Intent(MainActivity.this, MedicalStoresActivity.class));
    }

    public void card2(View view) {
        startActivity(new Intent(MainActivity.this,OnlineDoctor.class));
    }

    public void card5(View view) {
        startActivity(new Intent(MainActivity.this, HelpLineActivity.class));
    }

    public void data(View view) {
        startActivity(new Intent(MainActivity.this,DataActivity.class));
    }
}