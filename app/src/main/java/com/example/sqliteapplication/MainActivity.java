package com.example.sqliteapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;

public class MainActivity extends AppCompatActivity {

    EditText name, contact, dob;
    Button insert, update, delete,view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PrecreateDB.copyDB(context: this);
        databaseAdapter = new DatabaseAdapter(context: this);
       /* name = findViewById(R.id.name);
        id = findViewById(R.id.id);
        email= findViewById(R.id.email);
        marks= findViewById(R.id.marks);
        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);*/
        listView IvContact = findViewById(R.id.lvContact);
        SimpleCursorAdapter simpleCursorAdapter = databaseAdapter.populateListViewFromDB();
        IvContact.setAdapter(simpleCursorAdapter);
        
        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameTxt = name.getText().toString();
                String idTxt = id.getText().toString();
                String emailTxt = email.getText().toString();
                String marksTxt = marks.getText().toString();

                Boolean checkinsertData = DB.insertuserdata(nameTxt,idTxt,emailTxt, marksTxt);

                if(checkinsertData){
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "New Entry NOT Inserted", Toast.LENGTH_SHORT).show();
                }

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameTxt = name.getText().toString();
                String idTxt = id.getText().toString();
                String emailTxt = email.getText().toString();
                String marksTxt = marks.getText().toString();

                Boolean checkupdate = DB.updateuserdata(nameTxt,idTxt,emailTxt,marksTxt);

                if(checkupdate){
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Entry not Updated", Toast.LENGTH_SHORT).show();
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameTxt = name.getText().toString();

                Boolean checkdeletedata = DB.deleteuserdata(nameTxt);

                if(checkdeletedata){
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Entry not Deleted", Toast.LENGTH_SHORT).show();
                }

            }
        });

        view.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Cursor res = DB.getdata();
                if(res.getCount() == 0){
                    Toast.makeText(MainActivity.this, "Nothing existed!", Toast.LENGTH_SHORT).show();

                }
                else {

                    StringBuffer buffer = new StringBuffer();
                    while(res.moveToNext()){
                        buffer.append("Name: "+res.getString(0)+"\n");
                        buffer.append("id: "+res.getString(1)+"\n");
                        buffer.append("email: "+res.getString(2)+"\n\n\n");
                        buffer.append("marks: "+res.getString(2)+"\n\n\n");
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("Results");
                    builder.setMessage(buffer.toString());
                    builder.show();
                }
            }
        });


    }
}
