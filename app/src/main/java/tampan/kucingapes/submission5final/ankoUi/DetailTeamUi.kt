/*
 * DetailTeamUi.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/31/18 2:27 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.ankoUi

import android.graphics.Typeface
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.view.Gravity
import android.view.View
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.collapsingToolbarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.support.v4.viewPager
import tampan.kucingapes.submission5final.DetailTeam
import tampan.kucingapes.submission5final.R
import tampan.kucingapes.submission5final.Utils.myToolbar

class DetailTeamUi : AnkoComponent<DetailTeam> {
    override fun createView(ui: AnkoContext<DetailTeam>): View = with(ui) {
        coordinatorLayout {
            appBarLayout {
                collapsingToolbarLayout {
                    isTitleEnabled = false
                    fitsSystemWindows = true
                    relativeLayout {
                        backgroundColorResource = R.color.colorPrimary

                        verticalLayout {
                            gravity = Gravity.CENTER
                            imageView {
                                id = R.id.team_badge
                            }.lparams(dip(70), dip(70)) {
                                bottomMargin = dip(6)
                            }

                            textView {
                                id = R.id.name_team
                                typeface = Typeface.DEFAULT_BOLD
                                textWhite(this)
                            }.lparams(wrapContent, wrapContent)
                            textView {
                                id = R.id.year_team
                                textWhite(this)
                            }.lparams(wrapContent, wrapContent)
                            textView {
                                id = R.id.stadium
                                textWhite(this)
                            }.lparams(wrapContent, wrapContent) {
                                bottomMargin = dip(12)
                            }

                            textView {
                                id = R.id.manager
                                textWhite(this)
                            }.lparams(wrapContent, wrapContent)
                        }.lparams(wrapContent, wrapContent) {
                            topMargin = dip(50)
                            bottomMargin = dip(12)
                            centerInParent()
                        }

                    }.lparams(matchParent, wrapContent) {
                        collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PARALLAX
                    }

                    myToolbar().lparams(matchParent, wrapContent) {
                        collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PARALLAX
                    }

                }.lparams(matchParent, wrapContent) {
                    scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                }

                themedTabLayout(R.style.ThemeOverlay_AppCompat_Dark) {
                    id = R.id.tabs
                }.lparams {
                    width = matchParent
                    height = wrapContent
                    scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP
                }
            }.lparams(matchParent, wrapContent)

            viewPager {
                id = R.id.view_pager
            }.lparams(matchParent, matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }
        }
    }

    private fun textWhite(view: TextView) {
        view.textColorResource = android.R.color.white
    }
}