package com.ihavenodomain.quotesterminal.quoteslist.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ihavenodomain.quotesterminal.R
import com.ihavenodomain.quotesterminal.quoteslist.presentation.view.quoteslistadapter.SecuritiesListAdapter
import com.ihavenodomain.quotesterminal.quoteslist.presentation.viewmodel.SecuritiesListMainViewModel
import com.ihavenodomain.quotesterminal.quoteslist.presentation.viewstate.Error
import com.ihavenodomain.quotesterminal.quoteslist.presentation.viewstate.Errors.OTHER
import com.ihavenodomain.quotesterminal.quoteslist.presentation.viewstate.Errors.UNKNOWN_HOST
import com.ihavenodomain.quotesterminal.quoteslist.presentation.viewstate.Loading
import com.ihavenodomain.quotesterminal.quoteslist.presentation.viewstate.QuotesListState
import com.ihavenodomain.quotesterminal.quoteslist.presentation.viewstate.ShowList
import kotlinx.android.synthetic.main.fragment_main.pbMain
import kotlinx.android.synthetic.main.fragment_main.rvMain
import kotlinx.android.synthetic.main.fragment_main.tvError
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecuritiesListMainFragment: Fragment() {
    private lateinit var adapter: SecuritiesListAdapter

    private val viewModel: SecuritiesListMainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        viewModel.observeScreenState().observe(viewLifecycleOwner) { state ->
            render(state)
        }
    }

    private fun initViews() {
        // init recyclerview, etc.
        adapter = SecuritiesListAdapter()
        rvMain.layoutManager = LinearLayoutManager(requireActivity())
        rvMain.adapter = adapter
    }

    private fun render(state: QuotesListState) {
        when (state) {
            is ShowList -> {
                // render list
                adapter.submitList(state.list)
            }
            is Error -> {
                val errorText = when (state.errorType) {
                    UNKNOWN_HOST -> R.string.error_service_access_error
                    OTHER -> R.string.error_default
                }
                tvError.setText(errorText)
            }
            else -> {
                // do nothing
            }
        }

        tvError.isVisible = state is Error
        pbMain.isVisible = state is Loading
        rvMain.isVisible = state is ShowList
    }

    companion object {
        fun newInstance() = SecuritiesListMainFragment()
    }
}