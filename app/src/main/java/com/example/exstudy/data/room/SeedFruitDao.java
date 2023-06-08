package com.example.exstudy.data.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SeedFruitDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSeed(SeedEntity seed);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFruit(FruitEntity fruit);

    @Query("SELECT * FROM all_seeds_table")
    LiveData<List<SeedEntity>> getAllMySeeds();

    @Query("SELECT * FROM all_fruits_table")
    LiveData<List<FruitEntity>> getAllMyFruits();

    @Query("DELETE FROM all_seeds_table")
    void deleteAllSeeds();

    @Query("SELECT * FROM all_seeds_table WHERE name=:seedName")
    LiveData<SeedEntity> getSeedByName(String seedName);

    @Query("SELECT * FROM all_fruits_table WHERE name=:fruitName")
    FruitEntity getFruitByName(String fruitName);

    @Query("UPDATE all_fruits_table SET quantity=quantity+:delta WHERE name=:fruitName")
    void increaseFruitQuantity(String fruitName, int delta);

    @Query("DELETE FROM all_fruits_table WHERE quantity=0")
    void removeEmptyFruits();
}
