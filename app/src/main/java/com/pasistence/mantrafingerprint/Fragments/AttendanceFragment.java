package com.pasistence.mantrafingerprint.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.pasistence.mantrafingerprint.Adapter.CustomeWorkerAttendenceAdapter;
import com.pasistence.mantrafingerprint.Adapter.WorkerListAdapter;
import com.pasistence.mantrafingerprint.Common.PreferenceUtils;
import com.pasistence.mantrafingerprint.Main.HomeActivity;
import com.pasistence.mantrafingerprint.Main.WorkerDisplayList;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.Attendance;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.database.Database;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import in.co.ashclan.ashclanzcalendar.data.Day;
import in.co.ashclan.ashclanzcalendar.widget.CollapsibleCalendar;

public class AttendanceFragment extends Fragment {
    DateFormat df;
    String selectDate, today;
    Day day;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CollapsibleCalendar collapsibleCalendar;
    public RecyclerView fragmentrecyclerview;
    RecyclerView.LayoutManager layoutupload;
   CustomeWorkerAttendenceAdapter customeWorkerAttendenceAdapter;


    public AttendanceFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_attendance, container, false);
        collapsibleCalendar=(CollapsibleCalendar)view.findViewById(R.id.collapsibleCalendarView);
        fragmentrecyclerview = (RecyclerView)view.findViewById(R.id.workerfragment_recycler);
        fragmentrecyclerview.setHasFixedSize(true);
        layoutupload = new LinearLayoutManager(getActivity());
        fragmentrecyclerview.setLayoutManager(layoutupload);

        String strtext = getArguments().getString("worker_id");
        df = new SimpleDateFormat("yyyy-MM-dd");

       // final List<Attendance> attendances = new Database(getActivity()).getallTempAttendace(strtext);
        final List<Attendance> attendances = new Database(getActivity()).getallAttendace(strtext);
      /*  Attendance attendance1
                "123", =  new Attendance(
                "1",
                "nam",
                "fgfg",
                "fgdfg",
                "asda",
                "cvbcvb",
                "cvbcb",
                "cvbcb",
                "cvbcbv",
                "125",
                "dfgdfg",
                "dgdfg"
        );
        attendances.add(attendance1);
        attendances.add(attendance1);
        attendances.add(attendance1);
        attendances.add(attendance1);
        attendances.add(attendance1);
        attendances.add(attendance1);
        attendances.add(attendance1);
        attendances.add(attendance1);
        attendances.add(attendance1);*/
        customeWorkerAttendenceAdapter = new CustomeWorkerAttendenceAdapter(getActivity(), attendances);
        fragmentrecyclerview.setAdapter(customeWorkerAttendenceAdapter);
        customeWorkerAttendenceAdapter.notifyDataSetChanged();
       for(int i = 0 ; i<attendances.size();i++){
           try {
               Date day = df.parse(attendances.get(i).getCheckInDate().toString());

               collapsibleCalendar.addEventTag(day.getYear()+1900,
                       day.getMonth(),
                       day.getDate(),
                       Color.GREEN);

           } catch (ParseException e) {
               e.printStackTrace();
           }
       }






        mInit(view);

        collapsibleCalendar.setCalendarListener(new CollapsibleCalendar.CalendarListener() {
            @Override
            public void onDaySelect() {
                day = collapsibleCalendar.getSelectedDay();
                Toast.makeText(getActivity(), "onDaySelect Selected Day: "
                        + day.getYear() + "/" + (day.getMonth() + 1) + "/" + day.getDay(), Toast.LENGTH_LONG).show();
                Log.e("onDaySelect:--> ", "Selected Day: "
                        + day.getYear() + "/" + (day.getMonth() + 1) + "/" + day.getDay());
            }

            @Override
            public void onItemClick(View view) {
                day = collapsibleCalendar.getSelectedDay();
                selectDate = day.getYear() + "-" + (day.getMonth() + 1) + "-" + day.getDay();
                Date mDate, mStartDate, mEndDate;
                try {
                    ArrayList<Attendance> selectEventsList = new ArrayList<Attendance>();
                    mDate = df.parse(selectDate);
                    for (Attendance event : attendances) {
                        mStartDate = df.parse(event.getCheckInDate());
                        mEndDate = df.parse(event.getCheckInDate());
                        if (isEventAvialibity(df, mStartDate, mEndDate, mDate)) {
                            selectEventsList.add(event);
                        }
                    }
                    customeWorkerAttendenceAdapter = new CustomeWorkerAttendenceAdapter(getActivity(), selectEventsList);
                    fragmentrecyclerview.setAdapter(customeWorkerAttendenceAdapter);
                    customeWorkerAttendenceAdapter.notifyDataSetChanged();



                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onDataUpdate() {

            }

            @Override
            public void onMonthChange() {

            }

            @Override
            public void onWeekChange(int i) {

            }
        });
        return view;
    }

    private void mInit(View view) {

    }

    public boolean isEventAvialibity(DateFormat df, Date startDate, Date endDate, Date onClickDate) {
        if (startDate.compareTo(onClickDate) >= 0 && endDate.compareTo(onClickDate) <= 0) {
            return true;
        }
        return false;
    }
}
