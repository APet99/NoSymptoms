package com.nosymptoms.questionnaireapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionnaireActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
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
