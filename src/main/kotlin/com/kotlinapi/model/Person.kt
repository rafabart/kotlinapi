package com.kotlinapi.model

import jakarta.persistence.*

@Entity
data class Person(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    var firstName: String = "",

    @Column(nullable = false)
    var lastName: String = "",

    @Column(nullable = false)
    var address: String = "",

    @Column(nullable = false)
    var gender: String = ""

)
