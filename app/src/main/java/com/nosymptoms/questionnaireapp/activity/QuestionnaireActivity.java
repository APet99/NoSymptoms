package com.nosymptoms.questionnaireapp.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.nosymptoms.questionnaireapp.R;
import com.nosymptoms.questionnaireapp.model.LogEntry;

import java.util.BitSet;
import java.util.Calendar;

public class QuestionnaireActivity extends AppCompatActivity {

    Button photo;
    public static Boolean checkedIn = false;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        photo = (Button)findViewById(R.id.photo_button);
        System.out.println(Calendar.getInstance().getTime().toString().substring(0));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            bitmap = (Bitmap) data.getExtras().get("data");
        }
    }

    public void onSubmitButtonClick(View view) {
        RadioGroup group1 = findViewById(R.id.q1_radio_group);
        RadioGroup group2 = findViewById(R.id.q2_radio_group);
        CheckBox cough = findViewById(R.id.q3_check_symp1);
        CheckBox breath = findViewById(R.id.q3_check_symp2);
        CheckBox sneeze = findViewById(R.id.q3_check_symp3);
        CheckBox fever = findViewById(R.id.q3_check_symp4);
        CheckBox chills = findViewById(R.id.q3_check_symp5);
        CheckBox runny = findViewById(R.id.q3_check_symp6);
        EditText temp = findViewById(R.id.question4);

        if (group1.getCheckedRadioButtonId() == -1 || group2.getCheckedRadioButtonId() == -1 ||
                temp.getText().toString().matches("") || bitmap == null) {
            Toast.makeText(getBaseContext(), "Please answer all questions", Toast.LENGTH_LONG).show();
        } else if (group1.getCheckedRadioButtonId() == R.id.q1_radio_yes ||
                group2.getCheckedRadioButtonId() == R.id.q2_radio_yes ||
                cough.isChecked() || breath.isChecked() || sneeze.isChecked() ||
                fever.isChecked() || chills.isChecked() || runny.isChecked() ||
                Double.parseDouble(temp.getText().toString()) >= 100.4) {
            checkedIn = true;
            HomeActivity.log.add(new LogEntry(false, bitmap));
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else {
            checkedIn = true;
            HomeActivity.log.add(new LogEntry(true, bitmap));
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }

    public void onPhotoButtonClick(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }
}
