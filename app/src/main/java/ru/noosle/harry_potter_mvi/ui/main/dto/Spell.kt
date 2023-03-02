package ru.noosle.harry_potter_mvi.ui.main.dto

import com.google.gson.annotations.SerializedName

data class Spell(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
