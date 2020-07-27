package com.alexisgs.apignomo.personajes.ui

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.alexisgs.apignomo.MainActivity
import com.alexisgs.apignomo.R
import com.alexisgs.apignomo.common.service.mapColor
import com.alexisgs.apignomo.personajes.data.model.Characters
import com.google.gson.Gson
import kotlinx.android.synthetic.main.datils_characters.*

class DetailsCharactersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.datils_characters, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val characters = arguments!!.getParcelable<Characters>(MainActivity.KEY_CHARACTERS)
        Log.e(TAG, Gson().toJson(characters))
        tv_title.text = characters!!.name
        tv_age.text = getString(
            R.string.age_user,
            characters!!.age.toString()
        )
        tv_height.text = getString(R.string.height_user, characters.height.toString())
        tv_weight.text = getString(R.string.weight_user, characters.weight.toString())

        img_avatar.imageTintList =
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    context!!,
                    mapColor[characters.hair_color.trim()].hashCode()
                )
            )//R.color.green));


        if (characters.professions.isNullOrEmpty()) {
            tv_professions.visibility = View.GONE
            lv_professions.visibility = View.GONE
        } else {
            tv_professions.visibility = View.VISIBLE
            lv_professions.visibility = View.VISIBLE
            val adapter = ArrayAdapter(
                this.context!!,
                android.R.layout.simple_list_item_1,
                characters.professions
            )
            lv_professions.adapter = adapter
        }


        if (characters.friends.isNullOrEmpty()) {
            tv_friends.visibility = View.GONE
            lv_friends.visibility = View.GONE
        } else {
            tv_friends.visibility = View.VISIBLE
            lv_friends.visibility = View.VISIBLE
            val adapter = ArrayAdapter(
                this.context!!,
                android.R.layout.simple_list_item_1,
                characters.friends
            )
            lv_friends.adapter = adapter
        }
    }

    companion object {
        val TAG = DetailsCharactersFragment::class.java.canonicalName
        fun newInstance() = DetailsCharactersFragment()
    }

}