package com.nosymptoms.questionnaireapp.dao;

import com.nosymptoms.questionnaireapp.model.User;

public interface UserDao {
    void createUser(User user);
    User getUserById(int id);
}
