package com.alexisgs.apignomo.personajes.data.repository

import android.util.Log
import com.alexisgs.apignomo.personajes.data.api.ServiceApi
import com.alexisgs.apignomo.personajes.data.model.ApiResponse
import com.alexisgs.apignomo.personajes.data.model.Characters
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class CharactersRepository {

    var apiServices: ServiceApi = ServiceApi.Builder().build()

    fun createCharactersList(): Observable<ApiResponse> {
        /*var listTem = mutableListOf<Characters>()
        apiServices.getCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { Log.e(":::::::", "ABC ${it.message}") }
            .subscribe {
                it.characters.forEach {
                    Log.e("Color: ", it.hair_color)
                }
                listTem.addAll(it.characters)
            }*/

        return apiServices.getCharacters()
    }
}