package com.rendonsoft.justotest.module.home.domain.repository

import com.rendonsoft.justotest.apis.model.randomPeople.RandomPeople
import com.rendonsoft.justotest.module.home.domain.datasource.DataSourceRandomPeople
import com.rendonsoft.justotest.utils.retrofit.model.dataclass.ResponseData

interface HomeRepository{
    suspend fun getPeopleRandom(): ResponseData<RandomPeople?>
}

class HomeRepositoryImp(
    private val dataSource : DataSourceRandomPeople
) : HomeRepository{
    override suspend fun getPeopleRandom() = dataSource.getPeopleRandom()
}