package com.corbellini.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.corbellini.android.ext.show
import com.corbellini.presentation.R
import com.corbellini.presentation.databinding.FragmentPullRequestListBinding
import com.corbellini.presentation.models.PullRequestPresentation
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PullRequestListFragment : Fragment() {
    // region Members
    private val viewModel by viewModel<PullRequestListViewModel>()

    private lateinit var binding: FragmentPullRequestListBinding


    private val args: PullRequestListFragmentArgs by navArgs()


    private val adapterPr = createPullRequestAdapter()
    // endregion

    // region Android API
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        setupActionBar()

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_pull_request_list, container, false)
        binding.listPrRecyclerView.layoutManager = StaggeredGridLayoutManager(
            1,
            StaggeredGridLayoutManager.VERTICAL
        )

        observeRepoListState()

        dispatchLoadAll(args.ownerName, args.repoName)


        return binding.root
    }
    // endregion

    // region Private API
    private fun setupActionBar(){
       (activity as AppCompatActivity).supportActionBar?.apply {
           title = getString(R.string.tilte_pr)
           setDisplayHomeAsUpEnabled(true)
       }
    }

    private fun dispatchLoadAll(ownerName: String, repoName: String) {
        viewModel.dispatchEvent(
            PullRequestListEvent.LoadAll(
                ownerName = ownerName,
                repoName = repoName
            )
        )
    }

    private fun observeRepoListState() {
        lifecycleScope.launch {
            viewModel.listState.collect { state ->
                handleLoading(state)

                handleContent(state)

                handleError(state)
            }
        }
    }

    private fun handleError(state: PullRequestListState) {
        state.error?.run {
            //todo make it better
            Snackbar.make(binding.listPrRecyclerView, this, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun handleLoading(state: PullRequestListState) {
        //binding.loading.show()
    }

    private fun handleContent(state: PullRequestListState) {
        if (state.list.isNotEmpty()) {
            handleList(state.list)
        } else {
            handleNoList()
        }
    }

    private fun handleList(pullRequests: List<PullRequestPresentation>) {
        //binding.noDataFoundTextView.hide()
        binding.listPrRecyclerView.show()
        binding.listPrRecyclerView.apply {
            adapter ?: run { adapter = adapterPr }
            adapterPr.submitList(pullRequests)
        }
    }

    private fun handleNoList() {
        //binding.noDataFoundTextView.show()
    }

    // endregion
}


