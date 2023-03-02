package ru.noosle.harry_potter_mvi.ui.main.presenter

import ru.noosle.harry_potter_mvi.ui.main.dto.Person
import ru.noosle.harry_potter_mvi.ui.main.dto.Spell


sealed class MainState {
    data class Loading(val value: Boolean) : MainState()
    data class Persons(val data: List<Person>) : MainState()
    data class Spells(val data: List<Spell>) : MainState()
    data class Error(val error: String) : MainState()}
