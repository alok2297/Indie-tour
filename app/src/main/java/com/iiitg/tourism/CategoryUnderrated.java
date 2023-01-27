package com.iiitg.tourism;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iiitg.tourism.Model.CategoryModel;

import java.util.concurrent.TimeUnit;

public class CategoryUnderrated extends AppCompatActivity {
    SearchView search;
    RecyclerView recyclerView;
    CategoryAdapter categoryAdapter;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        search=findViewById(R.id.searching);
//firebase_calling
        database= FirebaseDatabase.getInstance();
        reference=database.getReference("Underrated");

        recyclerView=findViewById(R.id.rv);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        //  recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //waiting msg while loading recyclerView
        long duration= TimeUnit.SECONDS.toMillis(2);
        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                Toast.makeText(CategoryUnderrated.this,"Please wait! Connecting to the Firebase",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                ProgressBar textView=findViewById(R.id.warn);
                textView.setVisibility(View.INVISIBLE);
            }
        }.start();

//firebase_gathering info
        FirebaseRecyclerOptions<CategoryModel> options=new FirebaseRecyclerOptions.Builder<CategoryModel>()
                .setQuery(reference,CategoryModel.class)
                .build();

        categoryAdapter=new CategoryAdapter(options);
        recyclerView.setAdapter(categoryAdapter);


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

        FirebaseRecyclerOptions<CategoryModel> options=new FirebaseRecyclerOptions.Builder<CategoryModel>()
                .setQuery(reference.orderByChild("name").startAt(str).endAt(str + "~"), CategoryModel.class)
                .build();

        categoryAdapter=new CategoryAdapter(options);
        categoryAdapter.startListening();
        recyclerView.setAdapter(categoryAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        categoryAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        categoryAdapter.stopListening();
    }



}