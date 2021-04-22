package com.example.ropenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForgotPassword extends  AppCompatActivity implements View.OnClickListener{
    private EditText editTextEmail;
    private Button fgtPassBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        fgtPassBtn = (Button) findViewById(R.id.resetPassword);
        fgtPassBtn.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.email);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.resetPassword:
                forgotPassword();
                break;
        }

    }

    private void forgotPassword() {
        String email = editTextEmail.getText().toString().trim();

        if (email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }
    }
}