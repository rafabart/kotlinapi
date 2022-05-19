package com.kotlinapi.data.vo.v1

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

//Define a ordem de exibição do JSON
@JsonPropertyOrder("id", "address", "gender", "FIRSTNAME", "lastName")
data class PersonVOV1(

    //Ignora a exibição do campo
    @JsonIgnore
    var id: Long = 0,

    //Renomeia o campo no JSON
    @JsonProperty("FIRSTNAME")
    var firstName: String = "",

    var lastName: String = "",

    var address: String = "",

    var gender: String = ""

)
