/*
 * DetailPlayerUi.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/31/18 1:57 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.ankoUi

import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.collapsingToolbarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.support.v4.nestedScrollView
import tampan.kucingapes.submission5final.DetailPlayer
import tampan.kucingapes.submission5final.R
import tampan.kucingapes.submission5final.Utils.myToolbar

class DetailPlayerUi : AnkoComponent<DetailPlayer> {
    override fun createView(ui: AnkoContext<DetailPlayer>): View = with(ui) {
        coordinatorLayout {
            appBarLayout {
                collapsingToolbarLayout {
                    isTitleEnabled = false
                    fitsSystemWindows = true
                    setContentScrimResource(R.color.colorPrimary)

                    imageView {
                        id = R.id.player_banner
                        scaleType = ImageView.ScaleType.CENTER_CROP
                    }.lparams(matchParent, dip(200)) {
                        collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PARALLAX
                        topMargin = dimenAttr(android.R.attr.actionBarSize)
                    }

                    myToolbar().lparams(matchParent, wrapContent) {
                        collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN
                    }

                }.lparams(matchParent, wrapContent) {
                    scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or
                            AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
                }

            }.lparams(matchParent, wrapContent)

            nestedScrollView {
                verticalLayout {
                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL
                        textView("Weight (Kg)"){
                            gravity = Gravity.CENTER
                        }.lparams {
                            weight = 1f
                        }

                        textView("Height (m)"){
                            gravity = Gravity.CENTER
                        }.lparams {
                            weight = 1f
                        }
                    }.lparams(matchParent, wrapContent) {
                        topMargin = dip(20)
                    }

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL
                        textView {
                            id = R.id.player_weight
                            gravity = Gravity.CENTER
                            textSize = 26f
                            textColorResource = R.color.colorPrimary
                        }.lparams {
                            weight = 1f
                        }

                        textView {
                            id = R.id.player_height
                            gravity = Gravity.CENTER
                            textSize = 26f
                            textColorResource = R.color.colorPrimary
                        }.lparams {
                            weight = 1f
                        }
                    }

                    textView {
                        id = R.id.player_position
                        gravity = Gravity.CENTER
                    }.lparams(matchParent, matchParent) {
                        margin = dip(12)
                    }

                    textView {
                        id = R.id.player_desc
                    }.lparams(matchParent, wrapContent) {
                        margin = dip(12)
                    }
                }
            }.lparams(matchParent, matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }
        }
    }
}