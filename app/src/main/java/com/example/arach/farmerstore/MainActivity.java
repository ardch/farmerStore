package com.example.arach.farmerstore;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public DatabaseReference testapp;
    private TextView ntextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Edit Profile");

        /* Bottom Navigation */
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i;
                switch (item.getItemId()) {
                    case R.id.tabhome:
                //        i = new Intent(getApplicationContext(), MainActivity.class); startActivity(i);
                        return true;
                    case R.id.taborder:
                        i = new Intent(getApplicationContext(), SellerSetting.class); startActivity(i);
                        return true;
                    case R.id.tabrequest:
                //        i = new Intent(getApplicationContext(), BuyerSetting.class); startActivity(i);
                        return true;
                    case R.id.tabprofile:
                //        i = new Intent(getApplicationContext(), EditProfile.class); startActivity(i);
                        return true;
                }
                return false;
            }
        });

        /* Button Click */
        Button button = (Button) findViewById(R.id.butEditProfile);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EditProfile.class);
                startActivity(i);
            }
        });
        Button button2 = (Button) findViewById(R.id.butSeller);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), SellerSetting.class);
                startActivity(i2);
            }
        });
        Button button3 = (Button) findViewById(R.id.butBuyer);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(getApplicationContext(), BuyerSetting.class);
                startActivity(i3);
            }
        });

        //  Get Data From Firebase (ID)
        ntextview = (TextView) findViewById(R.id.test);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        testapp = database.getReference();
        testapp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map map = (Map) dataSnapshot.getValue();
                String username = String.valueOf(map.get("id"));
                ntextview.setText(username);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                ntextview.setText("Failed");
            }
        });
    }

    // Add Data to Firebase id:"ccc"
    public void addData(View view) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("id", "ccc");
        testapp.updateChildren(value);
    }
}
