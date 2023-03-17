package com.example.librarymanagementsystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.librarymanagementsystem.DBHandler;
import com.example.librarymanagementsystem.R;
import com.example.librarymanagementsystem.update_delete.UpdateDeleteBookActivity;
import com.example.librarymanagementsystem.insert_data.AddBook;
import com.example.librarymanagementsystem.adapter.BookListViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class Book extends AppCompatActivity {
    private ArrayList<ArrayList> list;
    private FloatingActionButton addBookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        addBookButton = findViewById(R.id.bookAddButton);
        list = getAllBookDetails();
        ListView listView = findViewById(R.id.bookListView);
        BookListViewAdapter bookListViewAdapter = new BookListViewAdapter(this, list);
        listView.setAdapter(bookListViewAdapter);

        addBookButton.setOnClickListener(v -> {
            Intent intent = new Intent(Book.this, AddBook.class);
            startActivity(intent);
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            ArrayList<String> particularBookDetails = list.get(position);
            Intent intent = new Intent(Book.this, UpdateDeleteBookActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("ARRAYLIST",(Serializable) particularBookDetails);
            intent.putExtra("details", bundle);
            startActivity(intent);
        });
    }

    private ArrayList<ArrayList> getAllBookDetails() {
        DBHandler dbHandler = new DBHandler(this);
        return dbHandler.getAllBook();
    }

    @Override
    protected void onStart() {
        super.onStart();
        list = getAllBookDetails();
        ListView listView = findViewById(R.id.bookListView);
        BookListViewAdapter bookListViewAdapter = new BookListViewAdapter(this, list);
        listView.setAdapter(bookListViewAdapter);
    }
}

