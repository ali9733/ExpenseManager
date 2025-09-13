package com.alaminali.expensemanager.dbUtils;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.alaminali.expensemanager.modelPackage.noteModel;

import java.util.List;

public class transctionViewModel extends AndroidViewModel
{

    private static repositoryModel repoInstance;

    public transctionViewModel(@NonNull Application application)
    {
        super(application);
        repoInstance=new repositoryModel(application);


    }




    public void insertTransctionViewData(transctionModel model)
    {
       repoInstance.insertTransctionRepoData(model);
    }
    public void updateTransctionViewData(transctionModel model)
    {
        repoInstance.updateTransctionRepoData(model);
    }

    public void deleteTransctionViewData(int uid)
    {
        repoInstance.deleteTransctionRepoData(uid);
    }


    public LiveData<List<transctionModel>> getMonthlyTransctionViewData(String date)
    {
        return repoInstance.getMonthlyTransctionRepoData(date);
    }

    public LiveData<List<transctionModel>> getDailyTransctionViewData(String date)
    {
        return repoInstance.getDailyTransctionRepoData(date);
    }

    public LiveData<List<noteModel>> getDailyTransctionViewNotes(String date)
    {
        return repoInstance.getDailyTransctionRepoNotes(date);
    }

    public LiveData<List<noteModel>> getMonthlyTransctionViewNotes(String date)
    {
        return repoInstance.getMonthlyTransctionRepoNotes(date);
    }

}
