package com.kotlinapi.controller.v1

import com.kotlinapi.data.vo.v1.PersonVOV1
import com.kotlinapi.mapper.DozerMapper
import com.kotlinapi.service.PersonService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/person")
class PersonControllerV1(

    private val service: PersonService

) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody person: PersonVOV1): PersonVOV1 =
        service.create(person)


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): List<PersonVOV1> =
        service.findAll()
            .let { pl -> DozerMapper.parseListObjects(pl, PersonVOV1::class.java) }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun update(@RequestBody person: PersonVOV1): PersonVOV1 =
        service.update(person)
            .let { p -> DozerMapper.parseObject(p, PersonVOV1::class.java) }


    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable("id") id: Long): PersonVOV1 =
        service.findById(id)
            .let { p -> DozerMapper.parseObject(p, PersonVOV1::class.java) }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable("id") id: Long) =
        service.delete(id)

}