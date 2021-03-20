package com.example.movieApp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.recyclerview.widget.RecyclerView
import com.example.movieApp.ui.viewModel.HomeViewModel
import com.example.myapplication.R
import com.example.myapplication.data.Movie
import java.util.*
import kotlin.collections.ArrayList

class MoviesAdapter(
    homeViewModel: HomeViewModel,
    list: List<Movie>?,
    val listener: (Movie) -> Unit
) :RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    private var allMovies: List<Movie>? = ArrayList()
    private var filteredMovies: List<Movie>? = ArrayList()
    private var homeViewModel: HomeViewModel

    init {
        allMovies = list
        filteredMovies = list
        this.homeViewModel = homeViewModel
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(
                charSequence: CharSequence?,
                filterResults: FilterResults
            ) {
                if (filterResults.values == null) {
                    filterResults.values = ArrayList<Movie>()
                }
                filteredMovies = filterResults.values as? List<Movie>
                notifyDataSetChanged()
            }

            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val queryString = charSequence?.toString()?.toLowerCase(Locale.ROOT)
                val filterResults = FilterResults()
                filterResults.values = homeViewModel.getTopFiveMoviesByYear(queryString,allMovies)
                return filterResults
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val vh: RecyclerView.ViewHolder
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_item, parent, false)
        vh = MovieViewHolder(v)
        return vh
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        configureItemView(viewHolder as MovieViewHolder, position)
    }

    private fun configureItemView(viewHolder: MovieViewHolder, position: Int) {
        val movieItem: Movie = filteredMovies!![position]

        viewHolder.titleTv.text = movieItem.title
        viewHolder.movieYearTv.text = movieItem.year.toString()
        viewHolder.ratingBar.rating = movieItem.rating.toFloat()
        viewHolder.itemView.setOnClickListener {
                listener(movieItem)
        }
    }

    override fun getItemCount(): Int {
        return filteredMovies?.size?:0
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var titleTv: TextView = itemView.findViewById(R.id.titleTv)
        internal var movieYearTv: TextView = itemView.findViewById(R.id.movieYearTv)
        internal var ratingBar: AppCompatRatingBar = itemView.findViewById(R.id.rating_bar)
    }
}