package ru.noosle.harry_potter_mvi.ui.main.dao

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HogwartsRepository @Inject constructor(private val hogwartsService: HogwartsService) {

    suspend fun getAllPersons() = hogwartsService.getAllPersons()

    suspend fun getAllSpells() = hogwartsService.getAllSpells()

    suspend fun getAllStudents() = hogwartsService.getAllStudents()

    suspend fun getAllStaff() = hogwartsService.getAllStaff()

    suspend fun getPersonByHouseName(house: String) = hogwartsService.getPersonByHouseName(house)
}