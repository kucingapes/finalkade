/*
 * BaseHolder.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 11/1/18 10:43 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import org.jetbrains.anko.find
import tampan.kucingapes.submission5final.R

open class BaseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var stringTime: String
    lateinit var hour: String
    lateinit var minute: String
    lateinit var day: String
    lateinit var month: String
    lateinit var year: String

    val eventDate: TextView = itemView.find(R.id.date_match)
    val homeTeam: TextView = itemView.find(R.id.home_team)
    val homeScore: TextView = itemView.find(R.id.home_score)
    val awayTeam: TextView = itemView.find(R.id.away_team)
    val awayScore: TextView = itemView.find(R.id.away_score)
    val badgeHome: ImageView = itemView.find(R.id.badge_home)
    val badgeAway: ImageView = itemView.find(R.id.badge_away)
    val alarmAction: ImageView = itemView.find(R.id.alarm_action)
}