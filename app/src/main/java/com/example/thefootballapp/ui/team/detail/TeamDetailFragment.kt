package com.example.thefootballapp.ui.team.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.ImageLoader
import coil.clear
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.thefootballapp.R
import com.example.thefootballapp.data.model.team.TeamModel
import com.example.thefootballapp.data.model.team.lastmatch.MatcheTeam
import com.example.thefootballapp.databinding.FragmentTeamDetailBinding
import com.example.thefootballapp.ui.viewmodel.FootBallViewModel
import com.example.thefootballapp.util.ResponseType
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamDetailFragment : Fragment() {
    private var _binding: FragmentTeamDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FootBallViewModel by activityViewModels()
    private val _viewModel : FootBallViewModel by activityViewModels()

    companion object {
        const val TAG = "TeamDetailFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTeamDetailBinding.inflate(inflater, container, false)

        /*_binding!!.ivClose.setOnClickListener {
            viewModel.teamSelected = false
            this.dismiss()
        }*/

        viewModel.teamResult.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseType.LOADING -> {
                }
                is ResponseType.SUCCESS -> {
                    initViews(it.response)
                }
                is ResponseType.ERROR -> {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
        _viewModel.teamMatchResult.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseType.LOADING -> {
                }
                is ResponseType.SUCCESS -> {
                    initViewsMatch(it.response)
                }
                is ResponseType.ERROR -> {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.getTeamDetailList()
        _viewModel.getTeamMatchResult()

        return binding.root
    }



    private fun initViews(data: TeamModel){
        data.let {
            _binding!!.ivName.text = data.name
            _binding!!.ivLogo.clear()
            val crest = data.crest
            if (crest.lowercase().endsWith(".svg")){
                _binding!!.ivLogo.loadSvg(crest)
            }
            else {
                Picasso.get()
                    .load(crest)
                    .into(_binding!!.ivLogo)
            }/*
            val flag = data.area.flag
            if (flag.lowercase().endsWith(".svg")){
                _binding!!.ivCountry.loadSvg(flag)
            }
            else {
                Picasso.get()
                    .load(flag)
                    .into(_binding!!.ivCountry)
            }*/
            _binding!!.ivFounded.text = data.founded.toString()
            _binding!!.ivStadium.text = data.venue
        }
    }

    private fun initViewsMatch(_data: List<MatcheTeam>){
        _data.let {
            _binding!!.ivTeam1.clear()
            _binding!!.ivTeam2.clear()
            _binding!!.ivTeam3.clear()
            _binding!!.ivTeam4.clear()
            _binding!!.ivTeam5.clear()
            for (i in _data.indices){
                if (i==0){
                    _binding!!.ivTeam1Result.text = "${_data[i].score.fullTime.home}-${_data[i].score.fullTime.away}"
                    if (_data[i].homeTeam.id == _viewModel.footballObject!!.team.id){
                        if (_data[i].score.winner.lowercase() == "home_team"){
                            _binding!!.ivTeam1WDL.setImageResource(R.drawable.ic_won_circle)
                        }
                        else if (_data[i].score.winner.lowercase() == "away_team"){
                            _binding!!.ivTeam1WDL.setImageResource(R.drawable.ic_lost_circle)
                        }
                        else{
                            _binding!!.ivTeam1WDL.setImageResource(R.drawable.ic_draw_circle)
                        }
                        val crest = _data[i].awayTeam.crest
                        if (crest.lowercase().endsWith(".svg")){
                            _binding!!.ivTeam1.loadSvg(crest)
                        }
                        else {
                            Picasso.get()
                                .load(crest)
                                .into(_binding!!.ivTeam1)
                        }
                    }
                    else{
                        if (_data[i].score.winner.lowercase() == "home_team"){
                            _binding!!.ivTeam1WDL.setImageResource(R.drawable.ic_lost_circle)
                        }
                        else if (_data[i].score.winner.lowercase() == "away_team"){
                            _binding!!.ivTeam1WDL.setImageResource(R.drawable.ic_won_circle)
                        }
                        else{
                            _binding!!.ivTeam1WDL.setImageResource(R.drawable.ic_draw_circle)
                        }
                        val crest = _data[i].homeTeam.crest
                        if (crest.lowercase().endsWith(".svg")){
                            _binding!!.ivTeam1.loadSvg(crest)
                        }
                        else {
                            Picasso.get()
                                .load(crest)
                                .into(_binding!!.ivTeam1)
                        }
                    }
                }
                else if (i==1){
                    _binding!!.ivTeam2Result.text = "${_data[i].score.fullTime.home}-${_data[i].score.fullTime.away}"
                    if (_data[i].homeTeam.id == _viewModel.footballObject!!.team.id){
                        if (_data[i].score.winner.lowercase() == "home_team"){
                            _binding!!.ivTeam2WDL.setImageResource(R.drawable.ic_won_circle)
                        }
                        else if (_data[i].score.winner.lowercase() == "away_team"){
                            _binding!!.ivTeam2WDL.setImageResource(R.drawable.ic_lost_circle)
                        }
                        else{
                            _binding!!.ivTeam2WDL.setImageResource(R.drawable.ic_draw_circle)
                        }
                        val crest = _data[i].awayTeam.crest
                        if (crest.lowercase().endsWith(".svg")){
                            _binding!!.ivTeam2.loadSvg(crest)
                        }
                        else {
                            Picasso.get()
                                .load(crest)
                                .into(_binding!!.ivTeam2)
                        }
                    }
                    else{
                        if (_data[i].score.winner.lowercase() == "home_team"){
                            _binding!!.ivTeam2WDL.setImageResource(R.drawable.ic_lost_circle)
                        }
                        else if (_data[i].score.winner.lowercase() == "away_team"){
                            _binding!!.ivTeam2WDL.setImageResource(R.drawable.ic_won_circle)
                        }
                        else{
                            _binding!!.ivTeam2WDL.setImageResource(R.drawable.ic_draw_circle)
                        }
                        val crest = _data[i].homeTeam.crest
                        if (crest.lowercase().endsWith(".svg")){
                            _binding!!.ivTeam2.loadSvg(crest)
                        }
                        else {
                            Picasso.get()
                                .load(crest)
                                .into(_binding!!.ivTeam2)
                        }
                    }
                }

                else if (i==2){
                    _binding!!.ivTeam3Result.text = "${_data[i].score.fullTime.home}-${_data[i].score.fullTime.away}"
                    if (_data[i].homeTeam.id == _viewModel.footballObject!!.team.id){
                        if (_data[i].score.winner.lowercase() == "home_team"){
                            _binding!!.ivTeam3WDL.setImageResource(R.drawable.ic_won_circle)
                        }
                        else if (_data[i].score.winner.lowercase() == "away_team"){
                            _binding!!.ivTeam3WDL.setImageResource(R.drawable.ic_lost_circle)
                        }
                        else{
                            _binding!!.ivTeam3WDL.setImageResource(R.drawable.ic_draw_circle)
                        }
                        val crest = _data[i].awayTeam.crest
                        if (crest.lowercase().endsWith(".svg")){
                            _binding!!.ivTeam3.loadSvg(crest)
                        }
                        else {
                            Picasso.get()
                                .load(crest)
                                .into(_binding!!.ivTeam3)
                        }
                    }
                    else{
                        if (_data[i].score.winner.lowercase() == "home_team"){
                            _binding!!.ivTeam3WDL.setImageResource(R.drawable.ic_lost_circle)
                        }
                        else if (_data[i].score.winner.lowercase() == "away_team"){
                            _binding!!.ivTeam3WDL.setImageResource(R.drawable.ic_won_circle)
                        }
                        else{
                            _binding!!.ivTeam3WDL.setImageResource(R.drawable.ic_draw_circle)
                        }
                        val crest = _data[i].homeTeam.crest
                        if (crest.lowercase().endsWith(".svg")){
                            _binding!!.ivTeam3.loadSvg(crest)
                        }
                        else {
                            Picasso.get()
                                .load(crest)
                                .into(_binding!!.ivTeam3)
                        }
                    }
                }

                else if (i==3){
                    _binding!!.ivTeam4Result.text = "${_data[i].score.fullTime.home}-${_data[i].score.fullTime.away}"
                    if (_data[i].homeTeam.id == _viewModel.footballObject!!.team.id){
                        if (_data[i].score.winner.lowercase() == "home_team"){
                            _binding!!.ivTeam4WDL.setImageResource(R.drawable.ic_won_circle)
                        }
                        else if (_data[i].score.winner.lowercase() == "away_team"){
                            _binding!!.ivTeam4WDL.setImageResource(R.drawable.ic_lost_circle)
                        }
                        else{
                            _binding!!.ivTeam4WDL.setImageResource(R.drawable.ic_draw_circle)
                        }
                        val crest = _data[i].awayTeam.crest
                        if (crest.lowercase().endsWith(".svg")){
                            _binding!!.ivTeam4.loadSvg(crest)
                        }
                        else {
                            Picasso.get()
                                .load(crest)
                                .into(_binding!!.ivTeam4)
                        }
                    }
                    else{
                        if (_data[i].score.winner.lowercase() == "home_team"){
                            _binding!!.ivTeam4WDL.setImageResource(R.drawable.ic_lost_circle)
                        }
                        else if (_data[i].score.winner.lowercase() == "away_team"){
                            _binding!!.ivTeam4WDL.setImageResource(R.drawable.ic_won_circle)
                        }
                        else{
                            _binding!!.ivTeam4WDL.setImageResource(R.drawable.ic_draw_circle)
                        }
                        val crest = _data[i].homeTeam.crest
                        if (crest.lowercase().endsWith(".svg")){
                            _binding!!.ivTeam4.loadSvg(crest)
                        }
                        else {
                            Picasso.get()
                                .load(crest)
                                .into(_binding!!.ivTeam4)
                        }
                    }
                }

                else if (i==4){
                    _binding!!.ivTeam5Result.text = "${_data[i].score.fullTime.home}-${_data[i].score.fullTime.away}"
                    if (_data[i].homeTeam.id == _viewModel.footballObject!!.team.id){
                        if (_data[i].score.winner.lowercase() == "home_team"){
                            _binding!!.ivTeam5WDL.setImageResource(R.drawable.ic_won_circle)
                        }
                        else if (_data[i].score.winner.lowercase() == "away_team"){
                            _binding!!.ivTeam5WDL.setImageResource(R.drawable.ic_lost_circle)
                        }
                        else{
                            _binding!!.ivTeam5WDL.setImageResource(R.drawable.ic_draw_circle)
                        }
                        val crest = _data[i].awayTeam.crest
                        if (crest.lowercase().endsWith(".svg")){
                            _binding!!.ivTeam5.loadSvg(crest)
                        }
                        else {
                            Picasso.get()
                                .load(crest)
                                .into(_binding!!.ivTeam5)
                        }
                    }
                    else{
                        if (_data[i].score.winner.lowercase() == "home_team"){
                            _binding!!.ivTeam5WDL.setImageResource(R.drawable.ic_lost_circle)
                        }
                        else if (_data[i].score.winner.lowercase() == "away_team"){
                            _binding!!.ivTeam5WDL.setImageResource(R.drawable.ic_won_circle)
                        }
                        else{
                            _binding!!.ivTeam5WDL.setImageResource(R.drawable.ic_draw_circle)
                        }
                        val crest = _data[i].homeTeam.crest
                        if (crest.lowercase().endsWith(".svg")){
                            _binding!!.ivTeam5.loadSvg(crest)
                        }
                        else {
                            Picasso.get()
                                .load(crest)
                                .into(_binding!!.ivTeam5)
                        }
                    }
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun ImageView.loadSvg(url: String) {
        val imageLoader = ImageLoader.Builder(this.context)
            .componentRegistry {
                add(SvgDecoder(this@loadSvg.context))
            }
            .build()

        val request = ImageRequest.Builder(this.context)
            .crossfade(true)
            .crossfade(500)
            .data(url)
            .target(this)
            .build()

        imageLoader.enqueue(request)
    }

}