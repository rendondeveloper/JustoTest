package com.rendonsoft.justotest.utils.retrofit.model.exception

import java.io.IOException

class ConnectionException(private val messageCustom : String? = null) : IOException(){
    override val message: String
        get() = messageCustom ?: "Connection Error."
}