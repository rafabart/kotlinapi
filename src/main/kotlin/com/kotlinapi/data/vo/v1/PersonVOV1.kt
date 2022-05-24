package com.kotlinapi.data.vo.v1

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.github.dozermapper.core.Mapping
import org.springframework.hateoas.RepresentationModel

//Define a ordem de exibição do JSON
@JsonPropertyOrder("id", "address", "gender", "FIRSTNAME", "lastName")
data class PersonVOV1(

    @Mapping("id")
    @field:JsonProperty("id")
    var idPerson: Long = 0,

    //Renomeia o campo no JSON
    @JsonProperty("FIRSTNAME")
    var firstName: String = "",

    var lastName: String = "",

    var address: String = "",

    //Ignora a exibição do campo
    @JsonIgnore
    var gender: String = ""


//Ao estender da classe RepresentationModel, a entidade entra no padrão HATEOAS
//Alem da(s) entidade(s), retorna o links para as operação possíveis com a entidade(s) em questão
) : RepresentationModel<PersonVOV1>()
