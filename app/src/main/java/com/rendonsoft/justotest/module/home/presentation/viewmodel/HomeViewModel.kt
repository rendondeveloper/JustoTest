package com.rendonsoft.justotest.module.home.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rendonsoft.justotest.module.home.presentation.ui.model.UiPeople
import com.rendonsoft.justotest.module.home.presentation.usescases.UsesCasesRandomPerson
import kotlinx.coroutines.launch

class HomeViewModel(
    private val cuBook : UsesCasesRandomPerson
) : ViewModel() {

    private var randomPeopleMLD = MutableLiveData<List<UiPeople>>()
    val randomPeople: LiveData<List<UiPeople>>
        get() = randomPeopleMLD

    //TODO this method can exist in a viewmodel base
    private var errorMLD = MutableLiveData<String>()
    val error: LiveData<String>
        get() = errorMLD

    private var showShimmerMLD = MutableLiveData<Boolean>()
    val showShimmer: LiveData<Boolean>
        get() = showShimmerMLD

    fun getPeopleRandom() {
        viewModelScope.launch {
            showShimmerMLD.value = true
            val responsePair = cuBook.getPeopleRandom()
            showShimmerMLD.value = false
            when{
                responsePair.first != null -> {
                    randomPeopleMLD.value = responsePair.first
                }
                responsePair.second != null -> {
                    errorMLD.value = responsePair.second?.message
                }
            }

        }
    }
}