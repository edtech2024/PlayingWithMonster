package com.example.playingwithmonster.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.playingwithmonster.data.entity.*

@Database(entities = [PlayerEntity::class], version = 1)
abstract class CreatureDatabase: RoomDatabase() {

    abstract fun itemDao(): CreatureDao

    companion object {

        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: CreatureDatabase? = null

        fun getDatabase(context: Context): CreatureDatabase {

            if (INSTANCE != null) {
                return INSTANCE!!
            }

            if (INSTANCE == null) {
                synchronized(this) {
                    // Using an in-memory database because the information stored here disappears when the process is killed.
                    val instance = Room.inMemoryDatabaseBuilder(
                        context.applicationContext,
                        CreatureDatabase::class.java)
                        // Allowing main thread queries, just for testing.
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
            }
            return INSTANCE!!
        }

    }

}