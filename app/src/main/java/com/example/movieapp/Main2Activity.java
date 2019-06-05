package com.example.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    int numColumn=10;
    private int counter;
    int numRow=11;
    int numticket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seatbookinglayout);
        ArrayList<ArrayList<Seat>> twoDimensionalSeats=new ArrayList<>();
        Intent intent=getIntent();
        GridLayout gridLayout=getGridLayout();
        TextView movienameSeat=findViewById(R.id.movieNameseat);
        movienameSeat.setText(intent.getStringExtra("MovieInfo"));
        TextView cinenameSeat=findViewById(R.id.cineNameseat);
        cinenameSeat.setText(intent.getStringExtra("CineName"));
        TextView time=findViewById(R.id.timeShowingseat);
        time.setText(intent.getStringExtra("Time"));
        final TextView ticket=findViewById(R.id.NumTickets);
        int count=1;
        for(int i=0;i<numRow;i++)
        {
            ArrayList<Seat> rowtmp=new ArrayList<>();
            for(int j=0;j<numColumn;j++)
            {
                final Seat oneseat =new Seat(this);
                oneseat.setIndex(i,j);
                oneseat.setState(false,false);
                oneseat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(oneseat.getIsBooked()==false)
                        {
                            if(oneseat.getIsSelected()==true)
                            {
                                oneseat.setText("");
                                oneseat.setState(false,false);
                                numticket-=1;
                                ticket.setText("Number of ticket:"+String.valueOf(numticket));
                            }
                            else
                            {
                                oneseat.setText("Selected");
                                oneseat.setState(true,false);
                                numticket+=1;
                                ticket.setText("Number of ticket:"+String.valueOf(numticket));
                            }
                        }
                    }
                });
                addButtontoGridLayout(oneseat,gridLayout);
                count++;
                rowtmp.add(oneseat);
            }
            twoDimensionalSeats.add(rowtmp);
        }

    }

    private void addButtontoGridLayout(Button btn, GridLayout gridLayout) {
        gridLayout.addView(btn);
    }

    private Button createButton(String i) {
        Button button=new Button(this);
        button.setText(i);
        button.setId(20190602+ counter++);
        return button;
    }

    private GridLayout getGridLayout() {
        return (GridLayout)findViewById(R.id.gridViewMain);
    }
}
