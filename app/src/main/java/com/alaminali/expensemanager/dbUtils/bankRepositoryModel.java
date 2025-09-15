package com.alaminali.expensemanager.dbUtils;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class bankRepositoryModel
{
    private transctionDatabase database;

    public bankDao bankDau;
    public onlineBankingDao ebankDau;

    ExecutorService executorService;

    public bankRepositoryModel(Application application)
    {
        database=transctionDatabase.getInstance(application);
        bankDau=database.bankingDao();
        ebankDau=database.ebankingDao();
        executorService= Executors.newSingleThreadExecutor();
    }

    public void insertRepoBankingRecords(bankModel model)
    {

        executorService.execute(()->{
            bankDau.insertBankingRecords(model);
        });
    }

    public void updateRepoBankingRecords(bankModel model)
    {

        executorService.execute(()->{
            bankDau.updateBankingRecords(model);
        });
    }


    public void deleteRepoBankingRecords(int id)
    {

        executorService.execute(()->{
            bankDau.deleteBankingRecords(id);
        });
    }
    public LiveData<List<bankModel>> getAllRepoBankingRecords()
    {
        return bankDau.getAllBankingRecords();
    }

    //-----------------------------------------------

    public void insertRepoOnlineBankingRecords(onlineBanking model)
    {

        executorService.execute(()->{
            ebankDau.insertOnlineBankingRecords(model);
        });
    }

    public void updateRepoOnlineBankingRecords(onlineBanking model)
    {

        executorService.execute(()->{
            ebankDau.updateOnlineBankingRecords(model);
        });
    }


    public void deleteRepoOnlineBankingRecords(int id)
    {

        executorService.execute(()->{
            ebankDau.deleteOnlineBankingRecords(id);
        });
    }
    public LiveData<List<onlineBanking>> getAllRepoOnlineBankingRecords()
    {
        return ebankDau.getAllOnlineBankingRecords();
    }






}
