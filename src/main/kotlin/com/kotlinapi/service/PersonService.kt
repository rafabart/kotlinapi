package com.kotlinapi.service

import com.kotlinapi.data.vo.v1.PersonVOV1
import com.kotlinapi.data.vo.v2.PersonVOV2
import com.kotlinapi.exception.ResourceNotFoundException
import com.kotlinapi.mapper.DozerMapper
import com.kotlinapi.mapper.custom.PersonMapper
import com.kotlinapi.model.Person
import com.kotlinapi.repository.PersonRepository
import org.springframework.stereotype.Service
import java.util.logging.*

@Service
class PersonService(

    private val personMapper: PersonMapper,
    private val personRepository: PersonRepository

) {

    private val logger = Logger.getLogger(PersonService::class.java.name)


    fun findAll(): List<Person> =
        personRepository.findAll()
            .also { logger.info("[findAll] Finding all people") }


    fun findById(id: Long): Person =
        personRepository.findById(id)
            .also { logger.info("[findById] Finding people for id=$id") }
            .orElseThrow { ResourceNotFoundException("No records found for id=$id") }


    fun create(person: PersonVOV1): PersonVOV1 =
        person
            .let { DozerMapper.parseObject(person, Person::class.java) }
            .also { logger.info("[create] Try create new people in database") }
            .let(personRepository::save)
            .let { ps -> DozerMapper.parseObject(ps, PersonVOV1::class.java) }
            .also { logger.info("[create] People for id=${it.id} create with success") }

    fun create(person: PersonVOV2): PersonVOV2 =
        person
            .let(personMapper::mapVOToEntity)
            .also { logger.info("[create] Try create new people in database") }
            .let(personRepository::save)
            .let(personMapper::mapEntityToVO)
            .also { logger.info("[create] People for id=${it.id} create with success") }


    fun update(personToUpdate: PersonVOV1): Person =
        findById(personToUpdate.id)
            .also { logger.info("[update] Try update people for id=${personToUpdate.id} in database") }
            .apply {
                id = personToUpdate.id
                firstName = personToUpdate.firstName
                lastName = personToUpdate.lastName
                address = personToUpdate.address
                gender = personToUpdate.gender
            }
            .let(personRepository::save)
            .also { logger.info("[update] People for id=${personToUpdate.id} updated with success") }


    fun delete(id: Long) =
        findById(id)
            .also { logger.info("[delete] Try delete people for id=${id} in database") }
            .let(personRepository::delete)
            .also { logger.info("[delete] People for id=${id} deleted with success") }
}


