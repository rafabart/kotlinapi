package com.kotlinapi.controller.v2

import com.kotlinapi.data.vo.v2.PersonVOV2
import com.kotlinapi.service.PersonService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v2/person")
class PersonControllerV2(

    private val service: PersonService

) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody person: PersonVOV2): PersonVOV2 =
        service.create(person)

}