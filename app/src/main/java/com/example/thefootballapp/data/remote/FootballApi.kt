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

    @GET(PREMIER)
    suspend fun getPremierLeagueTable(): Response<StandingModel>

    @GET(LALIGA)
    suspend fun getLaLigaTable(): Response<StandingModel>

    @GET(BUNDES)
    suspend fun getBundesTable(): Response<StandingModel>

    @GET(PLMATCH)
    suspend fun getPremierLeagueMatch(): Response<MatchModel>

    @GET(LLMATCH)
    suspend fun getLaLigaMatch(): Response<MatchModel>

    @GET(TEAM)
    suspend fun getTeamById(
        @Path("id") id: Int
    ): Response<TeamModel>

    @GET(TEAMLASTMATCH)
    suspend fun getLastTeamMatch(
        @Path("id") id: Int,
        @Query(LIMIT) limit: Int,
        @Query(STATUS) status: String
    ): Response<LastMatchModel>



    companion object{
        //https://api.football-data.org/v4/competitions/PL/standings
        //http://api.football-data.org/v4/teams/78/matches?season=2022&limit=5&status=FINISHED&competitions=PD
        private const val ID_PATH = "id"
        const val BASE_URL = "https://api.football-data.org/"
        const val PREMIER = "v4/competitions/PL/standings"
        const val LALIGA = "v4/competitions/PD/standings"
        const val BUNDES = "v4/competitions/BL1/standings"
        const val PLMATCH = "v4/competitions/2021/matches"
        const val LLMATCH = "v4/competitions/2014/matches"
        const val TEAM = "v4/teams/{$ID_PATH}"
        const val TEAMLASTMATCH = "v4/teams/{$ID_PATH}/matches"
        const val LIMIT = "limit"
        const val STATUS = "status"

        const val AUTH_TOKEN =
            "e466fe4aa6754e079c329213091b0057"
        const val AUTH_HEADER = "X-Auth-Token"
    }
}