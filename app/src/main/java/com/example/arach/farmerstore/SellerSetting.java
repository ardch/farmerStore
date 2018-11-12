package com.example.arach.farmerstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class SellerSetting extends AppCompatActivity {
    TextView farmName, farmerID, farmDes, farmRating, addProduct;
    String textFarmName, textFarmerNumber, textFarmDes;
    Double textFarmRating;
    String farmID;
    Integer farmSell;
    ArrayList<Integer> pSell = new ArrayList<>();
    ArrayList<String> pFruit = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sellersetting);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.sellersetting);

        farmID = "-LQXekLT3h7e4X5R04e5";

        farmName = (TextView) findViewById(R.id.farmName);
        farmerID = (TextView) findViewById(R.id.farmerID);
        farmDes = (TextView) findViewById(R.id.farmDes);
        farmRating = (TextView) findViewById(R.id.farmRating);
        addProduct = (TextView) findViewById(R.id.addProduct);

        /* ** ADD TO ADDPRODUCT PAGE ** */
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddProduct.class);
                i.putExtra("do","add");
                startActivity(i);
            }
        });

        /* ** FARM HEADER ** */
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference dataRef1 = database.getReference().child("farmer").child(farmID);
        dataRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                textFarmName = dataSnapshot.child("farmName").getValue(String.class);
                textFarmDes = dataSnapshot.child("farmDescription").getValue(String.class);
                textFarmerNumber = dataSnapshot.child("farmerNumber").getValue(String.class);
                textFarmRating = dataSnapshot.child("farmRating").getValue(Double.class);
                farmSell = dataSnapshot.child("farmSellProduct").getValue(Integer.class);
                farmName.setText(textFarmName);
                farmDes.setText(textFarmDes);
                farmerID.setText(textFarmerNumber);
                farmRating.setText(textFarmRating.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /* ** SHOW ALL PRODUCT ** */
        DatabaseReference dataRef2 = database.getReference().child("product").child("marketProduct");
        DatabaseReference dataRef3 = database.getReference().child("product").child("preorderProduct");
        Query q1 = dataRef2.orderByChild("farmID").equalTo(farmID);
        Query q2 = dataRef3.orderByChild("farmID").equalTo(farmID);
        q1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()){
                    String fname = d.child("fruitName").getValue(String.class);
                    String pname = d.child("productName").getValue(String.class);
                    Integer psell = d.child("productSell").getValue(Integer.class);
                    pFruit.add(fname+pname);
                    pSell.add(psell);
                }
                ListView listView = findViewById(R.id.list_product);
                ViewGroup.LayoutParams params = listView.getLayoutParams();
                if (pFruit.size()<=10){
                    float pixels = pFruit.size()*120 * getResources().getDisplayMetrics().density;
                    params.height = (int)pixels;
                    listView.setLayoutParams(params);
                }
                else{
                    float pixels = 1200 * getResources().getDisplayMetrics().density;
                    params.height = (int)pixels;
                    listView.setLayoutParams(params);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ListView list = (ListView) findViewById(R.id.list_product);
        AdapterPL adapter = new AdapterPL(SellerSetting.this, pFruit, pSell);
        list.setAdapter(adapter);

        /* ** Go to edit page ** */
        /*Button button = (Button)findViewById(R.id.butEdit1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddProduct.class);
                //i.putExtra("orderID","");
                startActivity(i);
            }
        });*/
    }
}
