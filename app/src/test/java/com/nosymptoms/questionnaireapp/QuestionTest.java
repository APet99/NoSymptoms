package com.nosymptoms.questionnaireapp;

import com.nosymptoms.questionnaireapp.model.User;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit testing for User Class.
 *
 * @see User
 * @author Alex Peterson    AlexJoseph.Peterson@CalBaptist.edu
 */
public class QuestionTest {

    private Question createQuestion(){
        return  new Question(1, Question.QuestionType.TRUE_FALSE,"Is it working?",new String[]{"Yes","No"},new String[]{"No"});
    }
    @Test
    public void testGetQuestionID() {
        Question q = createQuestion();

        Assert.assertEquals(1,q.getQuestionID());
    }

    @Test
    public void testGetQuestionType() {
        Question q = createQuestion();

        Assert.assertEquals(Question.QuestionType.TRUE_FALSE,q.getQuestionType());
    }

    @Test
    public void testGetQuestionText() {
        Question q = createQuestion();

        Assert.assertEquals("Is it working?",q.getQuestionText());
    }

    @Test
    public void testGetPotentialAnswers() {
        Question q = createQuestion();

        Assert.assertEquals(new String[]{"Yes", "No"},q.getPotentialAnswers());
    }

    @Test
    public void testGetFlaggedAnswers() {
        Question q = createQuestion();

        Assert.assertEquals(new String[]{"No"},q.getFlaggedAnswers());
    }



    //Test Setters:
    @Test
    public void testSetQuestionID() {
        Question q = createQuestion();
        q.setQuestionID(5);

        Assert.assertEquals(5,q.getQuestionID());
    }

    @Test
    public void testSetQuestionType() {
        Question q = createQuestion();
        q.setQuestionType(Question.QuestionType.FILL_IN_NUM_BLANK);

        Assert.assertEquals(Question.QuestionType.FILL_IN_NUM_BLANK,q.getQuestionType());
    }

    @Test
    public void testSetQuestionText() {
        Question q = createQuestion();
        q.setQuestionText("Did I change?");

        Assert.assertEquals("Did I change?",q.getQuestionText());
    }

    @Test
    public void testSetPotentialAnswers() {
        Question q = createQuestion();
        q.setPotentialAnswers(new String[]{"Maybe", "IDK"});

        Assert.assertEquals(new String[]{"Maybe", "IDK"},q.getPotentialAnswers());
    }

    @Test
    public void testSetFlaggedAnswers() {
        Question q = createQuestion();
        q.setFlaggedAnswers(new String[]{"Maybe"});

        Assert.assertEquals(new String[]{"Maybe"},q.getFlaggedAnswers());
    }


    //Test Constructors:

    @Test
    public void testDefaultConstructor(){
        Question q = new Question();

        Assert.assertEquals(-1,q.getQuestionID());
        Assert.assertEquals(Question.QuestionType.NONE,q.getQuestionType());
        Assert.assertEquals("",q.questionText);
        Assert.assertNull(q.getPotentialAnswers());
        Assert.assertNull(q.getFlaggedAnswers());
    }

    @Test
    public void testPartialConstruct(){
        Question partial = new Question(1, Question.QuestionType.TRUE_FALSE,"Yes or No?");
        Question full = createQuestion();
        full.setQuestionID(1);
        full.setQuestionType(Question.QuestionType.TRUE_FALSE);
        full.setQuestionText("Yes or No?");

        Assert.assertEquals(full.getQuestionID(),partial.getQuestionID());
        Assert.assertEquals(Question.QuestionType.TRUE_FALSE,partial.getQuestionType());
        Assert.assertEquals(full.getQuestionText(),partial.questionText);
    }

}
