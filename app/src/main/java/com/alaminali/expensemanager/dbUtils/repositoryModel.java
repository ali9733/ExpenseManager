package com.alaminali.expensemanager.dbUtils;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.alaminali.expensemanager.modelPackage.noteModel;

import java.util.List;

public class repositoryModel
{
    public transctionDatabase database;
    public static transctionDao Daou;

    public repositoryModel(Application application)
    {
        database=transctionDatabase.getInstance(application);
       Daou=database.transDao();

    }

    public void insertTransctionRepoData(transctionModel model)
    {
        Daou.insertTransctionData(model);
    }
    public void updateTransctionRepoData(transctionModel model)
    {
        Daou.updateTransctionDatas(model);
    }

    public void deleteTransctionRepoData(int uid)
    {
        Daou.deleteTransctionDatas(uid);
    }


    public LiveData<List<transctionModel>> getMonthlyTransctionRepoData(String date)
    {
       return Daou.getMonthlyTransctionDatas(date);
    }

    public LiveData<List<transctionModel>> getDailyTransctionRepoData(String date)
    {
       return Daou.getDailyTransctionDatas(date);
    }

    public LiveData<List<noteModel>> getDailyTransctionRepoNotes(String date)
    {
        return Daou.getDailyTransctionNotes(date);
    }

    public LiveData<List<noteModel>> getMonthlyTransctionRepoNotes(String date)
    {
        return Daou.getMonthlyTransctionNotes(date);
    }


}
