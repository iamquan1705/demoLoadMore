package com.example.loadmoreexam

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.loadmoreexam.database.Constant
import com.example.loadmoreexam.database.ExamDatabase
import com.example.loadmoreexam.database.ExamRepo
import com.example.loadmoreexam.model.Exam
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExamViewModel(app: Application) : ViewModel() {
    private val examRepo: ExamRepo = ExamRepo(
        ExamDatabase.getInstance(app.applicationContext).getExamDao()
    )
    private val readAllData: LiveData<List<Exam>> = examRepo.readAllExamData
    private var readData = ArrayList<Exam>()
    private var offset = -1

    val readDataOffSet = MutableLiveData<List<Exam>>()

    fun getAll(key: String) {
        viewModelScope.launch(Dispatchers.IO) {
            readData =
                readData.plus(examRepo.getAll(key, offset * Constant.LIMIT)) as ArrayList<Exam>
            readDataOffSet.postValue(readData)
        }
        offset++
    }

    fun insertExam(exam: Exam) {
        viewModelScope.launch(Dispatchers.IO) {
            examRepo.insertExam(exam)
        }
    }
}