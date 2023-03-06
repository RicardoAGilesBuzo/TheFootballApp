package com.example.thefootballapp.ui.premier.standing


import android.graphics.Color
import android.view.ViewGroup
import android.widget.ImageView
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.thefootballapp.data.model.standing.Table
import com.example.thefootballapp.databinding.ItemDetailsBinding
import coil.ImageLoader
import coil.clear
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.squareup.picasso.Picasso

class PremierStandingAdapter (
    private val footballList: MutableList<Table> = mutableListOf(),
    private val clickListener: (Table) -> Unit
) : RecyclerView.Adapter<PremierStandingAdapter.ViewHolder>() {

    fun updatePremierTableAdapter(newSeason: List<Table>) {
        footballList.clear()
        footballList.addAll(newSeason)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: ItemDetailsBinding) : RecyclerView.ViewHolder(view.root) {
        fun setup(premierStandingModel: Table, clickListener: (Table) -> Unit) {
            val pos = premierStandingModel.position
            if (pos<=9){
                view.ivTeamPosition.text = "0$pos"
            }
            else {
                view.ivTeamPosition.text = pos.toString()
            }
            if (pos <= 4){
                view.ivGames.setBackgroundColor(Color.parseColor("#0E1E5B"))
            }
            else if (pos == 5){
                view.ivGames.setBackgroundColor(Color.parseColor("#D87600"))
            }
            else if (pos >= 18){
                view.ivGames.setBackgroundColor(Color.parseColor("#A10000"))
            }
            else {
                view.ivGames.setBackgroundColor(Color.WHITE)
            }

            view.ivTeamLogo.clear()
            val crest = premierStandingModel.team.crest
            if (crest.lowercase().endsWith(".svg")){
                view.ivTeamLogo.loadSvg(crest)
            }
            else {
                Picasso.get()
                    .load(crest)
                    .into(view.ivTeamLogo)
            }

            val games = premierStandingModel.playedGames
            if (games<=9){
                view.ivPlays.text = "0$games"
            }
            else {
                view.ivPlays.text = games.toString()
            }

            val wins = premierStandingModel.won
            if (wins<=9){
                view.ivWins.text = "0$wins"
            }
            else {
                view.ivWins.text = wins.toString()
            }

            val draw = premierStandingModel.draw
            if (draw<=9){
                view.ivDraws.text = "0$draw"
            }
            else {
                view.ivDraws.text = draw.toString()
            }
            val lose = premierStandingModel.lost
            if (lose<=9){
                view.ivLoses.text = "0$lose"
            }
            else {
                view.ivLoses.text = lose.toString()
            }

            val points = premierStandingModel.points
            if (points<=9){
                view.ivTeamPosition.text = "0$points"
            }
            else {
                view.ivTeamPoints.text = points.toString()
            }

            itemView.setOnClickListener { clickListener(premierStandingModel) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemDetailsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount(): Int = footballList.size

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