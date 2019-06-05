package com.example.movieapp;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.movieapp.data.model.Showtime;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OneCinema implements  Serializable {
    String nameCine;
    ArrayList<OneCinema> listCineOneday;
    final ArrayList<Button> Showtime;
    Integer whichButtonisOn;
    CharSequence selectedTime;
    Boolean isChosen;
    public Integer getIDofOnButton(){ return whichButtonisOn;}
    public void setButton(int button){whichButtonisOn=button;}
    public OneCinema(String nameCinein, List<Showtime> showtime,Context context,ArrayList<OneCinema> listCineOnedayIn)
    {
        Showtime=new ArrayList<>();
        nameCine=nameCinein;
        for(int i=0;i<showtime.size();i++)
        {
            Button tmp=new Button(context);
            tmp.setText(String.valueOf(showtime.get(i).getTime().getHours())+':'+String.valueOf(showtime.get(i).getTime().getMinutes()));
            Showtime.add(tmp);
        }
        whichButtonisOn=0;
        isChosen=false;
        listCineOneday=listCineOnedayIn;
        selectedTime="";
    }
    public OneCinema(String nameCinein)//default constructor for showtime
    {
        Showtime=new ArrayList<>();
        nameCine=nameCinein;
        isChosen=false;
    }
    public void setDefaultColorForAllButton(Context context)
    {
        View rowView = LayoutInflater.from(context).inflate(R.layout.move_layout, null);
        final ViewGroup layout = (ViewGroup) rowView.findViewById(R.id.list_view_layout);
        int count=0;
        Log.d("numChild",String.valueOf( layout.getChildCount()));
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if(child instanceof Button)
            {
                final Button button=(Button) child;
                button.setText("cc");
                Log.d("buttonText",String.valueOf(button.getText()));
                button.setBackgroundColor(android.R.drawable.btn_default);
                count++;
            }
        }
        rowView.setTag(this);
    }
    public View createAndBind2ItemView(final Context context) {
        View rowView = LayoutInflater.from(context).inflate(R.layout.move_layout, null);
        TextView textViewTitle = (TextView)rowView.findViewById(R.id.cineName);
        textViewTitle.setText(nameCine);
        final ViewGroup layout = (ViewGroup) rowView.findViewById(R.id.list_view_layout);
        //Coloring the selected button and set default color to other buttons
        int count=0;
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            final int tmpi=i;
            if(child instanceof Button)
            {
                final Button button=(Button) child;
                button.setText(Showtime.get(count).getText());
                count++;
                if(button.getId()==whichButtonisOn)
                {
                    button.setBackgroundColor(Color.BLUE);
                }
                else
                {
                    button.setBackgroundColor(android.R.drawable.btn_default);
                }
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        whichButtonisOn=button.getId();
                        selectedTime=button.getText();
                        button.setBackgroundColor(Color.BLUE);
                        for (int j = 0; j < layout.getChildCount(); j++) {
                            View child1 = layout.getChildAt(j);
                            if (child1 != button) {
                                final Button buttontmp = (Button) child1;
                                buttontmp.setBackgroundColor(android.R.drawable.btn_default);
                            }
                        }
                    }
                });


            }
        }
        rowView.setTag(this);
        return rowView;
    }
}
