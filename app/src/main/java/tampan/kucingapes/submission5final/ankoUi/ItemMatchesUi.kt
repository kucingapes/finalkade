/*
 * ItemMatchesUi.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 11/1/18 2:44 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.ankoUi

import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import tampan.kucingapes.submission5final.R
import tampan.kucingapes.submission5final.Utils
import tampan.kucingapes.submission5final.Utils.ripple

class ItemMatchesUi : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            cardView {
                lparams {
                    width = matchParent
                    height = wrapContent
                    topMargin = dip(6)
                    bottomMargin = dip(6)
                    rightMargin = dip(12)
                    leftMargin = dip(12)
                }

                ripple(this)

                radius = 8f

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    elevation = 3f
                }

                relativeLayout {

                    Utils.ripple(this)

                    imageView {
                        id = R.id.alarm_action
                        imageResource = R.drawable.ic_alarm
                    }.lparams(dip(25), dip(25)) {
                        margin = dip(6)
                        alignParentRight()
                    }


                    linearLayout {
                        lparams(matchParent, wrapContent) {
                            margin = dip(6)
                        }

                        orientation = LinearLayout.VERTICAL
                        padding = dip(6)

                        textView {
                            id = R.id.date_match
                            textColorResource = R.color.colorPrimary
                            gravity = Gravity.CENTER
                        }.lparams(matchParent, wrapContent) {
                            bottomMargin = dip(6)
                            gravity = Gravity.CENTER
                        }

                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            gravity = Gravity.CENTER

                            imageView {
                                id = R.id.badge_home
                            }.lparams(dip(30), dip(30))

                            textView {
                                id = R.id.home_team
                                gravity = Gravity.END
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                                    textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                                }
                            }.lparams(dip(0), wrapContent) {
                                weight = 3f
                            }

                            textView {
                                id = R.id.home_score
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                                }
                            }.lparams(dip(0), wrapContent) {
                                weight = 1f
                            }

                            textView(context.getString(R.string.versus)) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                                }
                            }.lparams(dip(0), wrapContent) {
                                weight = 0.5f
                            }

                            textView {
                                id = R.id.away_score
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                                }
                            }.lparams(dip(0), wrapContent) {
                                weight = 1f
                            }

                            textView {
                                id = R.id.away_team
                            }.lparams(dip(0), wrapContent) {
                                weight = 3f
                            }

                            imageView {
                                id = R.id.badge_away
                            }.lparams(dip(30), dip(30))
                        }
                    }

                }

            }
        }
    }
}