package com.example.virginmoneyapp.Api

import com.example.virginmoneyapp.model.people.People
import com.example.virginmoneyapp.model.rooms.Room
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/people")
    suspend fun getPeople() : Response<People>

    @GET("/rooms")
    suspend fun getRoom() : Response<Room>
}


