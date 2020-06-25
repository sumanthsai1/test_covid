package com.example.test_covid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AdminActivity extends AppCompatActivity {

    TextView confirmed,recovered,deceased;
    TextView plus_confirmed,plus_recovered,plus_deceased;

    TextView testing;

    Button update;

    DatabaseReference reference,myreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        confirmed=findViewById(R.id.con);
        plus_confirmed=findViewById(R.id.conn);
        recovered=findViewById(R.id.rec);
        plus_recovered=findViewById(R.id.recc);
        deceased=findViewById(R.id.dec);
        plus_deceased=findViewById(R.id.decc);

        testing=findViewById(R.id.testing);

        update=findViewById(R.id.update);

        myreference = FirebaseDatabase.getInstance().getReference("Active Cases").child("India");

        myreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String test=dataSnapshot.child("Added Cases").getValue().toString();
                testing.setText(test);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String con=confirmed.getText().toString();
                String conn=plus_confirmed.getText().toString();
                String rec=recovered.getText().toString();
                String recc=plus_recovered.getText().toString();
                String dec=deceased.getText().toString();
                String decc=plus_deceased.getText().toString();

                reference = FirebaseDatabase.getInstance().getReference("Active Cases").child("India");

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("Total Cases", con);
                hashMap.put("Added Cases", conn);
                hashMap.put("Total Recovery", rec);
                hashMap.put("Added Recovery", recc);
                hashMap.put("Total Deceased", dec);
                hashMap.put("Added Deceased", decc);


                reference.setValue(hashMap);

            }
        });


    }
}