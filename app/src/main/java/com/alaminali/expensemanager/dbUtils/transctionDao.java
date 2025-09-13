package com.alaminali.expensemanager.dbUtils;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.alaminali.expensemanager.modelPackage.noteModel;

import java.util.List;

@Dao
public interface transctionDao
{

    @Insert
    public void insertTransctionData(transctionModel model);

    @Update
    public void updateTransctionDatas(transctionModel model);


    @Query("delete from transctionTable where uid=:uid")
    public void deleteTransctionDatas(int uid);


    @Query("select * from transctionTable where shortDate=:date")
    public LiveData<List<transctionModel>> getMonthlyTransctionDatas(String date);

    @Query("select * from transctionTable where fullDate=:date")
    public LiveData<List<transctionModel>> getDailyTransctionDatas(String date);



}
