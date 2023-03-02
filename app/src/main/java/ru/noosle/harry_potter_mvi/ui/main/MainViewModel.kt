package ru.noosle.harry_potter_mvi.ui.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Response
import ru.noosle.harry_potter_mvi.ui.main.dao.HogwartsRepository
import ru.noosle.harry_potter_mvi.ui.main.dto.Person
import ru.noosle.harry_potter_mvi.ui.main.presenter.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: HogwartsRepository) : ViewModel() {

    private val personLiveMutable = MutableStateFlow<MainState>(MainState.Loading(true))
    val personLive = personLiveMutable.asStateFlow()

    private var job: Job? = null

    init {
        send(AllPersonsEvent())
    }

    fun send(event: MainEvent) {
        personLiveMutable.value = MainState.Loading(true)
        when (event) {
            is AllPersonsEvent -> {
                loadAllPersons()
            }
            is AllStudentsEvent -> {
                loadAllStudents()
            }
            is AllStaffEvent -> {
                loadAllStaff()
            }
            is AllSpellsEvent -> {
                loadAllSpells()
            }
            is StudentsByFacultyEvent -> {
                loadAllPersonByHouseName(event.faculty)
            }
        }
    }

    private fun loadAllPersons() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getAllPersons()
            withContext(Dispatchers.Main) {
                confirmPersonResponse(response)
            }
        }
    }

    private fun loadAllPersonByHouseName(house: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getPersonByHouseName(house)
            withContext(Dispatchers.Main) {
                confirmPersonResponse(response)
            }
        }
    }

    private fun loadAllStudents() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getAllStudents()
            withContext(Dispatchers.Main) {
                confirmPersonResponse(response)
            }
        }
    }

    private fun loadAllStaff() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getAllStaff()
            withContext(Dispatchers.Main) {
                confirmPersonResponse(response)
            }
        }
    }

    private fun confirmPersonResponse(response: Response<List<Person>>) {
        personLiveMutable.value = MainState.Loading(false)
        if (response.isSuccessful) {
            personLiveMutable.value = MainState.Persons(response.body()!!)
        } else {
            personLiveMutable.value = MainState.Error(response.message())
        }
    }

    private fun loadAllSpells() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getAllSpells()
            withContext(Dispatchers.Main) {
                personLiveMutable.value = MainState.Loading(false)
                if (response.isSuccessful) {
                    personLiveMutable.value = MainState.Spells(response.body()!!)
                } else {
                    personLiveMutable.value = MainState.Error(response.message())
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}