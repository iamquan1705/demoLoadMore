package com.example.loadmoreexam.database

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.example.loadmoreexam.model.Exam

class ExamRepo(private val examDao: ExamDao) {
    val readAllExamData: LiveData<List<Exam>> = examDao.getAllData()

    suspend fun insertExam(exam: Exam) {
        examDao.insertExam(exam)
    }

    fun getAll(key: String, offset: Int): List<Exam> {
        return examDao.getAll(key, offset)
    }


}