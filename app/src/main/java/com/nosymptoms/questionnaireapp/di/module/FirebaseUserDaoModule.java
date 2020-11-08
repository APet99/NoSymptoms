package com.nosymptoms.questionnaireapp.di.module;

import com.nosymptoms.questionnaireapp.dao.FirebaseUserDao;
import com.nosymptoms.questionnaireapp.dao.UserDao;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class FirebaseUserDaoModule {

    @Singleton
    @Binds
    public abstract UserDao bindsUserDao(FirebaseUserDao firebaseUserDao);
}
