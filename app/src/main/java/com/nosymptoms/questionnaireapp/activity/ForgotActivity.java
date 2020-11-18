package com.nosymptoms.questionnaireapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
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

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        user_id_forgot = findViewById(R.id.id_forgot);
        change_pass = findViewById(R.id.enter_button);
        login_forgot = findViewById(R.id.login_forgot);
        securityAnswer =  findViewById(R.id.security_answer);
        securityQuestion =  findViewById(R.id.security_question);
        continue_button = findViewById(R.id.continue_button);


    }

    public void onEnterButtonClick(View view) {
        //TODO: Add recovery procedure
        String user_id = user_id_forgot.toString();
        //check if the user is valid

        userDAO.getUserById(Integer.parseInt(user_id)).get().addOnSuccessListener(
                documentSnapshot -> {
                    user = documentSnapshot.toObject(User.class);
                    boolean isUserValid = isUserInDB(user);

                    // if user is valid, display question and answer
                    if (isUserValid) {
                        displayQuestionAndAnswer(user);
                    }
                }
        );

    }

    public void onContinueButtonClick (View View) {
        String user_id = user_id_forgot.toString();
        String user_answer = securityAnswer.toString();

        //if the text matches the user's response in DB
        boolean correctAnswer = isSecurityAnswerCorrect(user, user_answer);
        //then let user access the Change Password Activity
        if(correctAnswer){
            //send to Change Pass Activity
            Intent intent = new Intent(this, ChangePasswordActivity.class);
            startActivity(intent);
        }

    }


    private void displayQuestionAndAnswer(User user){
        securityAnswer.setVisibility(View.VISIBLE);
        //display the continue button
        continue_button.setVisibility(View.VISIBLE);

        //get the user question and set the text to the user question
        String user_question = user.getSecurityQuestion();
        securityQuestion.setText(user_question);

        //display question
        securityQuestion.setVisibility(View.VISIBLE);

    }

    private boolean isSecurityAnswerCorrect(User userToCheck, String answerInput){
        if (!(userToCheck.getSecurityAnswer().equals(answerInput))){
            Toast.makeText(getApplicationContext(), "Answer does not match. Please try again.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean isUserInDB (User userToCheck){
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