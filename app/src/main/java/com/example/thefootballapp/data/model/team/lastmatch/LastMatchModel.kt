package com.example.thefootballapp.data.model.team.lastmatch


import com.google.gson.annotations.SerializedName

data class LastMatchModel(
    @SerializedName("matches")
    val matches: List<MatcheTeam> = listOf()
)

data class MatcheTeam(
    @SerializedName("awayTeam")
    val awayTeam: AwayTeam,
    @SerializedName("homeTeam")
    val homeTeam: HomeTeam,
    @SerializedName("id")
    val id: Int,
    @SerializedName("matchday")
    val matchday: Int,
    @SerializedName("score")
    val score: Score,
    @SerializedName("utcDate")
    val utcDate: String
)

data class AwayTeam(
    @SerializedName("crest")
    val crest: String,
    @SerializedName("id")
    val id: Int
)

data class HomeTeam(
    @SerializedName("crest")
    val crest: String,
    @SerializedName("id")
    val id: Int
)

data class Score(
    @SerializedName("fullTime")
    val fullTime: FullTime,
    @SerializedName("winner")
    val winner: String
)

data class FullTime(
    @SerializedName("away")
    val away: Int,
    @SerializedName("home")
    val home: Int
)