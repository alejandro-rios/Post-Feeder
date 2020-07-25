package com.alejandrorios.gpostfeeder.presentation.feeds

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.alejandrorios.gpostfeeder.R
import com.alejandrorios.gpostfeeder.domain.model.Feeds
import com.alejandrorios.gpostfeeder.utils.formatToDate
import com.bumptech.glide.Glide

/**
 * Created by Alejandro Rios on 7/25/20
 */
class FeedsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val feedUser = itemView.findViewById<AppCompatTextView>(R.id.tvFeedUser)
    private val feedDate = itemView.findViewById<AppCompatTextView>(R.id.tvFeedDate)
    private val feedInfo = itemView.findViewById<AppCompatTextView>(R.id.tvFeedInfo)
    private val feedImage = itemView.findViewById<AppCompatImageView>(R.id.ivFeedImage)

    fun bind(feed: Feeds) {
        feedUser?.text = String.format("%s %s", feed.firstName, feed.lastName)
        feedDate?.text = formatToDate(feed.unixTimestamp)
        feedInfo?.text = feed.postBody

        feed.image?.let {
            Glide
                .with(feedImage.context)
                .load(it)
                .into(feedImage)
        }
    }
}
