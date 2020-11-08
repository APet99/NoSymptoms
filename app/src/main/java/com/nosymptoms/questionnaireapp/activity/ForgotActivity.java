package com.nosymptoms.questionnaireapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nosymptoms.questionnaireapp.R;
import com.nosymptoms.questionnaireapp.dao.UserDao;
import com.nosymptoms.questionnaireapp.model.User;

import javax.inject.Inject;

public class ForgotActivity extends AppCompatActivity {

    EditText user_id_forgot;
    Button send_email;
    TextView login_forgot;

    @Inject
    UserDao userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        user_id_forgot = (EditText)findViewById(R.id.id_forgot);
        send_email = (Button)findViewById(R.id.send_email);
        login_forgot = (TextView)findViewById(R.id.login_forgot);
    }

    public void onSendButtonClick(View view) {
        //TODO: Add recovery procedure

        boolean isUserValid = isUserInDB(user_id_forgot.toString());

        if(isUserValid){
            //get security question
//            String securityQuestion = userDAO.getUserById(Integer.parseInt(user_id_forgot.toString())).getSecurityQuestion();
            //display security question in TextView and text for response


            //if the text matches the user's response in DB
            //then let user access the Change Password Activity

            Intent intent = new Intent(this, LoginActivity.class); //send to Change Pass Activity
            startActivity(intent);

            //change user's password in the database in Change Pass Activity
            //send to login activity
        }

    }

    private boolean isUserInDB (String userID){
        User userToCheck = userDAO.getUserById(Integer.parseInt(userID));

        //check if the username and password match a user in the database
        if((userToCheck != null)){
            Toast.makeText(getApplicationContext(), "User does not exist. Create an account.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void onRegisterButtonClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void onSignInButtonClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}