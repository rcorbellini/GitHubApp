package com.corbellini.presentation.ui.detail

import android.view.View
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.corbellini.presentation.R
import com.corbellini.presentation.databinding.ItemPullRequestBinding
import com.corbellini.presentation.models.PullRequestPresentation
import me.ibrahimyilmaz.kiel.adapterOf
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

internal class PullRequestViewHolder(
    view: View
) : RecyclerViewHolder<PullRequestPresentation>(view) {
    val binding = ItemPullRequestBinding.bind(view)
    override fun bind(position: Int, item: PullRequestPresentation) {
        super.bind(position, item)
        binding.executePendingBindings()
    }
}

internal fun createPullRequestAdapter() =
    adapterOf<PullRequestPresentation> {
        diff(
            areItemsTheSame = { old, new ->
                old == new
            },
            areContentsTheSame = { old, new ->
                old.title == new.title &&
                        old.body == new.body &&
                        old.user == new.user
            }
        )
        register(
            layoutResource = R.layout.item_pull_request,
            viewHolder = ::PullRequestViewHolder,
            onBindViewHolder = { vh, _, prPResentation ->
                vh.binding.pr = prPResentation
                vh.binding.repoCard.preventCornerOverlap = false
                val imgUrl = prPResentation.user.avatarUrl
                val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()

                Glide.with(vh.itemView.context)
                    .load(imgUri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .circleCrop()
                    .into(vh.binding.imageView)
            }
        )
    }