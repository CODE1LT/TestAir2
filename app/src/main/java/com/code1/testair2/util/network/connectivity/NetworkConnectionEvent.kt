package com.code1.testair2.util.network.connectivity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.*
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.os.Build
import androidx.lifecycle.LiveData
import com.code1.testair2.common.events.Event
import com.code1.testair2.util.Application
import javax.inject.Inject

@Suppress("DEPRECATION")
class NetworkConnectionEvent @Inject constructor(application: Application) :
    LiveData<@JvmSuppressWildcards Event<Boolean>>() {

    private var wasConnected = NetworkConnectionUtils.isNetworkAvailable(application)
    private val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onCapabilitiesChanged(network: Network, capabilities: NetworkCapabilities) {
            val isConnected = capabilities.hasCapability(NET_CAPABILITY_INTERNET)
            if (isConnected && (isConnected != wasConnected)
            ) {
                postValue(Event(isConnected))
                wasConnected = true
            }
        }

        override fun onLost(network: Network) {
            postValue(Event(false))
            wasConnected = false
        }
    }

    private inner class ConnectivityReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val networkInfo = connectivityManager.activeNetworkInfo
            val fallbackNetworkInfo: NetworkInfo? =
                intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO)
            val state = if (networkInfo?.isConnectedOrConnecting == true) {
                Event(networkInfo.isConnectedOrConnecting)
            } else if (networkInfo != null && fallbackNetworkInfo != null &&
                networkInfo.isConnectedOrConnecting != fallbackNetworkInfo.isConnectedOrConnecting
            ) {
                Event(fallbackNetworkInfo.isConnectedOrConnecting)
            } else {
                val state = networkInfo ?: fallbackNetworkInfo
                if (state != null) Event(state.isConnectedOrConnecting) else Event(false)
            }
            postValue(state)
        }
    }

    private val receiver = ConnectivityReceiver()

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } else {
            val builder = NetworkRequest.Builder()
            connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            application.registerReceiver(
                receiver,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            )
        }
    }
}