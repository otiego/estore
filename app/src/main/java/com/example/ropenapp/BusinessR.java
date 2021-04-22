package com.example.ropenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class BusinessR extends AppCompatActivity implements View.OnClickListener {
    private TextView bLogin2;
    private EditText editTextBusinessName, editTextEmail,editTextPhoneNumber, editTextLocation, editTextPassword;
    private Button bRegBtn;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        bLogin2 = (TextView) findViewById(R.id.bLogin2);
        bLogin2.setOnClickListener(this);

        bRegBtn = (Button) findViewById(R.id.registerBusiness);
        bRegBtn.setOnClickListener(this);

        editTextBusinessName = (EditText) findViewById(R.id.businessName);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPhoneNumber = (EditText) findViewById(R.id.phoneNumber);
        editTextLocation = (EditText) findViewById(R.id.location);
        editTextPassword = (EditText) findViewById(R.id.password);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bLogin2:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.registerBusiness:
                registerBusiness();
                break;
        }

    }

    private void registerBusiness() {

        String businessName = editTextBusinessName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        String location = editTextLocation.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


        if (businessName.isEmpty()) {
            editTextBusinessName.setError("Business name is required!");
            editTextBusinessName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email is reqiured!");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email1");
            editTextEmail.requestFocus();
            return;
        }

        if (phoneNumber.isEmpty()) {
            editTextPhoneNumber.setError("Phone number is required!");
            editTextPhoneNumber.requestFocus();
            return;
        }

        if (location.isEmpty()) {
            editTextLocation.setError("Location is reqiured!");
            editTextLocation.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is reqiured!");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 8) {
            editTextPassword.setError("Min length should be 8 characters!");
            editTextPassword.requestFocus();
            return;
        }




    }
}