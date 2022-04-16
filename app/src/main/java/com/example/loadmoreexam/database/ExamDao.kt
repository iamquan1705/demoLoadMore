package com.example.loadmoreexam.database

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.example.loadmoreexam.model.Exam

@Dao
interface ExamDao {
    @Query("Select * from exam_table where name like '%' || :key || '%' limit ${Constant.LIMIT} offset :offset ")
    fun getAll(key:String,offset: Int): List<Exam>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExam(exam: Exam)

    @Query("Select * from exam_table")
    fun getAllData(): LiveData<List<Exam>>

}