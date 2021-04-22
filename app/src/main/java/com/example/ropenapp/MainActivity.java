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

public class  MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView registerClient,registerBusiness,forgotPswd;
    private EditText editTextEmail, editTextPassword;
    private Button signIn;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerClient = (TextView) findViewById(R.id.registerAsClient);
        registerClient.setOnClickListener(this);

        registerBusiness = (TextView) findViewById(R.id.registerAsBusiness);
        registerBusiness.setOnClickListener(this);

        forgotPswd = (TextView) findViewById(R.id.forgtPassword);
        forgotPswd.setOnClickListener(this);

        signIn = (Button) findViewById(R.id.signIn);
        signIn.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerAsClient:
                startActivity(new Intent(this,RegisterClient.class));
                break;
            case R.id.registerAsBusiness:
                startActivity(new Intent(this,BusinessR.class));
                break;
            case R.id.forgtPassword:
                startActivity(new Intent(this,ForgotPassword.class));
                break;
            case R.id.signIn:
                userLogin();
                break;

        }
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

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

        if(password.isEmpty()){
            editTextPassword.setError("Password is require!");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 8){
            editTextPassword.setError("Minimum length is 8 characters!");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
    }
}