package com.example.arach.farmerstore;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditActivity extends AppCompatActivity {
    String data, head, userID;
    TextView attr ;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);

        userID = "DgFh0QHMmeN1YmBzhPuIRrLdWgg2";

        head = getIntent().getStringExtra("head");
        data = getIntent().getStringExtra("data");
        attr = (TextView) findViewById(R.id.editProfileText);
        attr.setText(head);

        editText = (EditText) findViewById(R.id.editProfileEditText) ;

        /* Action Bar */
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Edit Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuactionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.actionbarcheck) {
            AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
            builder.setMessage("ยืนยันการแก้ไข");
            builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                    final String userInput = editText.getText().toString().trim();
                    database.child("Users").child(userID).child(data).setValue(userInput);
                    Toast.makeText(getApplicationContext(),
                            "การแก้ไขเสร็จสิ้น", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), EditProfile.class);
                    startActivity(i);
                }
            });
            builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //dialog.dismiss();
                }
            });
            builder.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
