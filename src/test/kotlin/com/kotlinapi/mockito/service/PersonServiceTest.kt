package com.kotlinapi.mockito.service

import com.kotlinapi.mapper.custom.PersonMapper
import com.kotlinapi.repository.PersonRepository
import com.kotlinapi.service.PersonService
import com.kotlinapi.unittests.mocks.MockPerson
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class PersonServiceTest {


    private lateinit var inputObject: MockPerson

    //Cria um objeto e injeta nele as classes mocadas abaixo
    @InjectMocks
    private lateinit var service: PersonService

    //Cria um objeto mocado
    @Mock
    private lateinit var repository: PersonRepository

    @Mock
    private lateinit var mapper: PersonMapper


    @BeforeEach
    fun setUp() {
        inputObject = MockPerson()
        MockitoAnnotations.openMocks(this)
    }


    @Test
    fun findAll() {
    }

    @Test
    fun findById() {
        val person = inputObject.mockEntity(1)
        `when`(repository.findById(person.id)).thenReturn(Optional.of(person))

        val result = service.findById(person.id)

        assertNotNull(result)
        assertNotNull(result.idPerson)
        assertNotNull(result.links)
        assertTrue(result.links.toString().contains("</api/person/v1/1>;rel=\"self\""))
        assertEquals("Address Test1", result.address)
        assertEquals("First Name Test1", result.firstName)
        assertEquals("Last Name Test1", result.lastName)
        assertEquals("Female", result.gender)
    }

    @Test
    fun create() {
    }

    @Test
    fun update() {
    }

    @Test
    fun delete() {
    }
}