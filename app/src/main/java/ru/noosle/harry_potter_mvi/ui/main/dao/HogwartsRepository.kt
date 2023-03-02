package ru.noosle.harry_potter_mvi.ui.main.dao

class HogwartsRepository constructor(private val hogwartsService: HogwartsService) {

    suspend fun getAllPersons() = hogwartsService.getAllPersons()

    suspend fun getAllSpells() = hogwartsService.getAllSpells()

    suspend fun getAllStudents() = hogwartsService.getAllStudents()

    suspend fun getAllStaff() = hogwartsService.getAllStaff()

    suspend fun getPersonByHouseName(house: String) = hogwartsService.getPersonByHouseName(house)
}