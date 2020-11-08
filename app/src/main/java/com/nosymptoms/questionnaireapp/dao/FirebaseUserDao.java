package com.nosymptoms.questionnaireapp.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.nosymptoms.questionnaireapp.di.module.FirebaseModule;
import com.nosymptoms.questionnaireapp.model.User;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;

@Singleton
public class FirebaseUserDao implements UserDao {
    FirebaseFirestore db;

    @Inject
    public FirebaseUserDao(FirebaseFirestore db) {
        this.db = db;
    }


    @Override
    public void createUser(User user) {
        ObjectMapper oMapper = new ObjectMapper();
        CollectionReference usersRefs = db.collection("Users");
        HashMap userMap = oMapper.convertValue(user, HashMap.class);
        System.out.println(userMap.toString());
        usersRefs.document(Integer.toString(user.getIdNumber())).set(userMap);
    }

    @Override
    public User getUserById(int id) {
        DocumentReference docRef = db.collection("cities").document("BJ");
        DocumentSnapshot documentSnapshot = docRef.get().getResult();

        return documentSnapshot.toObject(User.class);
    }
}
