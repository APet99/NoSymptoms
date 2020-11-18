package com.nosymptoms.questionnaireapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nosymptoms.questionnaireapp.R;
import com.nosymptoms.questionnaireapp.dao.UserDao;
import com.nosymptoms.questionnaireapp.model.User;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {

    public EditText cbuIDNum;
    public EditText password;
    public Button login;
    public TextView welcome;

    @Inject
    public UserDao userDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cbuIDNum = findViewById(R.id.cbu_id_num);
        password = findViewById(R.id.password_login);
        login = findViewById(R.id.login_button);
        welcome = findViewById(R.id.welcome);
    }

    public void onLoginButtonClick(View view) {
        String idStr = cbuIDNum.getText().toString();
        System.out.println("Logging in...");

        if (idStr.matches("\\d+6")) {
            System.out.println("Valid id");
            int id =Integer.parseInt(idStr);

            userDAO.getUserById(id).get().addOnSuccessListener(documentSnapshot -> {
                User user = documentSnapshot.toObject(User.class);

                System.out.println("Checking for user...");
                //check if user is valid
                boolean isValid = isUserInDB(user, password.getText().toString());

                if (isValid) {
                    System.out.println("User exists");

                    Intent intent = new Intent(this, HomeActivity.class);

                    startActivity(intent);
                }
            });

        }


    }

    private boolean isUserInDB (User user, String userPass){

        //check if the username and password match a user in the database
        if((user == null) || (!user.getPassword().equals(userPass))){
            System.out.println(String.format("User: %s", user));
            Toast.makeText(getApplicationContext(), "Username or password is not valid", Toast.LENGTH_SHORT).show();
            failedLogin();
            return false;
        }

        successfulLogin();
        return true;
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
