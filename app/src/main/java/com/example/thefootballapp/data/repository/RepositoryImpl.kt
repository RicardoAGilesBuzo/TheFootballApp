package com.example.thefootballapp.data.repository

import com.example.thefootballapp.data.model.match.Matche
import com.example.thefootballapp.data.model.standing.Table
import com.example.thefootballapp.data.model.team.TeamModel
import com.example.thefootballapp.data.model.team.lastmatch.MatcheTeam
import com.example.thefootballapp.data.remote.FootballApi
import com.example.thefootballapp.util.ResponseType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiDetails: FootballApi
) : Repository {
    override suspend fun getPremierStanding(): Flow<ResponseType<List<Table>>> = flow{
        emit(ResponseType.LOADING)

        try {
            val response = apiDetails.getPremierLeagueTable()
            if (response.isSuccessful){
                response.body()?.let { it ->
                    emit(ResponseType.SUCCESS(it.standings[0].table))
                }
            } else{
                emit(ResponseType.ERROR(response.message()))
            }
        } catch (e:java.lang.Exception){
            emit(ResponseType.ERROR(e.localizedMessage as String))
        }
    }

    override suspend fun getLaLigaStanding(): Flow<ResponseType<List<Table>>> = flow{
        emit(ResponseType.LOADING)

        try {
            val response = apiDetails.getLaLigaTable()
            if (response.isSuccessful){
                response.body()?.let { it ->
                    emit(ResponseType.SUCCESS(it.standings[0].table))
                }
            } else{
                emit(ResponseType.ERROR(response.message()))
            }
        } catch (e:java.lang.Exception){
            emit(ResponseType.ERROR(e.localizedMessage as String))
        }
    }

    override suspend fun getBundesStanding(): Flow<ResponseType<List<Table>>> = flow{
        emit(ResponseType.LOADING)

        try {
            val response = apiDetails.getBundesTable()
            if (response.isSuccessful){
                response.body()?.let { it ->
                    emit(ResponseType.SUCCESS(it.standings[0].table))
                }
            } else{
                emit(ResponseType.ERROR(response.message()))
            }
        } catch (e:java.lang.Exception){
            emit(ResponseType.ERROR(e.localizedMessage as String))
        }
    }

    override suspend fun getPremierMatch(): Flow<ResponseType<List<Matche>>> = flow {
        emit(ResponseType.LOADING)

        try {
            val response = apiDetails.getPremierLeagueMatch()
            if (response.isSuccessful){
                response.body()?.let { it ->
                    emit(ResponseType.SUCCESS(it.matches))
                }
            } else{
                emit(ResponseType.ERROR(response.message()))
            }
        } catch (e:java.lang.Exception){
            emit(ResponseType.ERROR(e.localizedMessage as String))
        }
    }

    override suspend fun getTeam(id: Int): Flow<ResponseType<TeamModel>> = flow{
        emit(ResponseType.LOADING)

        try {
            val response = apiDetails.getTeamById(id)
            if (response.isSuccessful){
                response.body()?.let { it ->
                    emit(ResponseType.SUCCESS(it))
                }
            } else{
                emit(ResponseType.ERROR(response.message()))
            }
        } catch (e:java.lang.Exception){
            emit(ResponseType.ERROR(e.localizedMessage as String))
        }
    }

    override suspend fun getMatchTeam(
        id: Int,
        limit: Int,
        status: String
    ): Flow<ResponseType<List<MatcheTeam>>> = flow{
        emit(ResponseType.LOADING)

        try {
            val response = apiDetails.getLastTeamMatch(id, limit, status)
            if (response.isSuccessful){
                response.body()?.let { it ->
                    emit(ResponseType.SUCCESS(it.matches))
                }
            } else{
                emit(ResponseType.ERROR(response.message()))
            }
        } catch (e:java.lang.Exception){
            emit(ResponseType.ERROR(e.localizedMessage as String))
        }
    }

}