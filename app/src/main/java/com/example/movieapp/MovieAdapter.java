package com.example.movieapp;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

class MovieAdapter extends ArrayAdapter<OneCinema> {
    private final Context context;

    public MovieAdapter(Context context, ArrayList<OneCinema> arrayList) {
        super(context, 0, arrayList);
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View v;
            v=createARowFromLayout(R.layout.move_layout);
            OneCinema item = getItem(position);
            v=item.createAndBind2ItemView(context);
            convertView=v;
        return convertView;//super.getView(position, convertView, parent);
    }

//    private void bindDatatoRow(View v, final OneCinema item) {
//        View rowView = LayoutInflater.from(context).inflate(R.layout.move_layout, null);
//        TextView textViewTitle = (TextView)rowView.findViewById(R.id.cineName);
//        textViewTitle.setText(item.nameCine);
//        final ViewGroup layout = (ViewGroup) rowView.findViewById(R.id.list_view_layout);
//        //Coloring the selected button and set default color to other buttons
//        for (int i = 0; i < layout.getChildCount(); i++) {
//            View child = layout.getChildAt(i);
//            if(child instanceof Button)
//            {
//                final Button button = (Button) child;
//                button.setText(item.Showtime.get(i));
//                if(button.getId()==item.whichButtonisOn)
//                {
//                    button.setBackgroundColor(Color.BLUE);
//                }
//                else
//                {
//                    button.setBackgroundColor(android.R.drawable.btn_default);
//                }
//                button.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        item.whichButtonisOn=button.getId();
//                        button.setBackgroundColor(Color.BLUE);
//                        for (int j = 0; j < layout.getChildCount(); j++) {
//                            View child1 = layout.getChildAt(j);
//                            if (child1 != button) {
//                                final Button buttontmp = (Button) child1;
//                                buttontmp.setBackgroundColor(android.R.drawable.btn_default);
//                            }
//                        }
//                    }
//                });
//            }
//        }
//    }

    private View createARowFromLayout(int move_layout) {
        return LayoutInflater.from(this.context).inflate(move_layout, null);
    }
}
