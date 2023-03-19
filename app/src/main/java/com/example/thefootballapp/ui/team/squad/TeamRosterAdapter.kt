package com.example.thefootballapp.ui.team.squad

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thefootballapp.data.model.team.Squad
import com.example.thefootballapp.databinding.ItemRosterBinding

class TeamRosterAdapter (
    private val footballListTeam: MutableList<Squad> = mutableListOf(),
    private val clickListener: (Squad) -> Unit
) : RecyclerView.Adapter<TeamRosterAdapter.ViewHolder>() {

    fun updateRosterAdapter(newSeason: List<Squad>) {
        footballListTeam.clear()
        footballListTeam.addAll(newSeason)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: ItemRosterBinding) : RecyclerView.ViewHolder(view.root) {
        fun setup(teamMatchModel: Squad, clickListener: (Squad) -> Unit) {
            view.ivName.text = teamMatchModel.name
            view.ivNationality.text = teamMatchModel.nationality
            itemView.setOnClickListener { clickListener(teamMatchModel) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemRosterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount(): Int = footballListTeam.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setup(footballListTeam[position], clickListener)
    }
}