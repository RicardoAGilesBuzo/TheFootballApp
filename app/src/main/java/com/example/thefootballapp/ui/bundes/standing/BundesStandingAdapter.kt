package com.example.thefootballapp.ui.bundes.standing

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.clear
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.thefootballapp.data.model.standing.Table
import com.example.thefootballapp.databinding.ItemDetailsBinding
import com.squareup.picasso.Picasso

class BundesStandingAdapter (
    private val footballList: MutableList<Table> = mutableListOf(),
    private val clickListener: (Table) -> Unit
) : RecyclerView.Adapter<BundesStandingAdapter.ViewHolder>() {

    fun updateBundesTableAdapter(newSeason: List<Table>) {
        footballList.clear()
        footballList.addAll(newSeason)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: ItemDetailsBinding) : RecyclerView.ViewHolder(view.root) {
        fun setup(bundesStandingModel: Table, clickListener: (Table) -> Unit) {

            val pos = bundesStandingModel.position
            if (pos<=9){
                view.ivTeamPosition.text = "0$pos"
            }
            else {
                view.ivTeamPosition.text = pos.toString()
            }
            if (pos <= 4){
                view.ivGames.setBackgroundColor(android.graphics.Color.parseColor("#0E1E5B"))
            }
            else if (pos == 5){
                view.ivGames.setBackgroundColor(android.graphics.Color.parseColor("#D87600"))
            }
            else if (pos == 6) {
                view.ivGames.setBackgroundColor(android.graphics.Color.parseColor("#22AB00"))
            }
            else if (pos == 16){
                view.ivGames.setBackgroundColor(android.graphics.Color.parseColor("#D88910"))
            }
            else if (pos >= 17){
                view.ivGames.setBackgroundColor(android.graphics.Color.parseColor("#A10000"))
            }
            else {
                view.ivGames.setBackgroundColor(android.graphics.Color.WHITE)
            }

            view.ivTeamLogo.clear()
            val crest = bundesStandingModel.team.crest
            if (crest.lowercase().endsWith(".svg")){
                view.ivTeamLogo.loadSvg(crest)
            }
            else {
                Picasso.get()
                    .load(crest)
                    .into(view.ivTeamLogo)
            }

            val games = bundesStandingModel.playedGames
            if (games<=9){
                view.ivPlays.text = "0$games"
            }
            else {
                view.ivPlays.text = games.toString()
            }

            val wins = bundesStandingModel.won
            if (wins<=9){
                view.ivWins.text = "0$wins"
            }
            else {
                view.ivWins.text = wins.toString()
            }

            val draw = bundesStandingModel.draw
            if (draw<=9){
                view.ivDraws.text = "0$draw"
            }
            else {
                view.ivDraws.text = draw.toString()
            }
            val lose = bundesStandingModel.lost
            if (lose<=9){
                view.ivLoses.text = "0$lose"
            }
            else {
                view.ivLoses.text = lose.toString()
            }

            val points = bundesStandingModel.points
            if (points<=9){
                view.ivTeamPosition.text = "0$points"
            }
            else {
                view.ivTeamPoints.text = points.toString()
            }

            itemView.setOnClickListener { clickListener(bundesStandingModel) }
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