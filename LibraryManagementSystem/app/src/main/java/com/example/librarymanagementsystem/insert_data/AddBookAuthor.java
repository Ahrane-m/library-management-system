package com.example.librarymanagementsystem.insert_data;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.librarymanagementsystem.DBHandler;
import com.example.librarymanagementsystem.R;
import com.example.librarymanagementsystem.regex.Regex;

public class AddBookAuthor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book_author);

        EditText editBookId = findViewById(R.id.idEditAuthorBookID);
        EditText editAuthorName = findViewById(R.id.idEditBookAuthorName);
        Button addBookAuthorBtn = findViewById(R.id.idBtnAddBookAuthor);
        DBHandler dbHandler;

        dbHandler = new DBHandler(AddBookAuthor.this);

        addBookAuthorBtn.setOnClickListener(v -> {
            String bookID = editBookId.getText().toString();
            String authorName = editAuthorName.getText().toString();

            if (bookID.isEmpty() && authorName.isEmpty()) {
                Toast.makeText(AddBookAuthor.this, "Please enter all the data..",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            if (!authorName.matches(Regex.TEXT_REGEX)) {
                editAuthorName.setTextColor(Color.RED);
                Toast.makeText(AddBookAuthor.this, "Please enter valid name",
                        Toast.LENGTH_LONG).show();
                return;
            }
            boolean isBookIdAndAuthorNameExists = dbHandler.isBookIdAndAuthorNameExists(bookID, authorName);
            boolean isBookIdAlreadyExistsInBookTable = dbHandler.isBookIdExists(bookID);
            if (isBookIdAlreadyExistsInBookTable && !isBookIdAndAuthorNameExists) {
                dbHandler.addNewBookAuthor(bookID, authorName);
                Toast.makeText(AddBookAuthor.this, "Book Author has been added.",
                        Toast.LENGTH_SHORT).show();
                editBookId.setText("");
                editAuthorName.setText("");
            } else if (!isBookIdAlreadyExistsInBookTable){
                editBookId.setTextColor(Color.RED);
                Toast.makeText(AddBookAuthor.this, "Book ID is not exists", Toast.LENGTH_LONG).show();
            } else {
                editBookId.setTextColor(Color.RED);
                editAuthorName.setTextColor(Color.RED);
                Toast.makeText(AddBookAuthor.this, "The data already exists", Toast.LENGTH_LONG).show();
            }
        });
    }
}