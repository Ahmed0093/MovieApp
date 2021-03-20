package com.example.movieApp.network

import com.example.myapplication.data.FlickerResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.io.InterruptedIOException

object NetworkRoute {


    /**
     * Main and only one public fun in this class to invoke api calls safely granting error and exception handling.
     * All the mapping stuff to return only success or error model is done here.
     */
    suspend fun <T : Any> makeApiCall(
        call: suspend () -> Response<T>,
    ): ResultType<T> {

        var response: Response<T>? = null
        try {
            response = call.invoke()
            val responseModel = response.body()
            val responseErrorBody = response.errorBody()
            if (isValidResponse(response)) return ResultType.Success(
                responseModel
            )
            if (responseErrorBody != null) return ResultType.Error("Error occurs in API")
            return ResultType.Error("Error occurs in API")
        } catch (throwable: Throwable) {
            return when (throwable) {
                is IOException, is InterruptedIOException, is HttpException -> ResultType.Error("Error Occurs")
                else -> ResultType.Error("Error Occurs")
            }
        }
    }

    /**
     * Check if call is successful.
     */
    private fun <T> isValidResponse(response: Response<T>?): Boolean {
        return response != null && response.isSuccessful && response.body() != null
    }

}

sealed class ResultType<out T : Any> {
    data class Success<out T : Any>(val data: Any?) : ResultType<T>()
    data class Error(val errorMsg: String) : ResultType<Nothing>()
}

