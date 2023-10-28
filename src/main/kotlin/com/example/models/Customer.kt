package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Customer(val id: String, val fullName:String, val email:String, val address:String)
    val customers = mutableListOf<Customer>(
        Customer("1", "Bello Wajiu Olarewaju", "bellowajiuo@gmail.com", "Ilorin Kwara State")
    )
