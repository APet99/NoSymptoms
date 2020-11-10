package com.nosymptoms.questionnaireapp;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.GeoPoint;
import com.nosymptoms.questionnaireapp.model.User;

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
                "who is your mom", "your mom",
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
                "who is your mom", "your mom",
                 Timestamp.now(), Timestamp.now(),
                new GeoPoint(0.00001, 0.000001));


        Assert.assertEquals("", user.getFirstName());
    }

    @Test
    public void testGetFirstNameNull() {
        User user = new User(123456, "testEmail@email.com", null, "myLName", "mYpAsSwOrD HaSh123!@#",
                "who is your mom", "your mom",
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
                "who is your mom", "your mom",
                Timestamp.now(), Timestamp.now(),
                new GeoPoint(0.00001, 0.000001));


        Assert.assertEquals("", user.getLastName());
    }

    @Test
    public void testGetLastNameNull() {
        User user = new User(123456, "testEmail@email.com", "myFName", null, "mYpAsSwOrD HaSh123!@#",
                "who is your mom", "your mom",
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
                "who is your mom", "your mom",
                Timestamp.now(), Timestamp.now(),
                new GeoPoint(0.00001, 0.000001));

        Assert.assertNull(user.getPassword());
    }

    @Test
    public void testGetPasswordEmpty() {
        User user = new User(123456, "testEmail@email.com", "myFName", "myLName", "",
                "who is your mom", "your mom",
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

// TODO: 10/7/2020 Finish Implementing User class Setter Tests

    // Test Setters:
    @Test
    public void testSetIDNumberNegative() {
        User u = createUser();
        u.setId(-5);

        Assert.assertEquals(-5, u.getIdNumber() );
    }

    @Test
    public void testSetIDNumberZero() {
        User u = createUser();
        u.setId(0);

        Assert.assertEquals(0, u.getIdNumber() );
    }

    @Test
    public void testSetIDNumberPositive() {
        User u = createUser();
        u.setId(27);

        Assert.assertEquals(27, u.getIdNumber() );
    }

    @Test
    public void testSetEmail() {
        User u = createUser();
        u.setEmail("AYO@gmail.com");

        Assert.assertEquals("AYO@gmail.com", u.getEmail() );
    }

    @Test
    public void testSetFirstName() {
        User u = createUser();
        u.setFirstName("John");

        Assert.assertEquals("John", u.getFirstName() );
    }

    @Test
    public void testSetLastName() {
        User u = createUser();
        u.setLastName("Smith");

        Assert.assertEquals("Smith", u.getLastName() );
    }

    @Test
    public void testSetPassword() {
        User u = createUser();
        u.setPassword("123ABC");

        Assert.assertEquals("123ABC", u.getPassword());
    }

    @Test
    public void testSetLastAccessedTimestamp() {
        User u = createUser();
        Timestamp n = Timestamp.now();
        u.setLastAccessedTimestamp(n);

        Assert.assertEquals(n, u.getLastAccessedTimestamp() );
    }

    @Test
    public void testSetLastAccessedLocation() {
        User u = createUser();
        GeoPoint geo = new GeoPoint(12.345, 89.99);
        u.setLastAccessedLocation(geo);

        Assert.assertEquals(geo, u.getLastAccessedLocation() );
    }

    @Test
    public void testConstructor(){
        User user = new User(123,"123@gmail.com","Alex","Peterson","123ABC","who is your mom", "your mom");
        User empty = new User();

        empty.setId(123);
        empty.setEmail("123@gmail.com");
        empty.setFirstName("Alex");
        empty.setLastName("Peterson");
        empty.setPassword("123ABC");

        Assert.assertEquals(user.idNumber, empty.getIdNumber());
        Assert.assertEquals(user.getEmail(), empty.getEmail());
        Assert.assertEquals(user.getFirstName(), empty.getFirstName());
        Assert.assertEquals(user.getLastName(),empty.getLastName());
        Assert.assertEquals(user.getPassword(),empty.getPassword());
    }
}