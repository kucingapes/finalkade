/*
 * TeamsFavoriteAdapter.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/31/18 3:35 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import tampan.kucingapes.submission5final.DetailTeam
import tampan.kucingapes.submission5final.R
import tampan.kucingapes.submission5final.ankoUi.ItemTeamsUi
import tampan.kucingapes.submission5final.model.TeamsFavorite

class TeamsFavoriteAdapter(private var list: MutableList<TeamsFavorite>) :
    RecyclerView.Adapter<TeamsFavoriteAdapter.Holder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        return Holder(
            ItemTeamsUi().createView(
                AnkoContext.create(context, parent)
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val teams = list[position]
        holder.itemView.find<TextView>(R.id.title_team).text = teams.strTeam
        Picasso.get().load(teams.strTeamBadge).into(holder.itemView.find<ImageView>(R.id.team_badge))

        holder.itemView.setOnClickListener {
            context.startActivity<DetailTeam>("idTeam" to teams.idTeam)
        }
    }

    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}