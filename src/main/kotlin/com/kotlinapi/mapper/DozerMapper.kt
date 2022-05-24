package com.kotlinapi.mapper

import com.github.dozermapper.core.DozerBeanMapperBuilder
import com.github.dozermapper.core.Mapper
import com.kotlinapi.data.vo.v1.PersonVOV1
import com.kotlinapi.model.Person

object DozerMapper {

    private val mapper: Mapper = DozerBeanMapperBuilder.buildDefault()


    fun <O, D> parseObject(origin: O, destination: Class<D>?): D =
        mapper.map(origin, destination)


    fun parseObject(origin: Person): PersonVOV1 =
        PersonVOV1(
            idPerson = origin.id,
            firstName = origin.firstName,
            lastName = origin.lastName,
            address = origin.address,
            gender = origin.gender
        )


    fun parseObject(origin: PersonVOV1): Person =
        Person(
            id = origin.idPerson,
            firstName = origin.firstName,
            lastName = origin.lastName,
            address = origin.address,
            gender = origin.gender
        )


    fun <O, D> parseListObjects(origin: List<O>, destination: Class<D>?): ArrayList<D> =
        origin
            .map { o -> mapper.map(o, destination) }
            .toCollection(ArrayList())

}