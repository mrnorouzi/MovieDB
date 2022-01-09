package com.mrn.moviedb.ui.common

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.fragment.app.Fragment
import com.mrn.core.data.MovieDataSource
import com.mrn.moviedb.data.MovieRepository
import com.mrn.moviedb.framework.network.NetworkService

open class BaseFragment : Fragment() {

    private var bound: Boolean? = null
    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as NetworkService.LocalBinder
            onServiceBound(binder.getService())
            bound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            bound = false
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (bound == null || bound == false) {
            Intent(activity, NetworkService::class.java).also { intent ->
                activity?.bindService(intent, connection, Context.BIND_AUTO_CREATE)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.unbindService(connection)
        bound = false
    }

    protected open fun onServiceBound(service: NetworkService) {
    }
}