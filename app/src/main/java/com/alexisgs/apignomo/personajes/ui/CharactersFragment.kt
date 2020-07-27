package com.alexisgs.apignomo.personajes.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexisgs.apignomo.R
import com.alexisgs.apignomo.personajes.data.model.Characters
import com.alexisgs.apignomo.personajes.viewmodel.CharactersViewModel
import kotlinx.android.synthetic.main.characters_fragment.*

class CharactersFragment : Fragment(), AdapterOfCharacters.AdapterRecyclerView {
    private lateinit var viewModel: CharactersViewModel
    private lateinit var recyclerviewAdapter: AdapterOfCharacters
    private lateinit var mCallback: ViewClickElement

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.characters_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CharactersViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        Log.e(TAG, "Entro::::::::________::::::::::::::::")

        rv_characters.layoutManager = LinearLayoutManager(this!!.context)

        progress_circular.visibility = View.VISIBLE


        val charactersObserver = Observer<List<Characters>> {
            recyclerviewAdapter = AdapterOfCharacters(
                it,
                this!!.context!!, this
            )
            progress_circular?.visibility = View.GONE
            rv_characters?.adapter = recyclerviewAdapter
            recyclerviewAdapter!!.notifyDataSetChanged()
        }

        viewModel.getListOfCharactersLiveData().observe(this.activity!!, charactersObserver)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mCallback = activity as ViewClickElement
    }

    companion object {
        val TAG = CharactersFragment::class.java.canonicalName
        fun newInstance() =
            CharactersFragment()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity!!.menuInflater.inflate(R.menu.main_menu, menu)

        var item = menu.findItem(R.id.actions_search)
        var searchView: SearchView = item.actionView as SearchView
        searchView.queryHint = getString(R.string.hint_search)
        searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.e(TAG, "2.- $newText")
                    recyclerviewAdapter!!.filter.filter(newText)
                    return false
                }

            }
        )

        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun viewsClickItem(result: Characters) {
        mCallback!!.viewDetailUser(result)
    }


    interface ViewClickElement {
        fun viewDetailUser(data: Characters)
    }
}