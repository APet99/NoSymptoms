package com.nosymptoms.questionnaireapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.nosymptoms.questionnaireapp.R;

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
        boolean cleared = true;
        RadioGroup group1 = findViewById(R.id.q1_radio_group);
        RadioGroup group2 = findViewById(R.id.q2_radio_group);
        CheckBox cough = findViewById(R.id.q3_check_symp1);
        CheckBox breath = findViewById(R.id.q3_check_symp2);
        CheckBox sneeze = findViewById(R.id.q3_check_symp3);
        CheckBox fever = findViewById(R.id.q3_check_symp4);
        CheckBox chills = findViewById(R.id.q3_check_symp5);
        CheckBox runny = findViewById(R.id.q3_check_symp6);
        if (group1.getCheckedRadioButtonId() == -1 || group2.getCheckedRadioButtonId() == -1) {
            findViewById(R.id.invalid_questionnaire).setVisibility(View.VISIBLE);
        } else if (group1.getCheckedRadioButtonId() == -1 || group2.getCheckedRadioButtonId() == -1
                || cough.isChecked()) {

        }
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void onPhotoButtonClick(View view) {
        //TODO: Implement photo procedure
    }
}
