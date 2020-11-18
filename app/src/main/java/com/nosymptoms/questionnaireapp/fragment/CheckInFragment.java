package com.nosymptoms.questionnaireapp.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nosymptoms.questionnaireapp.R;
import com.nosymptoms.questionnaireapp.activity.QuestionnaireActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckInFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class CheckInFragment extends Fragment {

    public static CheckInFragment newInstance() {
        return new CheckInFragment();
    }

    public CheckInFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_in, container, false);

        if (QuestionnaireActivity.checkedIn) {
            TextView status = view.findViewById(R.id.status);
            Button checkIn = view.findViewById(R.id.check_in);
            status.setText("You are checked in");
            checkIn.setVisibility(View.INVISIBLE);
        }

        return view;
    }
}