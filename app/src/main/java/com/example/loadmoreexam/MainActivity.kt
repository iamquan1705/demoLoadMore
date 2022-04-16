package com.example.loadmoreexam

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loadmoreexam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ExamViewModel by viewModels {
        ExamViewmodelFactory(application)
    }
    private val handler = Handler()

    private lateinit var adapter: ExamAdapter
    private var isLoading: Boolean = false
    private var total: Int = 0
    private var key = "item"
    private lateinit var layoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = ExamAdapter()
        viewModel.getAll(key)
        viewModel.readDataOffSet.observe(this) {
            adapter.submitData(it)
        }

        binding.rcExam.adapter = adapter
        layoutManager = LinearLayoutManager(this)
        binding.rcExam.layoutManager = layoutManager
        initOnCroll()
    }

    private fun initOnCroll() {
        binding.rcExam.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    if (!isLoading) {
                        total = adapter.itemCount
                        Toast.makeText(this@MainActivity, total.toString(), Toast.LENGTH_SHORT)
                            .show()
                        if (layoutManager.findLastCompletelyVisibleItemPosition() == total - 1) {
                            loadMore()
                            isLoading = true
                        }
                    }
                }
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadMore() {
        handler.postDelayed(Runnable {
            viewModel.getAll(key)
            isLoading = false
            adapter.notifyDataSetChanged()
        }, 1000)
    }
}