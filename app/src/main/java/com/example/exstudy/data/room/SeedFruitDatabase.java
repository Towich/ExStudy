package com.example.exstudy.data.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.exstudy.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

    @Database(entities = {SeedEntity.class, FruitEntity.class}, version = 2, exportSchema = false)
public abstract class SeedFruitDatabase extends RoomDatabase {
    public abstract SeedFruitDao seedFruitDao();

    private static volatile SeedFruitDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static SeedFruitDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (SeedFruitDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SeedFruitDatabase.class, "seed_fruit_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                SeedFruitDao dao = INSTANCE.seedFruitDao();
                dao.deleteAllSeeds();
                dao.insertSeed(new SeedEntity("Lemon", R.drawable.seeds_lemon, 30, 1));
                dao.insertSeed(new SeedEntity("Coconut", R.drawable.seeds_coconut, 45, 5));
            });
        }
    };
}
