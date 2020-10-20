package com.nosymptoms.questionnaireapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ForgotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
    }

    public void onSendButtonClick(View view) {
        //TODO: Add recovery procedure
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onSignInButtonClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}