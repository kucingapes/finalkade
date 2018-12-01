/*
 * ItemTeamsUi.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/31/18 3:35 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.ankoUi

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*
import tampan.kucingapes.submission5final.R
import tampan.kucingapes.submission5final.Utils

class ItemTeamsUi : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui){
        linearLayout {
            Utils.ripple(this)
            lparams(matchParent, wrapContent)
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL

            imageView {
                id = R.id.team_badge
            }.lparams(dip(50), dip(50)) {
                margin = dip(6)
            }

            textView {
                id = R.id.title_team
            }
        }
    }
}