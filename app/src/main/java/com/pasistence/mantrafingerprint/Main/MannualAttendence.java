package com.pasistence.mantrafingerprint.Main;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.pasistence.mantrafingerprint.Adapter.MannualAttendenceAdapter;
import com.pasistence.mantrafingerprint.Adapter.SearchAdapter;
import com.pasistence.mantrafingerprint.Models.MannualAttendencePOJO;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.database.Database;

import java.util.ArrayList;
import java.util.List;

public class MannualAttendence extends AppCompatActivity {

    StringBuffer sb = null;

    private static final String TAG = "workerdetails -->";
    RecyclerView MannualAttendenceRecyclerView;
    Button btnsave,btnNext;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapter adapter;


    Context mContext;
    MannualAttendenceAdapter mannualAttendenceAdapter;
    Database database;

    List<WorkerModel> Workerlsit;


    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mannual_attendence);


        mInit();

        Workerlsit = new Database(mContext).getAllWorkers();
        //database.addToMannualAttendance(Workermannual);

       mannualAttendenceAdapter = new MannualAttendenceAdapter(MannualAttendence.this, Workerlsit);
        MannualAttendenceRecyclerView.setAdapter(mannualAttendenceAdapter);
        mannualAttendenceAdapter.notifyDataSetChanged();
       // mannualAttendenceAdapter = new MannualAttendenceAdapter(this,getMannual());
    }

    private ArrayList<MannualAttendencePOJO> getMannual() {
        ArrayList<MannualAttendencePOJO> mannualpojos=new ArrayList<>();
      MannualAttendencePOJO M = new MannualAttendencePOJO();
      mannualpojos.add(M);
        return mannualpojos;
    }

    private void mInit() {

        mContext = MannualAttendence.this;

        MannualAttendenceRecyclerView = (RecyclerView) findViewById(R.id.worker_recycler_mannualAttendence);
        MannualAttendenceRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(mContext);
        MannualAttendenceRecyclerView.setLayoutManager(layoutManager);
        materialSearchBar = (MaterialSearchBar) findViewById(R.id.mannualAttendence_search_bar);

        btnsave   = (Button)findViewById(R.id.get_value);
        btnNext=(Button)findViewById(R.id.mannual_Next);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              sb = new StringBuffer();
                new Database(mContext).deleteToMannualAttendance();

              for (MannualAttendencePOJO mannualAttendence : mannualAttendenceAdapter.mannualList) {
                  sb.append(mannualAttendence.getName());
                  sb.append("\n");
                  if (mannualAttendenceAdapter.mannualList.size() > 0) {

                      Toast.makeText(mContext, sb.toString(), Toast.LENGTH_SHORT).show();
                      new Database(mContext).addToMannualAttendance(mannualAttendence);
                  } else {
                      Toast.makeText(mContext, "Please check mannual Attendence..", Toast.LENGTH_SHORT).show();
                  }

              }
            /*  if (mannualAttendenceAdapter.mannualList.size() > 0) {

                    Toast.makeText(mContext, sb.toString(), Toast.LENGTH_SHORT).show();
                    // new Database(mContext).addToMannualAttendance(mannualAttendence);
                } else {
                    Toast.makeText(mContext, "Please check mannual Attendence..", Toast.LENGTH_SHORT).show();
                }
*/
            }
        });

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
                    MannualAttendenceRecyclerView.setAdapter(adapter);
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
        adapter = new SearchAdapter(MannualAttendence.this, this, database.getAllWorkers());
        MannualAttendenceRecyclerView.setAdapter(adapter);

    }


    private void startSearch(String text) {
        adapter = new SearchAdapter(MannualAttendence.this, this, database.getWorkerName(text));
        MannualAttendenceRecyclerView.setAdapter(adapter);
    }

    private void loadSuggestList() {
        suggestList = database.getNames();
        materialSearchBar.setLastSuggestions(suggestList);
    }

}



