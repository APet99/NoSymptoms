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

    public EditText user_id_forgot;
    public TextView securityQuestion;
    public EditText securityAnswer;
    public Button change_pass;
    public Button continue_button;
    public TextView login_forgot;

    @Inject
    public UserDao userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        user_id_forgot = (EditText)findViewById(R.id.id_forgot);
        change_pass = (Button)findViewById(R.id.enter_button);
        login_forgot = (TextView)findViewById(R.id.login_forgot);
        securityAnswer = (EditText) findViewById(R.id.security_answer);
        securityQuestion = (TextView) findViewById(R.id.security_question);
        continue_button = (Button) findViewById(R.id.continue_button);

    }

    public void onEnterButtonClick(View view) {
        //TODO: Add recovery procedure
        String user_id = user_id_forgot.toString();
        //check if the user is valid
        boolean isUserValid = isUserInDB(user_id);

        // if user is valid, display question and answer
        if (isUserValid) {
            displayQuestionAndAnswer();
        }
    }

    public void onContinueButtonClick (View View) {
        String user_id = user_id_forgot.toString();
        String user_answer = securityAnswer.toString();

        //if the text matches the user's response in DB
        boolean correctAnswer = isSecurityAnswerCorrect(user_id, user_answer);
        //then let user access the Change Password Activity

//            Intent intent = new Intent(this, LoginActivity.class); //send to Change Pass Activity
//            startActivity(intent);

        //change user's password in the database in Change Pass Activity
        //send to login activity
    }


    private void displayQuestionAndAnswer(){
        securityAnswer.setVisibility(View.VISIBLE);
        //display the continue button
        continue_button.setVisibility(View.VISIBLE);

        //get the user question and set the text to the user question
        String user_question = userDAO.getUserById(Integer.parseInt(user_id_forgot.toString())).getSecurityQuestion();
        securityQuestion.setText(user_question);

        //display question
        securityQuestion.setVisibility(View.VISIBLE);

    }

    private boolean isSecurityAnswerCorrect(String userID, String answerInput){
        //checks to make sure that the answer entered is correct
        User userToCheck = userDAO.getUserById(Integer.parseInt(userID));

        if (!(userToCheck.getSecurityAnswer().equals(answerInput))){
            Toast.makeText(getApplicationContext(), "Answer does not match. Please try again.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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