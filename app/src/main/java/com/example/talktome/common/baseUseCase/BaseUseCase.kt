package com.example.talktome.common.baseUseCase

import androidx.lifecycle.MutableLiveData
import com.example.talktome.common.network.networkUtils.ResultApi
import kotlinx.coroutines.*

abstract class BaseUseCase<in Params, Type>() where Type : Any {

    private val parentJob = Job()
    private val scope = CoroutineScope(Dispatchers.Main)

    abstract suspend fun run(
        params: Params,
        response: (ResultApi<Type>) -> Unit
    )

    fun request(
        params: Params,
        onResult: (Type?) -> Unit,
        loading: MutableLiveData<Boolean>? = null,
        error: MutableLiveData<String>? = null,
    ) {
        scope.launch {
            loading?.value = true

            run(params) {
                when (it) {
                    is ResultApi.Success<Type> -> {
                        onResult.invoke(it.data)
                        loading?.value = false
                    }
                    is ResultApi.HttpError<*> -> {
                        error?.value = it.error.toString()
                        loading?.value = false
                    }
                }
            }
        }
    }
}

