package com.example.loadmoreexam

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ExamViewmodelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExamViewModel::class.java)) {
            return ExamViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown view model")
    }
}