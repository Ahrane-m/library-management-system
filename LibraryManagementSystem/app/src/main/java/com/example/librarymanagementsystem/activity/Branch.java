package com.example.librarymanagementsystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.librarymanagementsystem.DBHandler;
import com.example.librarymanagementsystem.R;
import com.example.librarymanagementsystem.adapter.BranchListViewAdapter;
import com.example.librarymanagementsystem.insert_data.AddBranch;
import com.example.librarymanagementsystem.update_delete.UpdateDeleteBookActivity;
import com.example.librarymanagementsystem.update_delete.UpdateDeleteBranchActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class Branch extends AppCompatActivity {
    private ArrayList<ArrayList> list;
    private FloatingActionButton addBranchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);
        addBranchButton = findViewById(R.id.branchAddButton);
        list = getAllBranchDetails();
        ListView listView = findViewById(R.id.branchListView);
        BranchListViewAdapter branchListViewAdapter = new BranchListViewAdapter(this, list);
        listView.setAdapter(branchListViewAdapter);

        addBranchButton.setOnClickListener(v -> {
            Intent intent = new Intent(Branch.this, AddBranch.class);
            startActivity(intent);
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            ArrayList<String> particularBranchDetails = list.get(position);
            Intent intent = new Intent(Branch.this, UpdateDeleteBranchActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("ARRAYLIST",(Serializable) particularBranchDetails);
            intent.putExtra("details", bundle);
            startActivity(intent);
        });
    }

    private ArrayList<ArrayList> getAllBranchDetails() {
        DBHandler dbHandler = new DBHandler(this);
        return dbHandler.getAllBranches();
    }

    @Override
    protected void onStart() {
        super.onStart();
        list = getAllBranchDetails();
        ListView listView = findViewById(R.id.branchListView);
        BranchListViewAdapter branchListViewAdapter = new BranchListViewAdapter(this, list);
        listView.setAdapter(branchListViewAdapter);
    }
}