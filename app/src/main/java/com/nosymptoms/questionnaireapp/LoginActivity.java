package com.nosymptoms.questionnaireapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLoginButtonClick(View view) {
        //TODO: Add login procedure
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onSignUpButtonClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void onForgotButtonClick(View view) {
        Intent intent = new Intent(this, ForgotActivity.class);
        startActivity(intent);
    }
}
