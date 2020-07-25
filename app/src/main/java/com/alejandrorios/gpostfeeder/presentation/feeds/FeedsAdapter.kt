package com.alejandrorios.gpostfeeder.presentation.feeds

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alejandrorios.domain.model.Feeds
import com.alejandrorios.gpostfeeder.R

/**
 * Created by Alejandro Rios on 7/25/20
 */
class FeedsAdapter(
    private val feeds: ArrayList<Feeds> = ArrayList()
) : RecyclerView.Adapter<FeedsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedsViewHolder {
        return FeedsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.feed_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return feeds.size
    }

    override fun onBindViewHolder(holder: FeedsViewHolder, position: Int) {
        holder.bind(feeds[position])
    }
}
