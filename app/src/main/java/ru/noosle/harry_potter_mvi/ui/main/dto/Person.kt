package ru.noosle.harry_potter_mvi.ui.main.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(

    @field:SerializedName("patronus")
    val patronus: String? = null,

    @field:SerializedName("hogwartsStudent")
    val hogwartsStudent: Boolean? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("ancestry")
    val ancestry: String? = null,

    @field:SerializedName("gender")
    val gender: String? = null,

    @field:SerializedName("alive")
    val alive: Boolean? = null,

    @field:SerializedName("hairColour")
    val hairColour: String? = null,

    @field:SerializedName("dateOfBirth")
    val dateOfBirth: String? = null,

    @field:SerializedName("house")
    val house: String? = null,

    @field:SerializedName("hogwartsStaff")
    val hogwartsStaff: Boolean? = null,

    @field:SerializedName("alternate_names")
    val alternateNames: List<String?>? = null,

    @field:SerializedName("actor")
    val actor: String? = null,

    @field:SerializedName("alternate_actors")
    val alternateActors: List<String?>? = null,

    @field:SerializedName("species")
    val species: String? = null,

    @field:SerializedName("wand")
    val wand: Wand? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("wizard")
    val wizard: Boolean? = null,

    @field:SerializedName("eyeColour")
    val eyeColour: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("yearOfBirth")
    val yearOfBirth: Int? = null
) : Parcelable

@Parcelize
data class Wand(

    @field:SerializedName("core")
    val core: String? = null,

    @field:SerializedName("length")
    val length: Double? = null,

    @field:SerializedName("wood")
    val wood: String? = null
) : Parcelable
