package com.nosymptoms.questionnaireapp.fragment;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nosymptoms.questionnaireapp.R;
import com.nosymptoms.questionnaireapp.activity.HomeActivity;
import com.nosymptoms.questionnaireapp.activity.QuestionnaireActivity;
import com.nosymptoms.questionnaireapp.model.LogEntry;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogFragment extends Fragment {

    public LogFragment() {
        // Required empty public constructor
    }

    public static LogFragment newInstance() {
        return new LogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_log, container, false);

        ConstraintLayout date = view.findViewById(R.id.log_layout_date);
        ConstraintLayout results = view.findViewById(R.id.log_layout_results);
        ConstraintLayout link = view.findViewById(R.id.log_layout_link);
        for (LogEntry e : HomeActivity.log) {
            TextView dateRow = new TextView(getActivity());
            dateRow.setText(e.getLogID());
            date.addView(dateRow);
            TextView resultsRow = new TextView(getActivity());
            resultsRow.setText((e.isCleared()) ? "Cleared" : "Not Cleared");
            results.addView(resultsRow);
            ImageView linkRow = new ImageView(getActivity());
            linkRow.setImageBitmap(e.getBitmap());
            link.addView(linkRow);
        }
        return view;
    }
}