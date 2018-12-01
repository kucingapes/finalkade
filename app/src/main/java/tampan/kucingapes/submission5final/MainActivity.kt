/*
 * MainActivity.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 11/1/18 12:08 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.androidnetworking.AndroidNetworking
import org.jetbrains.anko.*
import tampan.kucingapes.submission5final.ankoUi.MainUi
import tampan.kucingapes.submission5final.fragment.FragmentFavorite
import tampan.kucingapes.submission5final.fragment.FragmentSchedule
import tampan.kucingapes.submission5final.fragment.FragmentTeams

class MainActivity : AppCompatActivity() {
    private lateinit var mBottomNavigationView: BottomNavigationView

    private val fragmentShedule = FragmentSchedule()
    private val fragmentTeam = FragmentTeams()
    private val fragmentFavorite = FragmentFavorite()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidNetworking.initialize(this)
        MainUi().setContentView(this)
        mBottomNavigationView = find(R.id.bottom_layout)

        loadFragment(fragmentShedule, savedInstanceState)

        mBottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.schedule -> loadFragment(fragmentShedule, savedInstanceState)
                R.id.teams -> loadFragment(fragmentTeam, savedInstanceState)
                R.id.favorites -> loadFragment(fragmentFavorite, savedInstanceState)
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment, savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_parent, fragment, fragment::class.java.simpleName)
                .commit()
        }
    }
}
