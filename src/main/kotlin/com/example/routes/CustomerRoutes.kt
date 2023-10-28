package com.example.routes

import com.example.models.Customer
import com.example.models.customers
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.customerRoutes() {
    route("/customer"){
        get ("/all"){
            if(customers.isEmpty()){
                call.respondText("No customer available", status = HttpStatusCode.NotFound)
            }else{
                call.respond(customers)
            }
        }
        get ("/{id?}"){
            val id = call.parameters["id"] ?: return@get call.respondText("Missing id", status = HttpStatusCode.BadRequest)
            val customer = customers.find {it.id == id} ?: return@get call.respondText("User with id $id not found", status = HttpStatusCode.NotFound)
            call.respond(customer)
        }
        post ("/register"){
            val customer = call.receive<Customer>()
            customers.add(customer)
            call.respondText("Customer created successfully", status = HttpStatusCode.Created)
        }
        delete("{id?}"){
            val id = call.parameters["id"] ?: return@delete call.respondText("Missing id", status = HttpStatusCode.BadRequest)
            if (customers.removeIf{it.id == id}){
                call.respondText("Customer Remove Successfully", status = HttpStatusCode.Accepted)
            }else{
                call.respondText("Customer with id $id not found", status = HttpStatusCode.NotFound)
            }

        }

    }
}