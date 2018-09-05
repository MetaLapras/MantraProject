package com.pasistence.mantrafingerprint.Main;

import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.appolica.flubber.Flubber;
import com.pasistence.mantrafingerprint.Common.PreferenceUtils;
import com.pasistence.mantrafingerprint.R;

import java.util.Locale;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    LinearLayout member_register, member_list, member_transfer, member_dailyAttendence,mannual_aatendence;
    TextView dailyAttendence;
    Animation animation;
    MenuItem logout;
    Context mContext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = DashboardActivity.this;



            logout = (MenuItem) findViewById(R.id.action_logout);
            member_register = (LinearLayout) findViewById(R.id.cardView_MemberRegister);
            member_list = (LinearLayout) findViewById(R.id.cardView_MemberList);
            member_transfer = (LinearLayout) findViewById(R.id.cardView_MemberTransfer);
            member_dailyAttendence = (LinearLayout) findViewById(R.id.cardView_DailyAtendence);
            mannual_aatendence = (LinearLayout)findViewById(R.id.cardView_ManualAttendence) ;

            dailyAttendence = (TextView) findViewById(R.id.txt_daily_Attendence);
        /*Typeface custom_font = Typeface.createFromAsset(getAssets(),  "font/gradientico.ttf");

        dailyAttendence.setTypeface(custom_font);*/


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

        Flubber.with()
                .animation(Flubber.AnimationPreset.SLIDE_RIGHT) // Slide up animation
                .repeatCount(0)                              // Repeat once
                .duration(4000)                              // Last for 1000 milliseconds(1 second)
                .createFor(mannual_aatendence)                             // Apply it to the view
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



                startActivity(new Intent(DashboardActivity.this, TransferActivity.class));




                //startActivity(new Intent(DashboardActivity.this, MatchingActivity.class));

               /* startActivity(new Intent(DashboardActivity.this,MatchingActivity.class));*/

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
        mannual_aatendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(DashboardActivity.this, MannualAttendence.class);
               // intent.putExtra("type", "register");
                startActivity(inte);
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



/*
    private void mLogout() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You wanted to make decision");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(DashboardActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                            }
                        });
        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {

                 public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }
*/


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

        if (id == R.id.action_logout) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DashboardActivity.this);
            alertDialogBuilder.setMessage("Are you Sure Want to Logout")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // FIRE ZE MISSILES!
                            PreferenceUtils.setSignIn(DashboardActivity.this,false);
                            Intent intent1 = new Intent(DashboardActivity.this,LoginActivity.class);
                            startActivity(intent1);
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                            dialog.dismiss();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

            return true;
        }

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

        if (id == R.id.nav_workerDailyAttendence) {
            startActivity(new Intent(this,MatchingActivity.class));
            // Handle the camera action
        } else if (id == R.id.nav_workerRegistration) {
            startActivity(new Intent(this,WorkerRegistrationActivity.class));

        } else if (id == R.id.nav_workerList) {
            startActivity(new Intent(this,WorkerDisplayList.class));

        } else if (id == R.id.nav_workerTransfer) {
        startActivity(new Intent(this,TransferActivity.class));
        }
        else if(id == R.id.nav_workerMannulAttendence)
        {
            Intent intent2 = new Intent(this,MannualAttendence.class);
            this.startActivity(intent2);
        }
        else if (id == R.id.nav_workerUpload) {
            Intent intent1 = new Intent(this,UploadActivity.class);
            this.startActivity(intent1);
            return true;
        }

        else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBodyText = "Check it out. Your message goes here";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
            startActivity(Intent.createChooser(sharingIntent, "Shearing Option"));
            return true;


        } else if (id == R.id.nav_contact_us) {
         startActivity(new Intent(this,ContactUs.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}


