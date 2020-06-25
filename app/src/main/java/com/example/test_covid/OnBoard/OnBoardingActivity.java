package com.example.test_covid.OnBoard;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.test_covid.LoginActivity;
import com.example.test_covid.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class OnBoardingActivity extends AppCompatActivity {
    private ViewPager mslideviewpager;
    private LinearLayout mdots;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;

    private Button next,back;

    int mCurrentPage;
    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //check if user is null
        if (firebaseUser != null){
            Intent intent = new Intent(OnBoardingActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        mslideviewpager=(ViewPager) findViewById(R.id.slideviewpager1);

        next=findViewById(R.id.next);
        back=findViewById(R.id.back);
        mdots=(LinearLayout) findViewById(R.id.dots);
        sliderAdapter=new SliderAdapter(this);
        mslideviewpager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        mslideviewpager.addOnPageChangeListener(viewListener);



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mslideviewpager.setCurrentItem(mCurrentPage+1);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mslideviewpager.setCurrentItem(mCurrentPage-1);
            }
        });



        //startActivity(new Intent(MainActivity.this,NextActivity.class));
    }

    private void addDotsIndicator(int position) {
        mDots = new TextView[3];
        mdots.removeAllViews();


        for(int i =0;i <mDots.length;i++){
            mDots[i]=new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            mDots[i].setTextSize(35);
            mdots.addView(mDots[i]);
        }
        if (mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        }
    }

    ViewPager.OnPageChangeListener viewListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            mCurrentPage = position;

            if (position==0)
            {
                next.setEnabled(true);
                back.setEnabled(false);
                back.setVisibility(View.INVISIBLE);

                next.setText("Next");
                back.setText("");
            } else if (position==mDots.length-1) {
                next.setEnabled(true);
                back.setEnabled(true);
                back.setVisibility(View.VISIBLE);

                next.setText("Finish");
                back.setText("Back");
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(OnBoardingActivity.this, LoginActivity.class));
                    }
                });
            }else {
                next.setEnabled(true);
                back.setEnabled(true);
                back.setVisibility(View.VISIBLE);

                next.setText("Next");
                back.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}

