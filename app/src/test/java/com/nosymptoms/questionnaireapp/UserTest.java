package com.nosymptoms.questionnaireapp;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.GeoPoint;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit testing for User Class.
 *
 * @author Alex Peterson    AlexJoseph.Peterson@CalBaptist.edu
 * @see User
 */
public class UserTest {

    private User createUser() {
        return new User(123456, "testEmail@email.com", "myFName", "myLName", "mYpAsSwOrD HaSh123!@#",
                Timestamp.now(), Timestamp.now(),
                new GeoPoint(0.00001, 0.000001));
    }
    // Test Getters:

    /**
     * Test Get ID-Number
     */
    @Test
    public void testGetIDNumberNegative() {
        User user = createUser();
        user.idNumber = -123456;

        Assert.assertEquals(-123456, user.getIdNumber());
    }

    @Test
    public void testGetIDNumberZero() {
        User user = createUser();
        user.idNumber = 0;

        Assert.assertEquals(0, user.getIdNumber());
    }

    @Test
    public void testGetIDNumberPositive() {
        User user = createUser();

        Assert.assertEquals(123456, user.getIdNumber());
    }

    /**
     * Test Get email
     */
    @Test
    public void testGetEmailEmpty() {
        User user = new User();

        Assert.assertEquals("", user.getEmail());
    }

    @Test
    public void testGetEmail() {
        User user = createUser();

        Assert.assertEquals("testEmail@email.com", user.getEmail());
    }


    /**
     * Test Get firstName
     */
    @Test
    public void testGetFirstName() {
        User user = createUser();

        Assert.assertEquals("myFName", user.getFirstName());
    }

    @Test
    public void testGetFirstNameEmpty() {
        User user = new User(123456, "testEmail@email.com", "", "myLName", "mYpAsSwOrD HaSh123!@#",
                Timestamp.now(), Timestamp.now(),
                new GeoPoint(0.00001, 0.000001));


        Assert.assertEquals("", user.getFirstName());
    }

    @Test
    public void testGetFirstNameNull() {
        User user = new User(123456, "testEmail@email.com", null, "myLName", "mYpAsSwOrD HaSh123!@#",
                Timestamp.now(), Timestamp.now(),
                new GeoPoint(0.00001, 0.000001));

        assertNull(user.getFirstName());

    }


    /**
     * Test Get lastName
     */
    @Test
    public void testGetLastName() {
        User user = createUser();

        Assert.assertEquals("myLName", user.getLastName());
    }

    @Test
    public void testGetLastNameEmpty() {
        User user = new User(123456, "testEmail@email.com", "", "", "mYpAsSwOrD HaSh123!@#",
                Timestamp.now(), Timestamp.now(),
                new GeoPoint(0.00001, 0.000001));


        Assert.assertEquals("", user.getLastName());
    }

    @Test
    public void testGetLastNameNull() {
        User user = new User(123456, "testEmail@email.com", "myFName", null, "mYpAsSwOrD HaSh123!@#",
                Timestamp.now(), Timestamp.now(),
                new GeoPoint(0.00001, 0.000001));

        assertNull(user.getLastName());

    }


    /**
     * Test Get password
     */
    @Test
    public void testGetPasswordNull() {
        User user = new User(123456, "testEmail@email.com", "myFName", "myLName", null,
                Timestamp.now(), Timestamp.now(),
                new GeoPoint(0.00001, 0.000001));

        Assert.assertNull(user.getPassword());
    }

    @Test
    public void testGetPasswordEmpty() {
        User user = new User(123456, "testEmail@email.com", "myFName", "myLName", "",
                Timestamp.now(), Timestamp.now(),
                new GeoPoint(0.00001, 0.000001));

        Assert.assertEquals("", user.getPassword());
    }

    @Test
    public void testGetPassword() {
        User user = createUser();

        Assert.assertEquals("mYpAsSwOrD HaSh123!@#", user.getPassword());
    }


    /**
     * Test Get creationTimeStamp
     */
    @Test
    public void testGetCreationTimestamp() {
        User user = createUser();

        Assert.assertNotNull(user.getCreationTimestamp());
    }


    /**
     * Test Get lastAccessedTimestamp
     */
    @Test
    public void testGetLastAccessedTimestamp() {
        User user = createUser();

        Assert.assertNotNull(user.getLastAccessedTimestamp());
    }

    /**
     * Test Get lastAccessedLocation
     */
    @Test
    public void testGetLastAccessedLocation() {
        User user = createUser();

        Assert.assertNotNull(user.getLastAccessedLocation());
    }
//
//
//
//
//
//
// TODO: 10/7/2020 Finish Implementing User class Setter Tests

//    // Test Setters:
//    @Test
//    public void testSetIDNumberNegative() {
//
//    }
//
//    @Test
//    public void testSetIDNumberZero() {
//        assertEquals(4, 2 + 2);
//    }
//
//    @Test
//    public void testSetIDNumberPositive() {
//        assertEquals(4, 2 + 2);
//    }
//
//    @Test
//    public void testSetIDNumberNull() {
//        assertEquals(4, 2 + 2);
//    }
//
//


}