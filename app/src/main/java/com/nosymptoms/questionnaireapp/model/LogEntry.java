package com.nosymptoms.questionnaireapp.model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.nosymptoms.questionnaireapp.Question;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Created By: Alex Peterson        AlexJoseph.Peterson@CalBaptist.edu
 * Created On: October 8, 2020
 */
public class LogEntry {
    int idNumber;
    Timestamp timeOfSubmission;
    String imageLocation;
    HashMap<Integer, String> questionAnswerMap = new HashMap<>();
    String logID;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public LogEntry(User user, Question[] questions, String[] answers, String imageLocation) {
        this.idNumber = user.getIdNumber();
        this.timeOfSubmission = Timestamp.now();
        this.imageLocation = imageLocation;
        this.logID = Calendar.getInstance().getTime().toString() + "///" + user.idNumber;

        // maps the question with the submitted answer.
        for (int i = 0; i < Math.min(questions.length, answers.length); i++) {
            questionAnswerMap.put(questions[i].getQuestionID(), answers[i]);
        }
    }

    public LogEntry(User user, Question[] questions, String[] answers, String imageLocation, FirebaseFirestore db) {
        this.idNumber = user.getIdNumber();
        this.timeOfSubmission = Timestamp.now();
        this.imageLocation = imageLocation;
        this.logID = Calendar.getInstance().getTime().toString() + "///" + user.idNumber;

        // maps the question with the submitted answer.
        for (int i = 0; i < Math.min(questions.length, answers.length); i++) {
            questionAnswerMap.put(questions[i].getQuestionID(), answers[i]);
        }
        this.db = db;
    }

    private DocumentReference getUserReference(User user){
        return db.collection("Users").document(Integer.toString(user.getIdNumber()));
    }



    public int getUserRef() {
        return idNumber;
    }

    public void setUserRef(int idNumber) {
        this.idNumber = idNumber;
    }

    public Timestamp getTimeOfSubmission() {
        return timeOfSubmission;
    }

    public void setTimeOfSubmission(Timestamp timeOfSubmission) {
        this.timeOfSubmission = timeOfSubmission;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public HashMap<Integer, String> getQuestionAnswerMap() {
        return questionAnswerMap;
    }

    public void setQuestionAnswerMap(HashMap<Integer, String> questionAnswerMap) {
        this.questionAnswerMap = questionAnswerMap;
    }

    public String getLogID() {
        return logID;
    }

    public void setLogID(String logID) {
        this.logID = logID;
    }
}
