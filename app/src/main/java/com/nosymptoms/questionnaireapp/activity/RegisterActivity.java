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

import com.google.android.gms.common.util.NumberUtils;
import com.nosymptoms.questionnaireapp.R;
import com.nosymptoms.questionnaireapp.dao.UserDao;
import com.nosymptoms.questionnaireapp.model.User;

import java.util.Objects;

import javax.inject.Inject;

public class RegisterActivity extends AppCompatActivity {

    public Button register_button;

    public TextView sign_in_text;

    public EditText idNumber;
    public EditText email;
    public EditText firstName;
    public EditText lastName;
    public EditText password;
    public EditText confirmPass;
    public EditText securityQuestion;
    public EditText securityAnswer;

    @Inject
    public UserDao userDao; //Made this public because it wasn't compiling otherwise


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        email = (EditText) findViewById(R.id.email);
        idNumber = (EditText) findViewById(R.id.user_id_number);
        firstName = (EditText) findViewById(R.id.first_name_reg);
        lastName = (EditText) findViewById(R.id.last_name_reg);
        password = (EditText) findViewById(R.id.password_register);
        confirmPass = (EditText) findViewById(R.id.confirm);
        securityQuestion = (EditText) findViewById(R.id.question);
        securityAnswer = (EditText) findViewById(R.id.answer);

        register_button = (Button) findViewById(R.id.register_button);

        sign_in_text = (TextView) findViewById(R.id.sign_in_text);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegisterButtonClick();
            }
        });
    }

    private void onRegisterButtonClick() {
        //set everything to a string value
        String userEmail = email.getText().toString().trim();
        String userFirst = firstName.getText().toString().trim();
        String userLast = lastName.getText().toString().trim();
        String userPass = password.getText().toString().trim();
        String userPassConfirm = confirmPass.getText().toString().trim();
        String userQuestion = securityQuestion.getText().toString().trim();
        String userAnswer = securityAnswer.getText().toString().trim();
        String id = idNumber.getText().toString().trim();

        //check if values are valid
        boolean valid = checkUserValues(id, userEmail, userFirst, userLast, userPass, userPassConfirm, userQuestion, userAnswer);

        //if valid then push to database as a new user
        if(valid) {
            User newUser = new User();
            newUser.setEmail(userEmail);
            newUser.setFirstName(userFirst);
            newUser.setLastName(userLast);
            newUser.setPassword(userPass);
            newUser.setSecurityQuestion(userQuestion);
            newUser.setSecurityAnswer(userAnswer);
            newUser.setId(Integer.parseInt(id));

            userDao.createUser(newUser);

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }


    //check to make sure that the users values are correct
    private boolean checkUserValues(String id, String userEmail, String userFirst, String userLast, String userPass, String confirmPass, String userQuestion, String userAnswer) {
        boolean valid = false;
        if (TextUtils.isEmpty(userEmail) || (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches())) {
            Toast.makeText(getApplicationContext(), "Enter a valid email address!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(userPass)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(id) && id.matches("\\d+5")) {
            Toast.makeText(getApplicationContext(), "Enter CBU ID Number!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(userFirst) || (TextUtils.isEmpty(userLast))) {
            Toast.makeText(getApplicationContext(), "Enter your first and last name!", Toast.LENGTH_SHORT).show();
        } else if (userPass.length() < 10) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 10 characters!", Toast.LENGTH_SHORT).show();
        } else if (Objects.equals(userPass, confirmPass)) {
            Toast.makeText(getApplicationContext(), "Passwords do not match!!", Toast.LENGTH_SHORT).show();
        } else if ((TextUtils.isEmpty(userAnswer) || (TextUtils.isEmpty(userQuestion)))){
            Toast.makeText(getApplicationContext(), "Please enter a value for the security question and answer.", Toast.LENGTH_SHORT).show();
        } else if (userDao.getUserById(Integer.parseInt(id)) != null) {
            Toast.makeText(getApplicationContext(), String.format("User with id: %s already exists!", id), Toast.LENGTH_SHORT).show();
        } else {
            valid = true;
        }

        return valid;
    }

    /*
     * Go to sign-up activity if button is clicked
     */
    public void onSignInButtonClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
