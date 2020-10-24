package com.code1.testair2.common

import dagger.Module
import dagger.Provides
import retrofit2.Response
import javax.inject.Inject

@Module
class NetworkErrorFormatterModule {

    @Provides
    fun provideNetworkErrorFormatter(): NetworkErrorFormatter {
        return NetworkErrorFormatterImpl()
    }
}

interface NetworkErrorFormatter {
    fun <T> createException(response: Response<T>): Exception
}

class NetworkErrorFormatterImpl @Inject constructor() : NetworkErrorFormatter {
    override fun <T> createException(response: Response<T>): Exception {
        val exceptionText = String.format(
            " Error code: %s%n Error message: %s%n Error body: %s",
            response.code(),
            response.message(),
            response.errorBody()
        )
        return Exception(exceptionText)
    }
}



