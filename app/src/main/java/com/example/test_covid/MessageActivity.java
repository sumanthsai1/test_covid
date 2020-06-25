package com.example.test_covid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MessageActivity extends Activity {

    Button q1_yes,q1_no,q2_yes,q2_no,q3_yes,q3_no,q4_yes,q4_no,q5_yes,q5_no,q61_yes,q61_no,q41_yes,q41_no;
    TextView a1,a2,a3,a4,a5,a6,a7,a8,a9,a10;
    TextView q1,q2,q3,q4,q5,q6,q7,q8,q61,q9,q10,q41;
    ImageView back;

    DatabaseReference reference;
    FirebaseAuth auth;

    String ans1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);



        back=findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MessageActivity.this,MainActivity.class));
            }
        });


        //button
        q1_yes=findViewById(R.id.q1_yes);
        q1_no=findViewById(R.id.q1_no);
        q2_yes=findViewById(R.id.q2_yes);
        q2_no=findViewById(R.id.q2_no);
        q3_yes=findViewById(R.id.q3_yes);
        q3_no=findViewById(R.id.q3_no);
        q4_yes=findViewById(R.id.q4_yes);
        q4_no=findViewById(R.id.q4_no);
        q5_yes=findViewById(R.id.q5_yes);
        q5_no=findViewById(R.id.q5_no);
        q61_yes=findViewById(R.id.q61_yes);
        q61_no=findViewById(R.id.q61_no);
        q41_yes=findViewById(R.id.q41_yes);
        q41_no=findViewById(R.id.q41_no);

        //textview
        a1=findViewById(R.id.a1);
        a2=findViewById(R.id.a2);
        a3=findViewById(R.id.a3);
        a4=findViewById(R.id.a4);
        a5=findViewById(R.id.a5);
        a6=findViewById(R.id.a6);
        a7=findViewById(R.id.a7);
        a8=findViewById(R.id.a8);
        a9=findViewById(R.id.a9);
        a10=findViewById(R.id.a10);

        //questions
        q2=findViewById(R.id.q2);
        q3=findViewById(R.id.q3);
        q4=findViewById(R.id.q4);
        q5=findViewById(R.id.q5);
        q6=findViewById(R.id.q6);
        q7=findViewById(R.id.q7);
        q8=findViewById(R.id.q8);
        q61=findViewById(R.id.q61);
        q41=findViewById(R.id.q41);
        q9=findViewById(R.id.q9);
        q10=findViewById(R.id.q10);




        q1_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a1.setVisibility(View.VISIBLE);
                q1_yes.setVisibility(View.INVISIBLE);
                q1_no.setVisibility(View.INVISIBLE);
                q2.setVisibility(View.VISIBLE);
                q2_yes.setVisibility(View.VISIBLE);
                q2_no.setVisibility(View.VISIBLE);


            }
        });
        q1_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q1_no.setVisibility(View.INVISIBLE);
                q1_yes.setVisibility(View.INVISIBLE);
                a2.setVisibility(View.VISIBLE);
                q3.setVisibility(View.VISIBLE);
                q3_yes.setVisibility(View.VISIBLE);
                q3_no.setVisibility(View.VISIBLE);

            }
        });
        q2_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q2_no.setVisibility(View.INVISIBLE);
                q2_yes.setVisibility(View.INVISIBLE);
                a3.setVisibility(View.VISIBLE);
                q4.setVisibility(View.VISIBLE);
                q4_yes.setVisibility(View.VISIBLE);
                q4_no.setVisibility(View.VISIBLE);


            }
        });
        q2_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q2_no.setVisibility(View.INVISIBLE);
                q2_yes.setVisibility(View.INVISIBLE);
                a4.setVisibility(View.VISIBLE);
                q5.setVisibility(View.VISIBLE);
                q5_yes.setVisibility(View.VISIBLE);
                q5_no.setVisibility(View.VISIBLE);

            }
        });
        q3_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q3_no.setVisibility(View.INVISIBLE);
                q3_yes.setVisibility(View.INVISIBLE);
                a5.setVisibility(View.VISIBLE);
                q5.setVisibility(View.VISIBLE);
                q5_yes.setVisibility(View.VISIBLE);
                q5_no.setVisibility(View.VISIBLE);
            }
        });
        q3_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q3_no.setVisibility(View.INVISIBLE);
                q3_yes.setVisibility(View.INVISIBLE);
                a6.setVisibility(View.VISIBLE);
                q8.setVisibility(View.VISIBLE);
                Toast.makeText(MessageActivity.this, "The Covid-19 is not predicted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MessageActivity.this,MainActivity.class));


            }
        });
        q4_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q4_no.setVisibility(View.INVISIBLE);
                q4_yes.setVisibility(View.INVISIBLE);
                a7.setVisibility(View.VISIBLE);
                q6.setVisibility(View.VISIBLE);
                Toast.makeText(MessageActivity.this, "The Covid-19 is predicted", Toast.LENGTH_SHORT).show();
                //MainActivity.test.setVisibility(View.GONE);
                save();
                startActivity(new Intent(MessageActivity.this,settingGeoFence.class));
            }
        });
        q4_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q4_no.setVisibility(View.INVISIBLE);
                q4_yes.setVisibility(View.INVISIBLE);
                a8.setVisibility(View.VISIBLE);
                q7.setVisibility(View.VISIBLE);
                Toast.makeText(MessageActivity.this, "The Covid-19 is not predicted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MessageActivity.this,MainActivity.class));


            }
        });
        q5_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q5_no.setVisibility(View.INVISIBLE);
                q5_yes.setVisibility(View.INVISIBLE);
                a7.setVisibility(View.VISIBLE);
                q41.setVisibility(View.VISIBLE);
                q41_no.setVisibility(View.VISIBLE);
                q41_yes.setVisibility(View.VISIBLE);

            }
        });
        q5_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q5_no.setVisibility(View.INVISIBLE);
                q5_yes.setVisibility(View.INVISIBLE);
                a8.setVisibility(View.VISIBLE);
                q7.setVisibility(View.VISIBLE);
                Toast.makeText(MessageActivity.this, "The Covid-19 is not predicted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MessageActivity.this,MainActivity.class));

            }
        });
        q61_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q61_no.setVisibility(View.INVISIBLE);
                q61_yes.setVisibility(View.INVISIBLE);
                a9.setVisibility(View.VISIBLE);
                q9.setVisibility(View.VISIBLE);
                MainActivity.test.setVisibility(View.GONE);
                Toast.makeText(MessageActivity.this, "The Covid-19 is predicted", Toast.LENGTH_SHORT).show();
                save();
                startActivity(new Intent(MessageActivity.this,settingGeoFence.class));


            }
        });
        q61_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q5_no.setVisibility(View.INVISIBLE);
                q5_yes.setVisibility(View.INVISIBLE);
                a10.setVisibility(View.VISIBLE);
                q10.setVisibility(View.VISIBLE);
                Toast.makeText(MessageActivity.this, "The Covid-19 is not predicted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MessageActivity.this,MainActivity.class));

            }
        });
        q41_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q41_no.setVisibility(View.INVISIBLE);
                q41_yes.setVisibility(View.INVISIBLE);
                a9.setVisibility(View.VISIBLE);
                q9.setVisibility(View.VISIBLE);
                MainActivity.test.setVisibility(View.GONE);
                Toast.makeText(MessageActivity.this, "The Covid-19 is predicted", Toast.LENGTH_SHORT).show();
                save();
                startActivity(new Intent(MessageActivity.this,settingGeoFence.class));

            }
        });
        q41_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q41_no.setVisibility(View.INVISIBLE);
                q41_yes.setVisibility(View.INVISIBLE);
                a10.setVisibility(View.VISIBLE);
                q10.setVisibility(View.VISIBLE);
                Toast.makeText(MessageActivity.this, "The Covid-19 is not predicted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MessageActivity.this,MainActivity.class));

            }
        });


    }

    private void save() {


        reference= FirebaseDatabase.getInstance().getReference("Predicted Person");

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("status", "affected");

        reference.setValue(hashMap);


    }

    public void refresh(View view) {
        startActivity(new Intent(MessageActivity.this,MessageActivity.class));
    }
}
