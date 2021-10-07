package com.corbellini.presentation.ui.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.corbellini.android.ext.bindScrollListener
import com.corbellini.android.ext.show
import com.corbellini.presentation.R
import com.corbellini.presentation.databinding.FragmentRepositoryListBinding
import com.corbellini.presentation.models.RepositoryPresentation
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryListFragment : Fragment() {
    // region Members

    private val repoListViewModel by viewModel<RepositoryListViewModel>()

    private lateinit var binding: FragmentRepositoryListBinding

    private val repoAdapter = createRepoAdapter {
        val action = RepositoryListFragmentDirections.actionFromRepositoryToPr(
            ownerName = it.owner.login,
            repoName = it.name
        )
        findNavController().navigate(action)
    }

    private val onScrollHitBottomLoadMore = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                dispatchLoadMore()
            }
        }
    }

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
            DataBindingUtil.inflate(inflater, R.layout.fragment_repository_list, container, false)
        binding.listRepoRecyclerView.layoutManager = StaggeredGridLayoutManager(
            1,
            StaggeredGridLayoutManager.VERTICAL
        )

        observeRepoListState()


        if(isEmptyList()) {
            dispatchLoadMore()
        }

        return binding.root
    }
    // endregion

    // region Private API
    private fun setupActionBar(){
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.title_repo)
            setDisplayHomeAsUpEnabled(false)
        }
    }

    private fun isEmptyList() = repoAdapter.currentList.isEmpty()

    private fun dispatchLoadMore() {
        repoListViewModel.dispatchEvent(RepositoryListEvent.LoadMore)
    }


    private fun observeRepoListState() {
        lifecycleScope.launch {
            repoListViewModel.listState.collect { state ->
                handleLoading(state)

                handleContent(state)

                handleError(state)
            }
        }
    }

    private fun handleError(state: RepositoryListState) {
        state.error?.run {
            try {
                Snackbar.make(binding.listRepoRecyclerView, this, Snackbar.LENGTH_LONG).show()
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            }
        }
    }

    private fun handleLoading(state: RepositoryListState) {
        //binding.loading.show()
    }

    private fun handleContent(state: RepositoryListState) {
        if (state.list.isNotEmpty()) {
            binding.listRepoRecyclerView.bindScrollListener(
                state.complete,
                onScrollHitBottomLoadMore
            )
            handleList(state.list)
        } else {
            handleNoList()
        }
    }

    private fun handleList(repositories: List<RepositoryPresentation>) {
        //binding.noDataFoundTextView.hide()
        binding.listRepoRecyclerView.show()
        binding.listRepoRecyclerView.apply {
            adapter ?: run { adapter = repoAdapter }
            repoAdapter.submitList(repositories)
        }
    }

    private fun handleNoList() {
        //binding.noDataFoundTextView.show()
    }

    // endregion
}


