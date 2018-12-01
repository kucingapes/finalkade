/*
 * SearchUi.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 11/1/18 1:08 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.ankoUi

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import tampan.kucingapes.submission5final.R
import tampan.kucingapes.submission5final.Utils.myToolbar

class SearchUi : AnkoComponent<AppCompatActivity> {
    override fun createView(ui: AnkoContext<AppCompatActivity>): View = with(ui) {
        verticalLayout {
            myToolbar().apply {
                backgroundColorResource = R.color.colorPrimary
            }.lparams(matchParent, dimenAttr(android.R.attr.actionBarSize))

            recyclerView {
                id = R.id.list_search
                layoutManager = LinearLayoutManager(context)
            }.lparams(matchParent, matchParent)
        }
    }
}