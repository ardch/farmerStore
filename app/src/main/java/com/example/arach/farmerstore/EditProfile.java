package com.example.arach.farmerstore;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class EditProfile extends AppCompatActivity implements View.OnClickListener {
    EditText editTextEmail , editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);
        editTextEmail = (EditText) findViewById(R.id.EditTextEmail);
        editTextPassword = (EditText) findViewById(R.id.EditTextPassword);

        findViewById(R.id.but_delacc).setOnClickListener(this);
    }
    private void registerNewUser(){

        String emailString = editTextEmail.getText().toString().trim();
        String passwordString = editTextPassword.getText().toString().trim();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(emailString,passwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(EditProfile.this, "Register Success",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditProfile.this, "Failed",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.but_delacc:
                registerNewUser();
                break;
        }
    }
}
