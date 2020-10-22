package com.nosymptoms.questionnaireapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText email;
    EditText username_register;
    EditText password_register;
    EditText confirm;
    Button register_button;
    TextView sign_in_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText)findViewById(R.id.email);
        username_register = (EditText)findViewById(R.id.username_register);
        password_register = (EditText)findViewById(R.id.password_register);
        confirm = (EditText)findViewById(R.id.confirm);
        register_button = (Button)findViewById(R.id.register_button);
        sign_in_text = (TextView)findViewById(R.id.sign_in_text);
    }



    public void onRegisterButtonClick(View view) {
        //TODO: Add Register procedure
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onSignInButtonClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
