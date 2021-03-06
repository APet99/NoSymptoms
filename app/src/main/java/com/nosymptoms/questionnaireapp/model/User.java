package com.nosymptoms.questionnaireapp.model;

import androidx.annotation.Nullable;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.GeoPoint;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * User of the No Symptoms Questionnaire App.
 *
 * @author Alex Peterson    AlexJoseph.Peterson@CalBaptist.edu
 * Created On: October 7, 2020
 */
public class User {
    public int idNumber;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String securityQuestion;
    private String securityAnswer;
    private Timestamp creationTimestamp;
    private Timestamp lastAccessedTimestamp;
    private GeoPoint lastAccessedLocation;


    /**
     * Constructor(s):
     */
    public User(int idNumber, String email, String firstName, String lastName, String password,
                String question, String answer, Timestamp creationTimestamp, Timestamp lastAccessedTimestamp,
                GeoPoint lastAccessedLocation) {

        this.idNumber = idNumber;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.securityQuestion = question;
        this.securityAnswer = answer;
        this.creationTimestamp = creationTimestamp;
        this.lastAccessedTimestamp = lastAccessedTimestamp;
        this.lastAccessedLocation = lastAccessedLocation;
    }

    public User(int idNumber, String email, String firstName, String lastName, String password, String question, String answer ) {
        this.idNumber = idNumber;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.securityQuestion = question;
        this.securityAnswer = answer;
        this.creationTimestamp = com.google.firebase.Timestamp.now();
        this.lastAccessedTimestamp = Timestamp.now();
        this.lastAccessedLocation = new GeoPoint(0.000001, 0.000001);
    }

    //Made this constructor for the User u in MainActivity
    public User(int idNumber, String email, String firstName, String lastName, String password) {
        this.idNumber = idNumber;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.creationTimestamp = com.google.firebase.Timestamp.now();
        this.lastAccessedTimestamp = Timestamp.now();
        this.lastAccessedLocation = new GeoPoint(0.000001, 0.000001);
    }

    public User() {
        this.idNumber = -1;
        this.email = "";
        this.firstName = "";
        this.lastName = "";
        this.password = "";
        this.securityQuestion = "";
        this.securityAnswer = "";
        this.creationTimestamp = com.google.firebase.Timestamp.now();
        this.lastAccessedTimestamp = Timestamp.now();
        this.lastAccessedLocation = new GeoPoint(0.000001, 0.000001);
    }


    //getters:


    public String getSecurityQuestion() {
        return securityQuestion;
    }


    public String getSecurityAnswer() {
        return securityAnswer;
    }


    /**
     * gets the value of idNumber
     *
     * @return idNumber.
     */
    public int getIdNumber() {
        return idNumber;
    }

    /**
     * gets the value of email
     *
     * @return email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * gets the value of firstName
     *
     * @return firstName.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * gets the value of lastName
     *
     * @return lastName.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * gets the value of password
     *
     * @return password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * gets the value of creationTimestamp
     *
     * @return creationTimestamp.
     */
    public Timestamp getCreationTimestamp() {
        return creationTimestamp;
    }

    /**
     * gets the value of lastAccessedTimestamp
     *
     * @return lastAccessedTimestamp.
     */
    public Timestamp getLastAccessedTimestamp() {
        return lastAccessedTimestamp;
    }

    /**
     * gets the value of lastAccessedLocation
     *
     * @return lastAccessedLocation.
     */
    public GeoPoint getLastAccessedLocation() {
        return lastAccessedLocation;
    }


    //setters:

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    /**
     * Sets the value of idNumbers
     *
     * @param idNumber The organization's unique identifier for the user.
     */
    public void setId(int idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * Sets the value of email
     *
     * @param email The organization's unique email for the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the value of firstName
     *
     * @param firstName The users first name (given name).
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the value of lastName
     *
     * @param lastName The users last name (family name).
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the password hash for the user's accounts
     *
     * @param password The users passphrase hash. Used to secure account.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the value of lastAccessedTimestamp
     *
     * @param lastAccessedTimestamp The timestamp of when the user was last active.
     */
    public void setLastAccessedTimestamp(HashMap<String, Long> lastAccessedTimestamp) {
        this.lastAccessedTimestamp = new Timestamp(new Date(lastAccessedTimestamp.get("seconds") * 1000));
    }

    /**
     * Sets the value of creationTimestamp
     *
     * @param creationTimestamp The timestamp of when the user was last active.
     */
    public void setCreationTimestamp(HashMap<String, Integer> creationTimestamp) {
        this.creationTimestamp = new Timestamp(new Date(creationTimestamp.get("seconds") * 1000));
    }

    /**
     * Sets the value of lastAccessedLocation
     *
     * @param lastAccessedLocation The coordinates of where the user last used the service.
     */
    public void setLastAccessedLocation(HashMap<String, Double> geoPoint) {
        if(geoPoint.containsKey("latitude") && geoPoint.containsKey("longitude")) {
            this.lastAccessedLocation = new GeoPoint(geoPoint.get("latitude"), geoPoint.get("longitude"));
        }
    }


    /**
     * Compares Equality of two User class objects.
     *
     * @param obj object to compare equality to.
     * @return if the calling object and param object are equal.
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj != null && obj.getClass() == User.class) {
            User other = (User) obj;
            return this.getIdNumber() == other.getIdNumber() && this.getEmail().equals(other.getEmail()) &&
                    this.getFirstName().equals(other.getFirstName()) &&
                    this.getLastName().equals(other.getLastName());
        }
        return false;
    }
}
