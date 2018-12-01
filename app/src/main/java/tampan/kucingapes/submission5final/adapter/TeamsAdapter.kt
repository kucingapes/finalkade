/*
 * TeamsAdapter.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 11/1/18 1:08 PM
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
import org.jetbrains.anko.*
import tampan.kucingapes.submission5final.DetailTeam
import tampan.kucingapes.submission5final.R
import tampan.kucingapes.submission5final.model.Teams
import tampan.kucingapes.submission5final.ankoUi.ItemTeamsUi

class TeamsAdapter(private var list: MutableList<Teams>) : RecyclerView.Adapter<TeamsAdapter.Holder>() {
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