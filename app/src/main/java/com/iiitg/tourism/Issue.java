package com.iiitg.tourism;

import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iiitg.tourism.Model.IssueModel;

import java.util.concurrent.TimeUnit;

public class Issue extends AppCompatActivity {
    SearchView search;
    Button okay;
    RecyclerView recyclerView;
    IssueAdapter issueAdapter;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        search=findViewById(R.id.searching);
//firebase_calling
        database= FirebaseDatabase.getInstance();
        reference=database.getReference("Issue");


        Dialog progressDialog = new Dialog(Issue.this);
        progressDialog.setContentView(R.layout.dialogbox);
        progressDialog.setCancelable(true);
        progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        progressDialog.show();
        Button okay = progressDialog.findViewById(R.id.btn);

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.hide();
            }
        });



        recyclerView=findViewById(R.id.rv);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        //  recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //waiting msg while loading recyclerView
        long duration= TimeUnit.SECONDS.toMillis(2);
        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                ProgressBar textView=findViewById(R.id.warn);
                textView.setVisibility(View.INVISIBLE);
            }
        }.start();

//firebase_gathering info
        FirebaseRecyclerOptions<IssueModel> options=new FirebaseRecyclerOptions.Builder<IssueModel>()
                .setQuery(reference,IssueModel.class)
                .build();

        issueAdapter=new IssueAdapter(options);
        recyclerView.setAdapter(issueAdapter);


        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                txtSearch(newText);
                return false;
            }
        });
    }

    private void txtSearch(String str) {

        FirebaseRecyclerOptions<IssueModel> options=new FirebaseRecyclerOptions.Builder<IssueModel>()
                .setQuery(reference.orderByChild("name").startAt(str).endAt(str + "~"), IssueModel.class)
                .build();

        issueAdapter=new IssueAdapter(options);
        issueAdapter.startListening();
        recyclerView.setAdapter(issueAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        issueAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        issueAdapter.stopListening();
    }



}