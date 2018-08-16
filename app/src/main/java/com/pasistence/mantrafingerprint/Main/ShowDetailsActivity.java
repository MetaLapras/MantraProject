package com.pasistence.mantrafingerprint.Main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.pasistence.mantrafingerprint.Adapter.ViewPagerAdapter;
import com.pasistence.mantrafingerprint.Fragments.AttendanceFragment;
import com.pasistence.mantrafingerprint.Fragments.DetailsFragment;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;

import java.util.ArrayList;

public class ShowDetailsActivity extends AppCompatActivity {

    private TextView mTextMessage;
    ViewPagerAdapter mViewPagerAdapter;
    AttendanceFragment attendanceFragment;
    DetailsFragment detailsFragment;
    ViewPager WorkerViewPager;
    BottomNavigationView bottomNavigationView;
    MenuItem prevMenuItem;


    Context mContext;
    String id ;
    WorkerModel workerModel ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        mInit();

        try{
            if(getIntent()!= null)
            {
                id = (String)getIntent().getStringExtra("id");
                workerModel = (WorkerModel) getIntent().getSerializableExtra("workers");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                WorkerViewPager.setCurrentItem(0);
                                break;
                            case R.id.navigation_dashboard:
                                WorkerViewPager.setCurrentItem(1);
                                break;
                            case R.id.navigation_notifications:
                                WorkerViewPager.setCurrentItem(0);
                                break;
                        }
                        return false;
                    }
                });


        WorkerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page",""+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(WorkerViewPager);
    }

    private void mInit() {
        mContext = ShowDetailsActivity.this;
        WorkerViewPager = (ViewPager)findViewById(R.id.worker_view_pager);
        //Initializing the bottomNavigationView
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        workerModel = new WorkerModel();
    }


    private void setupViewPager(ViewPager viewPager) {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        attendanceFragment=new AttendanceFragment();
        detailsFragment=new DetailsFragment(workerModel);
        //notificationFragment=new NotificationFragment();

        mViewPagerAdapter.addFragment(detailsFragment);
        mViewPagerAdapter.addFragment(attendanceFragment);
       // adapter.addFragment(notificationFragment);
        viewPager.setAdapter(mViewPagerAdapter);
    }

}
