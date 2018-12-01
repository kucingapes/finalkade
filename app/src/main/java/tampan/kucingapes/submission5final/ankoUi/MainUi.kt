/*
 * MainUi.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/31/18 5:04 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.ankoUi

import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.View
import org.jetbrains.anko.*
import org.jetbrains.anko.design.bottomNavigationView
import tampan.kucingapes.submission5final.MainActivity
import tampan.kucingapes.submission5final.R

class MainUi: AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui){
        relativeLayout {
            frameLayout {
                id = R.id.frame_parent
            }.lparams(matchParent, matchParent) {
                above(R.id.bottom_layout)
            }

            view {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    background = ContextCompat.getDrawable(context,
                        R.drawable.shadow
                    )
                }
            }.lparams(matchParent, dip(5)) {
                above(R.id.bottom_layout)
            }

            bottomNavigationView {
                id = R.id.bottom_layout
                itemBackgroundResource = android.R.color.white
                inflateMenu(R.menu.bottom_menu)

            }.lparams(matchParent, wrapContent) {
                alignParentBottom()
            }
        }
    }
}