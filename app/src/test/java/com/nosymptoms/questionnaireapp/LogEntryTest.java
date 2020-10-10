package com.nosymptoms.questionnaireapp;

import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created By: Alex Peterson        AlexJoseph.Peterson@CalBaptist.edu
 * Created On: October 9, 2020
 */
public class LogEntryTest {
    /**
     * The test class does not work when associated with the database. Use testOptions{} to fix this behavior.
     *
     * Commented out to allow all others tests of the suite to run.
     * */
//    FirebaseFirestore db = FirebaseFirestore.getInstance();
//    private LogEntry createLogEntry() {
//        String[] flaggedAnswers = new String[]{"No"};
//
//        String[] potentialAnswers1 = new String[]{"Yes", "No"};
//        Question q1 = new Question(1, Question.QuestionType.TRUE_FALSE, "Q1?", potentialAnswers1, flaggedAnswers);
//
//        String[] potentialAnswers2 = new String[]{"Yes", "No"};
//        Question q2 = new Question(2, Question.QuestionType.TRUE_FALSE, "Q2?", potentialAnswers2, flaggedAnswers);
//
//        String[] potentialAnswers3 = new String[]{"Yes", "No"};
//        Question q3 = new Question(3, Question.QuestionType.TRUE_FALSE, "Q3?", potentialAnswers3, flaggedAnswers);
//
//        User u = new User(660123, "email@email.com", "fName", "lName", "a;lsdkhjfgbasdlfk");
//
//        Question[] questions = new Question[]{q1, q2, q3};
//        String[] answers = new String[]{"Yes", "No", "Yes"};
//        String imageLocation = "C://Desktop/NoSymptoms/ThermoImage.png";
//
//        return new LogEntry(u, questions, answers, imageLocation, db);
//    }
//
//
//    @Test
//    public void testGetIdNumber(){
//        LogEntry l = createLogEntry();
//
//        Assert.assertEquals(660863, l.getUserRef());
//    }
//
//    @Test
//    public void testGetTimeOfSubmission(){
//        LogEntry l = createLogEntry();
//
//        Assert.assertNotNull(l.getTimeOfSubmission());
//    }
//
//    @Test
//    public void testGetImageLocation(){
//        LogEntry l = createLogEntry();
//
//        Assert.assertEquals("C://Desktop/NoSymptoms/ThermoImage.png", l.getImageLocation());
//    }
//
//    @Test
//    public void testGetQuestionAnswerMap(){
//        LogEntry l = createLogEntry();
//
//        Assert.assertNotNull(l.getQuestionAnswerMap());
//    }

}
