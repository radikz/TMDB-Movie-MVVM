package id.radikz.tmdbmvvm.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import id.radikz.tmdbmvvm.R
import id.radikz.tmdbmvvm.databinding.ItemMovieBinding
import id.radikz.tmdbmvvm.model.Movie

class MoviesAdapter(
    private var movies: MutableList<Movie>
 ) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: ItemMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    fun appendMovies(movies: List<Movie>) {
        this.movies.addAll(movies)
        notifyItemRangeInserted(
            this.movies.size,
            movies.size - 1
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MoviesAdapter.MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.item = movie
            Glide.with(itemView)
                .load("http://image.tmdb.org/t/p/w342${movie.posterPath}")
                .transform(CenterCrop())
                .into(binding.itemMoviePoster)

            itemView.setOnClickListener { Log.i("MoviesAdapter", "click") }
        }
    }
}