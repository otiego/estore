package com.example.ropenapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterClient extends AppCompatActivity implements View.OnClickListener {
    private TextView bLogin1;
    private EditText editTextFullName,editTextEmail,editTextPhoneNumber,editTextPassword;
    private Button cRegBtn;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client);

        bLogin1 =(TextView) findViewById(R.id.bLogin1);
        bLogin1.setOnClickListener(this);

        cRegBtn = (Button) findViewById(R.id.registerClient);
        cRegBtn.setOnClickListener(this);

        editTextFullName = (EditText) findViewById(R.id.fullName);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPhoneNumber = (EditText) findViewById(R.id.phoneNumber);
        editTextPassword = (EditText) findViewById(R.id.password);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bLogin1:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.registerClient:
                registerClient();
                break;

        }
    }
    private void registerClient() {
        String fullName = editTextFullName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phoneNumber =editTextPhoneNumber.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(fullName.isEmpty()){
            editTextFullName.setError("Full Name is required!");
            editTextFullName.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid email!");
            editTextEmail.requestFocus();
            return;
        }

        if(phoneNumber.isEmpty()){
            editTextPhoneNumber.setError("Phone Number is required");
            editTextPhoneNumber.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 8){
            editTextPassword.setError("Min length is 8 characters");
            editTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()){
                           UserClient userclient = new UserClient(fullName, email, phoneNumber);
                           FirebaseDatabase.getInstance().getReference("Clients")
                                   .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                   .setValue(userclient).addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                                   if(task.isSuccessful()){
                                       Toast.makeText(RegisterClient.this, "Client has been registered successfully", Toast.LENGTH_LONG).show();
                                       progressBar.setVisibility(View.VISIBLE);
                                   }else{
                                       Toast.makeText(RegisterClient.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                       progressBar.setVisibility(View.GONE);
                                   }
                               }
                           });
                       }else
                       {
                           Toast.makeText(RegisterClient.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                           progressBar.setVisibility(View.GONE);
                       }
                    }
                });
    }
}