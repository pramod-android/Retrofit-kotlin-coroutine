package com.patil.retrofit.coroutines.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.patil.retrofit.coroutines.R.*
import com.patil.retrofit.coroutines.data.api.ApiHelper
import com.patil.retrofit.coroutines.data.api.RetrofitBuilder
import com.patil.retrofit.coroutines.data.model.Acromine
import com.patil.retrofit.coroutines.ui.base.ViewModelFactory
import com.patil.retrofit.coroutines.ui.main.adapter.AchromineAdapter
import com.patil.retrofit.coroutines.ui.main.viewmodel.MainViewModel
import com.patil.retrofit.coroutines.utils.Status.ERROR
import com.patil.retrofit.coroutines.utils.Status.LOADING
import com.patil.retrofit.coroutines.utils.Status.SUCCESS
import kotlinx.android.synthetic.main.activity_main.progressBar
import kotlinx.android.synthetic.main.activity_main.recyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var achromineAdapter: AchromineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        setupViewModel()
        setupUI()
        setupObservers()
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        achromineAdapter = AchromineAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = achromineAdapter
    }

    private fun setupObservers() {


        viewModel.getaCromine().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { lfs ->
                            retrieveList(lfs)
                        }
                    }
                    ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(lfsList: List<Acromine.AcromineItem.Lf>) {
        achromineAdapter.apply {
            addlfsList(lfsList)
            notifyDataSetChanged()
        }
    }
}
