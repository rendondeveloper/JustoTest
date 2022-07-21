package com.rendonsoft.justotest.module.home.presentation.ui.model

import com.rendonsoft.justotest.apis.model.randomPeople.RandomPeople

fun RandomPeople.mapperTuUI() :  List<UiPeople>{
    return this.results.map {
        UiPeople(
            nameFull = "${it.name.first} ${it.name.last}",
            gender = it.gender,
            email = it.email,
            address = "${it.location.country}, ${it.location.city}, ${it.location.state}, ${it.location.street.name} #${it.location.street.number}",
            urlPicture = it.picture.large
        )
    }.toList()
}