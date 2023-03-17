package com.example.librarymanagementsystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.librarymanagementsystem.DBHandler;
import com.example.librarymanagementsystem.R;
import com.example.librarymanagementsystem.adapter.BookLoanListViewAdapter;
import com.example.librarymanagementsystem.insert_data.AddBook;
import com.example.librarymanagementsystem.insert_data.AddBookLoan;
import com.example.librarymanagementsystem.update_delete.UpdateDeleteBookActivity;
import com.example.librarymanagementsystem.update_delete.UpdateDeleteBookLoanActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class BookLoan extends AppCompatActivity {
    private ArrayList<ArrayList> list;
    private FloatingActionButton addBookLoanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_loan);
        addBookLoanButton = findViewById(R.id.bookLoanAddButton);
        list = getAllBookLoanDetails();
        ListView listView = findViewById(R.id.bookLoanListView);
        BookLoanListViewAdapter bookLoanListViewAdapter = new BookLoanListViewAdapter(this, list);
        listView.setAdapter(bookLoanListViewAdapter);

        addBookLoanButton.setOnClickListener(v -> {
            Intent intent = new Intent(BookLoan.this, AddBookLoan.class);
            startActivity(intent);
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            ArrayList<String> particularBookLoanDetails = list.get(position);
            Intent intent = new Intent(BookLoan.this, UpdateDeleteBookLoanActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("ARRAYLIST",(Serializable) particularBookLoanDetails);
            intent.putExtra("details", bundle);
            startActivity(intent);
        });
    }
    private ArrayList<ArrayList> getAllBookLoanDetails() {
        DBHandler dbHandler = new DBHandler(this);
        return dbHandler.getAllBookLoans();
    }

    @Override
    protected void onStart() {
        super.onStart();
        list = getAllBookLoanDetails();
        ListView listView = findViewById(R.id.bookLoanListView);
        BookLoanListViewAdapter bookLoanListViewAdapter = new BookLoanListViewAdapter(this, list);
        listView.setAdapter(bookLoanListViewAdapter);
    }
}