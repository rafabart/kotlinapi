package com.kotlinapi.service

import com.kotlinapi.controller.v1.PersonControllerV1
import com.kotlinapi.data.vo.v1.PersonVOV1
import com.kotlinapi.data.vo.v2.PersonVOV2
import com.kotlinapi.exception.ResourceNotFoundException
import com.kotlinapi.mapper.DozerMapper
import com.kotlinapi.mapper.custom.PersonMapper
import com.kotlinapi.model.Person
import com.kotlinapi.repository.PersonRepository
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.logging.*

@Service
class PersonService(

    private val personMapper: PersonMapper,
    private val personRepository: PersonRepository

) {

    private val logger = Logger.getLogger(PersonService::class.java.name)


    fun findAll(): List<PersonVOV1> =
        personRepository.findAll()
            .map { DozerMapper.parseObject(it) }
            .map(this::addLink)
            .also { logger.info("[findAll] Finding all people") }


    fun findById(id: Long): PersonVOV1 =
        getById(id)
            .let { DozerMapper.parseObject(it) }
            .let(this::addLink)


    fun create(person: PersonVOV1): PersonVOV1 =
        person
            .let { DozerMapper.parseObject(it) }
            .also { logger.info("[create] Try create new people in database") }
            .let(personRepository::save)
            .let { DozerMapper.parseObject(it) }
            .let(this::addLink)
            .also { logger.info("[create] People for id=${it.idPerson} create with success") }


    fun create(person: PersonVOV2): PersonVOV2 =
        person
            .let(personMapper::mapVOToEntity)
            .also { logger.info("[create] Try create new people in database") }
            .let(personRepository::save)
            .let(personMapper::mapEntityToVO)
            .also { logger.info("[create] People for id=${it.id} create with success") }


    fun update(personToUpdate: PersonVOV1): PersonVOV1 =
        getById(personToUpdate.idPerson)
            .also { logger.info("[update] Try update people for id=${personToUpdate.idPerson} in database") }
            .apply {
                id = personToUpdate.idPerson
                firstName = personToUpdate.firstName
                lastName = personToUpdate.lastName
                address = personToUpdate.address
                gender = personToUpdate.gender
            }
            .let(personRepository::save)
            .let { DozerMapper.parseObject(it) }
            .let(this::addLink)
            .also { logger.info("[update] People for id=${personToUpdate.idPerson} updated with success") }


    fun delete(id: Long) =
        getById(id)
            .also { logger.info("[delete] Try delete people for id=${id} in database") }
            .let(personRepository::delete)
            .also { logger.info("[delete] People for id=${id} deleted with success") }


    private fun addLink(p: PersonVOV1): PersonVOV1 =
        p.add(linkTo(PersonControllerV1::class.java).withSelfRel())
            .also { logger.info("[addLink] add link to HATEOAS") }


    private fun getById(id: Long): Person =
        personRepository.findById(id)
            .also { logger.info("[getById] Finding people for id=$id") }
            .orElseThrow { ResourceNotFoundException("No records found for id=$id") }
}