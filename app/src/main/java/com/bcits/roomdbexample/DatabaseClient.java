package com.bcits.roomdbexample;

import android.content.Context;
import android.os.Environment;

import androidx.room.Room;

import java.io.File;

public class DatabaseClient {

    private Context mCtx;
    private static DatabaseClient mInstance;

    //our app database object
    private AppDatabase appDatabase;

    private DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;


        // static File dir = new File(Environment.getExternalStorageDirectory() + "");
        //    public static String MYDATABASE_NAME = dir + "/UHBVN/UHBVNELECTRICITYBOARD.db";

        //creating the app database with Room database builder
        //MyToDos is the name of the database

        File dir = new File(Environment.getExternalStorageDirectory() + "");
        String MYDATABASE_NAME = dir + "/ROOMCHETAN/MyToDos.db";
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, MYDATABASE_NAME).build();



      //  appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "MyToDos").build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
