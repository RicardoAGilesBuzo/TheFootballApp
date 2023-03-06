package com.example.thefootballapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thefootballapp.data.model.match.Matche
import com.example.thefootballapp.data.model.standing.Table
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

    var footballObject: Table? = null
    var matchObject: Matche? = null

    private val _result = MutableLiveData<ResponseType<List<Table>>>()
    val result: LiveData<ResponseType<List<Table>>> = _result

    private val _matchResult = MutableLiveData<ResponseType<List<Matche>>>()
    val matchResult: LiveData<ResponseType<List<Matche>>> = _matchResult

    private val _teamResult = MutableLiveData<ResponseType<TeamModel>>()
    val teamResult: LiveData<ResponseType<TeamModel>> = _teamResult
    var lastGames: String = ""

    private val _teamMatchResult = MutableLiveData<ResponseType<List<MatcheTeam>>>()
    val teamMatchResult: LiveData<ResponseType<List<MatcheTeam>>> = _teamMatchResult

    fun getPremierTableList(){
        viewModelScope.launch {
            repository.getPremierStanding().collect{
                _result.postValue(it)
            }
        }
    }

    fun getLaLigaTableList(){
        viewModelScope.launch {
            repository.getLaLigaStanding().collect{
                _result.postValue(it)
            }
        }
    }

    fun getBundesTableList(){
        viewModelScope.launch {
            repository.getBundesStanding().collect{
                _result.postValue(it)
            }
        }
    }

    fun getPremierMatchList(){
        viewModelScope.launch {
            repository.getPremierMatch().collect{
                _matchResult.postValue(it)
            }
        }
    }

    fun getTeamDetailList(id: Int = footballObject!!.team.id){
        viewModelScope.launch {
            repository.getTeam(id).collect{
                _teamResult.postValue(it)
                lastGames = footballObject!!.form
            }
        }
    }

    fun getTeamMatchResult(id: Int = footballObject!!.team.id, limit: Int = 5, status: String = "FINISHED"){
        viewModelScope.launch {
            repository.getMatchTeam(id,limit,status).collect{
                _teamMatchResult.postValue(it)
            }
        }
    }
}