package com.rendonsoft.justotest.utils.retrofit.model.inter

interface ValidationCode<in T> {
    fun executeValidation(response : T)
}