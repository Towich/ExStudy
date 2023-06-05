package com.example.exstudy.data.room;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SeedFruitDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSeed(SeedEntity seed);

    @Query("SELECT * FROM all_seeds_table")
    LiveData<List<SeedEntity>> getAllMySeeds();

    @Query("DELETE FROM all_seeds_table")
    void deleteAll();

    @Query("SELECT * FROM all_seeds_table WHERE id=1")
    SeedEntity getFirstSeed();
}
