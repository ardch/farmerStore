package com.example.arach.farmerstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        Button button = (Button)findViewById(R.id.butEditProfile);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EditProfile.class);
                startActivity(i);
            }
        });
        Button button2 = (Button)findViewById(R.id.butSeller);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), SellerSetting.class);
                startActivity(i2);
            }
        });

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

    public void addData(View view) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("id","ccc");
        testapp.updateChildren(value);
    }
}
