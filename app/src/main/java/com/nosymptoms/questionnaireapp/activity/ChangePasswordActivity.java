package com.nosymptoms.questionnaireapp.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.nosymptoms.questionnaireapp.R;
import com.nosymptoms.questionnaireapp.dao.UserDao;

import javax.inject.Inject;

public class ChangePasswordActivity extends AppCompatActivity {
    @Inject
    UserDao userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
    }

    }
