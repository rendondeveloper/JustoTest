package com.rendonsoft.justotest.module.home.domain.datasource

import com.rendonsoft.justotest.apis.apicall.HomeApiCall
import com.rendonsoft.justotest.apis.model.randomPeople.RandomPeople
import com.rendonsoft.justotest.utils.retrofit.model.dataclass.ResponseData

interface DataSourceRandomPeople {
    suspend fun getPeopleRandom(): ResponseData<RandomPeople?>
}

class DataSourceRandomPeopleImp(private val callApi: HomeApiCall) : DataSourceRandomPeople{
    override suspend fun getPeopleRandom() =  callApi.getPeopleRandom()
}