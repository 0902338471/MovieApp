package com.example.movieapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class MovieOnDay implements Serializable{
    String CinemaName;
    ArrayList<String> ListHour;
    public MovieOnDay(String cinemaName,ArrayList<String> ListTime){//Input Movie Time
        CinemaName=cinemaName;
//        this.ListHour = ListTime;
        ListHour=new ArrayList<>();
        for(int i=0;i<ListTime.size();i++)
        {
            ListHour.add(ListTime.get(i));
        }
    }
    public MovieOnDay(String cinemaName){//random Movie Time
        ListHour=new ArrayList<>();
        CinemaName=cinemaName;
        ListHour.add("9:40");
        ListHour.add("9:40");
        ListHour.add("9:40");
        ListHour.add("9:40");
    }
}
