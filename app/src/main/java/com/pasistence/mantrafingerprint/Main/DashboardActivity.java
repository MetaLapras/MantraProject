package com.pasistence.mantrafingerprint.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.appolica.flubber.Flubber;
import com.pasistence.mantrafingerprint.R;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    LinearLayout member_register, member_list, member_transfer, member_dailyAttendence;
    Animation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       member_register = (LinearLayout) findViewById(R.id.cardView_MemberRegister);
        member_list = (LinearLayout) findViewById(R.id.cardView_MemberList);
       member_transfer = (LinearLayout) findViewById(R.id.cardView_MemberTransfer);
        member_dailyAttendence = (LinearLayout) findViewById(R.id.cardView_DailyAtendence);

      /* //animation = AnimationUtils.makeInAnimation(getApplicationContext(),true);
       animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.right_to_left_animation);
       member_transfer.setAnimation(animation);*/

        Flubber.with()
                .animation(Flubber.AnimationPreset.SLIDE_RIGHT) // Slide up animation
                .repeatCount(0)                              // Repeat once
                .duration(1000)                              // Last for 1000 milliseconds(1 second)
                .createFor(member_dailyAttendence)                             // Apply it to the view
                .start();
        Flubber.with()
                .animation(Flubber.AnimationPreset.SLIDE_RIGHT) // Slide up animation
                .repeatCount(0)                              // Repeat once
                .duration(1200)                              // Last for 1000 milliseconds(1 second)
                .createFor(member_register)                             // Apply it to the view
                .start();
        Flubber.with()
                .animation(Flubber.AnimationPreset.SLIDE_RIGHT) // Slide up animation
                .repeatCount(0)                              // Repeat once
                .duration(2000)                              // Last for 1000 milliseconds(1 second)
                .createFor(member_list)                             // Apply it to the view
                .start();
        Flubber.with()
                .animation(Flubber.AnimationPreset.SLIDE_RIGHT) // Slide up animation
                .repeatCount(0)                              // Repeat once
                .duration(3000)                              // Last for 1000 milliseconds(1 second)
                .createFor(member_transfer)                             // Apply it to the view
                .start();


       member_dailyAttendence.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Intent intent = new Intent(DashboardActivity.this,MatchingActivity.class);
               startActivity(intent);
           }
       });


        member_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(DashboardActivity.this, MatchingActivity.class));


                startActivity(new Intent(DashboardActivity.this,MatchingActivity.class));

            }
        });
        member_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent = new Intent(DashboardActivity.this,MemberListActivity.class);
                startActivity(intent);*/

                startActivity(new Intent(DashboardActivity.this, WorkerDisplayList.class));
            }
        });

        member_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DashboardActivity.this, WorkerRegistrationActivity.class);
                intent.putExtra("type", "register");
                startActivity(intent);
            }
        });

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    }


