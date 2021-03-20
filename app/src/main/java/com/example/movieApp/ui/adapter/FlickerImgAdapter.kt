package com.example.movieApp.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import com.example.myapplication.data.Photo


class FlickerImgAdapter(
    list: List<Photo>?
) :
RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var photoFlickerList: List<Photo>? = ArrayList()

    init {
        photoFlickerList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val vh: RecyclerView.ViewHolder
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_flicker_item, parent, false)
        vh = FlickerViewHolder(v)
        return vh
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        configureItemView(viewHolder as FlickerViewHolder, position)
    }

    private fun configureItemView(viewHolder: FlickerViewHolder, position: Int) {
        val photoItem: Photo = photoFlickerList!![position]
        val url = "https://farm" + photoItem.farm +
                ".static.flickr.com/" + photoItem.server +
                "/" + photoItem.id + "_" + photoItem.secret + ".jpg"
        val myOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_image_24)

        Log.d("IMG = ", url)
        Glide.with(viewHolder.flikerIv.context)
            .asBitmap()
            .apply(myOptions)
            .load(url)
            .into(viewHolder.flikerIv)
//        viewHolder.itemView.setOnClickListener {
//            listener(photoItem)
//        }
    }

    override fun getItemCount(): Int {
        return photoFlickerList?.size?:0
    }

    inner class FlickerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var flikerIv: ImageView = itemView.findViewById(R.id.flikerIv)
    }
}