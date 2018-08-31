package com.pasistence.mantrafingerprint.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pasistence.mantrafingerprint.Adapter.CustomeWorkerAttendenceAdapter;
import com.pasistence.mantrafingerprint.Adapter.WorkerListAdapter;
import com.pasistence.mantrafingerprint.Common.PreferenceUtils;
import com.pasistence.mantrafingerprint.Main.WorkerDisplayList;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.Attendance;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.database.Database;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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


        List<Attendance> attendances = new Database(getActivity()).getallTempAttendace(strtext);
        Attendance attendance1 =  new Attendance(
                "1",
                "nam",
                "123",
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
        attendances.add(attendance1);
        customeWorkerAttendenceAdapter = new CustomeWorkerAttendenceAdapter(getActivity(), attendances);
        fragmentrecyclerview.setAdapter(customeWorkerAttendenceAdapter);
        customeWorkerAttendenceAdapter.notifyDataSetChanged();


        Calendar today = new GregorianCalendar();
        today.add(Calendar.DATE,10);
        collapsibleCalendar.addEventTag(today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH),
                Color.GREEN);

        mInit(view);
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

    private void mInit(View view) {

    }
}
