package id.radikz.tmdbmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.radikz.tmdbmvvm.model.Movie
import id.radikz.tmdbmvvm.repository.MoviesRepository

class FirstFragmentViewModel : ViewModel() {

    private var _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
            get() = _movies

    init {
        getPopularMovies()
    }

    private fun getPopularMovies(){
        _movies = MoviesRepository.getPopularMovies()
    }

    fun getMovie(): LiveData<List<Movie>> {
        return movies
    }
}