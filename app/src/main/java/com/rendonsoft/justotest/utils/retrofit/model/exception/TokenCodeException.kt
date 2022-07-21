package com.rendonsoft.justotest.utils.retrofit.model.exception

import java.io.IOException

class TokenCodeException constructor(private val messageCustom : String) : IOException(){
    override val message: String
        get() = messageCustom
}