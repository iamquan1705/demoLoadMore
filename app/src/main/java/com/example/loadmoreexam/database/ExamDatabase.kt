package com.example.loadmoreexam.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.loadmoreexam.model.Exam

@Database(
    entities =
    [
        Exam::class
    ], version = 1, exportSchema = false
)
abstract class ExamDatabase : RoomDatabase() {
    abstract fun getExamDao(): ExamDao

    companion object {
        private var INSTANCE: ExamDatabase? = null

        @Synchronized
        fun getInstance(context: Context): ExamDatabase {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ExamDatabase::class.java,
                        "Diary_Database"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
                return INSTANCE!!
            }
        }
    }
}