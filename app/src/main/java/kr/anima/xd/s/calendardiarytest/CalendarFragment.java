package kr.anima.xd.s.calendardiarytest;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.DatePicker;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import kr.anima.xd.s.calendardiarytest.db.DBOpener;

import static kr.anima.xd.s.calendardiarytest.utils.DatabaseContents.COL_TITLE;
import static kr.anima.xd.s.calendardiarytest.utils.DatabaseContents.FORMAT_DATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {

    private Context context;
    private String seletedDate;

    CalendarView calendarView;
    RecyclerView rvList;

    private CalendarView.OnDateChangeListener listener=new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
            int viewType=0;
            seletedDate=String.format(FORMAT_DATE, year+""+month+""+dayOfMonth);
            DBOpener opener=new DBOpener(context, seletedDate, null, 1);
            SQLiteDatabase db=opener.getWritableDatabase();

            InputStream is;
            InputStreamReader isr=new InputStreamReader(is);
            BufferedReader reader=new BufferedReader(isr);
            StringBuffer buffer=new StringBuffer();
            String line=reader.readLine();
            while (line!=null){
                buffer.append(line);
                line=reader.readLine();
            }
            JSONObject jsonObject=new JSONObject(buffer.toString());
            String title=jsonObject.get(COL_TITLE);
        }
    };



    public CalendarFragment() {
        // Required empty public constructor
    }

    public CalendarFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_calendar, container, false);
        calendarView=view.findViewById(R.id.viewCalendar);
        calendarView.setOnDateChangeListener(listener);

        rvList=view.findViewById(R.id.rvList);
        rvList.setAdapter(new CalendarListAdapter(context));
        return view;
    }

}
