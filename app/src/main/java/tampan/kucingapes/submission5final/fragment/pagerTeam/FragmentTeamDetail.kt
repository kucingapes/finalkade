/*
 * FragmentTeamDetail.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/31/18 2:30 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.fragment.pagerTeam

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.nestedScrollView

class FragmentTeamDetail : Fragment(), AnkoComponent<Context> {

    private lateinit var idTeam: String
    private lateinit var desc: String

    companion object {
        fun withData(idTeam: String, desc: String?) : FragmentTeamDetail {
            val fragmentTeamDetail = FragmentTeamDetail()
            val bundle = Bundle()
            bundle.putString("idTeam", idTeam)
            bundle.putString("desc", desc)
            fragmentTeamDetail.arguments = bundle
            return fragmentTeamDetail
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idTeam = it.getString("idTeam", "")
            desc = it.getString("desc", "")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        return context?.let {
            AnkoContext.create(it)
        }?.let {
            createView(it)
        }
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        nestedScrollView {
            relativeLayout {
                textView {
                    text = desc
                }.lparams(matchParent, wrapContent) {
                    margin = dip(12)
                }
            }.lparams(matchParent, matchParent)
        }
    }
}