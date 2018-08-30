package com.pasistence.mantrafingerprint.Main;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.pasistence.mantrafingerprint.Adapter.SearchAdapter;
import com.pasistence.mantrafingerprint.Adapter.WorkerListAdapter;
import com.pasistence.mantrafingerprint.Adapter.WorkerUploadAdapter;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.database.Database;

import java.util.ArrayList;
import java.util.List;

public class UploadWorkerDetailsActivity extends AppCompatActivity {
    private static final String TAG = "workerdetails -->";
    RecyclerView WorkeruploadListRecyclerView;
    RecyclerView.LayoutManager layoutupload;
    SearchAdapter adapter;


    Context mContext;
    WorkerUploadAdapter workerUploadAdapter;
    Database database;

    List<WorkerModel> WorkerDetails;


    MaterialSearchBar materialuploadSearchBar;
    List<String> suggestList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_worker_details);

        mInit();

        WorkerDetails = new Database(mContext).getAllTempWorkers();

        workerUploadAdapter = new WorkerUploadAdapter(UploadWorkerDetailsActivity.this, WorkerDetails);
        WorkeruploadListRecyclerView.setAdapter(workerUploadAdapter);
        workerUploadAdapter.notifyDataSetChanged();
    }

    private void mInit() {

        mContext = UploadWorkerDetailsActivity.this;

        WorkeruploadListRecyclerView = (RecyclerView) findViewById(R.id.workerUpload_recycler);
        WorkeruploadListRecyclerView.setHasFixedSize(true);
        layoutupload = new LinearLayoutManager(mContext);
        WorkeruploadListRecyclerView.setLayoutManager(layoutupload);
        materialuploadSearchBar = (MaterialSearchBar) findViewById(R.id.workerUpload_searchBar);

        //Init DB
        database = new Database(this);


        //Setup search bar
        materialuploadSearchBar.setHint("Search");
        materialuploadSearchBar.setCardViewElevation(10);

        loadSuggestList();
        materialuploadSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                List<String> suggest = new ArrayList<>();
                for (String search : suggestList) {
                    if (search.toLowerCase().contains(materialuploadSearchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                materialuploadSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        materialuploadSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if (!enabled) {
                    WorkeruploadListRecyclerView.setAdapter(adapter);
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
        adapter = new SearchAdapter(UploadWorkerDetailsActivity.this, this, database.getAllWorkers());
        WorkeruploadListRecyclerView.setAdapter(adapter);

    }


    private void startSearch(String text) {
        adapter = new SearchAdapter(UploadWorkerDetailsActivity.this, this, database.getWorkerName(text));
        WorkeruploadListRecyclerView.setAdapter(adapter);
    }

    private void loadSuggestList() {
        suggestList = database.getNames();
        materialuploadSearchBar.setLastSuggestions(suggestList);
    }

}

