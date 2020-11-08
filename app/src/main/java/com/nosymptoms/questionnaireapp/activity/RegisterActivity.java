package com.nosymptoms.questionnaireapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nosymptoms.questionnaireapp.R;
import com.nosymptoms.questionnaireapp.model.User;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    public EditText email;
    public EditText username_register;
    public EditText password_register;
    public EditText confirm;

    public Button register_button;

    public TextView sign_in_text;
    public User newUser;


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

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUserValues();
            }
        });

    }



    public void onRegisterButtonClick(View view) {
        //TODO: Add Register procedure

        checkUserValues();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    private void checkUserValues(){
        String userEmail = email.getText().toString().trim();
        String userName = username_register.getText().toString().trim();
        String userPass = password_register.getText().toString().trim();
        String confirmPass = confirm.getText().toString().trim();


        if (TextUtils.isEmpty(userEmail) || (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches())) {
            Toast.makeText(getApplicationContext(), "Enter a valid email address!", Toast.LENGTH_SHORT).show();
        return;
         } else if (TextUtils.isEmpty(userPass)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(userName)){
            Toast.makeText(getApplicationContext(), "Enter Username!", Toast.LENGTH_SHORT).show();
            return;
        } else if (userPass.length() < 10 ) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 10 characters!", Toast.LENGTH_SHORT).show();
            return;
        } else if (Objects.equals(userPass, confirmPass)){
            Toast.makeText(getApplicationContext(), "Passwords do not match!!", Toast.LENGTH_SHORT).show();
            return;
        } else {

        }
    }

    /*
     * Go to sign-up activity if button is clicked
     */
    public void onSignInButtonClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
