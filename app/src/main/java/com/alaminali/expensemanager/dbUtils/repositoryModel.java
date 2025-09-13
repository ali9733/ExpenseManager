package com.alaminali.expensemanager.dbUtils;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.alaminali.expensemanager.modelPackage.noteModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class repositoryModel
{
    public transctionDatabase database;
    public static transctionDao Daou;

    private static ExecutorService executorService;

    public repositoryModel(Application application)
    {
        database=transctionDatabase.getInstance(application);
       Daou=database.transDao();
       executorService= Executors.newSingleThreadExecutor();

    }

    public void insertTransctionRepoData(transctionModel model)
    {
        executorService.execute(()->{
            Daou.insertTransctionData(model);
        });
    }
    public void updateTransctionRepoData(transctionModel model)
    {
        executorService.execute(()->{
             Daou.updateTransctionDatas(model);
        });

    }

    public void deleteTransctionRepoData(int uid)
    {
        executorService.execute(()->{
            Daou.deleteTransctionDatas(uid);
        });

    }


    public LiveData<List<transctionModel>> getMonthlyTransctionRepoData(String date)
    {
       return Daou.getMonthlyTransctionDatas(date);
    }

    public LiveData<List<transctionModel>> getDailyTransctionRepoData(String date)
    {
       return Daou.getDailyTransctionDatas(date);
    }




}
