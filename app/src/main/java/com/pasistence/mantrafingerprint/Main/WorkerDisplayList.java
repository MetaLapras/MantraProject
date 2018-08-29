package com.pasistence.mantrafingerprint.Main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.pasistence.mantrafingerprint.Adapter.SearchAdapter;
import com.pasistence.mantrafingerprint.Adapter.WorkerListAdapter;
import com.pasistence.mantrafingerprint.Models.ModelWorker;
import com.pasistence.mantrafingerprint.Models.WorkerList;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.database.Database;
import com.pasistence.mantrafingerprint.database.DatabaseHelper;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class WorkerDisplayList extends AppCompatActivity {

    private static final String TAG = "workerdetails -->";
    RecyclerView WorkerListRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapter adapter;

    Context mContext;
    WorkerListAdapter workerListAdapter;
    Database database;

    List<WorkerModel> WorkerDetails;


    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_display_list);

        mInit();

        WorkerDetails = new Database(mContext).getAllWorkers();

        workerListAdapter = new WorkerListAdapter(WorkerDisplayList.this, WorkerDetails);
        WorkerListRecyclerView.setAdapter(workerListAdapter);
        workerListAdapter.notifyDataSetChanged();
    }

    private void mInit() {

        mContext = WorkerDisplayList.this;

        WorkerListRecyclerView = (RecyclerView) findViewById(R.id.worker_recycler_worker);
        WorkerListRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(mContext);
        WorkerListRecyclerView.setLayoutManager(layoutManager);
        materialSearchBar = (MaterialSearchBar) findViewById(R.id.worker_search_bar);

        //Init DB
        database = new Database(this);


        //Setup search bar
        materialSearchBar.setHint("Search");
        materialSearchBar.setCardViewElevation(10);

        loadSuggestList();
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                List<String> suggest = new ArrayList<>();
                for (String search : suggestList) {
                    if (search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if (!enabled) {
                    WorkerListRecyclerView.setAdapter(adapter);
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
        adapter = new SearchAdapter(WorkerDisplayList.this, this, database.getAllWorkers());
        WorkerListRecyclerView.setAdapter(adapter);

    }


    private void startSearch(String text) {
        adapter = new SearchAdapter(WorkerDisplayList.this, this, database.getWorkerName(text));
        WorkerListRecyclerView.setAdapter(adapter);
    }

    private void loadSuggestList() {
        suggestList = database.getNames();
        materialSearchBar.setLastSuggestions(suggestList);
    }

}


