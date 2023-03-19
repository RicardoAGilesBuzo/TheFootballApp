package com.example.thefootballapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thefootballapp.data.model.match.Matche
import com.example.thefootballapp.data.model.standing.Table
import com.example.thefootballapp.data.model.team.Squad
import com.example.thefootballapp.data.model.team.TeamModel
import com.example.thefootballapp.data.model.team.lastmatch.MatcheTeam
import com.example.thefootballapp.data.repository.Repository
import com.example.thefootballapp.util.ResponseType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FootBallViewModel @Inject constructor(
    private val repository: Repository
): ViewModel(){

    lateinit var footballObject: Table
    var matchObject: Matche? = null
    lateinit var squadObject: Squad
    var teamSelected: Boolean = false

    private val _standingResultPL = MutableLiveData<ResponseType<List<Table>>>()
    val standingResultPL: LiveData<ResponseType<List<Table>>> = _standingResultPL

    private val _standingResultPD = MutableLiveData<ResponseType<List<Table>>>()
    val standingResultPD: LiveData<ResponseType<List<Table>>> = _standingResultPD

    private val _standingResultBL = MutableLiveData<ResponseType<List<Table>>>()
    val standingResultBL: LiveData<ResponseType<List<Table>>> = _standingResultBL

    private val _matchResultPL = MutableLiveData<ResponseType<List<Matche>>>()
    val matchResultPL: LiveData<ResponseType<List<Matche>>> = _matchResultPL

    private val _matchResultPD = MutableLiveData<ResponseType<List<Matche>>>()
    val matchResultPD: LiveData<ResponseType<List<Matche>>> = _matchResultPD

    private val _matchResultBL = MutableLiveData<ResponseType<List<Matche>>>()
    val matchResultBL: LiveData<ResponseType<List<Matche>>> = _matchResultBL

    private val _matchTeamResult = MutableLiveData<ResponseType<List<Matche>>>()
    val matchTeamResult: LiveData<ResponseType<List<Matche>>> = _matchTeamResult

    private val _teamResult = MutableLiveData<ResponseType<TeamModel>>()
    val teamResult: LiveData<ResponseType<TeamModel>> = _teamResult
    var lastGames: String = ""

    private val _squadResult = MutableLiveData<ResponseType<List<Squad>>>()
    val squadResult: LiveData<ResponseType<List<Squad>>> = _squadResult

    private val _teamMatchResult = MutableLiveData<ResponseType<List<MatcheTeam>>>()
    val teamMatchResult: LiveData<ResponseType<List<MatcheTeam>>> = _teamMatchResult

    fun getPremierTableList(league_id: String = "PL"){
        viewModelScope.launch {
            repository.getPremierStanding(league_id).collect{
                _standingResultPL.postValue(it)
            }
        }
    }

    fun getLaLigaTableList(league_id: String = "PD"){
        viewModelScope.launch {
            repository.getLaLigaStanding(league_id).collect{
                _standingResultPD.postValue(it)
            }
        }
    }

    fun getBundesTableList(league_id: String = "BL1"){
        viewModelScope.launch {
            repository.getBundesStanding(league_id).collect{
                _standingResultBL.postValue(it)
            }
        }
    }

    fun getPremierMatchList(match_id: Int = 2021){
        viewModelScope.launch {
            repository.getPremierMatch(match_id).collect{
                _matchResultPL.postValue(it)
            }
        }
    }

    fun getLaLigaMatchList(match_id: Int = 2014){
        viewModelScope.launch {
            repository.getLaLigaMatch(match_id).collect{
                _matchResultPD.postValue(it)
            }
        }
    }

    fun getBundesMatchList(match_id: Int = 2002){
        viewModelScope.launch {
            repository.getBundesMatch(match_id).collect{
                _matchResultBL.postValue(it)
            }
        }
    }

    fun getTeamDetailList(id: Int = footballObject.team.id){
        viewModelScope.launch {
            repository.getTeam(id).collect{
                _teamResult.postValue(it)
                lastGames = footballObject.form
            }
        }
    }

    fun getMatchList(id: Int = footballObject.team.id){
        viewModelScope.launch {
            repository.getAllMatchTeam(id).collect{
                _matchTeamResult.postValue(it)
            }
        }
    }

    fun getTeamMatchResult(id: Int = footballObject.team.id, limit: Int = 5, status: String = "FINISHED"){
        viewModelScope.launch {
            repository.getMatchTeam(id,limit,status).collect{
                _teamMatchResult.postValue(it)
            }
        }
    }

    fun getSquad(id: Int = footballObject.team.id){
        viewModelScope.launch {
            repository.getSquad(id).collect{
                _squadResult.postValue(it)
            }
        }
    }
}