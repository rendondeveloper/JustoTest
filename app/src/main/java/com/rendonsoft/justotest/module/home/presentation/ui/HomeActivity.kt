package com.rendonsoft.justotest.module.home.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.rendonsoft.justotest.databinding.ActivityMainBinding
import com.rendonsoft.justotest.module.home.presentation.ui.adapter.PeopleAdapter
import com.rendonsoft.justotest.module.home.presentation.ui.adapter.ShimmerAdapter
import com.rendonsoft.justotest.module.home.presentation.ui.model.UiPeople
import com.rendonsoft.justotest.module.home.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel : HomeViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initObservers()
        initData()
    }

    private fun initView(){
        binding.rvBook.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.srReload.setOnRefreshListener {
            viewModel.getPeopleRandom()
        }
    }

    private fun initObservers() {
        viewModel.randomPeople.observe(this, resultData())
        viewModel.showShimmer.observe(this, setShimmer())
        viewModel.error.observe(this, showError())
    }

    private fun initData() {
        viewModel.getPeopleRandom()
    }

    private fun setShimmer() : (Boolean) -> Unit = {
        val shimmerAdapter = ShimmerAdapter()
        binding.rvBook.adapter = shimmerAdapter
    }

    private fun resultData() : (List<UiPeople>) -> Unit = { data ->
        val adapterData =  PeopleAdapter(data)
        binding.rvBook.adapter = adapterData
        binding.srReload.isRefreshing  = false
    }

    private fun showError() : (String) -> Unit = { message ->
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        binding.srReload.isRefreshing  = false
    }
}