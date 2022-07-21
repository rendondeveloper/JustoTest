package com.rendonsoft.justotest.apis.endpoints

import com.rendonsoft.justotest.apis.model.randomPeople.RandomPeople
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface HomeApi {
    @GET(HomeConfig.GET_RANDOM)
    fun getRandomPeopleAsync(): Deferred<Response<RandomPeople>>
}