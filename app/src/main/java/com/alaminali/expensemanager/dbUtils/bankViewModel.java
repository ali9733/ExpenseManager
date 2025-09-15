package com.alaminali.expensemanager.dbUtils;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class bankViewModel extends AndroidViewModel
{
    private  bankRepositoryModel repositoryModel;
    ExecutorService executorService;
    public bankViewModel(@NonNull Application application)
    {
        super(application);
        repositoryModel=new bankRepositoryModel(application);
        executorService= Executors.newSingleThreadExecutor();
    }



    public void insertViewBankingRecords(bankModel model)
    {
        repositoryModel.insertRepoBankingRecords(model);
    }

    public void updateViewBankingRecords(bankModel model)
    {
        executorService.execute(()->{
            repositoryModel.updateRepoBankingRecords(model);
        });
    }


    public void deleteViewBankingRecords(int id)
    {
        repositoryModel.deleteRepoBankingRecords(id);
    }
    public LiveData<List<bankModel>> getAllViewBankingRecords()
    {
        return repositoryModel.getAllRepoBankingRecords();
    }

    //-----------------------------------------------

    public void insertViewOnlineBankingRecords(onlineBanking model)
    {
        repositoryModel.insertRepoOnlineBankingRecords(model);
    }

    public void updateViewOnlineBankingRecords(onlineBanking model)
    {
        repositoryModel.updateRepoOnlineBankingRecords(model);
    }


    public void deleteViewOnlineBankingRecords(int id)
    {
        repositoryModel.deleteRepoOnlineBankingRecords(id);
    }
    public LiveData<List<onlineBanking>> getAllViewOnlineBankingRecords()
    {
        return repositoryModel.getAllRepoOnlineBankingRecords();
    }












}
