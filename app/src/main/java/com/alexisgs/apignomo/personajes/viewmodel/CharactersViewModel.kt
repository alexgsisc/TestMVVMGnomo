package com.alexisgs.apignomo.personajes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexisgs.apignomo.personajes.data.model.Characters
import com.alexisgs.apignomo.personajes.domain.CharactersInteraction
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class CharactersViewModel : ViewModel() {

    private val charactersInteraction = CharactersInteraction()
    private val listData = MutableLiveData<List<Characters>>()
    val listDataTemp: LiveData<List<Characters>> get() = listData
    private val progressSuccess = MutableLiveData<Boolean>()
    val _progressSuccess: LiveData<Boolean> get() = progressSuccess

    init {
        getListOfCharacters()
    }


    private fun setListData(listOfCharacters: List<Characters>) {
        progressSuccess.value = false
        listData.value = listOfCharacters
    }

    fun getListOfCharacters() {
        progressSuccess.value = true
        charactersInteraction.getListOfCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { Log.e(":::::::", "ABC ${it.message}") }
            .subscribe {
                setListData(it.characters)
            }

    }

}