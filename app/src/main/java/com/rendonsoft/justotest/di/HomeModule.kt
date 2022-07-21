package com.rendonsoft.justotest.di

import com.rendonsoft.justotest.apis.apicall.HomeApiCall
import com.rendonsoft.justotest.module.home.domain.datasource.DataSourceRandomPeople
import com.rendonsoft.justotest.module.home.domain.datasource.DataSourceRandomPeopleImp
import com.rendonsoft.justotest.module.home.domain.repository.HomeRepository
import com.rendonsoft.justotest.module.home.domain.repository.HomeRepositoryImp
import com.rendonsoft.justotest.module.home.presentation.usescases.UsesCasesRandomPerson
import com.rendonsoft.justotest.module.home.presentation.usescases.UsesCasesRandomPersonImp
import com.rendonsoft.justotest.module.home.presentation.viewmodel.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val allModules = module {

    factory {
        HomeApiCall(androidApplication())
    }

    single<DataSourceRandomPeople>{
         DataSourceRandomPeopleImp(get())
    }

    single<HomeRepository>{
        HomeRepositoryImp(get())
    }

    single<UsesCasesRandomPerson>{
        UsesCasesRandomPersonImp(get())
    }

    viewModel {
        HomeViewModel(get())
    }
}