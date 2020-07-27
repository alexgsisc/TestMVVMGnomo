package com.alexisgs.apignomo

import com.alexisgs.apignomo.personajes.data.model.Characters
import com.alexisgs.apignomo.personajes.data.repository.CharactersRepository
import org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.runners.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MockitoUnitTest {

    @InjectMocks
    private val characterRepository = CharactersRepository()


    @Test
    fun testOne() {

        var listTem = mutableListOf<Characters>()

        characterRepository.createCharactersList().subscribe {
            listTem.addAll(it.characters)
        }

        assertNotNull(listTem)

    }


}

/**
 * que está diseñada para ayudarlo a trabajar con flujos de datos asíncronos en
 * un estilo de programación reactivo y sin tener que escribir toneladas de devoluciones de llamada
 * */