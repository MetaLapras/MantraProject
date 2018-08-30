package com.pasistence.mantrafingerprint.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pasistence.mantrafingerprint.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

import in.co.ashclan.ashclanzcalendar.widget.CollapsibleCalendar;

public class AttendanceFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CollapsibleCalendar collapsibleCalendar;


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

        Calendar today = new GregorianCalendar();
        today.add(Calendar.DATE,10);
        collapsibleCalendar.addEventTag(today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH),
                Color.GREEN);

        collapsibleCalendar.setCalendarListener(new CollapsibleCalendar.CalendarListener() {
            @Override
            public void onDaySelect() {

            }

            @Override
            public void onItemClick(View view) {

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
}
