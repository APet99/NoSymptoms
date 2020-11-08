package com.nosymptoms.questionnaireapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nosymptoms.questionnaireapp.R;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login_button);
    }

    public void onLoginButtonClick(View view) {
        //TODO: Add login procedure
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

    }

    public void successfulLogin(){
        login.setEnabled(true);
        finish();
    }

    public void failedLogin(){
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
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
