package com.elthobhy.movieapplicatiodb.ui.movie

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.elthobhy.movieapplicatiodb.R
import com.elthobhy.movieapplicatiodb.databinding.ActivityMainBinding
import com.elthobhy.movieapplicatiodb.ui.detail.DetailActivity
import com.elthobhy.movieapplicatiodb.utils.showDialogError
import com.elthobhy.movieapplicationdb.core.data.Resource
import com.elthobhy.movieapplicationdb.core.domain.model.DomainModel
import com.elthobhy.movieapplicationdb.core.service.MyService
import com.elthobhy.movieapplicationdb.core.utils.Constants
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterMovie: AdapterList
    private val viewModelMovie: MovieViewModel by viewModel()
    private lateinit var dialogError: AlertDialog
    private lateinit var cm: ConnectivityManager
    private val dataUpdateReceiver = object : BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            lifecycleScope.launch {
                viewModelMovie.delete()
                Log.e("TAG check", "onReceive: database deleted" )
            }
            Snackbar.make(binding.root,"Old Data Have Been Deleted. New Data is Available, Click Load if data isn't showing",
                Snackbar.LENGTH_LONG).setAction("Load") {
                getData()
                Toast.makeText(this@MainActivity, "Data Loaded", Toast.LENGTH_LONG).show()
            }.show()
            getData()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        adapterMovie= AdapterList()
        setContentView(binding.root)
        dialogError = showDialogError(this)
        checkConnection(cm)
        showRv()
        getData()
        startService(Intent(this, MyService::class.java))
        Handler(Looper.getMainLooper())
            .postDelayed({
                val intent = IntentFilter(Constants.ACTION_DATA_UPDATED)
                LocalBroadcastManager.getInstance(this).registerReceiver(dataUpdateReceiver, intent)
            },Constants.DIRECT_UPDATE.toLong())
    }

    @Suppress("DEPRECATION")
    private fun checkConnection(cm: ConnectivityManager) {
        val infoNet = cm.activeNetworkInfo == null && cm.activeNetworkInfo?.isConnected != true
        if(infoNet){
            dialogError=showDialogError(this, getString(R.string.you_are_offline))
            dialogError.show()
        }else{
            Toast.makeText(this, getString(R.string.online),Toast.LENGTH_LONG).show()
        }
    }

    private fun getData() {
        viewModelMovie.getMovies(cm).observe(this){
            when(it){
                is Resource.Loading -> {}
                is Resource.Success -> {
                    adapterMovie.setData(it.data)
                    Log.e("data nya", "getData: ${it.data?.size}" )
                }
                is Resource.Error -> {
                    dialogError = showDialogError(this@MainActivity, it.message)
                    dialogError.show()
                }
                else -> {}
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
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(Constants.DATA, data)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        dialogError.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dialogError.dismiss()
    }
}