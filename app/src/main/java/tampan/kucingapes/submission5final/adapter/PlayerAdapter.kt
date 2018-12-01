/*
 * PlayerAdapter.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/31/18 1:52 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import tampan.kucingapes.submission5final.DetailPlayer
import tampan.kucingapes.submission5final.R
import tampan.kucingapes.submission5final.model.Player
import tampan.kucingapes.submission5final.Utils.ripple

class PlayerAdapter(private var playerList: MutableList<Player>) : RecyclerView.Adapter<PlayerAdapter.Holder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        return Holder(PlayerListUi().createView(AnkoContext.create(context, parent)))
    }

    override fun getItemCount(): Int = playerList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val player = playerList[position]

        val facePlayer = holder.itemView.find<ImageView>(R.id.player_face)
        val namePlayer = holder.itemView.find<TextView>(R.id.player_name)
        val positionPlayer = holder.itemView.find<TextView>(R.id.player_position)


        if (player.strCutout == null) {
            val defaultFace = "http://www.dath.be/wp-content/uploads/2014/10/profile.png"
            Picasso.get().load(defaultFace).into(facePlayer)
        } else {
            Picasso.get().load(player.strCutout).into(facePlayer)
        }

        namePlayer.text = player.strPlayer
        positionPlayer.text = player.strPosition

        holder.itemView.setOnClickListener {
            context.startActivity<DetailPlayer>("idPlayer" to player.idPlayer)
        }
    }

    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    class PlayerListUi : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
            linearLayout {
                ripple(this)
                lparams(matchParent, wrapContent)
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL

                imageView {
                    id = R.id.player_face
                }.lparams(dip(50), dip(50)) {
                    margin = dip(6)
                }

                textView {
                    id = R.id.player_name
                }

                textView {
                    id = R.id.player_position
                    gravity = Gravity.END
                }.lparams(matchParent, wrapContent) {
                    rightMargin = dip(6)
                }

            }
        }
    }
}