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

public class RegisterClient extends AppCompatActivity implements View.OnClickListener {
    private TextView bLogin1;
    private EditText editTextFullName,editTextEmail,editTextPhoneNumber,editTextPassword;
    private Button cRegBtn;
    private ProgressBar progressBar;

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
    }
}