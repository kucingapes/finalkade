/*
 * Utils.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 11/1/18 2:05 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final

import android.graphics.Typeface
import android.os.Build
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.view.View
import android.view.ViewManager
import android.widget.ImageView
import android.widget.TextView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.squareup.picasso.Picasso
import org.jetbrains.anko.appcompat.v7.themedToolbar
import org.jetbrains.anko.dip
import tampan.kucingapes.submission5final.model.TeamsResponse
import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager


object Utils {

    const val TABLE_FAVORITE_MATCH = "TABLE_FAVORITE_MATCH"
    const val ID = "ID_"
    const val MATCH_DATE = "MATCH_DATE"
    const val MATCH_TIME = "MATCH_TIME"
    const val MATCH_ID = "MATCH_ID"
    const val HOME_TEAM = "HOME_TEAM"
    const val HOME_ID = "HOME_ID"
    const val HOME_SCORE = "HOME_SCORE"
    const val AWAY_TEAM = "AWAY_TEAM"
    const val AWAY_ID = "AWAY_ID"
    const val AWAY_SCORE = "AWAY_SCORE"

    const val TABLE_FAVORITE_TEAM = "TABLE_FAVORITE_TEAM"
    const val TEAM_ID = "TEAM_ID"
    const val BADGE_TEAM = "BADGE_TEAM"
    const val NAME_TEAM = "NAME_TEAM"

    fun ViewManager.myToolbar() = themedToolbar (R.style.ThemeOverlay_AppCompat_Dark) {
        id = R.id.toolbar
        title = resources.getString(R.string.app_name)
        contentInsetStartWithNavigation = dip(0)
    }

    fun setupText(view: TextView, data: String?) {
        view.apply {
            text = data
            if (text == "") {
                text = "-"
            }
        }
    }


    fun setBadgeImage(idTeam: String?, badge: ImageView) {
        val baseUrlTeam = "https://www.thesportsdb.com/api/v1/json/1/lookupteam.php"
        AndroidNetworking.get(baseUrlTeam)
            .addQueryParameter("id", idTeam)
            .build()
            .getAsObject(TeamsResponse::class.java, object : ParsedRequestListener<TeamsResponse> {
                override fun onResponse(response: TeamsResponse) {
                    val urlBadge = response.teams[0].strTeamBadge
                    Picasso.get()
                        .load(urlBadge)
                        .fit()
                        .centerCrop()
                        .into(badge)
                }

                override fun onError(anError: ANError?){}

            })
    }

    fun ripple(view: View) {
        view.apply {
            val rippleValue = TypedValue()
            context.theme.resolveAttribute(android.R.attr.selectableItemBackground, rippleValue, true)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                foreground = ContextCompat.getDrawable(context, rippleValue.resourceId)
            }
        }
    }

    fun textBold(textView: TextView) {
        textView.apply {
            typeface = Typeface.DEFAULT_BOLD
        }
    }

    fun hideSoftKeyboard(activity: AppCompatActivity) {
        val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
    }

}