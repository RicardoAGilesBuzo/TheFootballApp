package com.example.thefootballapp.data.model.match


import com.google.gson.annotations.SerializedName

data class MatchModel(
    @SerializedName("matches")
    val matches: List<Matche> = listOf()
)

data class Matche(
    @SerializedName("awayTeam")
    val awayTeam: AwayTeam = AwayTeam(),
    @SerializedName("group")
    val group: Any = Any(),
    @SerializedName("homeTeam")
    val homeTeam: HomeTeam = HomeTeam(),
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("lastUpdated")
    val lastUpdated: String = "",
    @SerializedName("matchday")
    val matchday: Int = 0,
    @SerializedName("score")
    val score: Score = Score(),
    @SerializedName("status")
    val status: String = "",
    @SerializedName("utcDate")
    val utcDate: String = ""
)

data class AwayTeam(
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

data class HomeTeam(
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

data class Score(
    @SerializedName("duration")
    val duration: String = "",
    @SerializedName("fullTime")
    val fullTime: FullTime = FullTime(),
    @SerializedName("halfTime")
    val halfTime: HalfTime = HalfTime(),
    @SerializedName("winner")
    val winner: String = ""
)

data class FullTime(
    @SerializedName("away")
    val away: Int = 0,
    @SerializedName("home")
    val home: Int = 0
)

data class HalfTime(
    @SerializedName("away")
    val away: Int = 0,
    @SerializedName("home")
    val home: Int = 0
)