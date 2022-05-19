package com.kotlinapi.mapper.custom

import com.kotlinapi.data.vo.v2.PersonVOV2
import com.kotlinapi.model.Person
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonMapper {


    fun mapEntityToVO(person: Person): PersonVOV2 =
        PersonVOV2().apply {
            id = person.id
            address = person.address
            birthday = Date()
            firstName = person.firstName
            lastName = person.lastName
            gender = person.gender
        }


    fun mapVOToEntity(personVOV2: PersonVOV2): Person =
        Person().apply {
            id = personVOV2.id
            address = personVOV2.address
            firstName = personVOV2.firstName
            lastName = personVOV2.lastName
            gender = personVOV2.gender
        }
}