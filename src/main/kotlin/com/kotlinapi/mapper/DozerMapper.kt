package com.kotlinapi.mapper

import com.github.dozermapper.core.DozerBeanMapperBuilder
import com.github.dozermapper.core.Mapper

object DozerMapper {

    private val mapper: Mapper = DozerBeanMapperBuilder.buildDefault()


    fun <O, D> parseObject(origin: O, destination: Class<D>?): D =
        mapper.map(origin, destination)


    fun <O, D> parseListObjects(origin: List<O>, destination: Class<D>?): ArrayList<D> =
        origin
            .map { o -> mapper.map(o, destination) }
            .toCollection(ArrayList())

}