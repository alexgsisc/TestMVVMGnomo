package com.alexisgs.apignomo.personajes.domain

import com.alexisgs.apignomo.personajes.data.model.ApiResponse
import com.alexisgs.apignomo.personajes.data.repository.CharactersRepository
import rx.Observable

class CharactersInteraction {

    private val charactersRepository = CharactersRepository()

    fun getListOfCharacters(): Observable<ApiResponse> {
        return charactersRepository.createCharactersList()
    }
}