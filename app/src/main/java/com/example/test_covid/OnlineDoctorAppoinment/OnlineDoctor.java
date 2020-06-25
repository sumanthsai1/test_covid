package com.example.test_covid.OnlineDoctorAppoinment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test_covid.MainActivity;
import com.example.test_covid.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class OnlineDoctor extends AppCompatActivity {

    DatabaseReference reference;
    FirebaseAuth auth;
    private static final int DATE_PICKER_ID = 1;
    private EditText patient_name,email,age,dob,ph_no,address;
    ImageView calender;
    Spinner symptoms;
    RadioButton male, female;
    TextView selected_gender,selected_symp;
    RadioGroup radio;
    String symptomname;
    int year;
    int month;
    int day;
    Button appoinment;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_doctor);

        back=findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnlineDoctor.this,MainActivity.class));
            }
        });

        patient_name = findViewById(R.id.username);
        email = findViewById(R.id.email);
        age = findViewById(R.id.password);
        dob = findViewById(R.id.date);
        ph_no=findViewById(R.id.mobile);
        address=findViewById(R.id.address);
        appoinment=findViewById(R.id.appoinment);
        calender = findViewById(R.id.calender);
        symptoms = findViewById(R.id.spinner);
        radio=findViewById(R.id.radio);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        selected_gender=findViewById(R.id.selected_gender);
        selected_symp=findViewById(R.id.selected_symp);

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (male.isChecked())
                {
                    selected_gender.setText("Male");
                }
                else
                {
                    selected_gender.setText("Female");
                }
            }
        });


        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.symptoms, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        symptoms.setAdapter(adapter);
        symptoms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                symptomname = parent.getItemAtPosition(position).toString();
                String symp = symptomname.replaceAll("\\s","").toLowerCase().toString();

                selected_symp.setText(symptomname);





            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
        calender.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                SelectDate();
            }
        });

        appoinment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_patient=patient_name.getText().toString();
                String txt_email=email.getText().toString();
                String txt_age=age.getText().toString();
                String txt_dob=dob.getText().toString();
                String txt_symptom=selected_symp.getText().toString();
                String txt_gender=selected_gender.getText().toString();
                String txt_mobile=ph_no.getText().toString();
                String txt_address=address.getText().toString();

                if (TextUtils.isEmpty(txt_patient) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_age)  || TextUtils.isEmpty(txt_dob)  || TextUtils.isEmpty(txt_symptom)  || TextUtils.isEmpty(txt_gender)  || TextUtils.isEmpty(txt_mobile)  || TextUtils.isEmpty(txt_address)) {
                    Toast.makeText(OnlineDoctor.this, "All fileds are required", Toast.LENGTH_SHORT).show();
                }
                else if (txt_address.length() < 20 ) {
                    Toast.makeText(OnlineDoctor.this, "address must be at least 20 characters", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    sendMail();
                    register(txt_patient,txt_email,txt_age,txt_address,txt_dob,txt_gender,txt_symptom,txt_mobile);

                    //toast();
                }
            }
        });
    }

    private void register(String patient,String email,String age,String address,String dob,String gender,String symptom,String mobile) {



        reference = FirebaseDatabase.getInstance().getReference("Patient Details-ODA");

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Patient name", patient);
        hashMap.put("Email", email);
        hashMap.put("Age", age);
        hashMap.put("DOB", dob);
        hashMap.put("Gender", gender);
        hashMap.put("Symptom", symptom);
        hashMap.put("Phone Number", mobile);
        hashMap.put("Address", address);

        reference.setValue(hashMap);

        startActivity(new Intent(OnlineDoctor.this,MainActivity.class));
    }

    private void toast() {
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
    }

    private void sendMail() {
        String message="Patient data has been received and the appointment has fixed.Please wait for the further information";
        String subject="Online Doctor Appointment";
        String mail = email.getText().toString().trim();


        //Send Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,mail,subject,message);

        javaMailAPI.execute();

    }







    @RequiresApi(api = Build.VERSION_CODES.N)
    private void SelectDate() {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);


        showDialog(DATE_PICKER_ID);
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:

                // open datepicker dialog.
                // set date picker for current date
                // add pickerListener listner to date picker
                return new DatePickerDialog(this, pickerListener, year, month,day);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;

            // Show selected date
            dob.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

        }
    };
}

