@file:Suppress("unused")

package com.code1.testair2.extensions

import retrofit2.HttpException
import retrofit2.Response

fun <T> Response<T>.unwrap(): T {
    if (this.isSuccessful) {
        return this.body()!!
    } else {
        throw HttpException(this)
    }
}