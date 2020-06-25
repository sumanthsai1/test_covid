package com.example.test_covid.OnBoard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.test_covid.R;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context=context;
    }
    public int[]  slide_images= {
            R.drawable.onb_1,
            R.drawable.onb_2,
            R.drawable.onb_3
    };
    public String[] slide_headings={
            "EAT",
            "CODE",
            "SLEEP"
    };
    public String[]  slide_desc={
            "You can take self-assessment incase if you feel any illness",
            "In self-assessment check whether covid virus is predicted or not and spot your zone to other users",
            "You can see predicted covid virus person zone and if you entered their zone you receive notification and stay alert "
    };
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout)  o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view= layoutInflater.inflate(R.layout.slide_layout,container,false);
        ImageView slideimageview=(ImageView) view.findViewById(R.id.slide_images);
        TextView slideheading=(TextView)view.findViewById(R.id.slide_heading);
        TextView slidedescription=(TextView)view.findViewById(R.id.slide_desc);
        slideimageview.setImageResource(slide_images[position]);
        slideheading.setText(slide_headings[position]);
        slidedescription.setText(slide_desc[position]);
        container.addView(view);



        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}

