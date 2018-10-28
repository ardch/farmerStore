package com.example.arach.farmerstore;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
/*import android.support.v7.app.AppCompatActivity;
import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;*/

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


public class AddProduct extends Activity {

    private Spinner productUnitSpinner;
    private DatePickerDialog mDatePicker;
    private Calendar mCalendar;
    private TextView mTextDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproduct);

        /* * Switch for Preorder *
        productUnitSpinner = (Spinner)findViewById(R.id.dropdownUnit);
        String[] productUnit = getResources().getStringArray(R.array.proUnit);
        ArrayAdapter<String> adapterUnit = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, productUnit);
        productUnitSpinner.setAdapter(adapterUnit);

        *//* * Select Date **/
        /*mTextDate = (TextView) findViewById(R.id.button_date);
        mCalendar = Calendar.getInstance();

        mDatePicker = DatePickerDialog.newInstance(onDateSetListener,
                mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH),
                false); // vibrate*/

        /*PrefFragment prefFragment = new PrefFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(android.R.id.content, prefFragment);
        fragmentTransaction.commit();*/
    }

    /*private final DatePickerDialog.OnDateSetListener onDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {

                    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
                    mCalendar.set(year, month, day);
                    Date date = mCalendar.getTime();
                    String textDate = dateFormat.format(date);

                    mTextDate.setText(textDate);
                    mTextDate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mDatePicker.setYearRange(2000, 2020);
                            mDatePicker.show(getSupportFragmentManager(), "datePicker");
                        }
                    });
                }

            };*/
}



