package com.alaminali.expensemanager.dbUtils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {transctionModel.class}, version =2)
public abstract class transctionDatabase extends RoomDatabase
{
    public abstract transctionDao transDao();
    private static transctionDatabase Instance;

    public static synchronized transctionDatabase getInstance(Context context)
    {
         Instance= Room.databaseBuilder(context, transctionDatabase.class,"roomDb")
                 .fallbackToDestructiveMigration()
                 .build();

        return Instance;
    }

}
