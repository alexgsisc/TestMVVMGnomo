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

    val sets = mutableSetOf<String>()

    private val charactersInteraction = CharactersInteraction()
    private val listData = MutableLiveData<List<Characters>>()
    /*init {
        getListOfCharacters()
    }*/

    private fun setListData(listOfCharacters: List<Characters>) {
        listData.value = listOfCharacters
    }

    private fun getListOfCharacters() {
        charactersInteraction.getListOfCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { Log.e(":::::::", "ABC ${it.message}") }
            .subscribe {
                setListData(it.characters)
                /*it.characters.forEach {
                    sets.add(it.hair_color)
                }
                Log.e("COLORS:::", sets.toString())*/
            }

        //setListData(charactersInteraction.getListOfCharacters())
    }

    fun getListOfCharactersLiveData(): LiveData<List<Characters>> {
        getListOfCharacters()
        return listData
    }

}