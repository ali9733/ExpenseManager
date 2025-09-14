package com.alaminali.expensemanager.dbUtils;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class bankRepositoryModel
{
    private transctionDatabase database;

    public bankDao bankDau;
    public onlineBankingDao ebankDau;

    public bankRepositoryModel(Application application)
    {
        database=transctionDatabase.getInstance(application);
        bankDau=database.bankingDao();
        ebankDau=database.ebankingDao();
    }

    public void insertRepoBankingRecords(bankModel model)
    {
        bankDau.insertBankingRecords(model);
    }

    public void updateRepoBankingRecords(bankModel model)
    {
        bankDau.updateBankingRecords(model);
    }


    public void deleteRepoBankingRecords(int id)
    {
        bankDau.deleteBankingRecords(id);
    }
    public LiveData<List<bankModel>> getAllRepoBankingRecords()
    {
        return bankDau.getAllBankingRecords();
    }

    //-----------------------------------------------

    public void insertRepoOnlineBankingRecords(onlineBanking model)
    {
        ebankDau.insertOnlineBankingRecords(model);
    }

    public void updateRepoOnlineBankingRecords(onlineBanking model)
    {
        ebankDau.updateOnlineBankingRecords(model);
    }


    public void deleteRepoOnlineBankingRecords(int id)
    {
        ebankDau.deleteOnlineBankingRecords(id);
    }
    public LiveData<List<onlineBanking>> getAllRepoOnlineBankingRecords()
    {
        return ebankDau.getAllOnlineBankingRecords();
    }






}
