package com.pasistence.mantrafingerprint.Main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.pasistence.mantrafingerprint.Adapter.SearchAdapter;
import com.pasistence.mantrafingerprint.Adapter.WorkerAteendenceAdapter;
import com.pasistence.mantrafingerprint.Common.Common;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.Attendance;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.Remote.IMyAPI;
import com.pasistence.mantrafingerprint.database.Database;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadWorkerAttendence extends AppCompatActivity {
    private static final String TAG = "UploadDetails -->";
    RecyclerView WorkerAttendenceListRecyclerView;
    RecyclerView.LayoutManager layoutupload;
    SearchAdapter adapter;
    Button btnUpload;

    Context mContext;
    IMyAPI mService;
    WorkerAteendenceAdapter workerAteendenceAdapter;
    Database database;

    List<Attendance> attendanceList;


    MaterialSearchBar materialAttendenceSearchBar;
    List<String> suggestList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_worker_attendence);

        mInit();
        mService = Common.getApi();

        attendanceList = new Database(mContext).getallTempAttendace();

        workerAteendenceAdapter = new WorkerAteendenceAdapter(UploadWorkerAttendence.this, attendanceList);
        WorkerAttendenceListRecyclerView.setAdapter(workerAteendenceAdapter);
        workerAteendenceAdapter.notifyDataSetChanged();

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(attendanceList.isEmpty()){
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                    alertDialogBuilder.setMessage("No Data to Upload into the Server");
                    alertDialogBuilder.setNegativeButton("Ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    arg0.dismiss();
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }else{
                    uploadAllAttendanceData(attendanceList);
                }

            }
        });
    }

    private void uploadAllAttendanceData(final List<Attendance> attendanceList) {
        //JSONArray jsArray = new JSONArray(workerList);
       // Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //String json = gson.toJson(attendanceList);
        String stringToPost = new Gson().toJson(attendanceList);
        //String joint = "worker :";
       // Log.e(TAG, stringToPost.toString() );
       // System.out.println(json);


        final AlertDialog dialog = new SpotsDialog(mContext);
        dialog.show();
        dialog.setMessage("Loading Data");
        dialog.setCancelable(false);

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),stringToPost);

        mService.uploadAttendanceDetails(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               // Log.e(TAG, response.body().toString() );
                try
                {
                    //get your response....
                    Log.d(TAG, "RetroFit2.0 : " + response.body().string());
                    String str = response.body().contentType().toString();
                    //Log.d(TAG, "json : " + str.toString());


                    for(Attendance attendance : attendanceList){
                        database.addToAttendance(attendance);
                    }
                    database.deleteToTempAttendance();
                    dialog.dismiss();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                dialog.dismiss();
            }
        });

        finish();
    }

    private void mInit() {

        mContext = UploadWorkerAttendence.this;

        WorkerAttendenceListRecyclerView = (RecyclerView) findViewById(R.id.workerattendence_recycler);
        WorkerAttendenceListRecyclerView.setHasFixedSize(true);
        layoutupload = new LinearLayoutManager(mContext);
        WorkerAttendenceListRecyclerView.setLayoutManager(layoutupload);
        materialAttendenceSearchBar = (MaterialSearchBar) findViewById(R.id.workerAttendence_searchBar);
        btnUpload = (Button)findViewById(R.id.btn_submit_upload) ;

        //Init DB
        database = new Database(this);


        //Setup search bar
        materialAttendenceSearchBar.setHint("Search");
        materialAttendenceSearchBar.setCardViewElevation(10);

        loadSuggestList();
        materialAttendenceSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                List<String> suggest = new ArrayList<>();
                for (String search : suggestList) {
                    if (search.toLowerCase().contains(materialAttendenceSearchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                materialAttendenceSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        materialAttendenceSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if (!enabled) {
                    WorkerAttendenceListRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        //Init Adapter default set all result
        adapter = new SearchAdapter(UploadWorkerAttendence.this, this, database.getAllWorkers());
        WorkerAttendenceListRecyclerView.setAdapter(adapter);

    }

    private void startSearch(String text) {
        adapter = new SearchAdapter(UploadWorkerAttendence.this, this, database.getWorkerName(text));
        WorkerAttendenceListRecyclerView.setAdapter(adapter);
    }

    private void loadSuggestList() {
        suggestList = database.getNames();
        materialAttendenceSearchBar.setLastSuggestions(suggestList);
    }

}

