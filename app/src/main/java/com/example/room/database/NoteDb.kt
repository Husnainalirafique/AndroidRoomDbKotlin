package com.example.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room.consts.DB_NAME

@Database(entities = [Note::class], version = 1)
abstract class NoteDb : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDb? = null
        fun getDatabase(context: Context): NoteDb {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDb::class.java,
                        DB_NAME
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}