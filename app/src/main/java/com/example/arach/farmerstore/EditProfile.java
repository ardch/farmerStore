package com.example.arach.farmerstore;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EditProfile extends AppCompatActivity implements View.OnClickListener {
    EditText editTextEmail, editTextPassword, editTextName, editTextPhone;
    TextView textEmail, textPassword, textName, textPhone, textAddress;
    private FirebaseAuth firebaseAuth;
    private TextView ntextview;
    public DatabaseReference databaseReference;
    private static final String PREFS = "PREFS";
    String userID;
    String email, name, phone, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);

        /* Action Bar */
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Edit Profile");

        userID = "DgFh0QHMmeN1YmBzhPuIRrLdWgg2";
        firebaseAuth = FirebaseAuth.getInstance();
    //    findViewById(R.id.but_delacc).setOnClickListener(this);

        textEmail = (TextView) findViewById(R.id.textEmail);
        textPassword = (TextView) findViewById(R.id.textPassword);
        textName = (TextView) findViewById(R.id.textName);
        textPhone = (TextView) findViewById(R.id.textPhone);
        textAddress = (TextView) findViewById(R.id.textAddress);

        /* ** Click and Edit ** */
        textName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EditActivity.class);
                i.putExtra("head", "แก้ไขชื่อ-นามสกุล");
                i.putExtra("data", "name");
                startActivity(i);
            }
        });
        textPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EditActivity.class);
                i.putExtra("head", "แก้ไขเบอร์โทร");
                i.putExtra("data", "phone");
                startActivity(i);
            }
        });
        textAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EditActivity.class);
                i.putExtra("head", "แก้ไขที่อยู่");
                i.putExtra("data", "address");
                startActivity(i);
            }
        });

        /* ** Show Data from Database ** */
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Users").child(userID);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                email = dataSnapshot.child("email").getValue(String.class);
                name = dataSnapshot.child("name").getValue(String.class);
                phone = dataSnapshot.child("phone").getValue(String.class);
                address = dataSnapshot.child("address").getValue(String.class);
                textEmail.setText(email);
                textName.setText(name);
                textPhone.setText(phone);
                textAddress.setText(address);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth != null) {

        }
    }
    // create an action bar button
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuactionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }*/

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.actionbarcheck) {
            // do something here
        }
        return super.onOptionsItemSelected(item);
    }
}
