package com.lagosdevelopers.lagdevs.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.lagosdevelopers.lagdevs.R;
import com.lagosdevelopers.lagdevs.adapters.DeveloperAdapter;
import com.lagosdevelopers.lagdevs.entities.Developer;
import com.lagosdevelopers.lagdevs.presenters.DeveloperListPresenter;
import com.lagosdevelopers.lagdevs.views.DevelopersView;

import java.util.List;

public class DevelopersActivity extends AppCompatActivity implements DevelopersView {

    RecyclerView recyclerView;
    ProgressBar progressBar;

    private final String TITLE = "Lagos Java Developers";

    DeveloperListPresenter developerListPresenter;
    DeveloperAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_list);

        getReferencesToWidgets();

        developerListPresenter = new DeveloperListPresenter(this);
        developerListPresenter.getListOfDevelopers();

    }

    private void getReferencesToWidgets(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.progress);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(TITLE);
    }

    @Override
    public void showLoading() {
        hideView(recyclerView);
        showView(progressBar);
    }

    @Override
    public void stopLoading() {
        hideView(progressBar);
        showView(recyclerView);
    }

    @Override
    public void showDevelopers(List<Developer> developers) {
        adapter = new DeveloperAdapter(this, developers, new DeveloperAdapter.OnDeveloperSelectedListener() {
            @Override
            public void selectedDeveloperPosition(int position) {
                Intent intent = new Intent(DevelopersActivity.this, DeveloperDetailsActivity.class);
                intent.putExtra(DeveloperDetailsActivity.KEY_DEVELOPER_POSITION, position);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void hideView(View view){
        view.setVisibility(View.GONE);
    }

    private void showView(View v){
        v.setVisibility(View.VISIBLE);
    }
}
