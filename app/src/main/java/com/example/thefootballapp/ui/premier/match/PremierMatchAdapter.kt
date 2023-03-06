package com.example.thefootballapp.ui.premier.match

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.clear
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.example.thefootballapp.R
import com.example.thefootballapp.data.model.match.Matche
import com.example.thefootballapp.databinding.ItemMatchBinding
import com.squareup.picasso.Picasso
import java.time.Instant
import java.time.ZoneId

class PremierMatchAdapter (
    private val footballList: MutableList<Matche> = mutableListOf(),
    private val clickListener: (Matche) -> Unit
) : RecyclerView.Adapter<PremierMatchAdapter.ViewHolder>() {

    fun updatePremierMatchAdapter(newSeason: List<Matche>) {
        footballList.clear()
        footballList.addAll(newSeason)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: ItemMatchBinding) : RecyclerView.ViewHolder(view.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun setup(premierMatchModel: Matche, clickListener: (Matche) -> Unit) {
            view.ivHomeLogo.clear()
            view.ivAwayLogo.clear()
            val crestHome = premierMatchModel.homeTeam.crest
            if (crestHome.lowercase().endsWith(".svg")){
                view.ivHomeLogo.loadSvg(crestHome)
            }
            else{
                Picasso.get()
                    .load(crestHome)
                    .into(view.ivHomeLogo)
            }
            val crestAway = premierMatchModel.awayTeam.crest
            if (crestAway.lowercase().endsWith(".svg")){
                view.ivAwayLogo.loadSvg(crestAway)
            }
            else{
                Picasso.get()
                    .load(crestAway)
                    .into(view.ivAwayLogo)
            }
            view.ivHomeName.text = premierMatchModel.homeTeam.shortName
            view.ivAwayName.text = premierMatchModel.awayTeam.shortName
            if (premierMatchModel.status == "FINISHED"){
                view.ivHomeScore.text = premierMatchModel.score.fullTime.home.toString()
                view.ivAwayScore.text = premierMatchModel.score.fullTime.away.toString()
                view.ivStatus.text = premierMatchModel.status
            }
            else {
                val dateString = premierMatchModel.utcDate
                val timestamp = Instant.parse(dateString)
                val date = timestamp.atZone(ZoneId.of("US/Eastern")).toString()
                val status = date.subSequence(11,16)
                view.ivStatus.text = status
                view.ivHomeScore.text = "-"
                view.ivAwayScore.text = "-"
            }
            val date = premierMatchModel.utcDate.subSequence(0,10)
            view.ivDate.text = date
            //view.ivDate.visibility = View.GONE
            itemView.setOnClickListener { clickListener(premierMatchModel) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemMatchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount(): Int = footballList.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setup(footballList[position], clickListener)
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