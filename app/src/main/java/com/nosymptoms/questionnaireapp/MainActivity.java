package com.nosymptoms.questionnaireapp;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;


/**
 * Last Edited: Alex Peterson       AlexJoseph.Peterson@CalBaptist.edu
 * Last Edit Date:  October 7, 2020
 * */

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    static final String TAG = "NoSymptoms";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ObjectMapper oMapper = new ObjectMapper();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUp();
        queryUsers("lastName", "Peterson");                                            // Queries The firestore database

        Question q = new Question();
        //User u = new User(660123,"email@email.com","fName","lName","a;lsdkhjfgbasdlfk");          // Un commenting the 2 lines of code and running will ADD to the firestore database.
        //createUser(u);


    }

    private void setUp() {
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
                .setPersistenceEnabled(true).build(); // enable offline access
        db.setFirestoreSettings(settings);
    }








    /**
     * The following Methods are wrappers for query functionality:
     */

    //CREATE:
    protected void createUser(User user) {
        ObjectMapper oMapper = new ObjectMapper();
        CollectionReference usersRefs = db.collection("Users");
        HashMap userMap = oMapper.convertValue(user, HashMap.class);
        System.out.println(userMap.toString());
        usersRefs.document().set(userMap);
    }


    protected void createQuestion(Question question) {
        ObjectMapper oMapper = new ObjectMapper();
        CollectionReference questionsRefs = db.collection("Questions");
        HashMap questionMap = oMapper.convertValue(question, HashMap.class);

        questionsRefs.document().set(questionMap);
    }

    //READ:
    protected void queryUsers(String field, String lName) {
        CollectionReference usersRefs = db.collection("Users");
        Query queryEquals =
                usersRefs.whereEqualTo(field, lName);

        queryEquals.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " User Is:  => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }


    //UPDATE:
    protected void updateUser(User user) {
        ObjectMapper oMapper = new ObjectMapper();
        CollectionReference usersRefs = db.collection("Users");
        HashMap userMap = oMapper.convertValue(user, HashMap.class);
        System.out.println(userMap.toString());
        usersRefs.document().set(userMap, SetOptions.merge());
    }


    protected void updateQuestion(Question question) {
        ObjectMapper oMapper = new ObjectMapper();
        CollectionReference questionsRefs = db.collection("Questions");
        HashMap questionMap = oMapper.convertValue(question, HashMap.class);

        questionsRefs.document().set(questionMap, SetOptions.merge());
    }
    //DELETE:


}