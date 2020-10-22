package com.nosymptoms.questionnaireapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionnaireActivity extends AppCompatActivity {

    Button photo;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        photo = (Button)findViewById(R.id.photo_button);
        submit = (Button)findViewById(R.id.submit_questionnaire);
    }

    public void onSubmitButtonClick(View view) {
        //TODO: Add submission procedure
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void onPhotoButtonClick(View view) {
        //TODO: Implement photo procedure
    }
}
