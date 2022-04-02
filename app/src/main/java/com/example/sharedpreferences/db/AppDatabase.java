package com.example.sharedpreferences.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import org.jetbrains.annotations.NotNull;

//@Database(entities = {Category.class}, version = 1)
@Database(entities = {Category.class}, version = 1)
public class AppDatabase extends RoomDatabase {
    public static AppDatabase INSTANCE;

    @NonNull
    @NotNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @NotNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }

    public static AppDatabase getDbInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "AppDatabase")
            .allowMainThreadQueries()
            .build();
        }

        return INSTANCE;
    }

}
