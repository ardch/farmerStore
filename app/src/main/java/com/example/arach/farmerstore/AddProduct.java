package com.example.arach.farmerstore;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
/*import android.support.v7.app.AppCompatActivity;
import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;*/

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class AddProduct extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private static final String PREFS = "PREFS";
    private static String preorder;
    private static String addoredit;
    String unitSelect;
    TextView textName, perunit;
    EditText editName, editQ, editP, editD, editM, editY;
    String OrderID = "-LQXgxS1fUu4hixoiIhi";
    private static String[] fruits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproduct);

        Spinner spinnerUnit = findViewById(R.id.dropdownUnit);
        textName = (TextView) findViewById(R.id.textProductName);
        perunit = (TextView) findViewById(R.id.perunit);

        /* Action Bar */
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Edit Product");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String[] unit = {"กิโลกรัม","ผล"};
        ArrayAdapter<String> adapterSelect = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, unit);
        spinnerUnit.setAdapter(adapterSelect);
        spinnerUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unitSelect = unit[position];
                perunit.setText("ต่อ 1 " + unit[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*SharedPreferences sp = getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("CurrentOrder",t);
        editor.commit();*/

        Switch preorderSwitch = (Switch) findViewById(R.id.switchPreorder);
        preorderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RelativeLayout layout = (RelativeLayout) findViewById(R.id.preorderlayout);
                if(isChecked){
                    layout.setVisibility(RelativeLayout.VISIBLE);
                    preorder = "true";
                }
                else {
                    layout.setVisibility(RelativeLayout.GONE);
                }
            }
        });


        /* ** FRUIT NAME SELECT ** */
        textName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = database.getInstance().getReference().child("FruitName");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int i = 0;
                        ArrayList<String> fname = new ArrayList<>();
                        for (DataSnapshot d: dataSnapshot.getChildren()){
                            fname.add(d.child("Name").getValue().toString());
                            i++;
                        }
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddProduct.this);
                        builder.setTitle("เลือกชนิดผลไม้");
                        fruits = fname.toArray(new String[fname.size()]);
                        builder.setItems(fruits, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                textName.setText(fruits[which]);
                            }
                        });
                        builder.show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        textName.setText("Unable to select fruit name!");
                    }
                });
            }
        });
    }


    public void saveData(){
        String farmID = "-LQXekLT3h7e4X5R04e5";
        textName = (TextView) findViewById(R.id.textProductName);
        perunit = (TextView) findViewById(R.id.perunit);
        editName = (EditText) findViewById(R.id.addTextProductName);
        editQ = findViewById(R.id.editQuantity);
        editP = findViewById(R.id.editprice);
        editD = findViewById(R.id.editDate);
        editM = findViewById(R.id.editMonth);
        editY = findViewById(R.id.editYear);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        Intent i = getIntent();
        addoredit = i.getStringExtra("do");

        if (addoredit.matches("edit")){
            databaseReference.child("Order").child(OrderID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String productID = dataSnapshot.child("productID").getValue(String.class);
                    DatabaseReference dataRef = database.getReference().child("product").child(productID);
                    dataRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String productName = dataSnapshot.child("productName").getValue(String.class);
                            textName.setText(productName);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
        else if (addoredit.matches("add")){
            String fruitName = textName.getText().toString();
            String productName = editName.getText().toString().trim();
            int price = Integer.parseInt(editP.getText().toString().trim());
            int quantity = Integer.parseInt(editQ.getText().toString().trim());
            String dDate = editD.getText().toString().trim();
            String mDate = editM.getText().toString().trim();
            String yDate = editY.getText().toString().trim();
            Switch preorderSwitch = findViewById(R.id.switchPreorder);
            if(preorderSwitch.isChecked()){
                DatabaseReference dref = databaseReference.child("product").child("preorderProduct");
                ProductModel productModel = new ProductModel(farmID, fruitName, productName, price,
                        quantity, unitSelect,dDate+"-"+mDate+"-"+yDate);
                dref.push().setValue(productModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),
                                "เพิ่ม/แก้ไขสินค้าเสร็จสิ้น", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), SellerSetting.class);
                        startActivity(i);
                    }
                });
            }
            else {
                DatabaseReference dref = databaseReference.child("product").child("marketProduct");
                    ProductModel productModel = new ProductModel(farmID, fruitName, productName, price, quantity, unitSelect);
                    dref.push().setValue(productModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getApplicationContext(),
                                    "เพิ่ม/แก้ไขสินค้าเสร็จสิ้น", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), SellerSetting.class);
                            startActivity(i);
                        }
                    });
            }
        }
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
        if (id == R.id.actionbarcheck){

            /* ** CHECK DATA FIELD ** */

            textName = findViewById(R.id.textProductName);
            editName = findViewById(R.id.addTextProductName);
            editQ = findViewById(R.id.editQuantity);
            editP = findViewById(R.id.editprice);
            editD = findViewById(R.id.editDate);
            editM = findViewById(R.id.editMonth);
            editY = findViewById(R.id.editYear);

            if (textName.getText().toString().matches("เลือกชื่อผลไม้")){
                Toast.makeText(getApplicationContext(), "กรุณาเลือกชื่อผลไม้", Toast.LENGTH_SHORT).show();
                return false;
            }
            else if (editName.getText().length()<=0){
                Toast.makeText(getApplicationContext(), "กรุณาใส่ชื่อสินค้า", Toast.LENGTH_SHORT).show();
                return false;
            }
            else if (editQ.getText().length()<=0){
                Toast.makeText(getApplicationContext(), "กรุณาใส่จำนวนผลไม้", Toast.LENGTH_SHORT).show();
                return false;
            }
            else if (editP.getText().length()<=0){
                Toast.makeText(getApplicationContext(), "กรุณาใส่ราคาผลไม้", Toast.LENGTH_SHORT).show();
                return false;
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(AddProduct.this);
            builder.setMessage(R.string.savedata);
            builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    saveData();
                }
            });
            builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }
        else{
            Intent i = new Intent(getApplicationContext(), SellerSetting.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

}



