/*
 * MatchAdapter.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 11/1/18 1:08 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.startActivity
import tampan.kucingapes.submission5final.DetailMatch
import tampan.kucingapes.submission5final.Utils.setBadgeImage
import tampan.kucingapes.submission5final.ankoUi.ItemMatchesUi
import tampan.kucingapes.submission5final.model.Match
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class MatchAdapter(private var data: List<Match>) : RecyclerView.Adapter<MatchAdapter.Holder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        return Holder(
            ItemMatchesUi().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bindItem(context, data[position])
    }

    class Holder(itemView: View) : BaseHolder(itemView) {
        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        fun bindItem(context: Context, match: Match) {

            val parseDateFormat = SimpleDateFormat("yyyy-MM-dd")
            parseDateFormat.timeZone = TimeZone.getTimeZone("UTC")
            val dateFormat = SimpleDateFormat("EEEE',' dd MMMM yyyy", Locale("id"))
            val parseDate = parseDateFormat.parse(match.dateEvent)
            val stringDate = dateFormat.format(parseDate)

            val patternYear = SimpleDateFormat("yyyy")
            val patternMonth = SimpleDateFormat("MM")
            val patternDay = SimpleDateFormat("dd")

            year = patternYear.format(parseDateFormat.parse(match.dateEvent))
            month = patternMonth.format(parseDateFormat.parse(match.dateEvent))
            day = patternDay.format(parseDateFormat.parse(match.dateEvent))


            // di beberapa item, format 'strTime' berbeda bahkan null, jadi dibuat try catch

            try {
                val parseTimeFormat = SimpleDateFormat("HH:mm:ss+SS:SS")
                parseTimeFormat.timeZone = TimeZone.getTimeZone("UTC")
                val timeFormat = SimpleDateFormat("HH:mm")
                val parseTime = parseTimeFormat.parse(match.strTime)
                stringTime = timeFormat.format(parseTime)
                eventDate.text = "$stringDate \n$stringTime"


                val patternHour = SimpleDateFormat("HH")
                val patternMinute = SimpleDateFormat("mm")
                hour = patternHour.format(parseTimeFormat.parse(match.strTime))
                minute = patternMinute.format(parseTimeFormat.parse(match.strTime))

            } catch (e: Exception) {
                val parseTimeFormat = SimpleDateFormat("HH:mm:ss")
                parseTimeFormat.timeZone = TimeZone.getTimeZone("UTC")
                val timeFormat = SimpleDateFormat("HH:mm")

                try {
                    val parseTime = parseTimeFormat.parse(match.strTime)
                    stringTime = timeFormat.format(parseTime)
                    eventDate.text = "$stringDate \n$stringTime"

                    val patternHour = SimpleDateFormat("HH")
                    val patternMinute = SimpleDateFormat("mm")
                    hour = patternHour.format(parseTimeFormat.parse(match.strTime))
                    minute = patternMinute.format(parseTimeFormat.parse(match.strTime))

                } catch (e: Exception) {}
            }

            homeTeam.text = match.strHomeTeam
            homeScore.text = match.intHomeScore
            awayTeam.text = match.strAwayTeam
            awayScore.text = match.intAwayScore

            if (homeScore.text == "" && awayScore.text == "") {
                homeScore.text = "-"
                awayScore.text = "-"
            }

            setBadgeImage(match.idHomeTeam, badgeHome)
            setBadgeImage(match.idAwayTeam, badgeAway)

            itemView.setOnClickListener {
                context.startActivity<DetailMatch>("id_event" to match.idEvent,
                    "date_match" to "$stringDate \n$stringTime")
            }

            alarmAction.setOnClickListener {
                val startMilis = Calendar.getInstance().run {
                    set(year.toInt(), (month.toInt()-1), day.toInt(), hour.toInt(), minute.toInt())
                    timeInMillis
                }

                val endMilis = Calendar.getInstance().run {
                    set(year.toInt(), (month.toInt()-1), day.toInt(), hour.toInt(), (minute.toInt()+90))
                    timeInMillis
                }

                val intent = Intent().apply {
                    action = Intent.ACTION_EDIT
                    type = "vnd.android.cursor.item/event"
                    putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMilis)
                    putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMilis)
                    putExtra(CalendarContract.Events.TITLE, "${match.strHomeTeam} vs ${match.strAwayTeam}")
                }

                context.startActivity(intent)
            }
        }
    }
}