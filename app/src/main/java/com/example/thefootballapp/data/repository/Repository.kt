package com.example.thefootballapp.data.repository

import com.example.thefootballapp.data.model.match.Matche
import com.example.thefootballapp.data.model.standing.Table
import com.example.thefootballapp.data.model.team.Squad
import com.example.thefootballapp.data.model.team.TeamModel
import com.example.thefootballapp.data.model.team.lastmatch.MatcheTeam
import com.example.thefootballapp.util.ResponseType
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getPremierStanding(league_id: String): Flow<ResponseType<List<Table>>>
    suspend fun getLaLigaStanding(league_id: String): Flow<ResponseType<List<Table>>>
    suspend fun getBundesStanding(league_id: String): Flow<ResponseType<List<Table>>>
    suspend fun getPremierMatch(match_id: Int): Flow<ResponseType<List<Matche>>>
    suspend fun getLaLigaMatch(match_id: Int): Flow<ResponseType<List<Matche>>>
    suspend fun getBundesMatch(match_id: Int): Flow<ResponseType<List<Matche>>>
    suspend fun getTeam(id: Int): Flow<ResponseType<TeamModel>>
    suspend fun getSquad(id: Int): Flow<ResponseType<List<Squad>>>
    suspend fun getMatchTeam(id: Int, limit: Int, status: String): Flow<ResponseType<List<MatcheTeam>>>
    suspend fun getAllMatchTeam(id: Int): Flow<ResponseType<List<Matche>>>
}