package com.example.thefootballapp.ui.team.match

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.clear
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.thefootballapp.data.model.match.Matche
import com.example.thefootballapp.databinding.ItemTeamMatchBinding
import com.squareup.picasso.Picasso
import java.time.Instant
import java.time.ZoneId

class TeamMatchAdapter (
    private val footballListTeam: MutableList<Matche> = mutableListOf(),
    private val clickListener: (Matche) -> Unit
) : RecyclerView.Adapter<TeamMatchAdapter.ViewHolder>() {

    fun updateMatchAdapter(newSeason: List<Matche>) {
        footballListTeam.clear()
        footballListTeam.addAll(newSeason)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: ItemTeamMatchBinding) : RecyclerView.ViewHolder(view.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun setup(teamMatchModel: Matche, clickListener: (Matche) -> Unit) {
            view.ivTeamHomeLogo.clear()
            view.ivTeamAwayLogo.clear()
            val crestHome = teamMatchModel.homeTeam.crest
            if (crestHome.lowercase().endsWith(".svg")){
                view.ivTeamHomeLogo.loadSvg(crestHome)
            }
            else{
                Picasso.get()
                    .load(crestHome)
                    .into(view.ivTeamHomeLogo)
            }
            val crestAway = teamMatchModel.awayTeam.crest
            if (crestAway.lowercase().endsWith(".svg")){
                view.ivTeamAwayLogo.loadSvg(crestAway)
            }
            else{
                Picasso.get()
                    .load(crestAway)
                    .into(view.ivTeamAwayLogo)
            }
            view.ivTeamHomeName.text = teamMatchModel.homeTeam.shortName
            view.ivTeamAwayName.text = teamMatchModel.awayTeam.shortName
            if (teamMatchModel.status == "FINISHED"){
                view.ivTeamHomeScore.text = teamMatchModel.score.fullTime.home.toString()
                view.ivTeamAwayScore.text = teamMatchModel.score.fullTime.away.toString()
                view.ivTeamStatus.text = teamMatchModel.status
            }
            else {
                val dateString = teamMatchModel.utcDate
                val timestamp = Instant.parse(dateString)
                val date = timestamp.atZone(ZoneId.of("US/Eastern")).toString()
                val status = date.subSequence(11,16)
                view.ivTeamStatus.text = status
                view.ivTeamHomeScore.text = "-"
                view.ivTeamAwayScore.text = "-"
            }
            val date = teamMatchModel.utcDate.subSequence(0,10)
            view.ivTeamDate.text = date
            //view.ivDate.visibility = View.GONE
            itemView.setOnClickListener { clickListener(teamMatchModel) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemTeamMatchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount(): Int = footballListTeam.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setup(footballListTeam[position], clickListener)
    }

    fun ImageView.loadSvg(url: String) {
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