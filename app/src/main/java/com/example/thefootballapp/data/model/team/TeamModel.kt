package com.example.thefootballapp.data.model.team


import com.google.gson.annotations.SerializedName

data class TeamModel(
    @SerializedName("address")
    val address: String = "",
    @SerializedName("area")
    val area: Area = Area(),
    @SerializedName("clubColors")
    val clubColors: String = "",
    @SerializedName("coach")
    val coach: Coach = Coach(),
    @SerializedName("crest")
    val crest: String = "",
    @SerializedName("founded")
    val founded: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("lastUpdated")
    val lastUpdated: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("shortName")
    val shortName: String = "",
    @SerializedName("squad")
    val squad: List<Squad> = listOf(),
    @SerializedName("staff")
    val staff: List<Any> = listOf(),
    @SerializedName("tla")
    val tla: String = "",
    @SerializedName("venue")
    val venue: String = "",
    @SerializedName("website")
    val website: String = ""
)

data class Area(
    @SerializedName("code")
    val code: String = "",
    @SerializedName("flag")
    val flag: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = ""
)

data class Coach(
    @SerializedName("dateOfBirth")
    val dateOfBirth: String = "",
    @SerializedName("firstName")
    val firstName: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("lastName")
    val lastName: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("nationality")
    val nationality: String = ""
)

data class Squad(
    @SerializedName("dateOfBirth")
    val dateOfBirth: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("nationality")
    val nationality: String = "",
    @SerializedName("position")
    val position: String = ""
)