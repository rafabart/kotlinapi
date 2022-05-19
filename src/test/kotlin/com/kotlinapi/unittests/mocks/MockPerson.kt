package com.kotlinapi.unittests.mocks

import com.kotlinapi.data.vo.v1.PersonVOV1
import com.kotlinapi.model.Person

class MockPerson {


    fun mockEntity(): Person {
        return mockEntity(0)
    }


    fun mockVO(): PersonVOV1 {
        return mockVO(0)
    }


    fun mockEntityList(): ArrayList<Person> {
        val persons: ArrayList<Person> = ArrayList<Person>()
        for (i in 0..13) {
            persons.add(mockEntity(i))
        }
        return persons
    }


    fun mockVOList(): ArrayList<PersonVOV1> {
        val persons: ArrayList<PersonVOV1> = ArrayList()
        for (i in 0..13) {
            persons.add(mockVO(i))
        }
        return persons
    }


    fun mockEntity(number: Int): Person {
        val person = Person()
        person.id = number.toLong()
        person.address = "Address Test$number"
        person.firstName = "First Name Test$number"
        person.gender = if (number % 2 == 0) "Male" else "Female"
        person.lastName = "Last Name Test$number"
        return person
    }


    fun mockVO(number: Int): PersonVOV1 {
        val person = PersonVOV1()
        person.id = number.toLong()
        person.address = "Address Test$number"
        person.firstName = "First Name Test$number"
        person.gender = if (number % 2 == 0) "Male" else "Female"
        person.lastName = "Last Name Test$number"
        return person
    }
}