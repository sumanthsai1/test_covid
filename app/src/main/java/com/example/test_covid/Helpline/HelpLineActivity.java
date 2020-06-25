package com.example.test_covid.Helpline;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.test_covid.MainActivity;
import com.example.test_covid.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

public class HelpLineActivity extends AppCompatActivity {

    private TextView helpline;
    private Spinner spinner;
    private String stateName;
    FirebaseDatabase firebaseDatabase;
    private Button select;
    private DatabaseReference databaseReference;
    private String contactNumber;
    private ImageView back;

    private static final int REQUEST_CALL=1;
    private static final int MAKE_CALL_PERMISSION_REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_line);
        helpline = (TextView) findViewById(R.id.helpline);
        spinner = (Spinner) findViewById(R.id.spinner);

        back=findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HelpLineActivity.this, MainActivity.class));
            }
        });
        if(checkPermission(Manifest.permission.CALL_PHONE)){
            helpline.setEnabled(true);
        }
        else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},MAKE_CALL_PERMISSION_REQUEST_CODE);
        }

        firebaseDatabase = FirebaseDatabase.getInstance();

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.states, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stateName = parent.getItemAtPosition(position).toString();
                String stateNameLower = stateName.replaceAll("\\s","").toLowerCase().toString();

                if (stateNameLower.equalsIgnoreCase("selectthestate")
                        || stateNameLower.equalsIgnoreCase("----unionterritory----"))
                {
                    helpline.setText("Please select a contact");

                }
                else
                    {

                    Toast.makeText(HelpLineActivity.this,stateNameLower,Toast.LENGTH_SHORT).show();
                    databaseReference = firebaseDatabase.getReference("states").child(stateNameLower);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            PhoneNoDetails phoneNoDetails = dataSnapshot.getValue(PhoneNoDetails.class);
                            contactNumber = phoneNoDetails.getPhone();
                            helpline.setText(phoneNoDetails.getPhone());

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        helpline.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                String contact = "tel:" + contactNumber;
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(contact));
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });
    }

    private boolean checkPermission(String permission){
        return ContextCompat.checkSelfPermission(this,permission) == PERMISSION_GRANTED;
    }


}
