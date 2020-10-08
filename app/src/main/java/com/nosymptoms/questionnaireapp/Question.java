package com.nosymptoms.questionnaireapp;

/**
 * Questions from No Symptoms Questionnaire App.
 *
 * @author Alex Peterson    AlexJoseph.Peterson@CalBaptist.edu
 * Created On: October 7, 2020
 */

public class Question{
    public int questionID;
    protected QuestionType questionType;
    protected String questionText;
    private String[] potentialAnswers;

    enum QuestionType {
        MULTIPLE_CHOICE,
        TRUE_FALSE,
        FILL_IN_TEX_BLANK,
        FILL_IN_NUM_BLANK,
        MULTIPLE_CHECKBOX,
        NONE
    }

    /**
     * Constructs a question object
     */
    public Question(int questionID, QuestionType questionType, String questionText) {
        this.questionID = questionID;
        this.questionType = questionType;
        this.questionText = questionText;
    }

    public Question(int questionID, QuestionType questionType, String questionText, String[] potentialAnswers) {
        this.questionID = questionID;
        this.questionType = questionType;
        this.questionText = questionText;
        this.potentialAnswers = potentialAnswers;
    }

    public Question() {
        this.questionID = -1;
        this.questionType = QuestionType.NONE;
        this.questionText = "";
        this.potentialAnswers = null;
    }

    //getters:
    /**
     * Get the ID of the question
     *
     * @return questionID
     */
    public int getQuestionID() {
        return questionID;
    }

    /**
     * Get the question type of the question
     *
     * @return questionType
     */
    public QuestionType getQuestionType() {
        return questionType;
    }

    /**
     * Get the text content of the question
     *
     * @return questionText
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Get the potential answer of the question
     *
     * @return potentialAnswers
     */
    public String[] getPotentialAnswers (){return potentialAnswers;}



    //setters:
    /**
     * Sets the unique ID of the question.
     *
     * @param questionID
     */
    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    /**
     * Sets the question type .
     *
     * @param questionType
     */
    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    /**
     * Sets the value of the question body.
     *
     * @param questionText
     */
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    /**
     * Sets the potential answer of the selected question object.
     *
     * @param potentialAnswers
     */
    public void setPotentialAnswers(String[] potentialAnswers){this.potentialAnswers = potentialAnswers;}

}
