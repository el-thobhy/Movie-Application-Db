package com.elthobhy.movieapplicationdb.core.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.elthobhy.movieapplicationdb.core.utils.Constants

class MyService : Service() {

    private val handler = Handler()
    private val updateTask = object : Runnable{
        override fun run() {

            val intent = Intent(Constants.ACTION_DATA_UPDATED)
            LocalBroadcastManager.getInstance(this@MyService).sendBroadcast(intent)
            handler.postDelayed(this,Constants.DIRECT_UPDATE.toLong())
            Log.e("tes", "run: check" )
        }

    }

    override fun onCreate() {
        super.onCreate()
        handler.post(updateTask)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(updateTask)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}