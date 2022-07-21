package com.rendonsoft.justotest.apis.apicall

import android.content.Context
import com.rendonsoft.justotest.apis.endpoints.HomeApi
import com.rendonsoft.justotest.apis.endpoints.HomeConfig.HOST
import com.rendonsoft.justotest.apis.model.randomPeople.RandomPeople
import com.rendonsoft.justotest.utils.retrofit.builder.RetrofitApp
import com.rendonsoft.justotest.utils.retrofit.managercall.ManagerCall
import com.rendonsoft.justotest.utils.retrofit.model.dataclass.ResponseData

class HomeApiCall(context: Context) : ManagerCall() {

    private val callApi = RetrofitApp
        .Build<HomeApi>()
        .setContext(context)
        .setHost(HOST)
        .setClass(HomeApi::class.java)
        .builder().instance()

    suspend fun getPeopleRandom(): ResponseData<RandomPeople?> {
        return managerCallApi(
            call = {
                callApi.getRandomPeopleAsync().await()
            }
        )
    }
}