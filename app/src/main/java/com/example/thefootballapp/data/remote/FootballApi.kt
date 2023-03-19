package com.example.thefootballapp.data.remote

import com.example.thefootballapp.data.model.match.MatchModel
import com.example.thefootballapp.data.model.standing.StandingModel
import com.example.thefootballapp.data.model.team.TeamModel
import com.example.thefootballapp.data.model.team.lastmatch.LastMatchModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FootballApi {

    @GET(STANDING)
    suspend fun getStanding(
        @Path("league_id") league_id: String
    ): Response<StandingModel>

    @GET(MATCHES)
    suspend fun getMatches(
        @Path("match_id") match_id: Int
    ): Response<MatchModel>

    @GET(TEAM)
    suspend fun getTeamById(
        @Path("id") id: Int
    ): Response<TeamModel>

    @GET(TEAM_LAST_MATCH)
    suspend fun getLastTeamMatch(
        @Path("id") id: Int,
        @Query(LIMIT) limit: Int,
        @Query(STATUS) status: String
    ): Response<LastMatchModel>

    @GET(TEAM_LAST_MATCH)
    suspend fun getTeamMatch(
        @Path("id") id: Int
    ): Response<MatchModel>

    companion object{
        //https://api.football-data.org/v4/competitions/PL/standings
        //http://api.football-data.org/v4/teams/78/matches?season=2022&limit=5&status=FINISHED&competitions=PD
        private const val ID_TEAM = "id"
        private const val LEAGUE_ID = "league_id"
        private const val MATCH_ID = "match_id"

        const val BASE_URL = "https://api.football-data.org/"
        const val STANDING = "v4/competitions/{$LEAGUE_ID}/standings"
        const val MATCHES = "v4/competitions/{$MATCH_ID}/matches"
        const val TEAM = "v4/teams/{$ID_TEAM}"
        const val TEAM_LAST_MATCH = "v4/teams/{$ID_TEAM}/matches"

        const val LIMIT = "limit"
        const val STATUS = "status"

        const val AUTH_TOKEN = "e466fe4aa6754e079c329213091b0057"
        const val AUTH_HEADER = "X-Auth-Token"
    }
}