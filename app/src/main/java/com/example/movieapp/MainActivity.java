package com.example.movieapp;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.graphics.Typeface;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.movieapp.data.CinemaShowtimeRepository;
import com.example.movieapp.data.DummyCinemaShowtimeDataSource;
import com.example.movieapp.data.model.MovieInfo;
import com.example.movieapp.data.model.Showtime;
public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    static final ArrayList<Integer> myListViewData=new ArrayList<>();
    static Button selectedDate;
    static OneCinema selectedCinema;
    ArrayList<OneCinema> listCineOneday;
    DummyCinemaShowtimeDataSource dataSource;
    CinemaShowtimeRepository dataRepository;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataSource = DummyCinemaShowtimeDataSource.getInstance();
        dataRepository = CinemaShowtimeRepository.getInstance(dataSource);

        //    get the sample movie information data from data repository
        MovieInfo movie = dataRepository.getMovieInfo();
        Log.d(TAG, String.format("Movie title: %s", movie.getMovieTitle()));

        //    get list of date from data repository with start date. It will get 10 dates from dummy
        // data source
        Date startDate = new Date(2019, 05, 28);
        List<Date> listDates = dataRepository.getListDates(startDate);
        for (Date date : listDates) {
            Log.d(
                    TAG,
                    String.format("d/M/y = %d / %d / %d ;", date.getDate(), date.getMonth(), date.getYear()));
        }

        //    get list of showtimes from data repository with starting time. It will get 10 showtimes
        // from dummy data source
        Date startTime = new Date(0, 0, 0, 10, 30);
        List<Showtime> listTimes = dataRepository.getListShowtimes(startTime);
        for (Showtime time : listTimes) {
            Log.d(
                    TAG,
                    String.format(
                            "h:m = %d : %d; is available = %b",
                            time.getTime().getHours(), time.getTime().getMinutes(), time.isAvailable()));
        }
        MovieListShowing(listDates,listTimes,movie);
    }
//listTimes of all cine are the same
    private void MovieListShowing(final List<Date> listDates, List<Showtime> listTimes, final MovieInfo movie) {
//        ListView listView1=(ListView)findViewById(R.id.DateListView);
        ListView listView2=(ListView)findViewById(R.id.CineListView);
//        ArrayList<MovListOneDay> listDays=new ArrayList<>();
        listCineOneday=new ArrayList<OneCinema>();//add data
        listCineOneday.add(new OneCinema("Galaxy Quang Trung",listTimes,this,listCineOneday));
        listCineOneday.add(new OneCinema("Galaxy Trung Chanh",listTimes,this,listCineOneday));
        listCineOneday.add(new OneCinema("Lotte Phan Van Tri",listTimes,this,listCineOneday));
        listCineOneday.add(new OneCinema("Lotte Nguyen Van Luong",listTimes,this,listCineOneday));
        listCineOneday.add(new OneCinema("Galaxy Nguyen Van Qua",listTimes,this,listCineOneday));
        for(int i=0;i<myListViewData.size();i++)
        {
            listCineOneday.get(i).setButton(myListViewData.get(i));
        }
        View rowView = LayoutInflater.from(this).inflate(R.layout.activity_main,null);
        final ViewGroup layout = (ViewGroup) rowView.findViewById(R.id.CineListView);
        buttonDate(listDates);
        TextView movTitle=findViewById(R.id.filmname);
        movTitle.setText(movie.getMovieTitle());
        TextView ratingMov=findViewById(R.id.rating);
        ratingMov.setText(String.valueOf(movie.getVoteScore()));
        TextView duration=findViewById(R.id.MovieTime);
        duration.setText(String.valueOf(movie.getRuntime()));
        TextView showType=findViewById(R.id.showType);
        showType.setText(movie.getMovieFormat());
        TextView filmgenres=findViewById(R.id.genres);
        String text="Genres:";
        for(int i=0;i<movie.getListGenres().size();i++)
        {
            text+=movie.getListGenres().get(i)+" ";
        }
        filmgenres.setText(text);
        TextView sysnosis=findViewById(R.id.sysnosis);
        sysnosis.setText(movie.getSynopsis());
        MovieAdapter movieAdapter = new MovieAdapter(this, listCineOneday);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Next Activity");
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("MovieInfo",movie.getMovieTitle());
                intent.putExtra("Time",selectedCinema.selectedTime);
                intent.putExtra("CineName",selectedCinema.nameCine);
                startActivity(intent);
            }
        });
        final Context context=this;
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OneCinema chosenItem=(OneCinema)parent.getAdapter().getItem(position);
                selectedCinema=chosenItem;
                Log.d("ChosenItem",chosenItem.nameCine);
                for(OneCinema ele:listCineOneday)
                {
                    if(ele.nameCine!=chosenItem.nameCine)
                    {
                        ele.setDefaultColorForAllButton(context);
                    }
                }
            }
        });
        listView2.setAdapter(movieAdapter);
    }

    private void resetAllButtonExceptThisOne(Button exceptButton) {
        View rowView = LayoutInflater.from(this).inflate(R.layout.activity_main,null);
        final ViewGroup layout = (ViewGroup) rowView.findViewById(R.id.CineListView);
        Log.d("Count",String.valueOf(layout.getChildCount()));
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if(child instanceof Button)
            {
                final Button button = (Button) child;
                if(button==exceptButton)
                {
                    button.setBackgroundColor(Color.BLUE);
                }
                else
                {
                    button.setBackgroundColor(android.R.drawable.btn_default);
                }
            }
        }

    }

    private void buttonDate(List<Date> listdates) {
        View rowView = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        final ViewGroup layout = (ViewGroup)findViewById(R.id.my_movie);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if(child instanceof Button)
            {
                final Button button = (Button) child;
                button.setText(listdates.get(i).getDate()+"/"+listdates.get(i).getMonth());
                button.setTextSize(20);
                if(selectedDate!=null)
                {
                    if(button.getId()==selectedDate.getId())
                    {
                        button.setBackgroundColor(Color.BLUE);
                    }
                    else
                    {
                        button.setBackgroundColor(android.R.drawable.btn_default);
                    }
                }
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        button.setBackgroundColor(Color.BLUE);
                        selectedDate=button;
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
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(myListViewData.size()==0)
        {
            for(int i=0;i<listCineOneday.size();i++)
            {
                myListViewData.add(new Integer(listCineOneday.get(i).getIDofOnButton()));
                Log.d("Item "+ String.valueOf(i),String.valueOf(listCineOneday.get(i).getIDofOnButton()));
            }
        }
        else
        {
            for(int i=0;i<listCineOneday.size();i++)
            {
                myListViewData.set(i,listCineOneday.get(i).getIDofOnButton());
                Log.d("Item "+ String.valueOf(i),String.valueOf(listCineOneday.get(i).getIDofOnButton()));
            }
        }
        super.onSaveInstanceState(outState);
    }
}
