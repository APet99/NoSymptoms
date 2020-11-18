package com.nosymptoms.questionnaireapp.dao;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.nosymptoms.questionnaireapp.model.User;

public interface UserDao {
    void createUser(User user);
    void updateUser(User user);
    DocumentReference getUserById(int id);
}
