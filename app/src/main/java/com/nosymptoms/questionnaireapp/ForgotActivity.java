package com.nosymptoms.questionnaireapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ForgotActivity extends AppCompatActivity {

    EditText email_forgot;
    EditText user_forgot;
    Button send_email;
    TextView login_forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        email_forgot = (EditText)findViewById(R.id.email_forgot);
        user_forgot = (EditText)findViewById(R.id.username_forgot);
        send_email = (Button)findViewById(R.id.send_email);
        login_forgot = (TextView)findViewById(R.id.login_forgot);
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