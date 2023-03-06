package com.example.thefootballapp.data.model.standing


import com.google.gson.annotations.SerializedName

data class StandingModel(
    @SerializedName("competition")
    val competition: Competition = Competition(),
    @SerializedName("standings")
    val standings: List<Standing> = listOf()
)

data class Competition(
    @SerializedName("code")
    val code: String = "",
    @SerializedName("emblem")
    val emblem: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("type")
    val type: String = ""
)

data class Standing(
    @SerializedName("table")
    val table: List<Table> = listOf()
)

data class Table(
    @SerializedName("draw")
    val draw: Int = 0,
    @SerializedName("form")
    val form: String = "",
    @SerializedName("goalDifference")
    val goalDifference: Int = 0,
    @SerializedName("goalsAgainst")
    val goalsAgainst: Int = 0,
    @SerializedName("goalsFor")
    val goalsFor: Int = 0,
    @SerializedName("lost")
    val lost: Int = 0,
    @SerializedName("playedGames")
    val playedGames: Int = 0,
    @SerializedName("points")
    val points: Int = 0,
    @SerializedName("position")
    val position: Int = 0,
    @SerializedName("team")
    val team: Team = Team(),
    @SerializedName("won")
    val won: Int = 0
)

data class Team(
    @SerializedName("crest")
    val crest: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("shortName")
    val shortName: String = "",
    @SerializedName("tla")
    val tla: String = ""
)