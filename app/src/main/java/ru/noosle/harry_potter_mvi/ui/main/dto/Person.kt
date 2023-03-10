package ru.noosle.harry_potter_mvi.ui.main.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val patronus: String? = null,
    val hogwartsStudent: Boolean? = null,
    val image: String? = null,
    val ancestry: String? = null,
    val gender: String? = null,
    val alive: Boolean? = null,
    val hairColour: String? = null,
    val dateOfBirth: String? = null,
    val house: String? = null,
    val hogwartsStaff: Boolean? = null,
    val alternateNames: List<String?>? = null,
    val actor: String? = null,
    val alternateActors: List<String?>? = null,
    val species: String? = null,
    val wand: Wand? = null,
    val name: String? = null,
    val wizard: Boolean? = null,
    val eyeColour: String? = null,
    val id: String? = null,
    val yearOfBirth: Int? = null
) : Parcelable

@Parcelize
data class Wand(
    val core: String? = null,
    val length: Double? = null,
    val wood: String? = null
) : Parcelable
