package com.kotlinapi.config

import com.kotlinapi.serilization.converter.YamlJackson2HttpMessageConverter
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    private val MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml")


    override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters.add(YamlJackson2HttpMessageConverter())
    }


    override fun configureContentNegotiation(configurer: ContentNegotiationConfigurer) {

/*        Ao passar na Headers da requisição o parametro "Accept" com o valor "application/json", "application/xml" ou "application/x-yaml"
é possível receber como retorno os recursos nos respectivos formatos "json", "xml" ou "yaml"
*/

        configurer
            .favorParameter(false)
            .ignoreAcceptHeader(false)
            .useRegisteredExtensionsOnly(false)
            .defaultContentType(MediaType.APPLICATION_JSON)
            .mediaType("json", MediaType.APPLICATION_JSON)
            .mediaType("xml", MediaType.APPLICATION_XML)
            .mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YML)
    }
}