package com.corbellini.presentation.ui.list

import android.view.View
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.corbellini.presentation.R
import com.corbellini.presentation.databinding.ItemRepositoryBinding
import com.corbellini.presentation.models.RepositoryPresentation
import me.ibrahimyilmaz.kiel.adapterOf
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

internal class RepoViewHolder(
    view: View
) : RecyclerViewHolder<RepositoryPresentation>(view) {
    val binding = ItemRepositoryBinding.bind(view)
    override fun bind(position: Int, item: RepositoryPresentation) {
        super.bind(position, item)
        binding.executePendingBindings()
    }
}

internal fun createRepoAdapter(onClick: (RepositoryPresentation) -> Unit) =
    adapterOf<RepositoryPresentation> {
        diff(
            areItemsTheSame = { old, new ->
                old == new
            },
            areContentsTheSame = { old, new ->
                old.name == new.name
            }
        )
        register(
            layoutResource = R.layout.item_repository,
            viewHolder = ::RepoViewHolder,
            onBindViewHolder = { vh, _, repoPresentation ->
                vh.binding.repo = repoPresentation
                vh.binding.repoCard.setOnClickListener { onClick(repoPresentation) }
                vh.binding.repoCard.preventCornerOverlap = false
                val imgUrl = repoPresentation.owner.avatarUrl
                val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()

                Glide.with(vh.itemView.context)
                    .load(imgUri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(vh.binding.imageView)
            }
        )
    }