package com.nosymptoms.questionnaireapp.activity;

import android.content.Intent;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {

    EditText cbuIDNum;
    EditText password;
    Button login;
    TextView welcome;

    @Inject
    UserDao userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cbuIDNum = (EditText)findViewById(R.id.cbu_id_num);
        password = (EditText)findViewById(R.id.password_login);
        login = (Button)findViewById(R.id.login_button);
        welcome = findViewById(R.id.welcome);
    }

    public void onLoginButtonClick(View view) {
        //check if user is valid
//        boolean isValid = isUserInDB(cbuIDNum.toString(), password.toString());
////
////        if(isValid){
////            String message = "Welcome " + userDAO.getUserById(Integer.parseInt(cbuIDNum.toString())).getFirstName() + "!";
////            welcome.setText(message);
////            Intent intent = new Intent(this, HomeActivity.class);
////            startActivity(intent);
////        }

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);


    }

    private boolean isUserInDB (String userID, String userPass){
        User userToCheck = userDAO.getUserById(Integer.parseInt(userID));

        //check if the username and password match a user in the database
        if((userToCheck != null) || (!userToCheck.getPassword().equals(userPass))){
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
