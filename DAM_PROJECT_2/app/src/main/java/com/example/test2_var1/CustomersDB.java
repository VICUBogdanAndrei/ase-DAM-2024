package com.example.test2_var1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Customer.class}, version =1, exportSchema = false)
@TypeConverters({DateConvertor.class})
public abstract class CustomersDB extends RoomDatabase {
    public static final String DB_NAME = "customer_DB";

    private  static CustomersDB instance;

    public static CustomersDB getInstance(Context context)
    {
        if(instance==null)
            instance = Room.databaseBuilder(context,
                    CustomersDB.class,DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

        return instance;
    }

    public abstract CustomersDAO getCustomersDao();
}
