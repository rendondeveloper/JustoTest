package com.rendonsoft.justotest.utils.retrofit.interceptor

import android.content.Context
import android.net.ConnectivityManager
import com.rendonsoft.justotest.utils.retrofit.model.exception.ConnectionException
import okhttp3.Interceptor
import okhttp3.Response
import java.net.InetAddress

private const val URL_VERIFICATION_GOOGLE_DEFAULT = "www.google.com"

class ConnectionInterceptor constructor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        if(!isConnected()){
            throw ConnectionException("Activa tus datos moviles o tu wifi para conectarte a internet.")
        }

        if(!this.availableNetwork()){
            throw ConnectionException("No hay conexión a internet disponible.")
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    private fun isConnected() : Boolean{
        val connectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectionManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    private fun availableNetwork(): Boolean {
        return try {
            val connection : InetAddress = InetAddress.getByName(URL_VERIFICATION_GOOGLE_DEFAULT)
            !connection.equals("")
        } catch (e: java.lang.Exception) {
            false
        }
    }
}