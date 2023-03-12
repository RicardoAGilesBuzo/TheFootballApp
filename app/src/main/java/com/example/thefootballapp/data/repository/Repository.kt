package com.example.thefootballapp.data.repository

import com.example.thefootballapp.data.model.match.Matche
import com.example.thefootballapp.data.model.standing.Table
import com.example.thefootballapp.data.model.team.TeamModel
import com.example.thefootballapp.data.model.team.lastmatch.MatcheTeam
import com.example.thefootballapp.util.ResponseType
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getPremierStanding(): Flow<ResponseType<List<Table>>>
    suspend fun getLaLigaStanding(): Flow<ResponseType<List<Table>>>
    suspend fun getBundesStanding(): Flow<ResponseType<List<Table>>>
    suspend fun getPremierMatch(): Flow<ResponseType<List<Matche>>>
    suspend fun getLaLigaMatch(): Flow<ResponseType<List<Matche>>>
    suspend fun getTeam(id: Int): Flow<ResponseType<TeamModel>>
    suspend fun getMatchTeam(id: Int, limit: Int, status: String): Flow<ResponseType<List<MatcheTeam>>>
}