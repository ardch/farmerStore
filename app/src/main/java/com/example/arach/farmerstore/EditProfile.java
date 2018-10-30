package com.example.arach.farmerstore;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class EditProfile extends AppCompatActivity implements View.OnClickListener {
    EditText editTextEmail, editTextPassword, editTextName, editTextPhone;
    private FirebaseAuth firebaseAuth;
    private TextView ntextview;
    public DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);

        /* Set Variable */
        editTextEmail = (EditText) findViewById(R.id.EditTextEmail);
        editTextPassword = (EditText) findViewById(R.id.EditTextPassword);
        editTextName = (EditText) findViewById(R.id.EditTextUsername);
        editTextPhone = (EditText) findViewById(R.id.EditTextPhone);

        firebaseAuth = FirebaseAuth.getInstance();
        findViewById(R.id.but_delacc).setOnClickListener(this);

        /* ** Show Data from Database ** */
        ntextview = (TextView) findViewById(R.id.edit_text6);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map map = (Map) dataSnapshot.getValue();
                String username = String.valueOf(map.get("id"));
                ntextview.setText(username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void registerNewUser(){

        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        final String username = editTextName.getText().toString().trim();
        final String phone = editTextPhone.getText().toString().trim();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    com.example.arach.farmerstore.User user = new com.example.arach.farmerstore.User(
                            username, email, phone
                    );
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth
                            .getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(EditProfile.this, "Register Success",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
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
    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth != null) {

        }
    }
}
