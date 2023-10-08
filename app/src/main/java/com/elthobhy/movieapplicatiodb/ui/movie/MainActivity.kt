package com.elthobhy.movieapplicatiodb.ui.movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.elthobhy.movieapplicatiodb.databinding.ActivityMainBinding
import com.elthobhy.movieapplicationdb.core.data.Resource
import com.elthobhy.movieapplicationdb.core.domain.model.DomainModel
import com.elthobhy.movieapplicationdb.core.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterMovie: AdapterList
    private val viewModelMovie: MovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        adapterMovie= AdapterList()
        setContentView(binding.root)
        showRv()
        getData()
    }

    private fun getData() {
        viewModelMovie.getMovies().observe(this){
            when(it){
                is Resource.Loading -> {}
                is Resource.Success -> {
                    adapterMovie.setData(it.data)
                    Log.e("data nya", "getData: ${it.data?.size}" )
                }
                is Resource.Error -> {}
            }
        }
    }

    private fun showRv() {
        binding.rvMovie.apply {
            layoutManager=LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = adapterMovie
        }
        adapterMovie.setOnItemClickCallback(object :AdapterList.OnItemClickCallback{
            override fun onItemClicked(data: DomainModel) {
                goToDetail(data)
            }
        })
    }

    private fun goToDetail(data: DomainModel) {
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra(Constants.DATA, data)
        startActivity(intent)
    }
}