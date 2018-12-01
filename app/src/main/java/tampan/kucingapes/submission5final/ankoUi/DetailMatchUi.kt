/*
 * DetailMatchUi.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 11/1/18 2:32 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.ankoUi

import android.support.design.widget.AppBarLayout
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import tampan.kucingapes.submission5final.DetailMatch
import tampan.kucingapes.submission5final.R
import tampan.kucingapes.submission5final.Utils
import tampan.kucingapes.submission5final.Utils.myToolbar

class DetailMatchUi : AnkoComponent<DetailMatch> {
    override fun createView(ui: AnkoContext<DetailMatch>): View = with(ui) {
        coordinatorLayout {
            appBarLayout {
                myToolbar().lparams(matchParent, wrapContent) {
                    scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                }
            }.lparams(matchParent, wrapContent)

            scrollView {
                verticalLayout {
                    gravity = Gravity.CENTER_HORIZONTAL

                    progressBar {
                        id = R.id.progress_loader
                    }.lparams(matchParent, wrapContent) {
                        margin = dip(20)
                    }
                    linearLayout {
                        gravity = Gravity.CENTER_HORIZONTAL
                        id = R.id.container_detail
                        orientation = LinearLayout.VERTICAL
                        padding = dip(12)
                        visibility = View.GONE

                        textView("date") {
                            id = R.id.date_match
                            textColorResource = R.color.colorPrimary
                            gravity = Gravity.CENTER
                        }.lparams(matchParent, wrapContent) {
                            bottomMargin = dip(20)
                        }
                        linearLayout {
                            gravity = Gravity.CENTER
                            orientation = LinearLayout.HORIZONTAL

                            linearLayout {
                                gravity = Gravity.CENTER or Gravity.TOP
                                orientation = LinearLayout.VERTICAL

                                imageView {
                                    id = R.id.home_badge
                                }.lparams(dip(80), dip(80))
                                textView {
                                    id = R.id.home_team
                                    gravity = Gravity.CENTER
                                    textColorResource = R.color.colorPrimary
                                }.lparams(matchParent, wrapContent) {
                                    margin = dip(12)
                                }
                            }.lparams(dip(120), dip(170))
                            textView {
                                id = R.id.home_score
                                textSize = 20f
                                Utils.textBold(this)
                            }.lparams(wrapContent, wrapContent) {
                                leftMargin = dip(20)
                                rightMargin = dip(10)
                            }
                            textView(R.string.versus).lparams(wrapContent, wrapContent)
                            textView {
                                id = R.id.away_score
                                textSize = 20f
                                Utils.textBold(this)
                            }.lparams(wrapContent, wrapContent) {
                                leftMargin = dip(10)
                                rightMargin = dip(20)
                            }
                            linearLayout {
                                gravity = Gravity.CENTER or Gravity.TOP
                                orientation = LinearLayout.VERTICAL

                                imageView {
                                    id = R.id.away_badge
                                }.lparams(dip(80), dip(80))
                                textView {
                                    id = R.id.away_team
                                    gravity = Gravity.CENTER
                                    textColorResource = R.color.colorPrimary
                                }.lparams(matchParent, wrapContent) {
                                    margin = dip(12)
                                }
                            }.lparams(dip(120), dip(170))
                        }.lparams(matchParent, wrapContent)
                        textView(R.string.event) {
                            Utils.textBold(this)
                        }.lparams(wrapContent, wrapContent) {
                            margin = dip(12)
                        }
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL

                            textView(R.string.player) {
                                id = R.id.home_goals_player
                            }.lparams(dip(0), wrapContent) {
                                weight = 1f
                            }
                            textView(R.string.goal) {
                                textColorResource = R.color.colorPrimary
                            }.lparams(wrapContent, wrapContent) {
                                leftMargin = dip(16)
                                rightMargin = dip(16)
                            }
                            textView(R.string.player) {
                                gravity = Gravity.END
                                id = R.id.away_goals_player
                            }.lparams(dip(0), wrapContent) {
                                weight = 1f
                            }
                        }.lparams(matchParent, wrapContent) {
                            bottomMargin = dip(12)
                        }
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL

                            textView(R.string.player) {
                                id = R.id.home_shots
                            }.lparams(dip(0), wrapContent) {
                                weight = 1f
                            }
                            textView(R.string.shot) {
                                textColorResource = R.color.colorPrimary
                            }.lparams(wrapContent, wrapContent) {
                                leftMargin = dip(16)
                                rightMargin = dip(16)
                            }
                            textView(R.string.player) {
                                gravity = Gravity.END
                                id = R.id.away_shots
                            }.lparams(dip(0), wrapContent) {
                                weight = 1f
                            }
                        }.lparams(matchParent, wrapContent) {
                            bottomMargin = dip(12)
                        }
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL

                            textView(R.string.player) {
                                id = R.id.home_yellow
                            }.lparams(dip(0), wrapContent) {
                                weight = 1f
                            }
                            textView(R.string.yellow_card) {
                                textColorResource = R.color.colorPrimary
                            }.lparams(wrapContent, wrapContent) {
                                leftMargin = dip(16)
                                rightMargin = dip(16)
                            }
                            textView(R.string.player) {
                                gravity = Gravity.END
                                id = R.id.away_yellow
                            }.lparams(dip(0), wrapContent) {
                                weight = 1f
                            }
                        }.lparams(matchParent, wrapContent) {
                            bottomMargin = dip(12)
                        }
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL

                            textView(R.string.player) {
                                id = R.id.home_red
                            }.lparams(dip(0), wrapContent) {
                                weight = 1f
                            }
                            textView(R.string.red_card) {
                                textColorResource = R.color.colorPrimary
                            }.lparams(wrapContent, wrapContent) {
                                leftMargin = dip(16)
                                rightMargin = dip(16)
                            }
                            textView(R.string.player) {
                                gravity = Gravity.END
                                id = R.id.away_red
                            }.lparams(dip(0), wrapContent) {
                                weight = 1f
                            }
                        }.lparams(matchParent, wrapContent) {
                            bottomMargin = dip(16)
                        }
                        textView(R.string.lineup) {
                            Utils.textBold(this)
                        }.lparams(wrapContent, wrapContent) {
                            margin = dip(12)
                        }
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL

                            textView(R.string.player) {
                                id = R.id.home_kiper
                            }.lparams(dip(0), wrapContent) {
                                weight = 1f
                            }
                            textView(R.string.gk) {
                                textColorResource = R.color.colorPrimary
                            }.lparams(wrapContent, wrapContent) {
                                leftMargin = dip(16)
                                rightMargin = dip(16)
                            }
                            textView(R.string.player) {
                                gravity = Gravity.END
                                id = R.id.away_kiper
                            }.lparams(dip(0), wrapContent) {
                                weight = 1f
                            }
                        }.lparams(matchParent, wrapContent) {
                            bottomMargin = dip(12)
                        }
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL

                            textView(R.string.player) {
                                id = R.id.home_df
                            }.lparams(dip(0), wrapContent) {
                                weight = 1f
                            }
                            textView(R.string.df) {
                                textColorResource = R.color.colorPrimary
                            }.lparams(wrapContent, wrapContent) {
                                leftMargin = dip(16)
                                rightMargin = dip(16)
                            }
                            textView(R.string.player) {
                                gravity = Gravity.END
                                id = R.id.away_df
                            }.lparams(dip(0), wrapContent) {
                                weight = 1f
                            }
                        }.lparams(matchParent, wrapContent) {
                            bottomMargin = dip(12)
                        }
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL

                            textView(R.string.player) {
                                id = R.id.home_md
                            }.lparams(dip(0), wrapContent) {
                                weight = 1f
                            }
                            textView(R.string.md) {
                                textColorResource = R.color.colorPrimary
                            }.lparams(wrapContent, wrapContent) {
                                leftMargin = dip(16)
                                rightMargin = dip(16)
                            }
                            textView(R.string.player) {
                                gravity = Gravity.END
                                id = R.id.away_md
                            }.lparams(dip(0), wrapContent) {
                                weight = 1f
                            }
                        }.lparams(matchParent, wrapContent) {
                            bottomMargin = dip(12)
                        }
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL

                            textView(R.string.player) {
                                id = R.id.home_fw
                            }.lparams(dip(0), wrapContent) {
                                weight = 1f
                            }
                            textView(R.string.fw) {
                                textColorResource = R.color.colorPrimary
                            }.lparams(wrapContent, wrapContent) {
                                leftMargin = dip(16)
                                rightMargin = dip(16)
                            }
                            textView(R.string.player) {
                                gravity = Gravity.END
                                id = R.id.away_fw
                            }.lparams(dip(0), wrapContent) {
                                weight = 1f
                            }
                        }.lparams(matchParent, wrapContent) {
                            bottomMargin = dip(12)
                        }
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL

                            textView(R.string.player) {
                                id = R.id.home_sub
                            }.lparams(dip(0), wrapContent) {
                                weight = 1f
                            }
                            textView(R.string.subs) {
                                textColorResource = R.color.colorPrimary
                            }.lparams(wrapContent, wrapContent) {
                                leftMargin = dip(16)
                                rightMargin = dip(16)
                            }
                            textView(R.string.player) {
                                gravity = Gravity.END
                                id = R.id.away_sub
                            }.lparams(dip(0), wrapContent) {
                                weight = 1f
                            }
                        }.lparams(matchParent, wrapContent) {
                            bottomMargin = dip(12)
                        }
                    }.lparams(matchParent, wrapContent)
                }.lparams(matchParent, wrapContent)
            }.lparams(matchParent, matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }
        }
    }

}