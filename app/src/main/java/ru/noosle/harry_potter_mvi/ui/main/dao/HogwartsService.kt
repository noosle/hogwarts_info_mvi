package ru.noosle.harry_potter_mvi.ui.main.dao

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import ru.noosle.harry_potter_mvi.ui.main.dto.Person
import ru.noosle.harry_potter_mvi.ui.main.dto.Spell

interface HogwartsService {

    @Headers("Content-Type: application/json")
    @GET("characters")
    suspend fun getAllPersons(): Response<List<Person>>

    @Headers("Content-Type: application/json")
    @GET("spells")
    suspend fun getAllSpells(): Response<List<Spell>>

    @Headers("Content-Type: application/json")
    @GET("characters/students")
    suspend fun getAllStudents(): Response<List<Person>>

    @Headers("Content-Type: application/json")
    @GET("characters/staff")
    suspend fun getAllStaff(): Response<List<Person>>

    @Headers("Content-Type: application/json")
    @GET("characters/house/{house_name}")
    suspend fun getPersonByHouseName(@Path("house_name") house: String): Response<List<Person>>
}
