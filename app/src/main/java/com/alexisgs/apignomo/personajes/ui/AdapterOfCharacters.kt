package com.alexisgs.apignomo.personajes.ui

import android.content.Context
import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alexisgs.apignomo.R
import com.alexisgs.apignomo.common.service.mapColor
import com.alexisgs.apignomo.personajes.data.model.Characters
import com.bumptech.glide.Glide


class AdapterOfCharacters(
    private val listDataTemp: List<Characters>,
    private val context: Context,
    views: AdapterRecyclerView
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    private var listData = mutableListOf<Characters>()
    private var viewsAdapter: AdapterRecyclerView = views

    init {
        listData = listDataTemp as MutableList<Characters>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewDataPerson(
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val characters = listData[position]
        val viewTemp = holder as ViewDataPerson
        viewTemp.tvTitle.text = characters.name
        viewTemp.tvSubtitle.text = context.getString(
            R.string.age_user,
            characters.age.toString()
        )
        viewTemp.tvDescriptions.text =
            characters.professions.toString().replace("\\[|\\]".toRegex(), "")

        Glide.with(context)
            .load(characters.thumbnail.trim())
            .placeholder(R.drawable.ic_cloud_download)
            .error(R.drawable.ic_error)
            .into(viewTemp.imgAvatar)

        viewTemp.imgAvatar.imageTintList =
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    context!!,
                    mapColor[characters.hair_color.trim()].hashCode()
                )
            )

        viewTemp.itemView.setOnClickListener {
            viewsAdapter.viewsClickItem(characters)
        }
    }

    class ViewDataPerson(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        var tvSubtitle: TextView = itemView.findViewById(R.id.tv_subtitle)
        var tvDescriptions: TextView = itemView.findViewById(R.id.tv_descriptions)
        var imgAvatar: ImageView = itemView.findViewById(R.id.img_avatar)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                listData = if (charSearch.isEmpty()) {
                    listDataTemp as MutableList<Characters>
                } else {
                    val resultList = mutableListOf<Characters>()
                    for (row in listDataTemp) {

                        if (row.name.decapitalize().contains(
                                charSearch.decapitalize(),
                                true
                            ) || row.professions.toString().replace("\\[|\\]".toRegex(), "")
                                .contains(charSearch.decapitalize(), true)
                        ) {
                            resultList.add(row)
                        }

                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = listData
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listData = results?.values as MutableList<Characters>
                notifyDataSetChanged()
            }
        }
    }


    interface AdapterRecyclerView {
        fun viewsClickItem(result: Characters)
    }
}