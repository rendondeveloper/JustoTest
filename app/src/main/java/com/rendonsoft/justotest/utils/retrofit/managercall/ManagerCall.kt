package com.rendonsoft.justotest.utils.retrofit.managercall

import com.rendonsoft.justotest.utils.extension.log
import com.rendonsoft.justotest.utils.retrofit.model.dataclass.ResponseData
import com.rendonsoft.justotest.utils.retrofit.model.dataclass.ResultApi
import com.rendonsoft.justotest.utils.retrofit.model.exception.BackendException
import com.rendonsoft.justotest.utils.retrofit.model.exception.ConnectionException
import com.rendonsoft.justotest.utils.retrofit.model.inter.ValidationCode
import retrofit2.HttpException
import retrofit2.Response

private const val TAG = "ManagerCall"
private const val MESSAGE_ERROR_GENERAL : String  = "Por el momento no es posible realizar esta operación, intentelo más tarde."
private const val MESSAGE_ERROR_GENERAL_NETWORK : String  = "Ocurrió un error al intentar conectar con nuestros servidores."

open class ManagerCall{


    suspend fun <T : Any> managerCallApi(call: suspend () -> Response<T>, validation : ValidationCode<Response<T>>? = null) : ResponseData<T?> {
        val result : ResultApi<T> = safeApiResult(call, validation)
        val dataResponse : ResponseData<T?> = ResponseData()
        when(result){
            is ResultApi.Success -> {
                dataResponse.apply {
                    sucess = true
                    data = result.data
                }
            }
            is ResultApi.Error -> {
                dataResponse.exception = result.exception
            }
        }

        return dataResponse
    }

    private suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>, validation : ValidationCode<Response<T>>? = null): ResultApi<T> {
        var exception: Exception? = null
        var data: T? = null
        try {
            val response = call.invoke()
            validation?.executeValidation(response)
            if (response.isSuccessful) {
                data = response.body()
            }
        } catch (ex: BackendException) {
            "LOG-$TAG BackendException -> ${ex.message}".log()
            exception = Exception(ex.message)
        } catch (ex: ConnectionException) {
            "LOG-$TAG ConnectionException -> ${ex.message}".log()
            exception = Exception(ex.message)
        } catch (ex: HttpException) {
            "LOG-$TAG HttpException -> ${ex.message}".log()
            exception = Exception(MESSAGE_ERROR_GENERAL_NETWORK)
        } catch (ex: Exception) {
            "LOG-$TAG Exception ->  ${ex.message}".log()
            exception = Exception(MESSAGE_ERROR_GENERAL)
        }

        return exception?.let {
            ResultApi.Error(it)
        } ?: kotlin.run {
            ResultApi.Success(data)
        }
    }
}