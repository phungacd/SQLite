package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText id, title, author;
    Button select, save, exit, update,delete;
    GridView display;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = findViewById(R.id.txtmaso);
        title = findViewById(R.id.txttieude);
        author = findViewById(R.id.txttentacgia);
        select = findViewById(R.id.btnselect);
        save = findViewById(R.id.btnsave);
        delete =findViewById(R.id.btndelete);
        exit = findViewById(R.id.btnexit);
        update = findViewById(R.id.btnupdate);
        display = findViewById(R.id.gridview);
        dbHelper = new DBHelper(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setId(Integer.parseInt(id.getText().toString()));
                book.setTitle((title.getText().toString()));
                book.setAuthor(author.getText().toString());
                if (dbHelper.insertBook(book)) {
                    Toast.makeText(getApplicationContext(), "Da luu thanh cong ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Luu that bai", Toast.LENGTH_LONG).show();
                }
            }


        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list = new ArrayList<String>();
                ArrayList<Book> books = new ArrayList<Book>();

                if (id.getText().toString().equalsIgnoreCase("")) {
                    books = dbHelper.getAllBook();
                    for (Book b : books) {
                        list.add(b.getId() + "");
                        list.add(b.getTitle() + "");
                        list.add(b.getAuthor() + "");
                    }
                } else {

                    Book book = dbHelper.getBook(Integer.parseInt(id.getText().toString()));
                    books.add(book);
                    for (Book b : books) {
                        list.add(b.getId() + "");
                        list.add(b.getTitle() + "");
                        list.add(b.getAuthor() + "");
                    }
                }


                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);
                display.setAdapter(adapter);
            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}
