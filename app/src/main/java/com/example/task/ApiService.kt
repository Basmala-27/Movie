package com.example.task

import retrofit2.http.GET

interface ApiService {
    @GET("films")
    suspend fun getMovies(): List<Movies>
}
