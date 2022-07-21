package com.rendonsoft.justotest.module.home.presentation.usescases

import com.rendonsoft.justotest.module.home.domain.repository.HomeRepository
import com.rendonsoft.justotest.module.home.presentation.ui.model.mapperTuUI
import com.rendonsoft.justotest.module.home.presentation.ui.model.UiPeople
import com.rendonsoft.justotest.utils.extension.log
import java.lang.Exception

interface UsesCasesRandomPerson{
    suspend fun getPeopleRandom() : Pair<List<UiPeople>?, Exception?>
}

class UsesCasesRandomPersonImp(private val repositoryImp: HomeRepository) : UsesCasesRandomPerson{
     override suspend fun getPeopleRandom() : Pair<List<UiPeople>?, Exception?> {
         val response = repositoryImp.getPeopleRandom()
         return if (response.sucess) {
             "RandomPerson -> ${response.data?.results ?: 0}".log()
             Pair(response.data?.mapperTuUI(), null)
         }else {
             "RandomPerson Error-> ${response.exception}".log()
             Pair(null, response.exception)
         }
     }
}