package com.alaminali.expensemanager.dbUtils;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface bankDao
{

    @Insert
    public void insertBankingRecords(bankModel model);

    @Update
    public void updateBankingRecords(bankModel model);

    @Query("delete from bankTable where uid=:id")
    public void deleteBankingRecords(int id);

    @Query("select * from bankTable")
    public LiveData<List<bankModel>> getAllBankingRecords();



}
