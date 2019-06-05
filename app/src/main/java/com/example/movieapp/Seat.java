package com.example.movieapp;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;

public class Seat extends android.support.v7.widget.AppCompatButton {
    int rowindex;
    int columnindex;
    Boolean isSelected;
    Boolean isBooked;
    public Seat(Context context)
    {
        super(context);
    }
    public void setIndex(int rowIn,int columnIn)
    {
        rowindex=rowIn;
        columnindex=columnIn;
    }
    public void setState(Boolean selectIn,Boolean BookedIn)
    {
        isBooked=BookedIn;
        isSelected=selectIn;
    }


    public Boolean getIsSelected()
    {
        return isSelected;
    }
    public Boolean getIsBooked()
    {
        return isBooked;
    }

    public int getRowindex() {
        return rowindex;
    }
    public int getColumnindex(){
        return columnindex;
    }
}
