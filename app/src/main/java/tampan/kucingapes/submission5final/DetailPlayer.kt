/*
 * DetailPlayer.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/31/18 1:57 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import tampan.kucingapes.submission5final.model.Player
import tampan.kucingapes.submission5final.Utils.setupText
import tampan.kucingapes.submission5final.ankoUi.DetailPlayerUi
import tampan.kucingapes.submission5final.model.PlayerResponsesSingle

class DetailPlayer : AppCompatActivity(), AnkoLogger {
    private lateinit var toolbar: Toolbar
    private lateinit var position: TextView
    private lateinit var weigth: TextView
    private lateinit var height: TextView
    private lateinit var desc: TextView
    private lateinit var banner: ImageView

    private lateinit var idPlayer: String

    private val baseUrlPlayer = "https://www.thesportsdb.com/api/v1/json/1/lookupplayer.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailPlayerUi().setContentView(this)

        idPlayer = intent.getStringExtra("idPlayer")

        toolbar = find(R.id.toolbar)
        position = find(R.id.player_position)
        weigth = find(R.id.player_weight)
        height = find(R.id.player_height)
        desc = find(R.id.player_desc)
        banner = find(R.id.player_banner)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        AndroidNetworking.get(baseUrlPlayer)
            .addQueryParameter("id", idPlayer)
            .build()
            .getAsObject(PlayerResponsesSingle::class.java, object : ParsedRequestListener<PlayerResponsesSingle> {
                override fun onResponse(response: PlayerResponsesSingle) {
                    val player = response.players
                    setupData(player[0])
                }

                override fun onError(anError: ANError) {
                    error { "my_error ${anError.localizedMessage}" }
                }

            })
    }

    private fun setupData(player: Player) {
        toolbar.title = player.strPlayer

        if (player.strFanart1 == null) {
            banner.visibility = View.GONE
        } else {
            Picasso.get().load(player.strFanart1).into(banner)
        }

        setupText(position, player.strPosition)
        setupText(weigth, player.strWeight)
        setupText(height, player.strHeight)
        setupText(desc, player.strDescriptionEN)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}