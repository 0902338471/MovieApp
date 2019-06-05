package com.example.movieapp;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
public class MovListOneDay implements Serializable{
    String day;
    String month;
    ArrayList<OneCinema> cinemaList;
    public MovListOneDay(String dayin,String monthin,ArrayList<OneCinema> Listin)
    {
        day=dayin;
        month=monthin;
        cinemaList=new ArrayList<>();
        for(int i=0;i<Listin.size();i++)
        {
            cinemaList.add(Listin.get(i));
        }
    }
//    public View createAndBind2ItemView(final Context context) {
//        View rowView = LayoutInflater.from(context).inflate(R.layout.button_layout, null);
//        final Button btn1 = (Button)rowView.findViewById(R.id.ButtonDate);
//        btn1.setText(month+' '+day);
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                btn1.setBackgroundColor(Color.BLUE);
//            }
//        });
//        rowView.setTag(this);
//        return rowView;
//    }
}
