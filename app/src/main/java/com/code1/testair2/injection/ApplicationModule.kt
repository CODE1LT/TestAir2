package com.code1.testair2.injection

import android.content.Context
import androidx.lifecycle.LiveData
import com.code1.testair2.util.Application
import com.code1.testair2.util.network.connectivity.NetworkConnectionEvent
import com.seasnve.watts.common.events.Event
import dagger.Binds
import dagger.Module
import javax.inject.Named
import javax.inject.Singleton

const val NETWORK_CONNECTION_EVENT = "NETWORK_CONNECTION_CHANGE_LIVE_DATA"

@Module
abstract class ApplicationModule {

    @Binds
    abstract fun provideContext(application: Application): Context



    @Singleton
    @Binds
    @Named(NETWORK_CONNECTION_EVENT)
    abstract fun provideNetworkConnectionEvent(networkConnectionEvent: NetworkConnectionEvent): LiveData<Event<Boolean>>
}