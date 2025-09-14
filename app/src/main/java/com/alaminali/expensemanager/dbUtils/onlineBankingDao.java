package com.alaminali.expensemanager.dbUtils;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface onlineBankingDao
{
  @Insert
  public void insertOnlineBankingRecords(onlineBanking model);

  @Update
    public void updateOnlineBankingRecords(onlineBanking model);

  @Query("delete from eBanking where uid=:id")
    public void deleteOnlineBankingRecords(int id);

  @Query("select * from eBanking")
    public LiveData<List<onlineBanking>> getAllOnlineBankingRecords();



}
