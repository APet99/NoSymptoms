package com.nosymptoms.questionnaireapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.view.View;

import com.nosymptoms.questionnaireapp.R;
import com.nosymptoms.questionnaireapp.model.LogEntry;
import com.nosymptoms.questionnaireapp.ui.main.SectionsPagerAdapter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    Map<Integer,String> linkMap;
    public static List<LogEntry> log = new LinkedList<LogEntry>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }

    public void onCheckInButtonClick(View view) {
        Intent intent = new Intent(this, QuestionnaireActivity.class);
        startActivity(intent);
    }

    public void onResourceButtonClick(View view) {
        //The map is filled on the first time a resource is clicked
        if (linkMap == null) {
            linkMap = new HashMap<>();
            ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.resource_fragment);
            String[] links = getResources().getStringArray(R.array.links);
            for (int i = 0; i < layout.getChildCount(); i++) {
                linkMap.put(layout.getChildAt(i).getId(), links[i]);
            }
        }

        String link = linkMap.get(view.getId());
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);
    }
}